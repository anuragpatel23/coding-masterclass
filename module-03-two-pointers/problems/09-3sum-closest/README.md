# 9. 3Sum Closest

**Difficulty:** Medium
**Pattern:** Sort + Two Pointers (Track Closest, Not Exact Match)
**LeetCode:** https://leetcode.com/problems/3sum-closest/

## Problem Summary
Given an integer array and a target, find the sum of three integers that is **closest** to the target (there's exactly one closest answer). Return that sum.

## Example
```
Input:  nums = [-1,2,1,-4], target = 1
Output: 2        (-1 + 2 + 1 = 2, which is closest to target 1)
```

## Pattern Recognition
Same skeleton as 3Sum: fix one element, two-pointer the rest. The only change is the objective — instead of stopping the moment you hit an exact match, you keep track of the closest sum seen so far, and use the comparison to `target` to decide which pointer to move (exactly like Two Sum II's "too big / too small" logic).

## Approach 1: Brute Force
Three nested loops checking every triplet's sum, tracking whichever is closest to the target.

- **Time:** O(n^3)
- **Space:** O(1)
- **Why it's not good enough:** identical reasoning to 3Sum's brute force — an O(n) inner search is being done as a nested loop instead of a sorted two-pointer sweep.

## Approach 2: Optimized (Sort + Two Pointers, Track Minimum Distance)
Sort the array. For each fixed index `i`, run two pointers across the rest of the array. At each step, compare the current triplet sum's distance from `target` to the best distance found so far, updating if it's closer. Move `left` or `right` based on whether the current sum is below or above the target (same logic as Two Sum II) — this systematically explores every promising region without needing to check every triplet.

- **Time:** O(n^2)
- **Space:** O(1) extra

## Dry Run
`nums = [-1,2,1,-4]` -> sorted: `[-4,-1,1,2]`, `target = 1`

| i | nums[i] | left, right | sum | distance | closest so far | move |
|---|---|---|---|---|---|---|
| 0 (-4) | left=1(-1),right=3(2) | -4-1+2=-3 | \|−3−1\|=4 | -3 | too small -> left++ |
| 0 (-4) | left=2(1),right=3(2) | -4+1+2=-1 | \|−1−1\|=2 | -1 (closer) | too small -> left++ |
| 1 (-1) | left=2(1),right=3(2) | -1+1+2=2 | \|2−1\|=1 | 2 (closer) | too big -> right-- |

Result: **2**

## Edge Cases
- Exactly 3 elements -> only one possible triplet, trivially the answer
- Multiple triplets equally close to the target -> the problem guarantees a unique answer sum, so this doesn't arise in valid inputs
- All elements identical -> every triplet has the same sum, which is trivially the closest (and only) sum

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^3) | O(1) |
| Optimized (sort + two pointers) | O(n^2) | O(1) extra |

## Related Problems / Pattern Family
- 3Sum (Module 3 #8 — the exact-match version of this exact skeleton)
- Two Sum II (Module 3 #1 — the two-pointer subroutine both problems are built on)
