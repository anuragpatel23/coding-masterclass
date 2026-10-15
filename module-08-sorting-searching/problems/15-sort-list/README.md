# 15. Sort List

**Difficulty:** Medium
**Pattern:** Merge Sort on a Linked List
**LeetCode:** https://leetcode.com/problems/sort-list/

## Problem Summary
Sort a linked list in O(n log n) time, ideally using O(1) extra space (beyond recursion).

## Example
```
Input:  4 -> 2 -> 1 -> 3
Output: 1 -> 2 -> 3 -> 4
```

## Pattern Recognition
This is Sort an Array (Module 8 #7) and Merge Two Sorted Lists (Module 5 #2), combined: merge sort's divide-and-conquer structure, but splitting and merging **linked list nodes** instead of array segments — which means "splitting in half" needs Module 3's fast/slow pointer trick instead of simple index arithmetic.

## Approach 1: Brute Force
Copy every value into an array, sort it, and rebuild an entirely new linked list from the sorted values.

- **Time:** O(n log n)
- **Space:** O(n) — the value array, plus new nodes
- **Why it's not good enough:** it works and hits the right time complexity, but discards the existing nodes entirely when they could be relinked in place instead.

## Approach 2: Optimized (Merge Sort Directly on the List)
Recursively: find the middle of the current list segment using fast/slow pointers, split it into two halves there, recursively sort each half, then merge the two sorted halves using the standard two-list merge.

- **Time:** O(n log n) — the same divide-and-conquer shape as array merge sort
- **Space:** O(log n) — the recursion stack (no auxiliary array needed for the data itself, since nodes are relinked in place)

## Dry Run
`4 -> 2 -> 1 -> 3`

**Split at middle** (fast/slow finds the split point between `2` and `1`): left half `4 -> 2`, right half `1 -> 3`.

**Recursively sort left half** `4 -> 2`: splits into `4` and `2`, merges to `2 -> 4`.
**Recursively sort right half** `1 -> 3`: splits into `1` and `3`, merges to `1 -> 3` (already in order).

**Merge** `2 -> 4` and `1 -> 3`: compare 2 vs 1 (take 1), 2 vs 3 (take 2), 4 vs 3 (take 3), then 4 remains -> `1 -> 2 -> 3 -> 4`.

Result: **1 -> 2 -> 3 -> 4**

## Edge Cases
- Empty list or single node -> the base case (`head == null || head.next == null`) returns immediately, nothing to sort
- Already-sorted list -> merge sort still performs its full split/merge process (no early-exit optimization), same as the array version
- All values identical -> merges correctly with no special casing needed

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (array copy, new nodes) | O(n log n) | O(n) |
| Optimized (merge sort on the list) | O(n log n) | O(log n) |

## Related Problems / Pattern Family
- Sort an Array (Module 8 #7 — the array version of this exact algorithm)
- Merge Two Sorted Lists (Module 5 #2 — the merge step this algorithm relies on)
- Merge K Sorted Lists (Module 5 #15 — a related divide-and-conquer merge, across many lists instead of within one)
