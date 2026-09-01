# 1. Binary Search

**Difficulty:** Easy
**Pattern:** Classic Binary Search
**LeetCode:** https://leetcode.com/problems/binary-search/

## Problem Summary
Given a sorted array and a target value, return its index, or `-1` if it's not present.



## Example
```
Input:  nums = [-1,0,3,5,9,12], target = 9
Output: 4
```

## Pattern Recognition
Sorted data + "find a value" is the foundational signal for binary search: every comparison against the middle element eliminates **half** of the remaining search space, because sortedness guarantees which half the target must be in.

## Approach 1: Brute Force
Scan the array linearly, checking each element.

- **Time:** O(n)
- **Space:** O(1)
- **Why it's not good enough:** it completely ignores that the array is sorted — information that lets you discard half the remaining candidates with every single comparison.

## Approach 2: Optimized (Binary Search)
Maintain `left` and `right` bounds spanning the whole array. Repeatedly check the middle element: if it's the target, done. If it's smaller than the target, the target (if present) must be to the right — move `left` past the middle. If it's larger, move `right` before the middle. Stop when the bounds cross.

- **Time:** O(log n) — the search space halves every step
- **Space:** O(1)

## Dry Run
`nums = [-1,0,3,5,9,12]`, `target = 9`

| left | right | mid | nums[mid] | comparison | action |
|---|---|---|---|---|---|
| 0 | 5 | 2 | 3 | too small | left=3 |
| 3 | 5 | 4 | 9 | match! | return 4 |

Result: **4**

## Edge Cases
- Target smaller than every element -> `right` gets pushed below `left` without ever matching, correctly returns `-1`
- Target larger than every element -> symmetric case, same correct `-1` outcome
- Single-element array -> the loop runs at most once, trivially correct either way

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (linear scan) | O(n) | O(1) |
| Optimized (binary search) | O(log n) | O(1) |

## Related Problems / Pattern Family
- Search Insert Position (Module 7 #2 — the same mechanics, adapted to return an insertion point)
- Search in Rotated Sorted Array (Module 7 #4 — binary search adapted to a non-fully-sorted structure)
