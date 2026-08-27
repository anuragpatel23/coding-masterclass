# 7. Next Greater Element II

**Difficulty:** Medium
**Pattern:** Monotonic Stack (Circular Array)
**LeetCode:** https://leetcode.com/problems/next-greater-element-ii/

## Problem Summary
Given a **circular** array, find the next greater element for every position — where "next" can wrap around from the end of the array back to the beginning, but only wraps once.

## Example
```
Input:  nums = [1,2,1]
Output: [2,-1,2]
```

## Pattern Recognition
Same monotonic stack idea as Daily Temperatures and Next Greater Element I, with one twist: the search needs to be able to "see" elements that wrap around to the start of the array. The standard trick for simulating circularity without actually duplicating the array: iterate through the indices **twice** (using `i % n`), but only push each index onto the stack during the *first* pass — the second pass exists purely to give earlier indices a chance to find a match that's physically located after them but logically wraps around.

## Approach 1: Brute Force
For each index, scan up to `n-1` positions forward (wrapping with modulo) for a greater element.

- **Time:** O(n^2)
- **Space:** O(1) extra

## Approach 2: Optimized (Monotonic Stack, Two Passes Over Indices)
Walk `i` from `0` to `2n - 1`, always working with `idx = i % n`. Use the same monotonic-decreasing-stack resolution as Daily Temperatures: while the current value is bigger than the value at the index on top of the stack, pop it and record the result. Only push `idx` onto the stack during the first pass (`i < n`) — by the second pass, every index has already had its fair chance to be pushed, and the second pass just gives them a chance to be *resolved* by wraparound values.

- **Time:** O(n) — each index is pushed once and popped at most once, despite the "2n" iteration range
- **Space:** O(n)

## Dry Run
`nums = [1,2,1]`, iterate `i = 0..5` (`2n=6`), `idx = i % 3`

| i | idx | value | stack action |
|---|---|---|---|
| 0 | 0 | 1 | push 0 |
| 1 | 1 | 2 | 2>1: pop0,result[0]=2. push 1 |
| 2 | 2 | 1 | push 2 |
| 3 | 0 | 1 | (i>=n, don't push) 1 is not > nums[stack top=2]=1, no pop |
| 4 | 1 | 2 | 2>1(nums[2]): pop 2, result[2]=2. stack now [1] (from i=1), 2>2? no (nums[1]=2, equal not greater), stop |
| 5 | 2 | 1 | (no push, second pass) |

Result: **[2, -1, 2]** (index 1's value 2 never finds anything strictly greater, stays -1)

## Edge Cases
- All elements equal -> no element is ever *strictly* greater than another, every result is `-1`
- Single element -> no other element exists to compare against (even wrapping to itself doesn't count), result is `[-1]`
- Strictly decreasing array that wraps to a larger first element -> the wraparound is exactly what makes later (smaller) elements resolve against the array's start

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) extra |
| Optimized (monotonic stack, circular) | O(n) | O(n) |

## Related Problems / Pattern Family
- Next Greater Element I (Module 6 #6 — the non-circular version of this exact technique)
- Daily Temperatures (Module 6 #5 — the same core monotonic stack resolution logic)
