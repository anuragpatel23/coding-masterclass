# 8. Kth Largest Element in an Array

**Difficulty:** Medium
**Pattern:** Quickselect
**LeetCode:** https://leetcode.com/problems/kth-largest-element-in-an-array/

## Problem Summary
Given an integer array and an integer `k`, return the `k`th largest element (the `k`th largest in sorted order, not the `k`th distinct value).

## Example
```
Input:  nums = [3,2,1,5,6,4], k = 2
Output: 5
```

## Pattern Recognition
Fully sorting the array to answer "what's at position k" is overkill — you don't need to know the order of *everything*, just what ends up at one specific position. That's the signature of **quickselect**: reuse quicksort's partitioning step, but only recurse into the one side that could actually contain the target position, throwing away the other side's work entirely.

## Approach 1: Brute Force
Sort the array, then read off the element `k` positions from the end.

- **Time:** O(n log n)
- **Space:** O(n) — the sorted copy (or O(1) extra if sorting in place)

## Approach 2: Optimized (Quickselect)
Convert "kth largest" to "element at index `n - k` in ascending sorted order." Use quicksort's Lomuto partition scheme: pick a pivot (here, the last element), partition the array so everything smaller is to the left and everything larger to the right, and note the pivot's final index. If that index matches the target, done. Otherwise, recurse into **only** the half that could contain the target index.

- **Time:** O(n) average case (each partition roughly halves the remaining work, similar to binary search's shape) — **O(n^2) worst case** on adversarial input (e.g., already-sorted data with a poor pivot choice), worth mentioning explicitly in an interview
- **Space:** O(1) extra (in-place partitioning), O(log n) recursion stack on average

## Dry Run
`nums = [3,2,1,5,6,4]`, `k = 2` -> target index (ascending) = `6 - 2 = 4`

| partition call | pivot | resulting pivot index | compare to target(4) | action |
|---|---|---|---|---|
| whole array | 4 (last element) | ends up at index 3 after partitioning | 3 < 4 | recurse right half only |
| right half | 6 (last element of that half) | ends up at index 5 | 5 > 4 | recurse left half of that |
| single element | - | index 4 | match | return value there = 5 |

Result: **5**

## Edge Cases
- `k = 1` -> target index is `n - 1`, the global maximum — quickselect still applies, though a simple max-scan would also work here
- `k = n` -> target index is `0`, the global minimum
- All elements identical -> every partition step still terminates correctly, since equal elements never need to move relative to each other for correctness (just efficiency)

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (full sort) | O(n log n) | O(n) |
| Optimized (quickselect) | O(n) average, O(n^2) worst case | O(1) extra, O(log n) recursion |

## Related Problems / Pattern Family
- Top K Frequent Elements (Module 8 #9 — a related "don't fully sort" problem, solved with bucket sort instead)
- Kth Smallest Element in a Sorted Matrix (Module 8 #14 — a different technique for a similar-sounding goal)
