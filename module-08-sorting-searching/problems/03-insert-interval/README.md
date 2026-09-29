# 3. Insert Interval

**Difficulty:** Medium
**Pattern:** Linear Scan (Already-Sorted Insert)
**LeetCode:** https://leetcode.com/problems/insert-interval/

## Problem Summary
Given a sorted, non-overlapping list of intervals and a new interval to insert, insert it and merge as needed so the result remains sorted and non-overlapping.

## Example
```
Input:  intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
```

## Pattern Recognition
Because the input is *already* sorted and merged, this doesn't need a full re-sort — it needs a single linear pass with three clearly separated phases: intervals entirely before the new one (copy as-is), intervals that overlap it (fold into one merged interval), and intervals entirely after it (copy as-is).

## Approach 1: Brute Force
Add the new interval to the list, then run the full Merge Intervals algorithm (sort + merge) from scratch.

- **Time:** O(n log n) — the sort is unnecessary work, since the input was already sorted before adding one element
- **Space:** O(n)

## Approach 2: Optimized (Three-Phase Linear Scan)
1. Copy every interval that ends strictly before the new interval starts.
2. Merge every interval that overlaps the new interval into a running `merged` interval (expanding its bounds as needed), then add that merged result.
3. Copy every remaining interval (all of which start after the merged interval ends).

- **Time:** O(n) — one pass
- **Space:** O(n) — the result list

## Dry Run
`intervals = [[1,3],[6,9]]`, `newInterval = [2,5]`

**Phase 1:** is `[1,3]`'s end (3) `< newInterval`'s start (2)? No -> stop phase 1 immediately, nothing copied yet.

**Phase 2:** `[1,3]` overlaps `[2,5]` (1<=5 and 2<=3) -> merge: `[1,5]`. `[6,9]` overlaps `[1,5]`? 6 <= 5? No -> stop merging. Add `[1,5]` to result.

**Phase 3:** copy remaining: `[6,9]`.

Result: **[[1,5],[6,9]]**

## Edge Cases
- New interval doesn't overlap anything -> phases 1 and 3 do all the work, phase 2 just inserts it standalone
- New interval swallows every existing interval -> phase 2 merges everything into one, phases 1 and 3 contribute nothing
- Inserting into an empty list -> phases 1 and 3 are no-ops, the result is just the new interval alone

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (add + full re-sort) | O(n log n) | O(n) |
| Optimized (three-phase linear scan) | O(n) | O(n) |

## Related Problems / Pattern Family
- Merge Intervals (Module 8 #1 — the general-purpose version this problem specializes for an already-sorted input)
