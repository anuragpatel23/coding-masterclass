# 15. Sliding Window Maximum

**Difficulty:** Hard
**Pattern:** Fixed-Size Sliding Window + Monotonic Deque
**LeetCode:** https://leetcode.com/problems/sliding-window-maximum/

## Problem Summary
Given an array and a window size `k`, return an array of the maximum value in each window as it slides from the start of the array to the end.

## Example
```
Input:  nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
```

## Pattern Recognition
This is the module's other capstone because a naive fixed window (like problem #1) breaks down here: re-scanning all `k` elements for the max at every position is O(n*k), and unlike a running *sum*, you can't cheaply "undo" a maximum when an element leaves the window. The fix is a new structure: a **monotonic deque** that stores candidate maximums in decreasing order, so the true maximum for the current window is always sitting at the front, ready in O(1).

## Approach 1: Brute Force
For each window position, scan all `k` elements to find the maximum.

- **Time:** O(n * k)
- **Space:** O(1) extra (excluding output)

## Approach 2: Optimized (Monotonic Decreasing Deque of Indices)
Maintain a deque of **indices**, kept in an order where their corresponding values are strictly decreasing from front to back. For each new index `i`:
1. Remove indices from the **front** that have fallen out of the current window (`index <= i - k`).
2. Remove indices from the **back** whose values are smaller than `nums[i]` — they can never be the maximum again, since `nums[i]` is both later *and* bigger.
3. Add `i` to the back.
4. Once the window is fully formed (`i >= k - 1`), the front of the deque holds the index of the current window's maximum.

- **Time:** O(n) — each index is added to and removed from the deque at most once, total
- **Space:** O(k) — the deque never holds more than the window size

## Dry Run
`nums = [1,3,-1,-3,5,3,6,7]`, `k = 3`

| i | value | deque after updates (indices, values shown) | window max (once i>=2) |
|---|---|---|---|
| 0 | 1 | [0(1)] | - |
| 1 | 3 | pop 0 (1<3) -> [1(3)] | - |
| 2 | -1 | [1(3), 2(-1)] | 3 |
| 3 | -3 | [1(3), 2(-1), 3(-3)] | 3 |
| 4 | 5 | pop 3,2,1 (all < 5) -> [4(5)] | 5 |
| 5 | 3 | [4(5), 5(3)] | 5 |
| 6 | 6 | pop 5,4 (both < 6) -> [6(6)] | 6 |
| 7 | 7 | pop 6 (6<7) -> [7(7)] | 7 |

Result: **[3, 3, 5, 5, 6, 7]**

## Edge Cases
- `k = 1` -> every element is its own window's maximum, output equals the input
- `k` equal to the array length -> a single output value, the global maximum
- Strictly decreasing array -> the deque holds every index simultaneously (nothing ever gets popped from the back), degrading gracefully to O(k) deque size without breaking correctness

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n*k) | O(1) extra |
| Optimized (monotonic deque) | O(n) | O(k) |

## Related Problems / Pattern Family
- Maximum Sum Subarray of Size K (Module 4 #1 — a fixed window where the running value *can* be cheaply updated, unlike max)
- Daily Temperatures (Module 6 — Stacks & Queues, the monotonic-structure idea applied with a stack instead of a deque)
