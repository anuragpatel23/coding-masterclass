# 7. Sort an Array

**Difficulty:** Medium
**Pattern:** Merge Sort (Implemented From Scratch)
**LeetCode:** https://leetcode.com/problems/sort-an-array/

## Problem Summary
Sort an integer array in ascending order, without using a built-in sort function.

## Example
```
Input:  nums = [5,2,3,1]
Output: [1,2,3,5]
```

## Pattern Recognition
This problem exists to test whether you actually know how a comparison sort works under the hood, not whether you know `Arrays.sort()` exists. It's worth having merge sort's divide-and-conquer structure memorized cold: split the array in half, recursively sort each half, then merge the two sorted halves back together.

## Approach 1: "Brute Force" (Insertion Sort)
Build the sorted array one element at a time: for each new element, shift it backward past every already-sorted element that's bigger than it.

- **Time:** O(n^2) — a valid, simple sort, just not the fastest possible comparison sort
- **Space:** O(n) (using a cloned array to avoid mutating the input for the demo; O(1) extra if sorting truly in-place)

## Approach 2: Optimized (Merge Sort)
Recursively split the array into halves until each piece has 0 or 1 elements (trivially sorted). Merge pairs of sorted halves back together, comparing their front elements and taking the smaller one repeatedly — exactly the same merge step as Merge Two Sorted Lists (Module 5 #2), just on arrays instead of linked lists.

- **Time:** O(n log n) — guaranteed, regardless of input order
- **Space:** O(n) — the temporary arrays used during merging

## Dry Run
`nums = [5,2,3,1]`

**Split:** `[5,2]` and `[3,1]` -> further split into `[5]`,`[2]`,`[3]`,`[1]` (all trivially sorted).

**Merge pairs:** `[5]`+`[2]` -> `[2,5]`. `[3]`+`[1]` -> `[1,3]`.

**Final merge:** `[2,5]`+`[1,3]` -> compare 2 vs 1 (take 1), 2 vs 3 (take 2), 5 vs 3 (take 3), then 5 remains -> `[1,2,3,5]`.

Result: **[1, 2, 3, 5]**

## Edge Cases
- Empty or single-element array -> the recursion's base case (`left >= right`) returns immediately, nothing to merge
- Already-sorted input -> merge sort still runs its full O(n log n) process (no early-exit optimization), unlike some adaptive sorts
- All elements identical -> merges correctly with no special casing, since equal elements compare cleanly either direction

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| "Brute Force" (insertion sort) | O(n^2) | O(1) extra (in-place) |
| Optimized (merge sort) | O(n log n) | O(n) |

## Related Problems / Pattern Family
- Merge Two Sorted Lists (Module 5 #2 — the exact merge step this algorithm is built on)
- Sort List (Module 8 #15 — this same merge sort applied directly to a linked list)
- Kth Largest Element in an Array (Module 8 #8 — a related problem where full sorting is more work than necessary)
