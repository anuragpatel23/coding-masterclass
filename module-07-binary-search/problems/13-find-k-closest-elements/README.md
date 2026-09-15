# 13. Find K Closest Elements

**Difficulty:** Medium
**Pattern:** Binary Search on the Window's Start Position
**LeetCode:** https://leetcode.com/problems/find-k-closest-elements/

## Problem Summary
Given a sorted array, an integer `k`, and a value `x`, return the `k` closest elements to `x` in the array, sorted in ascending order.

## Example
```
Input:  arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
```

## Pattern Recognition
The answer is always some contiguous window of size `k` within the sorted array (since the array is sorted, the k closest values to any point are always adjacent to each other). That turns the question into "where does this window of size `k` start" — and comparing two candidate windows only ever requires comparing their two edge elements, which is a monotonic decision you can binary search over directly.

## Approach 1: Brute Force
Compute the distance from `x` for every element, sort by that distance (breaking ties by value), take the first `k`, then sort those `k` values back into ascending order.

- **Time:** O(n log n) — dominated by the sort
- **Space:** O(n)

## Approach 2: Optimized (Binary Search on the Window's Left Edge)
Search for the correct starting index of the size-`k` window among the `n - k + 1` possible starting positions. At each candidate `mid`, compare the two elements just outside the window's current guess: `arr[mid]` (would be excluded if the window shifted right) versus `arr[mid + k]` (would be newly included). Whichever is farther from `x` tells you the window should shift away from it.

- **Time:** O(log(n - k)) — binary search over possible starting positions
- **Space:** O(1) extra (beyond the output)

## Dry Run
`arr = [1,2,3,4,5]`, `k = 4`, `x = 3`, search range for window start `[0, 1]` (since `n - k = 1`)

| left | right | mid | compare x - arr[mid] vs arr[mid+k] - x | action |
|---|---|---|---|---|
| 0 | 1 | 0 | (3-1=2) vs (arr[4]-3=2) : 2 > 2? no | right=0 |

`left == right == 0`.

Result: window starts at index 0 -> **[1, 2, 3, 4]**

## Edge Cases
- `x` smaller than every element -> the window converges to the very start of the array (indices `0` to `k-1`)
- `x` larger than every element -> the window converges to the very end of the array
- `k` equal to the array's full length -> only one possible window (the whole array), no searching needed

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (sort by distance) | O(n log n) | O(n) |
| Optimized (binary search on window start) | O(log(n-k)) | O(1) extra |

## Related Problems / Pattern Family
- Binary Search (Module 7 #1 — the foundational technique this problem applies to a window position instead of a single value)
- Kth Smallest Element in a Sorted Matrix (Module 8 — another "binary search over a derived space" problem)
