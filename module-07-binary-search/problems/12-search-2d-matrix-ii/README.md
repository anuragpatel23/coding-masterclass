# 12. Search a 2D Matrix II

**Difficulty:** Medium
**Pattern:** Staircase Search (Eliminate a Row or Column per Step)
**LeetCode:** https://leetcode.com/problems/search-a-2d-matrix-ii/

## Problem Summary
Given a matrix where each row is sorted left-to-right and each column is sorted top-to-bottom — but the matrix is **not** fully sorted in row-major order like problem #11 — determine if a target value exists.

## Example
```
Input:  matrix = [[1,4,7,11],[2,5,8,12],[3,6,9,16],[10,13,14,17]], target = 5
Output: true
```

## Pattern Recognition
Since the matrix isn't fully sorted, flattening it (problem #11's trick) doesn't work here. But there's still structure to exploit: start at the **top-right corner**. Every value below the current one is bigger; every value to the left is smaller. That means a single comparison at each step tells you to eliminate an entire row *or* an entire column — not through classic binary search, but through a related "staircase" elimination technique.

## Approach 1: Brute Force
Scan every cell.

- **Time:** O(m*n)
- **Space:** O(1)

## Approach 2: Optimized (Staircase Search from the Top-Right Corner)
Start at `row = 0`, `col = cols - 1` (top-right corner). At each step:
- If the current value equals the target, found it.
- If the current value is **greater** than the target, the entire column below-and-including this cell is too big (since columns increase downward) — move left (`col--`).
- If the current value is **less** than the target, the entire row to the left-and-including this cell is too small (since rows increase rightward) — move down (`row++`).

- **Time:** O(m+n) — each step eliminates one row or one column, and there are only `m+n` total steps possible before running out of matrix
- **Space:** O(1)

## Dry Run
`matrix` as above, `target = 5`, start at `(0, 3)` = 11

| row | col | value | comparison | action |
|---|---|---|---|---|
| 0 | 3 | 11 | too big | col-- |
| 0 | 2 | 7 | too big | col-- |
| 0 | 1 | 4 | too small | row++ |
| 1 | 1 | 5 | match! | return true |

Result: **true**

## Edge Cases
- Target smaller than every element in the top row and larger than nothing -> `col` walks all the way to `-1`, correctly exits without a match
- Target larger than everything in the rightmost column -> `row` walks all the way past the last row, correctly exits without a match
- Target not present anywhere, but within the overall value range -> the staircase path still terminates in bounded steps, correctly returning `false`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(m*n) | O(1) |
| Optimized (staircase search) | O(m+n) | O(1) |

## Related Problems / Pattern Family
- Search a 2D Matrix (Module 7 #11 — the fully-sorted variant, solvable with true binary search instead)
- Kth Smallest Element in a Sorted Matrix (Module 8 — a related matrix problem combining this staircase idea with binary search on values)
