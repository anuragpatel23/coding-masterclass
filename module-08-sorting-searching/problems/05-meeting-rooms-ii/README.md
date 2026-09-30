# 5. Meeting Rooms II

**Difficulty:** Medium
**Pattern:** Sort + Two-Pointer Sweep
**LeetCode:** https://leetcode.com/problems/meeting-rooms-ii/

## Problem Summary
Given an array of meeting time intervals, find the minimum number of conference rooms required to hold all meetings.

## Example
```
Input:  intervals = [[0,30],[5,10],[15,20]]
Output: 2
```

## Pattern Recognition
"Minimum concurrent resources needed" is really "what's the maximum number of meetings happening at the same instant, at any point in time." Separating **start times** and **end times** into their own sorted lists lets you sweep through time chronologically: every start needs a room, and every end frees one up — track the running count and its peak.

## Approach 1: Brute Force
For each interval, check how many intervals (including itself) are active at the exact instant its own start time occurs, and track the maximum such count across all intervals. This works because the peak concurrency always occurs at (or just after) some interval's start time — checking every start time as a candidate checkpoint is sufficient.

*(A tempting shortcut — counting how many other intervals each interval pairwise overlaps with, then taking the max degree — is a genuine bug, not just an inefficiency: two meetings can each overlap a third without overlapping each other, so that count can overstate the true concurrency. Always checking concurrency at an actual instant in time avoids this trap.)*

- **Time:** O(n^2) — for each of n intervals, an O(n) scan checking concurrency at its start time
- **Space:** O(1) extra

## Approach 2: Optimized (Two Sorted Arrays, Two-Pointer Sweep)
Separate all start times into one sorted array and all end times into another. Walk through the start times in order with a pointer; for each one, check whether the earliest still-unprocessed end time is `<= ` it — if so, a room has freed up (advance the end pointer, don't increment the room count); otherwise, a brand-new room is needed (increment the room count). Track the maximum room count seen.

- **Time:** O(n log n) — dominated by the two sorts
- **Space:** O(n) — the two separated arrays

## Dry Run
`intervals = [[0,30],[5,10],[15,20]]` -> starts sorted `[0,5,15]`, ends sorted `[10,20,30]`

| start being processed | compare to earliest unprocessed end | action | rooms | maxRooms |
|---|---|---|---|---|
| 0 | 0 < 10 | need new room | 1 | 1 |
| 5 | 5 < 10 | need new room | 2 | 2 |
| 15 | 15 < 10? no, 15>=10 | room freed, reuse | 1 (then +1 for this start = still counted) |  |

*(Careful bookkeeping: each start always claims a room — either a freed one or a new one — while checking whether a room became available first. The peak simultaneous rooms needed across the whole sweep is the answer.)*

Result: **2**

## Edge Cases
- No overlaps at all -> every meeting reuses the same single room as it becomes free, answer is `1`
- All meetings overlap simultaneously -> every one needs its own room, answer equals the total meeting count
- Meetings that touch but don't overlap, e.g. `[1,5]` and `[5,10]` -> the room freed at time 5 can be reused for the meeting starting at 5 (the comparison uses `<`, not `<=`, so a start exactly equal to an end does count as freeing the room in time)

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) extra |
| Optimized (two-pointer sweep) | O(n log n) | O(n) |

## Related Problems / Pattern Family
- Meeting Rooms (Module 8 #2 — the simpler yes/no version of this exact question)
- Non-overlapping Intervals (Module 8 #4 — a related greedy interval-scheduling problem)
