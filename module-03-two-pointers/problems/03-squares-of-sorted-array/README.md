# 3. Squares of a Sorted Array

**Difficulty:** Easy
**Pattern:** Opposite-Direction Two Pointers (Merge From the Ends)
**LeetCode:** https://leetcode.com/problems/squares-of-a-sorted-array/

## Problem Summary
Given an integer array sorted in non-decreasing order (which may include negative numbers), return an array of the squares of each number, also sorted in non-decreasing order.

## Example
```
Input:  nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
```

## Pattern Recognition
Negative numbers mean the *largest* squares can come from either end of the sorted array — a very negative number squares to something large, and so does a very positive one. Whenever the largest (or smallest) resulting values could come from **either extreme** of a sorted sequence, that's a signal to compare both ends with two pointers and consume the larger contender at each step.

## Approach 1: Brute Force
Square every element, then sort the resulting array.

- **Time:** O(n log n) — dominated by the sort
- **Space:** O(n) (or O(1) extra if sorting the required output array in place, but the sort itself still costs O(n log n) time)
- **Why it's not good enough:** you're throwing away the fact that the input was already sorted — sorting the squares from scratch ignores that structure entirely.

## Approach 2: Optimized (Two Pointers, Fill From the Back)
Place `left` at the start and `right` at the end. At each step, compare `nums[left]^2` and `nums[right]^2` — whichever is **larger** belongs at the current highest unfilled position in the result, since you're building the result from the largest value down to the smallest. Place it, then move that pointer inward.

- **Time:** O(n) — a single pass
- **Space:** O(n) for the required output array, O(1) extra beyond it

## Dry Run
`nums = [-4,-1,0,3,10]`, result has 5 slots, fill from index 4 down to 0

| left | right | left^2 | right^2 | larger | placed at | new pointer |
|---|---|---|---|---|---|---|
| 0 | 4 | 16 | 100 | right (100) | index 4 | right-- |
| 0 | 3 | 16 | 9 | left (16) | index 3 | left++ |
| 1 | 3 | 1 | 9 | right (9) | index 2 | right-- |
| 1 | 2 | 1 | 0 | left (1) | index 1 | left++ |
| 2 | 2 | 0 | 0 | either (0) | index 0 | done |

Result: **[0, 1, 9, 16, 100]**

## Edge Cases
- All non-negative input, e.g. `[0,1,2,3]` -> the right pointer always wins the comparison, effectively degrading gracefully to "square in place" without breaking
- All negative input, e.g. `[-5,-3,-1]` -> the left pointer always wins, since it starts at the most negative (largest magnitude) value
- Array containing a `0` -> handled naturally, no special casing needed

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (square + sort) | O(n log n) | O(n) |
| Optimized (two pointers) | O(n) | O(n) output, O(1) extra |

## Related Problems / Pattern Family
- Merge Sorted Array (Module 8 — Sorting & Searching, a very similar "merge from the correct end" idea)
- Two Sum II (Module 3 #1 — opposite-direction pointers on sorted data, different goal)
