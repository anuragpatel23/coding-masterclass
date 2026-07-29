# 14. Rotate Image

**Difficulty:** Medium
**Pattern:** In-Place Transpose + Reverse
**LeetCode:** https://leetcode.com/problems/rotate-image/

## Problem Summary
Given an `n x n` 2D matrix representing an image, rotate it **90 degrees clockwise**, in-place.

## Example
```
Input:  [[1,2,3],
         [4,5,6],
         [7,8,9]]
Output: [[7,4,1],
         [8,5,2],
         [9,6,3]]
```

## Pattern Recognition
Whenever an in-place matrix transformation seems too complex to reason about in one pass, ask: **can I decompose it into two simpler, well-known operations?** A 90° clockwise rotation happens to equal "transpose, then reverse each row" — recognizing this decomposition turns a fiddly index-juggling problem into two lines you already know how to write.

## Approach 1: Brute Force
Allocate a new `n x n` matrix. For each cell `(r, c)` in the original, place its value at `(c, n-1-r)` in the new matrix (the coordinate transform for a 90° clockwise rotation).

- **Time:** O(n²)
- **Space:** O(n²) — a full second matrix, which violates "in-place"

## Approach 2: Optimized (Transpose, Then Reverse Rows)
1. **Transpose** the matrix in-place: swap `matrix[r][c]` with `matrix[c][r]` for every `r < c`. This flips the matrix across its main diagonal.
2. **Reverse each row** in-place. Combined with the transpose, this produces exactly a 90° clockwise rotation.

Why this works: transposing turns rows into columns; reversing each row then flips the column order left-to-right — which is precisely what "rotate clockwise" needs.

- **Time:** O(n²)
- **Space:** O(1) extra

## Dry Run
`matrix = [[1,2,3],[4,5,6],[7,8,9]]`

**Step 1 — Transpose:**
```
1 2 3        1 4 7
4 5 6   →    2 5 8
7 8 9        3 6 9
```

**Step 2 — Reverse each row:**
```
1 4 7        7 4 1
2 5 8   →    8 5 2
3 6 9        9 6 3
```

Result: **[[7,4,1],[8,5,2],[9,6,3]]**

## Edge Cases
- 1×1 matrix → transpose and row-reversal are both no-ops, correctly unchanged
- Even the diagonal elements during transpose (`r == c`) are correctly skipped, since swapping a cell with itself is unnecessary
- Non-square matrices are out of scope for this problem — it's defined only for `n x n`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (new matrix) | O(n²) | O(n²) |
| Optimized (transpose + reverse) | O(n²) | O(1) extra |

## Related Problems / Pattern Family
- Set Matrix Zeroes (Module 1 #12 — different transformation, same in-place matrix mindset)
- Spiral Matrix (Module 1 #13 — boundary-based matrix traversal)
- Transpose Matrix (the first step of this problem, in isolation)
