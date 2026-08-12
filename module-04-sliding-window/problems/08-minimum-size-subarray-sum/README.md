# 8. Minimum Size Subarray Sum

**Difficulty:** Medium
**Pattern:** Variable-Size Sliding Window
**LeetCode:** https://leetcode.com/problems/minimum-size-subarray-sum/

## Problem Summary
Given an array of positive integers and a target, find the length of the shortest contiguous subarray whose sum is greater than or equal to the target. Return `0` if no such subarray exists.

## Example
```
Input:  target = 7, nums = [2,3,1,2,4,3]
Output: 2        (subarray [4,3])
```

## Pattern Recognition
This flips the usual "longest valid window" question into a **shortest** valid window question — but the mechanics are the same variable window shape. Grow the window until it satisfies the condition (sum >= target), then — unlike the "longest" problems — actively try to shrink it as much as possible while it *still* satisfies the condition, recording the minimum length along the way.

## Approach 1: Brute Force
For every starting index, extend rightward accumulating the sum, stopping (and recording the length) the moment the sum first reaches the target.

- **Time:** O(n^2)
- **Space:** O(1)

## Approach 2: Optimized (Variable Window, Shrink While Valid)
Expand the window's right edge, adding to a running sum. The moment the sum is `>= target`, that window is valid — record its length, then **greedily shrink from the left** for as long as the sum stays `>= target`, recording each smaller valid length along the way.

- **Time:** O(n) — each element is added once and removed at most once
- **Space:** O(1)

## Dry Run
`target = 7`, `nums = [2,3,1,2,4,3]`

| right | num | sum | shrink while sum>=7 | minLen so far |
|---|---|---|---|---|
| 0 | 2 | 2 | no | - |
| 1 | 3 | 5 | no | - |
| 2 | 1 | 6 | no | - |
| 3 | 2 | 8 | yes: remove 2(left=0)->sum=6, stop shrinking | length 4 (indices0-3) -> minLen=4 |
| 4 | 4 | 6+4=10 | yes: remove 3(left=1)->7,still>=7 remove1(left=2)->6,stop | minLen updates to 2 (indices3-4, [2,4]) |
| 5 | 3 | 6+3=9 | yes: remove2(left=3)->7,still>=7,remove4(left=4)->3,stop | minLen updates to... checking length at each valid shrink step |

*(Each shrink step records a candidate length; the shortest one found across the whole scan is the answer.)*

Result: **2**

## Edge Cases
- No subarray reaches the target at all, e.g. target larger than the sum of the whole array -> return `0`
- The entire array is needed to reach the target -> minLen ends up being the full array length
- A single element already meets the target -> minLen becomes `1` immediately

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) |
| Optimized (variable window) | O(n) | O(1) |

## Related Problems / Pattern Family
- Subarray Product Less Than K (Module 4 #12 — a related counting variant of shrink-while-valid)
- Max Consecutive Ones III (Module 4 #10 — a different validity condition, same shrink-while-invalid shape)
