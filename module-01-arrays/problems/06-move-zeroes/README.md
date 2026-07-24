# 6. Move Zeroes

**Difficulty:** Easy
**Pattern:** In-Place Write Pointer *(a Two Pointers preview — full module is Module 3)*
**LeetCode:** https://leetcode.com/problems/move-zeroes/

## Problem Summary
Given an integer array, move all `0`s to the end while maintaining the relative order of the non-zero elements. You must do this **in-place**, without making a copy of the array.

## Example
```
Input:  nums = [0, 1, 0, 3, 12]
Output: [1, 3, 12, 0, 0]
```

## Pattern Recognition
"In-place" + "maintain relative order" + "filter/compact an array" is the signature of the **read-pointer/write-pointer** technique: one pointer scans forward looking for elements worth keeping, the other marks where the next kept element should go.

## Approach 1: Brute Force
Build a new array: copy all non-zero elements first, then pad the rest with zeroes.

- **Time:** O(n)
- **Space:** O(n) — this is the part that violates "in-place," even though the time complexity is already optimal
- **Why it's not good enough:** the problem explicitly asks for in-place modification; allocating a second array defeats the point, and in an interview this is usually a hard requirement, not a suggestion.

## Approach 2: Optimized (Two-Pointer Swap)
Keep a `writePointer` starting at 0. Scan with a `readPointer` across the array:
- Whenever `nums[readPointer] != 0`, swap it into `nums[writePointer]` and advance `writePointer`.

Because you only ever swap a non-zero element forward (or with itself), all the zeros naturally get pushed to the back, and relative order among non-zero elements is preserved since you never reorder two non-zero elements relative to each other.

- **Time:** O(n)
- **Space:** O(1)

## Dry Run
`nums = [0, 1, 0, 3, 12]`

| readPointer | nums[read] | action | array state | writePointer after |
|---|---|---|---|---|
| 0 | 0 | skip | [0,1,0,3,12] | 0 |
| 1 | 1 | swap(0,1) | [1,0,0,3,12] | 1 |
| 2 | 0 | skip | [1,0,0,3,12] | 1 |
| 3 | 3 | swap(1,3) | [1,3,0,0,12] | 2 |
| 4 | 12 | swap(2,4) | [1,3,12,0,0] | 3 |

Result: **[1, 3, 12, 0, 0]**

## Edge Cases
- Array with no zeros → no swaps happen, array unchanged (each swap is a no-op self-swap or skipped)
- Array of all zeros → array unchanged
- Single-element array → trivially correct

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (new array) | O(n) | O(n) |
| Optimized (two-pointer swap) | O(n) | O(1) |

## Related Problems / Pattern Family
- Remove Element (identical write-pointer technique)
- Remove Duplicates from Sorted Array (Module 3 — Two Pointers)
- Sort Colors / Dutch National Flag (three-pointer partitioning — Module 3)
