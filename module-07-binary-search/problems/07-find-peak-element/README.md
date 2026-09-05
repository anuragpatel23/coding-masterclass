# 7. Find Peak Element

**Difficulty:** Medium
**Pattern:** Binary Search on an Unsorted-but-Structured Array
**LeetCode:** https://leetcode.com/problems/find-peak-element/

## Problem Summary
A peak element is one that is strictly greater than its neighbors (edges are treated as bordering `-infinity`). Given an array (not necessarily sorted), find the index of *any* peak element, in O(log n).

## Example
```
Input:  nums = [1,2,3,1]
Output: 2        (nums[2]=3 is greater than both neighbors)
```

## Pattern Recognition
This is the module's reminder that binary search doesn't require full sortedness — it just requires that **comparing the middle element to a neighbor reliably tells you which direction to search**. Here: if `nums[mid] < nums[mid+1]`, the array is "climbing" at that point, which guarantees a peak exists somewhere to the right (since the array must eventually stop climbing, whether at an interior peak or the last element, which the -infinity boundary treats as automatically qualifying).

## Approach 1: Brute Force
Scan linearly, checking each element against both neighbors.

- **Time:** O(n)
- **Space:** O(1)

## Approach 2: Optimized (Binary Search on the Slope)
Compare `nums[mid]` to `nums[mid + 1]`. If `nums[mid] < nums[mid + 1]`, the array is still climbing — a peak must exist to the right, so move `left = mid + 1`. Otherwise, the array is flat or descending at `mid` — a peak must exist at `mid` or to its left, so move `right = mid`.

- **Time:** O(log n) — the search space halves every step
- **Space:** O(1)

## Dry Run
`nums = [1,2,3,1]`

| left | right | mid | nums[mid] | nums[mid+1] | climbing? | action |
|---|---|---|---|---|---|---|
| 0 | 3 | 1 | 2 | 3 | yes (2<3) | left=2 |
| 2 | 3 | 2 | 3 | 1 | no (3>1) | right=2 |

`left == right == 2`.

Result: **2** (`nums[2] = 3`)

## Edge Cases
- Strictly increasing array -> the last element is always a valid peak (bordered by `-infinity` on the right), and the algorithm converges there
- Strictly decreasing array -> the first element is always a valid peak, converges to index `0`
- Multiple peaks exist -> the problem only requires finding *any* one of them, so the algorithm's specific convergence point (which depends on the comparisons made) is always a valid answer, even if it's not the only one

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n) | O(1) |
| Optimized (binary search on slope) | O(log n) | O(1) |

## Related Problems / Pattern Family
- Find Minimum in Rotated Sorted Array (Module 7 #6 — a related "compare to a neighbor to pick a direction" technique)
