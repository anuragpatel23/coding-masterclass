# 8. Sqrt(x)

**Difficulty:** Easy
**Pattern:** Binary Search on the Answer Space
**LeetCode:** https://leetcode.com/problems/sqrtx/

## Problem Summary
Given a non-negative integer `x`, return the integer square root of `x` (i.e., `floor(sqrt(x))`), without using a built-in power/sqrt function.

## Example
```
Input:  x = 8
Output: 2        (sqrt(8) ≈ 2.83, floor is 2)
```

## Pattern Recognition
This is the module's first "binary search on the **answer**" problem, rather than on an existing array. The candidates being searched aren't array elements — they're every possible integer from `0` to `x`. The key property that makes binary search valid: "is `candidate * candidate <= x`" is a **monotonic** condition — true for a contiguous range of small candidates, then false for everything larger. Whenever a yes/no condition is monotonic across a range of possible answers, you can binary search that range directly.

## Approach 1: Brute Force
Try every integer from `0` upward until its square exceeds `x`, then return the previous one.

- **Time:** O(sqrt(x))
- **Space:** O(1)
- **Why it's not good enough:** linear in the size of the *answer*, not the input — for a large `x`, that's a lot of wasted candidates checked one at a time.

## Approach 2: Optimized (Binary Search on the Answer)
Search the range `[1, x/2]` (a safe upper bound for `x >= 4`) for the largest value whose square doesn't exceed `x`. At each step, check `mid * mid` against `x` (using `long` to avoid overflow) and narrow accordingly.

- **Time:** O(log x)
- **Space:** O(1)

## Dry Run
`x = 8`

| left | right | mid | mid*mid | comparison | action |
|---|---|---|---|---|---|
| 1 | 4 | 2 | 4 | 4<8 | result=2, left=3 |
| 3 | 4 | 3 | 9 | 9>8 | right=2 |

Loop ends (`left > right`). Best recorded result: **2**

## Edge Cases
- `x = 0` or `x = 1` -> handled by an early return, since the general binary search range assumes `x >= 2`
- Perfect squares, e.g. `x = 16` -> the search finds the exact match (`4*4=16`), returned directly
- Very large `x` -> using `long` for the squared comparison prevents integer overflow that `int * int` could otherwise cause

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(sqrt(x)) | O(1) |
| Optimized (binary search on answer) | O(log x) | O(1) |

## Related Problems / Pattern Family
- Koko Eating Bananas (Module 7 #9 — the same "binary search on a monotonic answer condition" technique)
- Capacity To Ship Packages Within D Days (Module 7 #10 — another binary-search-on-answer problem)
