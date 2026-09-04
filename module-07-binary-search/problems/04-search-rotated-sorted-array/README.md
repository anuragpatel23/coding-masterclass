# 4. Search in Rotated Sorted Array

**Difficulty:** Medium
**Pattern:** Modified Binary Search
**LeetCode:** https://leetcode.com/problems/search-in-rotated-sorted-array/

## Problem Summary
A sorted array has been rotated at some unknown pivot (e.g., `[0,1,2,4,5,6,7]` becomes `[4,5,6,7,0,1,2]`). Given the rotated array and a target, find its index, or `-1` if absent, in O(log n).

## Example
```
Input:  nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```

## Pattern Recognition
The array isn't fully sorted, so you can't directly compare `target` to `nums[mid]` and know which half to search — but here's the key insight: **at least one of the two halves around any `mid` is always fully sorted.** Once you identify which half is sorted, you can check with a simple range comparison whether the target could be in that sorted half — if so, search there; if not, it must be in the other (rotated) half.

## Approach 1: Brute Force
Scan linearly for the target, ignoring the rotation structure entirely.

- **Time:** O(n)
- **Space:** O(1)
- **Why it's not good enough:** it throws away the fact that large sorted chunks still exist — just not spanning the whole array — which is exactly what a modified binary search can exploit.

## Approach 2: Optimized (Identify the Sorted Half, Then Decide)
At each step, compare `nums[left]` to `nums[mid]`. If `nums[left] <= nums[mid]`, the **left half** is sorted — check whether the target falls within `[nums[left], nums[mid])`; if so, search left, otherwise search right. If the left half isn't sorted, the **right half** must be — apply the symmetric logic.

- **Time:** O(log n) — still halves the search space every step, just with an extra branch to determine which half is safely comparable
- **Space:** O(1)

## Dry Run
`nums = [4,5,6,7,0,1,2]`, `target = 0`

| left | right | mid | nums[mid] | sorted half | target in range? | action |
|---|---|---|---|---|---|---|
| 0 | 6 | 3 | 7 | left (4<=7) | 0 in [4,7)? no | search right: left=4 |
| 4 | 6 | 5 | 1 | left (0<=1) | 0 in [0,1)? yes | search left: right=4 |
| 4 | 4 | 4 | 0 | - | match! | return 4 |

Result: **4**

## Edge Cases
- No rotation at all (pivot at index 0) -> the left half is always "sorted" in the sense the algorithm checks, degrading gracefully to standard binary search
- Target not present -> the search space still shrinks to nothing, correctly returning `-1`
- Single-element array -> the loop runs at most once, trivially correct

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n) | O(1) |
| Optimized (modified binary search) | O(log n) | O(1) |

## Related Problems / Pattern Family
- Search in Rotated Sorted Array II (Module 7 #5 — the same idea, complicated by duplicate values)
- Find Minimum in Rotated Sorted Array (Module 7 #6 — a related pivot-finding technique)
