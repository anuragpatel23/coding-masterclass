# 4. Remove Duplicates from Sorted Array

**Difficulty:** Easy
**Pattern:** Read/Write Two Pointers
**LeetCode:** https://leetcode.com/problems/remove-duplicates-from-sorted-array/

## Problem Summary
Given an integer array sorted in non-decreasing order, remove the duplicates in-place so each unique element appears only once, and return the new length. The relative order of elements should be kept.

## Example
```
Input:  nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums becomes [0,1,2,3,4,...]
```

## Pattern Recognition
This is Move Zeroes (Module 1 #6) and String Compression (Module 2 #8) again, wearing a new outfit: a `writePointer` records where the next "keeper" element should go, and a `readPointer` scans forward looking for the next value worth keeping. Because the array is sorted, "worth keeping" simply means "different from the last value written."

## Approach 1: Brute Force
Collect the unique values (preserving order) using a `LinkedHashSet`, then copy them back into the original array.

- **Time:** O(n)
- **Space:** O(n) — the set, which breaks the in-place requirement
- **Why it's not good enough:** the array is already sorted, meaning duplicates are always adjacent — you don't need a general-purpose "have I seen this value anywhere before" structure like a set; a single comparison to the previous element tells you everything.

## Approach 2: Optimized (Read/Write Pointers)
Keep `slow` (the write pointer) at index 0. Walk `fast` (the read pointer) from index 1 onward. Whenever `nums[fast] != nums[slow]`, you've found a new unique value — advance `slow` and copy `nums[fast]` into that position.

- **Time:** O(n)
- **Space:** O(1)

## Dry Run
`nums = [0,0,1,1,1,2,2,3,3,4]`

| fast | nums[fast] | nums[slow] | new value? | action |
|---|---|---|---|---|
| 1 | 0 | 0 | no | skip |
| 2 | 1 | 0 | yes | slow=1, nums[1]=1 |
| 3 | 1 | 1 | no | skip |
| 4 | 1 | 1 | no | skip |
| 5 | 2 | 1 | yes | slow=2, nums[2]=2 |
| 6 | 2 | 2 | no | skip |
| 7 | 3 | 2 | yes | slow=3, nums[3]=3 |
| 8 | 3 | 3 | no | skip |
| 9 | 4 | 3 | yes | slow=4, nums[4]=4 |

Result: new length **5**, array's first 5 elements are `[0,1,2,3,4]`

## Edge Cases
- Empty array -> return `0` immediately, no pointers needed
- No duplicates at all, e.g. `[1,2,3]` -> `slow` advances on every step, new length equals original length
- All elements identical, e.g. `[2,2,2]` -> `slow` never advances past index 0, new length is `1`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (LinkedHashSet) | O(n) | O(n) |
| Optimized (read/write pointers) | O(n) | O(1) |

## Related Problems / Pattern Family
- Remove Duplicates from Sorted Array II (Module 3 #5 — same idea, allowing up to 2 copies)
- Move Zeroes (Module 1 #6 — the original read/write pointer skeleton)
