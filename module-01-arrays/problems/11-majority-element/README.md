# 11. Majority Element

**Difficulty:** Easy
**Pattern:** Boyer-Moore Voting
**LeetCode:** https://leetcode.com/problems/majority-element/

## Problem Summary
Given an array of size `n`, return the majority element — the value that appears **more than `⌊n/2⌋` times**. You may assume the array always has a majority element.

## Example
```
Input:  nums = [2,2,1,1,1,2,2]
Output: 2
```

## Pattern Recognition
"An element that appears more than half the time" is the exact setup for **Boyer-Moore Voting** — because it appears more than every other value combined, it can survive a process of cancelling itself out one-for-one against every other value and still come out ahead.

## Approach 1: Brute Force
Count every element's frequency with a `HashMap`, return the one exceeding `n/2`.

- **Time:** O(n)
- **Space:** O(n)
- **Why it's not good enough:** same story as problems 9 and 10 — correct and linear, but uses space that a smarter pass avoids entirely. Interviewers frequently ask "now do it in O(1) space" as a follow-up here specifically.

## Approach 2: Optimized (Boyer-Moore Voting)
Maintain a `candidate` and a `count`. Walk through the array:
- If `count == 0`, the current element becomes the new `candidate`.
- If the current element equals `candidate`, increment `count`; otherwise, decrement it.

Intuition: think of matching elements as "votes for" the candidate and mismatches as "votes against." Because the majority element outnumbers everything else combined, it's mathematically guaranteed to survive as the final candidate, even though the count can hit zero and reset along the way.

- **Time:** O(n)
- **Space:** O(1)

## Dry Run
`nums = [2,2,1,1,1,2,2]`

| num | count before | action | candidate | count after |
|---|---|---|---|---|
| 2 | 0 | count==0 → candidate=2 | 2 | 1 |
| 2 | 1 | matches → count++ | 2 | 2 |
| 1 | 2 | mismatch → count-- | 2 | 1 |
| 1 | 1 | mismatch → count-- | 2 | 0 |
| 1 | 0 | count==0 → candidate=1 | 1 | 1 |
| 2 | 1 | mismatch → count-- | 1 | 0 |
| 2 | 0 | count==0 → candidate=2 | 2 | 1 |

Final candidate: **2**

## Edge Cases
- Array of length 1 → that single element is trivially the majority
- Majority element makes up exactly `n/2 + 1` occurrences (the minimum possible) → the algorithm still holds because votes-against can never fully cancel votes-for
- This algorithm assumes a majority element **exists**; if you can't assume that, add a final verification pass counting the candidate's actual occurrences

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (HashMap) | O(n) | O(n) |
| Optimized (Boyer-Moore) | O(n) | O(1) |

## Related Problems / Pattern Family
- Majority Element II (find all elements appearing more than n/3 times — extended Boyer-Moore with two candidates)
- Check If a String Is a Valid Sequence (different domain, same "cancel opposites" intuition)
