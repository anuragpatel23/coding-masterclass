# 4. Maximum Subarray

**Difficulty:** Medium
**Pattern:** Kadane's Algorithm
**LeetCode:** https://leetcode.com/problems/maximum-subarray/

## Problem Summary
Given an integer array, find the contiguous subarray (containing at least one number) with the largest sum, and return that sum.

## Example
```
Input:  nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Output: 6        (subarray [4, -1, 2, 1])
```

## Pattern Recognition
"Contiguous subarray" + "maximize/minimize a sum" is the single strongest signal for **Kadane's Algorithm**. The key insight: at every index you only ever need to make one decision — *extend the subarray ending at the previous index, or start fresh from here* — you never need to look back further than that.

## Approach 1: Brute Force
Check every possible subarray's sum and keep the max.

- **Time:** O(n²)
- **Space:** O(1)
- **Why it's not good enough:** for each starting index `i`, you recompute a running sum across all end points `j`, but that running sum overlaps almost entirely with the one computed for `i-1`. Nothing is reused.

## Approach 2: Optimized (Kadane's Algorithm)
Maintain `currentSum`, the best sum of a subarray *ending exactly at the current index*. At each step:

```
currentSum = max(nums[i], currentSum + nums[i])
```

If `currentSum + nums[i]` is worse than `nums[i]` alone, the running subarray has become a liability — drop it and restart at `i`. Track the best `currentSum` seen at any point as `maxSum`.

- **Time:** O(n)
- **Space:** O(1)

## Dry Run
`nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]`

| i | nums[i] | currentSum | maxSum |
|---|---|---|---|
| 0 | -2 | -2 | -2 |
| 1 | 1 | max(1, -1) = 1 | 1 |
| 2 | -3 | max(-3, -2) = -2 | 1 |
| 3 | 4 | max(4, 2) = 4 | 4 |
| 4 | -1 | max(-1, 3) = 3 | 4 |
| 5 | 2 | max(2, 5) = 5 | 5 |
| 6 | 1 | max(1, 6) = 6 | 6 |
| 7 | -5 | max(-5, 1) = 1 | 6 |
| 8 | 4 | max(4, 5) = 5 | 6 |

Result: **6**

## Edge Cases
- All negative numbers, e.g. `[-3,-1,-2]` → answer is the single largest (least negative) element, `-1`. This is why `maxSum` is initialized to `nums[0]`, not `0`.
- Single-element array → the answer is that element
- All positive numbers → the whole array is the answer

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n²) | O(1) |
| Optimized (Kadane's) | O(n) | O(1) |

## Related Problems / Pattern Family
- Maximum Product Subarray (Module 1 #5 — Kadane's variant with sign flips)
- Maximum Subarray Sum Circular (Kadane's + total-sum trick)
- Best Time to Buy and Sell Stock (Module 1 #3 — same "running best" shape)
