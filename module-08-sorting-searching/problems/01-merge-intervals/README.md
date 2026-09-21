# 1. Merge Intervals

**Difficulty:** Medium
**Pattern:** Sort + Linear Merge
**LeetCode:** https://leetcode.com/problems/merge-intervals/



## Problem Summary
Given an array of intervals, merge all overlapping intervals and return the resulting non-overlapping set.

## Example
```
Input:  intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,3],[2,6]] merge to [[1,6],[8,10],[15,18]]
```

## Pattern Recognition
Overlap-checking between arbitrary pairs of intervals is expensive — but once intervals are **sorted by start time**, any interval that overlaps the current one must be sitting immediately next to it. Sorting turns an all-pairs problem into a single linear scan.

## Approach 1: Brute Force
Without sorting, repeatedly scan all pairs for any overlap, merge the first pair found, and restart the scan — continue until no overlapping pair remains.

- **Time:** O(n^3) worst case — up to n merges, each triggering an O(n^2) pair scan
- **Space:** O(n)
- **Why it's not good enough:** checking every pair against every other pair, repeatedly, ignores that sorting would let you check only *adjacent* intervals instead.

## Approach 2: Optimized (Sort by Start, Then Merge Linearly)
Sort intervals by start time. Walk through them once, maintaining a "current merged interval." If the next interval's start is `<= ` the current merged interval's end, they overlap — extend the current interval's end. Otherwise, the current interval is finalized — add it to the result and start a new one.

- **Time:** O(n log n) — dominated by the sort
- **Space:** O(n) — the result list

## Dry Run
`intervals = [[1,3],[2,6],[8,10],[15,18]]` (already sorted by start)

| current | next | overlap? | action |
|---|---|---|---|
| [1,3] | [2,6] | 2<=3 yes | extend: [1,6] |
| [1,6] | [8,10] | 8<=6 no | finalize [1,6], current=[8,10] |
| [8,10] | [15,18] | 15<=10 no | finalize [8,10], current=[15,18] |

Finalize [15,18] at the end.

Result: **[[1,6],[8,10],[15,18]]**

## Edge Cases
- No overlaps at all -> every interval is finalized as-is, output equals input (just sorted)
- All intervals overlap into one -> result is a single merged interval
- Touching but not overlapping intervals, e.g. `[1,2]` and `[2,3]` -> depends on the problem's convention for whether touching counts as overlapping (this implementation treats `next.start <= current.end` as overlapping, so touching intervals do merge)

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^3) | O(n) |
| Optimized (sort + linear merge) | O(n log n) | O(n) |

## Related Problems / Pattern Family
- Insert Interval (Module 8 #3 — inserting into an already-sorted, already-merged set)
- Meeting Rooms (Module 8 #2 — a simpler yes/no version of overlap detection)
- Non-overlapping Intervals (Module 8 #4 — a greedy removal variant)
