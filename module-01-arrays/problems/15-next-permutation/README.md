# 15. Next Permutation

**Difficulty:** Medium
**Pattern:** Permutation Algorithm (Pivot–Successor–Reverse)
**LeetCode:** https://leetcode.com/problems/next-permutation/

## Problem Summary
Given an array of numbers, rearrange it into the **next lexicographically greater permutation**. If no greater permutation exists (the array is in fully descending order), rearrange it into the lowest possible order (ascending) instead. Must be done in-place with O(1) extra space.

## Example
```
Input:  nums = [1,2,3]
Output: [1,3,2]
```

## Pattern Recognition
"Next permutation" is a named algorithm — you either know the trick or you don't, so the real skill being tested is recognizing the phrase and recalling the three-step recipe below. The intuition: to get the *next* larger arrangement, you want to change as little as possible about the *rightmost* end of the array, and change it by the *smallest* possible amount.

## Approach 1: Conceptual Brute Force
Generate every permutation of the array, sort them lexicographically, find the current one, and return the next one in that sorted order.

- **Time:** O(n! · n log n) — factorial, completely impractical beyond tiny inputs
- **Space:** O(n! · n) to store every permutation
- **Why it's not good enough:** this is the "understand the definition literally" approach. It's useful for confirming you understand *what* the answer should be on a tiny example, but it is never viable code for an actual input size — the whole point of this problem is finding the O(n) shortcut below.

## Approach 2: Optimized (Pivot, Successor, Reverse)
1. **Find the pivot:** scan from the right for the first index `i` where `nums[i] < nums[i+1]` (i.e., the first place, from the right, where the sequence isn't in descending order). This is the digit that *can* be increased.
2. **If a pivot exists:** scan from the right again for the first index `j > i` where `nums[j] > nums[i]` (the smallest value on the right that's still bigger than the pivot), and swap `nums[i]` and `nums[j]`.
3. **Reverse the suffix** after the pivot's original position. Because everything after the pivot was in descending order, reversing it puts it in ascending order — the smallest possible arrangement for that suffix, which is exactly what "next" (smallest increase) requires.

If no pivot is found in step 1, the entire array is descending — it's already the largest permutation, so step 3 alone (reverse everything) wraps it around to the smallest.

- **Time:** O(n)
- **Space:** O(1) extra

## Dry Run
`nums = [1,3,2]`

1. Find pivot: scanning from the right, `nums[0]=1 < nums[1]=3` → pivot index = `0`.
2. Find successor: scanning from the right for the first value `> nums[0]=1` → `nums[2]=2` qualifies → successor index = `2`.
3. Swap pivot and successor: `[2,3,1]`.
4. Reverse everything after the pivot (indices 1 to 2): `[3,1]` → `[1,3]`.

Result: **[2,1,3]**

## Edge Cases
- Fully descending array, e.g. `[3,2,1]` → no pivot found → reverse the entire array → `[1,2,3]` (wraps to the smallest permutation)
- Fully ascending array, e.g. `[1,2,3]` → pivot found at the second-to-last index → smallest possible next arrangement, `[1,3,2]`
- Array with duplicate values, e.g. `[1,1,5]` → the "successor" search (`nums[j] > nums[i]`, strictly greater) correctly skips over equal values

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Conceptual Brute Force | O(n!) | O(n!) |
| Optimized (Pivot-Successor-Reverse) | O(n) | O(1) extra |

## Related Problems / Pattern Family
- Permutations / Permutations II (Module 10 — Recursion & Backtracking, generates all permutations rather than just the next one)
- Previous Permutation (mirror-image algorithm)
