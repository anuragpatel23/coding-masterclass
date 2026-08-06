# 6. Sort Colors

**Difficulty:** Medium
**Pattern:** Three-Pointer Partitioning (Dutch National Flag)
**LeetCode:** https://leetcode.com/problems/sort-colors/

## Problem Summary
Given an array containing only the values `0`, `1`, and `2` (representing red, white, and blue), sort it in-place **in one pass**, without using a library sort function.

## Example
```
Input:  nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
```

## Pattern Recognition
Whenever an array needs to be partitioned into exactly **three** known groups in a single pass, two pointers aren't quite enough — you need a third. This is the Dutch National Flag problem, named for its three-band structure: everything sorted into a "definitely 0s," "definitely 1s," and "definitely 2s" region simultaneously, using three pointers that carve the array into growing/shrinking zones as you scan once.

## Approach 1: Brute Force
Use a library sort. It's fast and simple — but the problem explicitly disallows it, which is exactly the point: this "brute force" exists mainly to highlight the constraint you're being asked to work around, and to contrast against a genuinely single-pass, constant-space solution below.

- **Time:** O(n log n)
- **Space:** O(log n) to O(n) depending on the sort implementation
- **Why it's not allowed:** the problem is testing whether you know the three-pointer partitioning trick — reaching for a library sort sidesteps the actual skill being assessed.

*(A valid two-pass alternative: count the occurrences of 0, 1, and 2, then overwrite the array accordingly — O(n) time, O(1) space, but two passes instead of one.)*

## Approach 2: Optimized (Dutch National Flag — Three Pointers, One Pass)
Maintain three pointers: `low` (boundary for the next `0` to be placed), `mid` (the current element being examined), and `high` (boundary for the next `2` to be placed). Walk `mid` through the array:
- If `nums[mid] == 0`: swap with `nums[low]`, advance both `low` and `mid` (everything before `low` is confirmed `0`s).
- If `nums[mid] == 1`: it's already in the right zone, just advance `mid`.
- If `nums[mid] == 2`: swap with `nums[high]`, decrement `high` **only** (don't advance `mid` — the swapped-in value from the high end hasn't been examined yet).

- **Time:** O(n) — a single pass, each element touched a bounded number of times
- **Space:** O(1)

## Dry Run
`nums = [2,0,2,1,1,0]`, `low=0, mid=0, high=5`

| nums[mid] | action | array after | low, mid, high after |
|---|---|---|---|
| 2 | swap(mid,high) | [0,0,2,1,1,2] | 0, 0, 4 |
| 0 | swap(low,mid); low++,mid++ | [0,0,2,1,1,2] | 1, 1, 4 |
| 0 | swap(low,mid); low++,mid++ | [0,0,2,1,1,2] | 2, 2, 4 |
| 2 | swap(mid,high) | [0,0,1,1,2,2] | 2, 2, 3 |
| 1 | mid++ | [0,0,1,1,2,2] | 2, 3, 3 |
| 1 | mid++ | [0,0,1,1,2,2] | 2, 4, 3 (mid > high, loop ends) |

Result: **[0,0,1,1,2,2]**

## Edge Cases
- Array of a single color, e.g. all `1`s -> `mid` just walks through, no swaps needed
- Already sorted input -> the algorithm still runs correctly (each comparison confirms the existing placement)
- All `0`s and `2`s, no `1`s -> `low` and `mid` stay together until a `2` is found, then only `high` moves

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Library sort (disallowed) | O(n log n) | O(log n) - O(n) |
| Counting sort (two-pass) | O(n) | O(1) |
| Optimized (Dutch National Flag) | O(n) | O(1) |

## Related Problems / Pattern Family
- Sort an Array of 0s, 1s (a simpler 2-color special case, solvable with plain two pointers)
- Kth Largest Element (Module 8 — Sorting & Searching, quickselect uses a related 3-way partition idea)
