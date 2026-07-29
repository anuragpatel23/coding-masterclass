# 1. Reverse String

**Difficulty:** Easy
**Pattern:** Two-Pointer Swap *(carried over from Module 1's in-place techniques)*
**LeetCode:** https://leetcode.com/problems/reverse-string/

## Problem Summary
Write a function that reverses a character array in-place, using O(1) extra space.

## Example
```
Input:  ['h','e','l','l','o']
Output: ['o','l','l','e','h']
```

## Pattern Recognition
"Reverse this sequence, in-place" is the most direct possible signal for two pointers converging from opposite ends. This is the simplest member of a pattern family you'll see again and again — Valid Palindrome (next problem) and Rotate Array (Module 1) are both built on this exact move.

## Approach 1: Brute Force
Build a new array by reading the input backward, then copy it back over the original.

- **Time:** O(n)
- **Space:** O(n) — the new array, which breaks the "in-place" requirement
- **Why it's not good enough:** functionally fine, but it does more work than necessary — you don't need a second array to reverse a sequence, only a way to swap pairs.

## Approach 2: Optimized (Two-Pointer Swap)
Place one pointer at the start, one at the end. Swap the characters they point to, then move both pointers toward the center. Stop when they meet or cross.

- **Time:** O(n) — you only ever touch each element once
- **Space:** O(1)

## Dry Run
`['h','e','l','l','o']`

| left | right | array after swap |
|---|---|---|
| 0 | 4 | ['o','e','l','l','h'] |
| 1 | 3 | ['o','l','l','e','h'] |
| 2 | 2 | loop ends (left >= right) |

Result: **['o','l','l','e','h']**

## Edge Cases
- Empty array → loop never runs, nothing to do
- Single character → `left == right` immediately, no swap needed
- Even-length array → pointers cross without ever being equal, which is fine since the loop condition is `left < right`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (new array) | O(n) | O(n) |
| Optimized (two-pointer swap) | O(n) | O(1) |

## Related Problems / Pattern Family
- Valid Palindrome (Module 2 #2 — same converging pointers, with a filtering step)
- Rotate Array (Module 1 #7 — swap-based reversal used as a building block)
- Reverse Words in a String (Module 2 #9 — reversal applied at two different levels)
