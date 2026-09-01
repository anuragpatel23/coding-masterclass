# 2. Search Insert Position

**Difficulty:** Easy
**Pattern:** Classic Binary Search (Lower Bound)
**LeetCode:** https://leetcode.com/problems/search-insert-position/

## Problem Summary
Given a sorted array and a target, return the index if found; otherwise, return the index where it would be inserted to keep the array sorted.

## Example
```
Input:  nums = [1,3,5,6], target = 2
Output: 1
```

## Pattern Recognition
This is binary search with a twist: instead of stopping only on an exact match, you want the **leftmost position** where the target could go — commonly called a "lower bound" search. The loop structure changes slightly: instead of a three-way branch (equal/less/greater), you only ever ask "is the middle value less than the target," narrowing toward the answer either way.

## Approach 1: Brute Force
Scan linearly, returning the first index whose value is `>= target`.

- **Time:** O(n)
- **Space:** O(1)

## Approach 2: Optimized (Binary Search, Lower Bound)
Use a half-open range `[left, right)` starting at `[0, n)`. At each step, if `nums[mid] < target`, the answer must be strictly to the right — move `left = mid + 1`. Otherwise, the answer could be `mid` itself or further left — move `right = mid`. When `left == right`, that's the answer: either the target's exact position, or the correct insertion point.

- **Time:** O(log n)
- **Space:** O(1)

## Dry Run
`nums = [1,3,5,6]`, `target = 2`

| left | right | mid | nums[mid] | action |
|---|---|---|---|---|
| 0 | 4 | 2 | 5 | 5>=2 -> right=2 |
| 0 | 2 | 1 | 3 | 3>=2 -> right=1 |
| 0 | 1 | 0 | 1 | 1<2 -> left=1 |

`left == right == 1`.

Result: **1**

## Edge Cases
- Target smaller than every element -> the loop converges to index `0`, the correct insertion point at the very front
- Target larger than every element -> converges to index `n`, correctly indicating "insert at the end"
- Target already present -> converges to its exact index, since equal values satisfy the `>= target` condition used to move `right`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n) | O(1) |
| Optimized (binary search, lower bound) | O(log n) | O(1) |

## Related Problems / Pattern Family
- Binary Search (Module 7 #1 — the exact-match-only version of this same idea)
- First and Last Position of Element in Sorted Array (Module 7 #3 — two lower-bound-style searches combined)
