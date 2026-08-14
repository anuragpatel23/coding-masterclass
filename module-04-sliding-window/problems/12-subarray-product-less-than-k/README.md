# 12. Subarray Product Less Than K

**Difficulty:** Medium
**Pattern:** Variable-Size Sliding Window (Counting Variant)
**LeetCode:** https://leetcode.com/problems/subarray-product-less-than-k/

## Problem Summary
Given an array of positive integers and an integer `k`, count the number of contiguous subarrays where the product of all elements is strictly less than `k`.

## Example
```
Input:  nums = [10,5,2,6], k = 100
Output: 8
```

## Pattern Recognition
This isn't asking for the *longest* or *shortest* window — it's asking to **count every valid window**. The trick: once you have a valid window `[left, right]` (product < k), every subarray ending at `right` and starting anywhere from `left` to `right` is also valid (since removing elements from the left can only shrink the product further, for positive integers). That means each `right` position contributes exactly `(right - left + 1)` new valid subarrays — no need to enumerate them individually.

## Approach 1: Brute Force
For every starting index, extend rightward multiplying into a running product, counting each valid extension, and stopping once the product reaches `k` (since it can only grow further from there, given all-positive integers).

- **Time:** O(n^2)
- **Space:** O(1)

## Approach 2: Optimized (Variable Window + Counting Trick)
Expand the window's right edge, multiplying into a running product. While the product is `>= k`, shrink from the left, dividing it back down. After each shrink, add `(right - left + 1)` to the running count — that's the number of valid subarrays ending exactly at `right`.

- **Time:** O(n)
- **Space:** O(1)

## Dry Run
`nums = [10,5,2,6]`, `k = 100`

| right | num | product | shrink? | count added | running count |
|---|---|---|---|---|---|
| 0 | 10 | 10 | no | 0-0+1=1 | 1 |
| 1 | 5 | 50 | no | 1-0+1=2 | 3 |
| 2 | 2 | 100 | yes: divide by 10, product=10, left=1 | 2-1+1=2 | 5 |
| 3 | 6 | 60 | no | 3-1+1=3 | 8 |

Result: **8**

## Edge Cases
- `k <= 1` -> since all elements are positive integers (minimum value 1), no product can ever be strictly less than 1 — return `0` immediately as a guard
- A single element already `>= k` -> it contributes 0 valid subarrays of its own, and the window correctly shrinks past it
- Every subarray is valid (product of the whole array still `< k`) -> the count accumulates the maximum possible, `n(n+1)/2`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) |
| Optimized (variable window + counting) | O(n) | O(1) |

## Related Problems / Pattern Family
- Count Number of Nice Subarrays (Module 4 #13 — a related counting technique using "at most K" subtraction)
- Minimum Size Subarray Sum (Module 4 #8 — the same variable-window mechanics, different objective)
