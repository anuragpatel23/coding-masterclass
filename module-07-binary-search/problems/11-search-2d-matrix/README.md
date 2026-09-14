# 11. Search a 2D Matrix

**Difficulty:** Medium
**Pattern:** Binary Search (Flatten Coordinates)
**LeetCode:** https://leetcode.com/problems/search-a-2d-matrix/

## Problem Summary
Given an `m x n` matrix where each row is sorted, and the first element of each row is greater than the last element of the previous row (meaning the whole matrix is sorted if read left-to-right, top-to-bottom), determine if a target value exists.

## Example
```
Input:  matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
```

## Pattern Recognition
Because the matrix is fully sorted when read in row-major order, it's really just a 1D sorted array wearing a 2D costume. The trick: treat a single "flat index" from `0` to `m*n - 1` as the search space, and convert it to `(row, col)` coordinates on the fly using integer division and modulo.

## Approach 1: Brute Force
Scan every cell in the matrix.

- **Time:** O(m*n)
- **Space:** O(1)

## Approach 2: Optimized (Binary Search Over a Virtual Flat Index)
Binary search over the range `[0, m*n - 1]`. For each candidate flat index `mid`, convert it to a real cell via `row = mid / cols`, `col = mid % cols`, and compare `matrix[row][col]` to the target using standard binary search logic.

- **Time:** O(log(m*n))
- **Space:** O(1)

## Dry Run
`matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]]`, `target = 3`, 3 rows x 4 cols, flat range `[0, 11]`

| left | right | mid | row,col | value | action |
|---|---|---|---|---|---|
| 0 | 11 | 5 | 1,1 | 11 | too big -> right=4 |
| 0 | 4 | 2 | 0,2 | 5 | too big -> right=1 |
| 0 | 1 | 0 | 0,0 | 1 | too small -> left=1 |
| 1 | 1 | 1 | 0,1 | 3 | match! |

Result: **true**

## Edge Cases
- Target smaller than the matrix's first element or larger than its last -> the search space shrinks to nothing without ever matching, correctly returns `false`
- Single row or single column matrix -> the flattening still works correctly, since row-major order degenerates gracefully to a 1D array in either case
- Empty matrix -> should be guarded against before starting the search (no valid flat index range exists)

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(m*n) | O(1) |
| Optimized (binary search, flattened) | O(log(m*n)) | O(1) |

## Related Problems / Pattern Family
- Search a 2D Matrix II (Module 7 #12 — a matrix that's row/column sorted but *not* fully sorted, needing a different technique)
- Binary Search (Module 7 #1 — the 1D foundation this problem maps onto)
