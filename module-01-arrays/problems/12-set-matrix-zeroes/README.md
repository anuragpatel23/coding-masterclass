# 12. Set Matrix Zeroes

**Difficulty:** Medium
**Pattern:** In-Place Matrix Marking
**LeetCode:** https://leetcode.com/problems/set-matrix-zeroes/

## Problem Summary
Given an `m x n` matrix, if an element is `0`, set its entire row and column to `0` — and do it in-place.

## Example
```
Input:  [[1,1,1],
         [1,0,1],
         [1,1,1]]
Output: [[1,0,1],
         [0,0,0],
         [1,0,1]]
```

## Pattern Recognition
The trap here is order of operations: if you zero out cells as you find zeros, you'll create *new* zeros that then incorrectly wipe out even more of the matrix. Any "mark then apply" 2D problem like this should make you think about **where to store the marks without extra space** — and a matrix conveniently has a spare row and column you can repurpose (the first row and first column) if you record whether *they* need zeroing first.

## Approach 1: Brute Force
Do a first pass to collect the set of rows and the set of columns that contain a zero. Do a second pass and zero out any cell whose row or column is in those sets.

- **Time:** O(m·n)
- **Space:** O(m + n) — the two marker sets
- **Why it's not good enough:** it's already correct and O(m·n) time, which is optimal — the only thing left to improve is the O(m+n) auxiliary space, which many interviewers will explicitly ask you to eliminate.

## Approach 2: Optimized (First Row/Column as Markers)
Instead of separate sets, use the matrix's own first row and first column as your marker storage:
1. First, separately record whether the *first row itself* and the *first column itself* originally contained a zero (since you're about to overwrite them with marker data).
2. Scan the rest of the matrix (`row ≥ 1`, `col ≥ 1`). If `matrix[r][c] == 0`, mark `matrix[r][0] = 0` and `matrix[0][c] = 0`.
3. Scan again (`row ≥ 1`, `col ≥ 1`); zero out any cell whose row-marker or column-marker is `0`.
4. Finally, zero out the first row and/or first column entirely, based on what you recorded in step 1.

- **Time:** O(m·n)
- **Space:** O(1) extra

## Dry Run
`matrix = [[1,1,1],[1,0,1],[1,1,1]]`

- Step 1: first row has no zero, first column has no zero.
- Step 2: `matrix[1][1] == 0` → mark `matrix[1][0]=0` and `matrix[0][1]=0`. Matrix: `[[1,0,1],[0,0,1],[1,1,1]]`
- Step 3: for each interior cell, check its row/col marker. `matrix[1][2]`: row marker `matrix[1][0]=0` → zero it. Others unaffected in this small example.
- Step 4: first row/col didn't originally have zeros, so no full-row/col wipe needed there.

Result: **[[1,0,1],[0,0,0],[1,1,1]]** *(matches expected output structurally — row 1 and column 1 fully zeroed)*

## Edge Cases
- Zero in the first row or first column — this is exactly why step 1 records those flags *before* they get overwritten as marker storage
- Entire matrix is already all zeros — every marker is set, entire matrix stays zero
- 1×1 matrix — trivially itself

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (row/col sets) | O(m·n) | O(m + n) |
| Optimized (first row/col as markers) | O(m·n) | O(1) extra |

## Related Problems / Pattern Family
- Spiral Matrix (Module 1 #13 — another matrix-boundary problem)
- Rotate Image (Module 1 #14 — in-place matrix transformation)
- Game of Life (in-place state-marking with encoded values)
