# 1. Two Sum

**Difficulty:** Easy
**Pattern:** Hashing — Complement Search *(previewed here, deep-dived in Module 9)*
**LeetCode:** https://leetcode.com/problems/two-sum/

## Problem Summary
You're given an array of integers and a target value. Return the indices of the two numbers that add up to the target. Exactly one valid answer exists, and you can't reuse the same element twice.

## Example
```
Input:  nums = [2, 7, 11, 15], target = 9
Output: [0, 1]        (nums[0] + nums[1] = 2 + 7 = 9)
```

## Pattern Recognition
Any time you're looking for a **pair that satisfies a sum/difference condition** in an unsorted array, ask: *"can I trade space for time with a hash map?"* The tell is that you're really asking "have I seen the value I need before?" — that question is what a hash map answers in O(1).

## Approach 1: Brute Force
For every element, scan every other element and check if the pair sums to target.

- **Time:** O(n²)
- **Space:** O(1)
- **Why it's not good enough:** at n = 10⁴ that's ~10⁸ comparisons. The real waste is that the inner loop re-scans numbers the outer loop already visited in earlier iterations — that's repeated work with nothing cached.

## Approach 2: Optimized (HashMap)
Walk the array once. At each index, compute `complement = target - nums[i]`:
- If `complement` is already a key in the map → you found your pair, return the stored index and the current index.
- Otherwise → store `nums[i] -> i` and continue.

By the time you reach index `i`, the map already holds every number seen *before* it, so "have I seen the complement" becomes an O(1) lookup instead of an O(n) scan.

- **Time:** O(n)
- **Space:** O(n)

## Dry Run
`nums = [2, 7, 11, 15]`, `target = 9`

| i | nums[i] | complement | map before check | action |
|---|---|---|---|---|
| 0 | 2 | 7 | {} | not found → store {2:0} |
| 1 | 7 | 2 | {2:0} | found! → return [0, 1] |

## Edge Cases
- Negative numbers — the map handles this with no special casing
- A duplicate value that is itself the answer, e.g. `nums=[3,3], target=6` — works correctly because you check for the complement *before* inserting the current value
- Answer pair at the very end of the array — still O(n) since you only pass through once

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n²) | O(1) |
| Optimized (HashMap) | O(n) | O(n) |

## Related Problems / Pattern Family
- 3Sum (Module 3 — Two Pointers, sorted-array variant)
- Two Sum II — Input Array Is Sorted (Two Pointers)
- Subarray Sum Equals K (Module 9 — prefix sum + hashing)
