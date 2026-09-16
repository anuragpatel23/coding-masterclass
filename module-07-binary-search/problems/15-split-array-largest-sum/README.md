# 15. Split Array Largest Sum

**Difficulty:** Hard
**Pattern:** Binary Search on the Answer Space
**LeetCode:** https://leetcode.com/problems/split-array-largest-sum/

## Problem Summary
Given an array and an integer `m`, split the array into `m` contiguous, non-empty subarrays to **minimize the largest sum** among them. Return that minimized largest sum.

## Example
```
Input:  nums = [7,2,5,10,8], m = 2
Output: 18        (split as [7,2,5] and [10,8]: max(14,18) = 18)
```

## Pattern Recognition
This is the module's second Hard capstone because it closes the loop on the "binary search on the answer" family (Koko Eating Bananas, Capacity To Ship Packages): the direct question ("what's the optimal split") is hard, but the indirect question ("can we split into `<= m` parts such that no part exceeds this candidate max sum") is easy to check greedily, and monotonic — exactly the conditions for binary searching the candidate answer directly.

## Approach 1: Brute Force (Linear Scan Over Candidate Answers)
Instead of binary searching the candidate max-sum, scan every integer from `max(nums)` (the true minimum possible answer — no split can have a smaller max than the single largest element) up to `sum(nums)`, checking feasibility at each one, stopping at the first one that works.

- **Time:** O(sum * n) — potentially very many candidates checked one at a time
- **Space:** O(1)

## Approach 2: Optimized (Binary Search on the Candidate Max Sum)
Same feasibility check as the brute force (greedily pack elements into a subarray until the next one would exceed the candidate max, then start a new subarray; count subarrays needed), but binary search the candidate range `[max(nums), sum(nums)]` instead of scanning it linearly.

- **Time:** O(n log(sum)) — O(log(sum - max)) candidates tried, each with an O(n) feasibility check
- **Space:** O(1)

## Dry Run
`nums = [7,2,5,10,8]`, `m = 2`, search range `[10, 32]`

| left | right | mid | subarrays needed | <= m(2)? | action |
|---|---|---|---|---|---|
| 10 | 32 | 21 | greedily: [7,2,5],[10,8] -> 2 subarrays | yes | right=21 |
| 10 | 21 | 15 | [7,2,5],[10],[8] -> 3 subarrays | no | left=16 |
| 16 | 21 | 18 | [7,2,5],[10,8] -> 2 subarrays | yes | right=18 |
| 16 | 18 | 17 | [7,2,5],[10],[8] -> 3 subarrays | no | left=18 |

`left == right == 18`.

Result: **18**

## Edge Cases
- `m = 1` -> the entire array must be one subarray, forcing the answer to `sum(nums)`
- `m` equal to the array's length -> every element becomes its own subarray, forcing the answer to `max(nums)`
- All elements equal -> the optimal split divides them as evenly as possible by count

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (linear scan of candidates) | O(sum * n) | O(1) |
| Optimized (binary search on answer) | O(n log(sum)) | O(1) |

## Related Problems / Pattern Family
- Koko Eating Bananas (Module 7 #9 — the same binary-search-on-answer skeleton)
- Capacity To Ship Packages Within D Days (Module 7 #10 — nearly identical feasibility-check structure)
