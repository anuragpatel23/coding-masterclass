# 10. Trapping Rain Water

**Difficulty:** Hard
**Pattern:** Two Pointers (Track Running Max From Both Sides)
**LeetCode:** https://leetcode.com/problems/trapping-rain-water/

## Problem Summary
Given an array representing an elevation map (bar widths of 1), compute how much water it can trap after raining.

## Example
```
Input:  height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
```

## Pattern Recognition
The water trapped above any single position `i` is `min(maxHeightToTheLeft, maxHeightToTheRight) - height[i]` (if positive). The brute-force version of that idea requires knowing the tallest bar on *both* sides of every position. The two-pointer insight: you don't need the exact max on both sides at every point — you only need to know, at each step, which side's current max is smaller, because that smaller max is what actually bounds the water at whichever pointer is behind it.

## Approach 1: Brute Force
For every index `i`, scan left to find the tallest bar before it and scan right to find the tallest bar after it. Water at `i` is `min(leftMax, rightMax) - height[i]`, if positive.

- **Time:** O(n^2) — an O(n) scan in both directions, triggered from every one of the n positions
- **Space:** O(1)
- **Why it's not good enough:** the left-max and right-max for adjacent positions overlap almost entirely — you're re-scanning nearly the same range over and over instead of maintaining a running value.

*(A common middle-ground: precompute `leftMax[]` and `rightMax[]` arrays in two passes, then combine them in a third pass — O(n) time, but O(n) extra space for the two arrays.)*

## Approach 2: Optimized (Two Pointers, O(1) Space)
Maintain `left` and `right` pointers at the two ends, and `leftMax`/`rightMax` tracking the tallest bar seen so far from each side. At each step, advance whichever side has the **smaller** current max:
- If `height[left] < height[right]`: the water level at `left` is bounded by `leftMax` (since we know there's something at least as tall as `rightMax` somewhere to the right, so `leftMax` is the binding constraint). Update `leftMax` or accumulate `leftMax - height[left]` as trapped water, then advance `left`.
- Otherwise, do the symmetric thing on the right side.

This works because whichever side has the smaller "max so far" is guaranteed to have its water level correctly bounded — the other side is guaranteed to have something at least that tall, even if you haven't scanned all the way to it yet.

- **Time:** O(n) — a single pass, pointers only move inward
- **Space:** O(1)

## Dry Run
`height = [0,1,0,2,1,0,1,3,2,1,2,1]` (abbreviated — the full trace visits every index once)

Key moments: at index 2 (`height=0`), both sides have already seen a bar of height 1 (`leftMax=1` from index 1), so 1 unit of water is trapped there. Later, once the height-3 peak at index 7 is established as `rightMax`, several lower bars between the two height-2 "walls" around it each trap a bounded amount. Summing every trapped unit across the array gives:

Result: **6**

## Edge Cases
- Strictly increasing or strictly decreasing heights -> no water can be trapped at all (there's no "valley"), correctly returns `0`
- Fewer than 3 bars -> can't form a valley, returns `0`
- All bars the same height -> flat surface, no water trapped, returns `0`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) |
| Prefix/suffix max arrays | O(n) | O(n) |
| Optimized (two pointers) | O(n) | O(1) |

## Related Problems / Pattern Family
- Container With Most Water (Module 3 #7 — visually similar, but a different objective and different pointer-movement rule)
- Product of Array Except Self (Module 1 #8 — the prefix/suffix technique this problem's middle-ground approach is built on)
