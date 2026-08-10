# 1. Maximum Sum Subarray of Size K

**Difficulty:** Easy
**Pattern:** Fixed-Size Sliding Window
**LeetCode:** (classic pattern problem, commonly given as "Maximum Sum Subarray of Size K")

## Problem Summary
Given an array of integers and an integer `k`, find the maximum sum of any contiguous subarray of exactly size `k`.

## Example
```
Input:  nums = [2,1,5,1,3,2], k = 3
Output: 9        (subarray [5,1,3])
```

## Pattern Recognition
"Contiguous subarray of a **fixed** size" is the simplest possible sliding window: as the window slides one position to the right, it loses exactly one element (the leftmost) and gains exactly one element (the new rightmost). If you already have the sum for the current window, you don't need to re-add every element for the next one — just subtract what leaves and add what enters.

## Approach 1: Brute Force
For every possible starting index, sum up the `k` elements from scratch.

- **Time:** O(n * k) — a fresh O(k) sum computed at each of the n-k+1 starting positions
- **Space:** O(1)
- **Why it's not good enough:** consecutive windows overlap in all but two elements (one leaving, one entering) — recomputing the whole sum throws that overlap away.

## Approach 2: Optimized (Fixed-Size Sliding Window)
Compute the sum of the first window directly. For every subsequent position, update the running sum by subtracting the element leaving the window and adding the element entering it.

- **Time:** O(n) — one initial O(k) sum, then O(1) per slide
- **Space:** O(1)

## Dry Run
`nums = [2,1,5,1,3,2]`, `k = 3`

| window | sum | maxSum |
|---|---|---|
| [2,1,5] | 8 | 8 |
| [1,5,1] (8 - 2 + 1) | 7 | 8 |
| [5,1,3] (7 - 1 + 3) | 9 | 9 |
| [1,3,2] (9 - 5 + 2) | 6 | 9 |

Result: **9**

## Edge Cases
- `k` equal to the array's length -> exactly one window, its sum is the answer
- `k = 1` -> the maximum single element
- Array shorter than `k` -> no valid window exists (should be validated before running)

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n*k) | O(1) |
| Optimized (fixed window) | O(n) | O(1) |

## Related Problems / Pattern Family
- Maximum Average Subarray I (identical technique, different final operation)
- Repeated DNA Sequences (Module 4 #2 — a fixed window combined with hashing)
