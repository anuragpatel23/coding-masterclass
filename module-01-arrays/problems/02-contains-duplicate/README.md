# 2. Contains Duplicate

**Difficulty:** Easy
**Pattern:** Hashing — Membership Check
**LeetCode:** https://leetcode.com/problems/contains-duplicate/

## Problem Summary
Given an integer array, return `true` if any value appears at least twice, and `false` if every element is distinct.

## Example
```
Input:  nums = [1, 2, 3, 1]
Output: true
```

## Pattern Recognition
"Has this value shown up before?" is the exact question a `HashSet` is built to answer in O(1). Whenever a problem boils down to a membership check across a single pass, reach for a set before you reach for a nested loop.

## Approach 1: Brute Force
Compare every pair of elements.

- **Time:** O(n²)
- **Space:** O(1)
- **Why it's not good enough:** you're re-checking `nums[i]` against every later element even after you've already confirmed it's unique against everything before it — no memory of past work.

*(A middle-ground approach — sort first, O(n log n), then check adjacent elements — is worth knowing too, but the hash set below is both simpler and asymptotically faster.)*

## Approach 2: Optimized (HashSet)
Walk the array once, adding each number to a set. If `add()` reports the value was already present, you've found a duplicate immediately.

- **Time:** O(n)
- **Space:** O(n)

## Dry Run
`nums = [1, 2, 3, 1]`

| i | nums[i] | set before | add() result | action |
|---|---|---|---|---|
| 0 | 1 | {} | added | continue |
| 1 | 2 | {1} | added | continue |
| 2 | 3 | {1,2} | added | continue |
| 3 | 1 | {1,2,3} | already present | return true |

## Edge Cases
- Empty array or single element → no duplicate possible, return `false`
- All elements identical → returns `true` on the second element, no need to scan further
- Duplicate at the very end → still correctly caught since the whole array is scanned if needed

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n²) | O(1) |
| Sort + adjacent scan | O(n log n) | O(1) (or O(n) if sort isn't in-place) |
| Optimized (HashSet) | O(n) | O(n) |

## Related Problems / Pattern Family
- Contains Duplicate II (window-bounded version — Module 4, Sliding Window)
- Single Number (Module 19 — Bit Manipulation, XOR trick for the "find the odd one" variant)
