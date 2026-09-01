# 3. First and Last Position of Element in Sorted Array

**Difficulty:** Medium
**Pattern:** Binary Search (Find Both Boundaries)
**LeetCode:** https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

## Problem Summary
Given a sorted array that may contain duplicates, find the first and last index of a given target. Return `[-1,-1]` if it's not present.

## Example
```
Input:  nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
```

## Pattern Recognition
"Find the boundary of a run of equal values" is binary search applied twice, with a small twist each time: when you find a match while searching for the **first** occurrence, don't stop — keep searching to the *left* for an even earlier match. When searching for the **last** occurrence, keep searching to the *right* instead.

## Approach 1: Brute Force
Scan linearly, tracking the first and last index where the value equals the target.

- **Time:** O(n)
- **Space:** O(1)

## Approach 2: Optimized (Two Directed Binary Searches)
Run binary search twice with a shared helper, parameterized by direction:
- **Finding the first occurrence:** on a match, record it, then continue searching the **left** half (`right = mid - 1`) in case an earlier match exists.
- **Finding the last occurrence:** on a match, record it, then continue searching the **right** half (`left = mid + 1`) in case a later match exists.

- **Time:** O(log n) — two independent binary searches
- **Space:** O(1)

## Dry Run
`nums = [5,7,7,8,8,10]`, `target = 8`

**Finding first occurrence:**
| left | right | mid | nums[mid] | action |
|---|---|---|---|---|
| 0 | 5 | 2 | 7 | too small -> left=3 |
| 3 | 5 | 4 | 8 | match! record 4, keep searching left -> right=3 |
| 3 | 3 | 3 | 8 | match! record 3, keep searching left -> right=2 |

Loop ends (`left > right`). First occurrence = **3**.

**Finding last occurrence** (symmetric, searching right after each match) converges to **4**.

Result: **[3, 4]**

## Edge Cases
- Target not present at all -> both searches return `-1`, giving `[-1,-1]`
- Target appears exactly once -> both searches converge to the same single index
- Entire array is the target value -> first search converges to index `0`, last search converges to index `n-1`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n) | O(1) |
| Optimized (two directed binary searches) | O(log n) | O(1) |

## Related Problems / Pattern Family
- Search Insert Position (Module 7 #2 — the single-boundary version of this same idea)
- Binary Search (Module 7 #1 — the foundation both boundary searches are built on)
