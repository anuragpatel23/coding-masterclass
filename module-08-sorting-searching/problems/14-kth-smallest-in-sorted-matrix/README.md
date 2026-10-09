# 14. Kth Smallest Element in a Sorted Matrix

**Difficulty:** Medium
**Pattern:** Binary Search on Value Range + Counting
**LeetCode:** https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

## Problem Summary
Given an `n x n` matrix where each row and column is sorted ascending, find the `k`th smallest element in the matrix.

## Example
```
Input:  matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
```

## Pattern Recognition
This combines two techniques from earlier modules: **binary search on the answer** (Module 7) — searching over the range of possible *values* in the matrix, not indices — and the **staircase search** (Module 7 #12) as the feasibility check, counting how many elements are `<= ` a candidate value in O(m+n).

## Approach 1: Brute Force
Flatten the matrix into a single array, sort it, and read off the `k`th element.

- **Time:** O(n^2 log n) — dominated by the sort
- **Space:** O(n^2)

## Approach 2: Optimized (Binary Search on Value + Staircase Counting)
Binary search the range `[matrix[0][0], matrix[n-1][n-1]]` (the matrix's global min and max). For each candidate value `mid`, count how many elements are `<= mid` using a staircase sweep (start at the bottom-left corner; move right when the current element qualifies, counting the whole column above it at once, or move up when it doesn't). If that count is `< k`, the answer is larger than `mid`; otherwise, the answer is `mid` or smaller.

- **Time:** O(n log(max - min)) — O(log(range)) candidate values, each with an O(n) counting sweep
- **Space:** O(1) extra

## Dry Run
`matrix = [[1,5,9],[10,11,13],[12,13,15]]`, `k = 8`, value range `[1, 15]`

| left | right | mid | count <= mid | count < k(8)? | action |
|---|---|---|---|---|---|
| 1 | 15 | 8 | (count elements <=8: 1,5 -> 2) | yes | left=9 |
| 9 | 15 | 12 | (count <=12: 1,5,9,10,11,12 -> 6) | yes | left=13 |
| 13 | 15 | 14 | (count <=14: 1,5,9,10,11,12,13,13 -> 8) | no (8 not < 8) | right=14 |
| 13 | 14 | 13 | (count <=13: 1,5,9,10,11,12,13,13 -> 8) | no | right=13 |

`left == right == 13`.

Result: **13**

## Edge Cases
- `k = 1` -> converges to the matrix's global minimum, `matrix[0][0]`
- `k = n*n` -> converges to the matrix's global maximum, `matrix[n-1][n-1]`
- Duplicate values in the matrix, as in this example (`13` appears twice) -> the counting-based approach handles duplicates correctly, since it counts occurrences rather than relying on strict ordering of distinct values

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (flatten + sort) | O(n^2 log n) | O(n^2) |
| Optimized (binary search on value + staircase count) | O(n log(max-min)) | O(1) extra |

## Related Problems / Pattern Family
- Search a 2D Matrix II (Module 7 #12 — the staircase counting technique used here as a subroutine)
- Median of Two Sorted Arrays (Module 7 #14 — another "binary search over a derived quantity" problem)
