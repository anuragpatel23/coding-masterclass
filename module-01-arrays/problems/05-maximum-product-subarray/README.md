# 5. Maximum Product Subarray

**Difficulty:** Medium
**Pattern:** Kadane's Variant (max/min tracking)
**LeetCode:** https://leetcode.com/problems/maximum-product-subarray/

## Problem Summary
Given an integer array, find the contiguous subarray with the largest product, and return that product.

## Example
```
Input:  nums = [2, 3, -2, 4]
Output: 6        (subarray [2, 3])
```

## Pattern Recognition
This looks like Maximum Subarray, but with one crucial twist: **multiplication flips sign**. A very negative running product can become the *best* product if multiplied by another negative number. Whenever a "running best" problem involves multiplication (not addition), that's your cue to track a running **min** alongside the running **max** — the min might become the max one step later.

## Approach 1: Brute Force
Check every subarray's product and keep the max.

- **Time:** O(n²)
- **Space:** O(1)
- **Why it's not good enough:** same redundant recomputation as plain Kadane's, but naively applying single-value Kadane's here would also just be *wrong* — it silently drops the sign-flip case, not just slow.

## Approach 2: Optimized (Track Running Max AND Min)
At each index, maintain both `currentMax` and `currentMin` — the best and worst product of a subarray ending here. Before updating them, if the current number is negative, **swap** `currentMax` and `currentMin` (multiplying by a negative reverses which one leads to a bigger result). Then:

```
currentMax = max(nums[i], currentMax * nums[i])
currentMin = min(nums[i], currentMin * nums[i])
```

Track the overall best `currentMax` as `maxProduct`.

- **Time:** O(n)
- **Space:** O(1)

## Dry Run
`nums = [2, 3, -2, 4]`

| i | num | swap? | currentMax | currentMin | maxProduct |
|---|---|---|---|---|---|
| 0 | 2 | — | 2 | 2 | 2 |
| 1 | 3 | no | max(3,6)=6 | min(3,6)=3 | 6 |
| 2 | -2 | yes → (3,6) | max(-2,3·-2=-6)=-2 | min(-2,6·-2=-12)=-12 | 6 |
| 3 | 4 | no | max(4,-2·4=-8)=4 | min(4,-12·4=-48)=-48 | 6 |

Result: **6**

## Edge Cases
- A single zero splits the array — zero resets both `currentMax` and `currentMin` to `0`/`num`, correctly preventing the product from "carrying over" across it (verify: `max(0, currentMax*0) = 0` unless currentMax is negative, in which case it still resolves to `0` since `nums[i]=0` dominates)
- All negative numbers with an odd count → the optimal subarray excludes exactly one negative number
- Array of all zeros → answer is `0`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n²) | O(1) |
| Optimized (max/min Kadane's) | O(n) | O(1) |

## Related Problems / Pattern Family
- Maximum Subarray (Module 1 #4 — the additive version of this exact idea)
- Maximum Product of Three Numbers (sorting-based variant)
