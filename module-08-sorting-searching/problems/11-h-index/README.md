# 11. H-Index

**Difficulty:** Medium
**Pattern:** Sort + Linear Scan
**LeetCode:** https://leetcode.com/problems/h-index/

## Problem Summary
Given an array of citation counts for a researcher's papers, find their h-index: the largest `h` such that at least `h` papers have `h` or more citations each.

## Example
```
Input:  citations = [3,0,6,1,5]
Output: 3        (3 papers have >= 3 citations: 3, 6, 5)
```

## Pattern Recognition
Checking every possible `h` value directly against the raw array is wasteful. Sorting first turns the question into something a single pass can answer directly: once citations are sorted, the number of papers with at least `X` citations from any position onward is simply "how many elements remain," which is just `n - index`.

## Approach 1: Brute Force
For every candidate `h` from `n` down to `0`, count how many papers have at least `h` citations; return the first `h` where that count is `>= h`.

- **Time:** O(n^2) — n candidates, each requiring an O(n) count
- **Space:** O(1)

## Approach 2: Optimized (Sort, Then Single Pass)
Sort citations ascending. At each index `i`, the number of papers with citations `>= sorted[i]` is exactly `n - i` (everything from `i` to the end). Find the first index where `sorted[i] >= (n - i)` — that value, `n - i`, is the h-index.

- **Time:** O(n log n) — dominated by the sort
- **Space:** O(n) for the sorted copy

## Dry Run
`citations = [3,0,6,1,5]` -> sorted: `[0,1,3,5,6]`, n=5

| i | sorted[i] | papers with >= this many citations (n-i) | sorted[i] >= (n-i)? |
|---|---|---|---|
| 0 | 0 | 5 | no |
| 1 | 1 | 4 | no |
| 2 | 3 | 3 | **yes** -> return 3 |

Result: **3**

## Edge Cases
- All citations are `0` -> h-index is `0`, since no paper has even 1 citation
- Every paper has the same very high citation count -> h-index caps at the total number of papers, `n`
- A single paper -> h-index is `min(citations[0], 1)`, correctly handled by the same general logic

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) |
| Optimized (sort + linear scan) | O(n log n) | O(n) |

## Related Problems / Pattern Family
- H-Index II (a follow-up requiring O(log n), solvable with binary search once the array is already sorted)
