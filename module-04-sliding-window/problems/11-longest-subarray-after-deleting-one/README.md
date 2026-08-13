# 11. Longest Subarray of 1's After Deleting One Element

**Difficulty:** Medium
**Pattern:** Variable-Size Sliding Window (k=1 variant + adjustment)
**LeetCode:** https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/

## Problem Summary
Given a binary array, you must delete **exactly one** element. Return the length of the longest contiguous run of ones in the resulting array.

## Example
```
Input:  nums = [1,1,0,1]
Output: 3        (delete the 0, leaving [1,1,1])
```

## Pattern Recognition
This is Max Consecutive Ones III (#10) with `k = 1` — but with a twist: deletion is **mandatory**, even if the array is already all ones. The clean way to handle that: find the longest window containing at most one zero (exactly the k=1 version of #10), then **subtract 1** from that length. Why subtract 1? Because that one zero (if present) is the element you delete — or, if there was no zero in the best window, you're still forced to delete one of the ones, shrinking your run by exactly one either way.

## Approach 1: Brute Force
For every starting index, extend rightward counting zeros, stopping once the zero count exceeds 1. Track the maximum window length found, then subtract 1 at the end.

- **Time:** O(n^2)
- **Space:** O(1)

## Approach 2: Optimized (Variable Window, At Most One Zero, Then Adjust)
Identical mechanics to Max Consecutive Ones III with `k` fixed at 1. Track the longest valid window across the whole scan, then return `maxLen - 1`.

- **Time:** O(n)
- **Space:** O(1)

## Dry Run
`nums = [1,1,0,1]`

| right | value | zeros | window | maxLen |
|---|---|---|---|---|
| 0 | 1 | 0 | [1] | 1 |
| 1 | 1 | 0 | [1,1] | 2 |
| 2 | 0 | 1 | [1,1,0] | 3 |
| 3 | 1 | 1 | [1,1,0,1] | 4 |

Peak window length found: 4. Final answer: `4 - 1 = ` **3**

## Edge Cases
- Array of all ones, e.g. `[1,1,1]` -> the "at most one zero" window covers the whole array (length 3), but since a deletion is mandatory, the answer is `3 - 1 = 2`
- Array of all zeros -> the best "at most one zero" window has length 1, giving `1 - 1 = 0` (correctly: no 1s can possibly remain)
- Single element -> whether it's `0` or `1`, deleting it leaves nothing, so the answer is always `0`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) |
| Optimized (variable window, k=1, adjusted) | O(n) | O(1) |

## Related Problems / Pattern Family
- Max Consecutive Ones III (Module 4 #10 — the general-k version this problem is built on)
