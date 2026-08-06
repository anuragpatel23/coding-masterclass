# 7. Container With Most Water

**Difficulty:** Medium
**Pattern:** Opposite-Direction Two Pointers (Greedy Shrink)
**LeetCode:** https://leetcode.com/problems/container-with-most-water/

## Problem Summary
Given an array `height` where `height[i]` is the height of a vertical line at position `i`, find two lines that, together with the x-axis, form a container holding the most water. Return the maximum area.

## Example
```
Input:  height = [1,8,6,2,5,4,8,3,7]
Output: 49        (lines at index 1 (height 8) and index 8 (height 7): area = min(8,7) * (8-1) = 49)
```

## Pattern Recognition
The area between two lines is `min(height[left], height[right]) * (right - left)` — bounded by the **shorter** line. Starting from the widest possible container (both pointers at the extreme ends) and narrowing inward, you can prove that it's never beneficial to move the taller line inward first: doing so only shrinks the width while the height stays capped by the same (or a shorter) line. This greedy insight is what makes two pointers provably correct here, not just fast.

## Approach 1: Brute Force
Check every pair of lines and compute the area, keeping the maximum.

- **Time:** O(n^2)
- **Space:** O(1)
- **Why it's not good enough:** most of these pairs are provably not worth checking — see the greedy argument below.

## Approach 2: Optimized (Two Pointers, Always Move the Shorter Side)
Start with `left` at index 0 and `right` at the last index — the widest possible container. Compute the area. Then, move whichever pointer points to the **shorter** line inward.

**Why this is safe:** the current area is limited by `min(height[left], height[right])`. If you move the *taller* line inward, the width shrinks and the height is still capped by the same shorter line (or an even shorter one) — the area can only get worse or stay the same. Moving the *shorter* line, on the other hand, is the only move that has a chance of finding a taller line that could increase the bounding height enough to outweigh the lost width.

- **Time:** O(n) — each pointer moves at most n times total
- **Space:** O(1)

## Dry Run
`height = [1,8,6,2,5,4,8,3,7]`

| left | right | area | shorter side | move |
|---|---|---|---|---|
| 0 (h=1) | 8 (h=7) | min(1,7)*8=8 | left | left++ |
| 1 (h=8) | 8 (h=7) | min(8,7)*7=49 | right | right-- |
| 1 (h=8) | 7 (h=3) | min(8,3)*6=18 | right | right-- |
| 1 (h=8) | 6 (h=8) | min(8,8)*5=40 | either | ... |

Best found so far: **49** (matches the expected answer, found early at left=1, right=8)

## Edge Cases
- Only two lines total -> the only possible container is the one formed by both, trivially the answer
- All lines the same height -> the widest pair (the two ends) is always optimal, since height never changes
- Strictly increasing or decreasing heights -> the algorithm still correctly narrows toward the actual best pair without missing it, due to the greedy proof above

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) |
| Optimized (two pointers) | O(n) | O(1) |

## Related Problems / Pattern Family
- Trapping Rain Water (Module 3 #10 — a visually similar problem solved with a related but distinct two-pointer technique)
- 3Sum (Module 3 #8 — another sorted-array two-pointer problem, different objective)
