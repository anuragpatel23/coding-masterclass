# 12. Largest Rectangle in Histogram

**Difficulty:** Hard
**Pattern:** Monotonic Stack
**LeetCode:** https://leetcode.com/problems/largest-rectangle-in-histogram/

## Problem Summary
Given an array of bar heights forming a histogram (each bar has width 1), find the area of the largest rectangle that can be formed within the histogram's outline.

## Example
```
Input:  heights = [2,1,5,6,2,3]
Output: 10
```

## Pattern Recognition
For every bar, the largest rectangle that uses it as its *shortest* bar extends as far left and right as other bars stay `>=` its height. That's a "how far can I extend before hitting something smaller" question — the same monotonic stack shape as Daily Temperatures, but resolving a width calculation instead of a distance.

## Approach 1: Brute Force
For each bar, expand left and right while neighboring bars are `>=` its height, then compute the resulting width times its height.

- **Time:** O(n^2) — for each bar, an O(n) expansion in both directions
- **Space:** O(1) extra

## Approach 2: Optimized (Monotonic Increasing Stack)
Walk the bars (with one extra virtual bar of height `0` appended at the end, to flush out anything left on the stack). Maintain a stack of indices with increasing heights. Whenever the current bar is **shorter** than the bar at the stack's top, that top bar can't extend any further right — pop it and compute its rectangle: its height times a width determined by the current index and whatever's now exposed on the stack (or the current index alone, if the stack is empty).

- **Time:** O(n) — each index is pushed once and popped at most once
- **Space:** O(n) — the stack

## Dry Run
`heights = [2,1,5,6,2,3]` (append a virtual `0` at the end)

| i | height | stack action | area computed |
|---|---|---|---|
| 0 | 2 | push 0 | - |
| 1 | 1 | 1<2: pop 0, width=1(stack empty->i=1), area=2*1=2. push 1 | 2 |
| 2 | 5 | push 2 | - |
| 3 | 6 | push 3 | - |
| 4 | 2 | 2<6: pop 3, width=4-2-1=1, area=6*1=6. 2<5: pop 2, width=4-1-1=2, area=5*2=10. push 4 | 6, **10** |
| 5 | 3 | push 5 | - |
| 6 | 0 | 0<3: pop5,width=6-4-1=1,area=3*1=3. 0<2(bar at index4):pop4,width=6-1-1=4,area=2*4=8. 0<1(bar at index1... stack now has [1]): pop1,width=6-(-1)-1=6,area=1*6=6 | 3, 8, 6 |

Peak area found across all steps: **10**

## Edge Cases
- A single bar -> the answer is just that bar's own height (width 1)
- Strictly increasing heights -> every bar's best rectangle is only discovered once the virtual trailing `0` forces the stack to unwind completely
- All bars the same height -> the entire histogram is one giant rectangle, `height * n`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) extra |
| Optimized (monotonic increasing stack) | O(n) | O(n) |

## Related Problems / Pattern Family
- Daily Temperatures (Module 6 #5 — the same monotonic stack resolution shape, a simpler payload)
- Maximal Rectangle (a harder 2D extension: run this algorithm once per row of a binary matrix)
