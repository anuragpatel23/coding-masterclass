# 6. Find Minimum in Rotated Sorted Array

**Difficulty:** Medium
**Pattern:** Modified Binary Search (Find the Pivot)
**LeetCode:** https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

## Problem Summary
Given a rotated sorted array with no duplicates, find its minimum element in O(log n).

## Example
```
Input:  nums = [4,5,6,7,0,1,2]
Output: 0
```

## Pattern Recognition
The minimum element is exactly the "pivot" where the rotation happened — the one place where a larger value is immediately followed by a smaller one. Comparing `nums[mid]` to `nums[right]` tells you which side of that pivot you're currently looking at: if `nums[mid] > nums[right]`, the pivot must be to the right of `mid`; otherwise, it's at `mid` or to the left.

## Approach 1: Brute Force
Scan linearly, tracking the minimum value seen.

- **Time:** O(n)
- **Space:** O(1)

## Approach 2: Optimized (Binary Search Toward the Pivot)
Compare `nums[mid]` to `nums[right]`. If `nums[mid] > nums[right]`, the minimum is somewhere in `(mid, right]` — move `left = mid + 1`. Otherwise, the minimum is at `mid` or earlier — move `right = mid` (not `mid - 1`, since `mid` itself could still be the minimum). Stop when `left == right`.

- **Time:** O(log n)
- **Space:** O(1)

## Dry Run
`nums = [4,5,6,7,0,1,2]`

| left | right | mid | nums[mid] | nums[right] | comparison | action |
|---|---|---|---|---|---|---|
| 0 | 6 | 3 | 7 | 2 | 7>2 | left=4 |
| 4 | 6 | 5 | 1 | 2 | 1<=2 | right=5 |
| 4 | 5 | 4 | 0 | 1 | 0<=1 | right=4 |

`left == right == 4`.

Result: **nums[4] = 0**

## Edge Cases
- No rotation at all -> `nums[mid] <= nums[right]` holds throughout, converging naturally to index `0`
- Rotation by exactly one position (minimum is the last element) -> still handled correctly by the same comparison logic
- Single-element array -> `left == right` immediately, trivially correct

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n) | O(1) |
| Optimized (binary search toward pivot) | O(log n) | O(1) |

## Related Problems / Pattern Family
- Search in Rotated Sorted Array (Module 7 #4 — searches for a target using a related pivot-awareness idea)
- Find Peak Element (Module 7 #7 — a related "find the special point via comparison with a neighbor" technique)
