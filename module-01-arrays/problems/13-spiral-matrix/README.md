# 13. Spiral Matrix

**Difficulty:** Medium
**Pattern:** Boundary Simulation
**LeetCode:** https://leetcode.com/problems/spiral-matrix/

## Problem Summary
Given an `m x n` matrix, return all of its elements in spiral order (right along the top, down the right side, left along the bottom, up the left side, repeat inward).

## Example
```
Input:  [[1,2,3],
         [4,5,6],
         [7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
```

## Pattern Recognition
"Walk the matrix in a specific traversal shape" (spiral, diagonal, boustrophedon) is a signal for **boundary simulation**: instead of tracking which cells you've visited, track the *edges* of the unvisited region and shrink them inward as you consume each side.

## Approach 1: Naive (Visited Matrix + Direction Vector)
Keep a `visited` boolean matrix and a current direction. Move forward until you'd step out of bounds or onto a visited cell, then rotate direction (right → down → left → up) and continue.

- **Time:** O(m·n)
- **Space:** O(m·n) — the visited matrix
- **Why it's not good enough:** it's already optimal in time, but the visited matrix is unnecessary bookkeeping — the "have I visited this?" question can be answered implicitly just by comparing to shrinking boundaries instead.

## Approach 2: Optimized (Shrinking Boundary Pointers)
Track four boundaries: `top`, `bottom`, `left`, `right`. Repeatedly:
1. Walk the entire **top** row left→right, then `top++`.
2. Walk the entire **right** column top→bottom, then `right--`.
3. If `top <= bottom` still, walk the **bottom** row right→left, then `bottom--`.
4. If `left <= right` still, walk the **left** column bottom→top, then `left++`.

The two guard checks in steps 3 and 4 prevent re-walking a row or column that's already been fully consumed once the boundaries cross (this matters for non-square matrices).

- **Time:** O(m·n)
- **Space:** O(1) extra (excluding the output list)

## Dry Run
`matrix = [[1,2,3],[4,5,6],[7,8,9]]`, boundaries start `top=0, bottom=2, left=0, right=2`

| Step | Action | Result so far | Boundaries after |
|---|---|---|---|
| 1 | top row: 1,2,3 | [1,2,3] | top=1 |
| 2 | right col: 6,9 | [1,2,3,6,9] | right=1 |
| 3 | bottom row (reversed): 8,7 | [1,2,3,6,9,8,7] | bottom=1 |
| 4 | left col (reversed): 4 | [1,2,3,6,9,8,7,4] | left=1 |
| 1 | top row (top=1,bottom=1,left=1,right=1): 5 | [1,2,3,6,9,8,7,4,5] | top=2, loop ends |

Result: **[1,2,3,6,9,8,7,4,5]**

## Edge Cases
- Single row → the top-row walk consumes everything; the guard checks prevent the algorithm from re-walking it as a "bottom row"
- Single column → symmetric case, handled by the `left <= right` guard
- Non-square matrix (more rows than columns, or vice versa) — this is exactly why the guard checks in steps 3–4 exist

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Naive (visited matrix) | O(m·n) | O(m·n) |
| Optimized (boundary pointers) | O(m·n) | O(1) extra |

## Related Problems / Pattern Family
- Spiral Matrix II (generate a matrix in spiral order — same boundary logic, reversed direction of writing)
- Rotate Image (Module 1 #14 — different matrix transformation, same "think in terms of layers/boundaries" mindset)
