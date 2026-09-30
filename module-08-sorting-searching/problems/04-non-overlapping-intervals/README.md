# 4. Non-overlapping Intervals

**Difficulty:** Medium
**Pattern:** Greedy + Sort (by End Time)
**LeetCode:** https://leetcode.com/problems/non-overlapping-intervals/

## Problem Summary
Given an array of intervals, find the minimum number of intervals to remove so that the rest don't overlap.

## Example
```
Input:  intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1        (remove [1,3])
```

## Pattern Recognition
"Minimum removals to eliminate overlaps" is equivalent to "maximum number of intervals you can keep, non-overlapping" — a classic **activity selection** problem. The greedy insight: sort by **end time**, not start time. Always keeping the interval that finishes earliest leaves the most room for everything that comes after it.

## Approach 1: Brute Force (Longest Non-Overlapping Chain, O(n²) DP)
Sort by start time. For each interval, compute the length of the longest chain of non-overlapping intervals that *ends* at it, by checking all earlier intervals (similar to the Longest Increasing Subsequence DP shape). The answer is `n - (longest chain found)`.

- **Time:** O(n^2) — a nested loop building up chain lengths
- **Space:** O(n) — the chain-length array

## Approach 2: Optimized (Greedy, Sort by End Time)
Sort intervals by their **end time**. Walk through them, tracking the end time of the last interval you decided to keep. For each interval: if its start is `>= ` the last kept interval's end, keep it (no conflict) and update the tracked end time. Otherwise, it overlaps — it must be removed, so increment a removal counter.

- **Time:** O(n log n) — dominated by the sort
- **Space:** O(1) extra (beyond the sorted copy)

## Dry Run
`intervals = [[1,2],[2,3],[3,4],[1,3]]` -> sorted by end time: `[[1,2],[2,3],[1,3],[3,4]]`

| interval | start >= lastEnd? | action | lastEnd after |
|---|---|---|---|
| [1,2] | (first, auto-keep) | keep | 2 |
| [2,3] | 2>=2 yes | keep | 3 |
| [1,3] | 1>=3 no | remove | 3 (unchanged) |
| [3,4] | 3>=3 yes | keep | 4 |

Removals: **1**

## Edge Cases
- No overlaps at all -> every interval gets kept, `0` removals
- All intervals identical -> all but one must be removed
- Intervals that only touch at an endpoint, e.g. `[1,2]` and `[2,3]` -> not considered overlapping (start `>= ` end, inclusive), both can be kept

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (O(n^2) DP) | O(n^2) | O(n) |
| Optimized (greedy, sort by end) | O(n log n) | O(1) extra |

## Related Problems / Pattern Family
- Meeting Rooms II (Module 8 #5 — a related interval-scheduling problem, counting resources instead of removals)
- Merge Intervals (Module 8 #1 — sorts by start time instead, for a different objective)
