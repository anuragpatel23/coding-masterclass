# 2. Meeting Rooms

**Difficulty:** Easy
**Pattern:** Sort + Overlap Check
**LeetCode:** (classic pattern problem, commonly listed as "Meeting Rooms")

## Problem Summary
Given an array of meeting time intervals, determine if a person could attend all of them (i.e., no two meetings overlap).

## Example
```
Input:  intervals = [[0,30],[5,10],[15,20]]
Output: false        ([0,30] overlaps both others)
```

## Pattern Recognition
This is the simplest possible overlap question — a yes/no version of Merge Intervals (#1). Once intervals are sorted by start time, you never need to check non-adjacent pairs: if any overlap exists at all, it must show up between two **consecutive** intervals in sorted order.

## Approach 1: Brute Force
Check every pair of intervals directly for overlap.

- **Time:** O(n^2)
- **Space:** O(1) extra

## Approach 2: Optimized (Sort, Then Check Adjacent Pairs)
Sort intervals by start time. Walk through consecutive pairs — if any interval starts before the previous one ends, there's a conflict.

- **Time:** O(n log n) — dominated by the sort
- **Space:** O(n) for the sorted copy (or O(log n)/O(1) depending on the sort implementation, excluding the copy itself)

## Dry Run
`intervals = [[0,30],[5,10],[15,20]]` -> sorted by start: `[[0,30],[5,10],[15,20]]` (already sorted)

| current | next | overlap? |
|---|---|---|
| [0,30] | [5,10] | 5 < 30 -> yes, conflict! |

Result: **false**

## Edge Cases
- Zero or one meeting -> trivially `true`, no pairs to compare
- Meetings that touch but don't overlap, e.g. `[1,5]` and `[5,10]` -> not a conflict under the strict `<` check (a meeting can start exactly when another ends)
- All meetings identical -> definitely a conflict, caught on the very first comparison

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) extra |
| Optimized (sort + adjacent check) | O(n log n) | O(n) |

## Related Problems / Pattern Family
- Merge Intervals (Module 8 #1 — the same sorted-adjacency insight, applied to merging instead of a yes/no check)
- Meeting Rooms II (Module 8 #5 — "how many rooms are needed," a significant step up in difficulty)
