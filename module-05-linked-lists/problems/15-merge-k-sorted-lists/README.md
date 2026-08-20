# 15. Merge K Sorted Lists

**Difficulty:** Hard
**Pattern:** Divide & Conquer (Pairwise Merge)
**LeetCode:** https://leetcode.com/problems/merge-k-sorted-lists/

## Problem Summary
Given an array of `k` sorted linked lists, merge them into a single sorted list.

## Example
```
Input:  lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
```

## Pattern Recognition
This is the module's capstone because it scales up Merge Two Sorted Lists (#2) to `k` lists at once. Merging them one at a time, left to right, works but wastes effort re-scanning an ever-growing result list. **Divide and conquer** fixes this: merge pairs of lists in parallel rounds, so the total list content is only ever "touched" `O(log k)` times instead of `O(k)` times.

## Approach 1: Brute Force
Collect every value from every list into one array, sort it, and build a single new list from the sorted result.

- **Time:** O(N log N), where N is the total number of nodes across all lists — this ignores that each individual list already arrives pre-sorted
- **Space:** O(N)

## Approach 2: Optimized (Divide and Conquer, Pairwise Merging)
Repeatedly merge the lists in pairs using the standard two-list merge (#2): round 1 merges list 0 with list 1, list 2 with list 3, and so on, halving the number of lists. Repeat on the resulting (halved) set of lists until only one remains.

- **Time:** O(N log k) — each of the `log k` rounds does a total of O(N) work merging that round's pairs
- **Space:** O(1) extra beyond the output (each merge reuses existing nodes)

## Dry Run
`lists = [[1,4,5], [1,3,4], [2,6]]`

**Round 1:** merge list 0 (`1,4,5`) with list 1 (`1,3,4`) -> `1,1,3,4,4,5`. List 2 (`2,6`) has no pair this round, carries forward unchanged.

**Round 2:** merge `[1,1,3,4,4,5]` with `[2,6]` -> `1,1,2,3,4,4,5,6`.

Result: **[1,1,2,3,4,4,5,6]**

## Edge Cases
- `k = 0` (empty array of lists) -> return `null` immediately
- Some lists are empty (`null`) -> the two-list merge subroutine already handles a `null` input correctly (its own edge case), so no extra logic is needed here
- Only one list total -> the divide-and-conquer loop's "size > 1" condition is false immediately, that single list is returned as-is

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (collect, sort, rebuild) | O(N log N) | O(N) |
| Optimized (divide and conquer pairwise merge) | O(N log k) | O(1) extra |

## Related Problems / Pattern Family
- Merge Two Sorted Lists (Module 5 #2 — the exact subroutine this problem repeatedly applies)
- Sort List (Module 8 — Sorting & Searching, the same divide-and-conquer merge idea applied to a single unsorted list)
