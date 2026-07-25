# 10. Missing Number

**Difficulty:** Easy
**Pattern:** Math — Gauss Sum *(XOR is a valid alternative, noted below)*
**LeetCode:** https://leetcode.com/problems/missing-number/

## Problem Summary
Given an array containing `n` distinct numbers taken from the range `[0, n]`, find the one number in that range that is missing from the array.

## Example
```
Input:  nums = [3, 0, 1]
Output: 2
```

## Pattern Recognition
Whenever a problem guarantees a **known, complete range of numbers** and asks what's missing (or duplicated), check if a closed-form formula sidesteps the need for a set entirely. The sum of `0..n` has a famous formula — that's your fastest path here.

## Approach 1: Brute Force
Put every number in a `HashSet`, then check each number from `0` to `n` for membership.

- **Time:** O(n)
- **Space:** O(n)
- **Why it's not good enough:** it's already linear time, so — like problem 9 — this is a space optimization opportunity, not a speed one. Still very much worth knowing for interviews, since "can you do this in O(1) space" is a common interviewer follow-up.

## Approach 2: Optimized (Gauss Sum Formula)
If nothing were missing, the numbers `0` through `n` would sum to `n(n+1)/2` (Gauss's formula). Compute that expected sum, subtract the actual sum of the array, and whatever's left over is the missing number.

- **Time:** O(n)
- **Space:** O(1)

*Alternative:* XOR every index and every value together. Every number that appears in both the index range and the array cancels itself out via `a ^ a = 0`; the missing number is whatever survives. Same complexity, useful if you're worried about integer overflow on the sum for very large `n`.

## Dry Run
`nums = [3, 0, 1]`, `n = 3`

- Expected sum = `3·4/2 = 6`
- Actual sum = `3 + 0 + 1 = 4`
- Missing = `6 - 4 = 2`

Result: **2**

## Edge Cases
- Missing number is `0` → still handled correctly, since it's just another value in the sum comparison
- Missing number is `n` (the largest possible value) → also handled the same way
- Array of length `0` (only possible value is `0` missing from range `[0,0]`) → expected sum is `0`, actual sum is `0`, result is `0`... but with `n=0` and an empty array, the loop doesn't run and `expectedSum - actualSum = 0`, correctly returning `0`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (HashSet) | O(n) | O(n) |
| Optimized (Gauss Sum) | O(n) | O(1) |
| Optimized (XOR) | O(n) | O(1) |

## Related Problems / Pattern Family
- Find All Numbers Disappeared in an Array (Module 1 #9 — index-marking approach to a similar idea)
- Single Number (Module 19 — Bit Manipulation, same XOR-cancellation trick)
