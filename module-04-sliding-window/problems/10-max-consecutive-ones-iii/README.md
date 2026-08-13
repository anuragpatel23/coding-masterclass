# 10. Max Consecutive Ones III

**Difficulty:** Medium
**Pattern:** Variable-Size Sliding Window
**LeetCode:** https://leetcode.com/problems/max-consecutive-ones-iii/

## Problem Summary
Given a binary array and an integer `k`, you may flip at most `k` zeros to ones. Return the length of the longest contiguous run of ones achievable.

## Example
```
Input:  nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
```

## Pattern Recognition
This is Longest Repeating Character Replacement (#9), stripped down to a two-symbol alphabet: a window is valid as long as it contains **at most `k` zeros** (each one representing an allowed flip). Track the zero count in the window; shrink whenever it exceeds `k`.

## Approach 1: Brute Force
For every starting index, extend rightward counting zeros, stopping once the zero count exceeds `k`.

- **Time:** O(n^2)
- **Space:** O(1)

## Approach 2: Optimized (Variable Window, Track Zero Count)
Expand the window's right edge, incrementing a zero counter whenever a `0` enters. While the zero count exceeds `k`, shrink from the left, decrementing the counter whenever a `0` leaves.

- **Time:** O(n)
- **Space:** O(1)

## Dry Run
`nums = [1,1,1,0,0,0,1,1,1,1,0]`, `k = 2`

| right | value | zeros | action | window length |
|---|---|---|---|---|
| 0-2 | 1,1,1 | 0 | ok | 3 |
| 3 | 0 | 1 | ok | 4 |
| 4 | 0 | 2 | ok | 5 |
| 5 | 0 | 3 | shrink left until zeros<=2 | window becomes [0,0,1,1,1] region, len5 |
| 6-9 | 1,1,1,1 | 2 (stays) | ok, window grows | up to length 6 |
| 10 | 0 | 3 | shrink again | final valid length found is 6 |

Result: **6**

## Edge Cases
- `k >= total number of zeros in the array` -> the entire array becomes valid, answer is the full length
- `k = 0` -> the answer is just the longest existing run of ones with no flips at all
- All zeros with `k` large enough to cover them all -> the whole array becomes one valid run

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) |
| Optimized (variable window) | O(n) | O(1) |

## Related Problems / Pattern Family
- Longest Repeating Character Replacement (Module 4 #9 — the general-alphabet version of this exact idea)
- Longest Subarray of 1's After Deleting One Element (Module 4 #11 — a close variant requiring exactly one deletion)
