# 1. Two Sum II - Input Array Is Sorted

**Difficulty:** Easy
**Pattern:** Opposite-Direction Two Pointers
**LeetCode:** https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

## Problem Summary
Given a 1-indexed array sorted in non-decreasing order, find two numbers that add up to a target and return their 1-indexed positions. Exactly one solution exists, and you can't use the same element twice.

## Example
```
Input:  numbers = [2,7,11,15], target = 9
Output: [1,2]        (numbers[0] + numbers[1] = 2 + 7 = 9, returned as 1-indexed)
```

## Pattern Recognition
This is the exact same problem as Two Sum (Module 1 #1) — except now the array is **sorted**, and that single fact changes everything. Sortedness means you can reason about direction: if the sum of your current pair is too small, you know moving the left pointer right will only increase it; if too big, moving the right pointer left will only decrease it. That monotonic relationship is what lets two pointers replace a hashmap here.

## Approach 1: Brute Force
Check every pair, same as unsorted Two Sum, ignoring the fact that the array is sorted.

- **Time:** O(n^2)
- **Space:** O(1)
- **Why it's not good enough:** it throws away the one piece of extra information the problem is handing you for free — sortedness — which is specifically what makes an O(n) solution possible here without any extra space.

*(A hashmap approach, identical to Module 1's Two Sum, also works here in O(n) time / O(n) space — but it doesn't exploit the sorted property either, and uses space the pointer approach doesn't need.)*

## Approach 2: Optimized (Opposite-Direction Two Pointers)
Place `left` at the start and `right` at the end. Compute their sum:
- If the sum equals the target, you're done.
- If the sum is **too small**, the only way to increase it is to move `left` rightward (toward larger values).
- If the sum is **too large**, the only way to decrease it is to move `right` leftward (toward smaller values).

Because the array is sorted, each move is guaranteed to be a step in the right direction — you never need to backtrack.

- **Time:** O(n)
- **Space:** O(1)

## Dry Run
`numbers = [2,7,11,15]`, `target = 9`

| left | right | sum | comparison | action |
|---|---|---|---|---|
| 0 | 3 | 2+15=17 | too big | right-- |
| 0 | 2 | 2+11=13 | too big | right-- |
| 0 | 1 | 2+7=9 | match! | return [1, 2] (1-indexed) |

Result: **[1, 2]**

## Edge Cases
- The two numbers are adjacent, e.g. at indices 0 and 1 — still found correctly on the very first comparison in some inputs
- Negative numbers mixed with positive ones — sortedness still holds, so the same monotonic logic applies without any special casing
- Duplicate values where both copies form the answer, e.g. `[3,3], target=6` — works correctly since `left` and `right` start at different indices

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) |
| HashMap (Module 1 style) | O(n) | O(n) |
| Optimized (two pointers) | O(n) | O(1) |

## Related Problems / Pattern Family
- Two Sum (Module 1 #1 — the unsorted version, solved with hashing instead)
- 3Sum (Module 3 #8 — fixes one element, then applies this exact two-pointer technique to the rest)
- Container With Most Water (Module 3 #7 — a different opposite-direction two-pointer problem)
