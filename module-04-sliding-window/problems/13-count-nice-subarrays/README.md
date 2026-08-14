# 13. Count Number of Nice Subarrays

**Difficulty:** Medium
**Pattern:** Variable-Size Sliding Window ("Exactly K" via "At Most K" Subtraction)
**LeetCode:** https://leetcode.com/problems/count-number-of-nice-subarrays/

## Problem Summary
Given an array and an integer `k`, a subarray is "nice" if it contains **exactly** `k` odd numbers. Return the count of nice subarrays.

## Example
```
Input:  nums = [1,1,2,1,1], k = 3
Output: 2
```

## Pattern Recognition
Sliding windows are naturally good at "at most" conditions (as seen in problem #12) — they're awkward for "exactly" conditions, since a window can't cleanly track "exactly K" while growing and shrinking. The fix is a classic trick: **exactly(K) = atMost(K) - atMost(K-1)**. If you can count subarrays with at most K odd numbers, subtracting the count with at most K-1 odd numbers leaves precisely those with *exactly* K.

## Approach 1: Brute Force
For every starting index, extend rightward counting odd numbers, incrementing a running count whenever the odd-count equals exactly `k`, and stopping once it exceeds `k`.

- **Time:** O(n^2)
- **Space:** O(1)

## Approach 2: Optimized (At-Most-K Sliding Window, Applied Twice)
Implement a helper `atMostK(nums, k)` using the exact same counting-window technique as Subarray Product Less Than K (#12), but counting odd numbers instead of tracking a product. Then compute `atMostK(nums, k) - atMostK(nums, k - 1)`.

- **Time:** O(n) — two linear passes
- **Space:** O(1)

## Dry Run
`nums = [1,1,2,1,1]`, `k = 3`

`atMostK(nums, 3)`: every window with at most 3 odd numbers — this ends up counting 14 subarrays (most of the array qualifies, since there are only 4 odd numbers total).

`atMostK(nums, 2)`: every window with at most 2 odd numbers — this counts 12 subarrays.

`exactly(3) = 14 - 12 = ` **2**

## Edge Cases
- `k = 0` -> `atMostK(nums, -1)` should return `0` (no subarray can have a negative odd-count), so the helper needs a guard for `k < 0`
- No subarray has exactly `k` odd numbers -> both `atMostK` calls end up counting the same total, and the subtraction correctly yields `0`
- All numbers even -> the only "nice" count possible is for `k = 0` (every subarray qualifies); for any `k > 0`, the answer is `0`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) |
| Optimized (atMost(k) - atMost(k-1)) | O(n) | O(1) |

## Related Problems / Pattern Family
- Subarray Product Less Than K (Module 4 #12 — the counting-window building block this technique reuses)
- Subarrays with K Different Integers (the same atMost(k)-atMost(k-1) trick, applied to distinct-value counting)
