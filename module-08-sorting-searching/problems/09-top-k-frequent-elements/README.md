# 9. Top K Frequent Elements

**Difficulty:** Medium
**Pattern:** Bucket Sort
**LeetCode:** https://leetcode.com/problems/top-k-frequent-elements/

## Problem Summary
Given an integer array, return the `k` most frequently occurring elements.

## Example
```
Input:  nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
```

## Pattern Recognition
Sorting by frequency seems natural, but it's more than necessary: frequency counts are bounded by the array's own length (`0` to `n`), which means you can use **bucket sort** — an array of buckets indexed by frequency itself — to sort in linear time instead of `O(n log n)`.

## Approach 1: Brute Force
Count frequencies with a hashmap, then sort the distinct elements by frequency (descending) and take the first `k`.

- **Time:** O(n log n) — dominated by the sort
- **Space:** O(n) — the frequency map

## Approach 2: Optimized (Bucket Sort by Frequency)
Count frequencies with a hashmap. Create an array of buckets indexed `0` to `n` (the maximum possible frequency is `n`), where `buckets[f]` holds every number that appeared exactly `f` times. Walk the buckets from highest frequency to lowest, collecting numbers until you've gathered `k` of them.

- **Time:** O(n) — counting is O(n), bucket placement is O(n), and reading off the top k never revisits more than n total elements
- **Space:** O(n) — the frequency map and buckets

## Dry Run
`nums = [1,1,1,2,2,3]`, `k = 2`

Frequencies: `{1:3, 2:2, 3:1}`.

Buckets (indexed by frequency): `bucket[1] = [3]`, `bucket[2] = [2]`, `bucket[3] = [1]`.

Walk from highest frequency (`6`, the array length) down to `0`, collecting until `k=2` numbers are found: `bucket[3] = [1]` -> collect `1`. `bucket[2] = [2]` -> collect `2`. Now have 2 -> stop.

Result: **[1, 2]**

## Edge Cases
- `k` equal to the number of distinct elements -> every distinct element gets collected, in descending frequency order
- All elements have the same frequency -> the specific order among them isn't well-defined by frequency alone, but any valid selection of `k` of them is an acceptable answer per the problem's convention
- A single element repeated `n` times, `k=1` -> trivially returns that one element

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (sort by frequency) | O(n log n) | O(n) |
| Optimized (bucket sort) | O(n) | O(n) |

## Related Problems / Pattern Family
- Kth Largest Element in an Array (Module 8 #8 — a related "avoid full sorting" problem, solved with quickselect instead)
- Sort Characters By Frequency (the same bucket-sort-by-frequency idea, applied to a string)
