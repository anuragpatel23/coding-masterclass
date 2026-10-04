# 6. Merge Sorted Array

**Difficulty:** Easy
**Pattern:** In-Place Merge from the Back
**LeetCode:** https://leetcode.com/problems/merge-sorted-array/

## Problem Summary
`nums1` has length `m + n`, with the first `m` elements meaningful and the last `n` slots empty (zero-filled placeholder space). Merge `nums2` (length `n`) into `nums1` in-place, so `nums1` ends up sorted.

## Example
```
Input:  nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
```

## Pattern Recognition
Merging from the **front** (like Merge Two Sorted Lists) would immediately start overwriting elements of `nums1` you haven't compared yet, since both sequences share the same array. The fix: merge from the **back** instead — the empty space is at the end, so writing there first can never clobber data you still need.

## Approach 1: Brute Force
Copy `nums2` into the empty tail slots of `nums1`, then sort the entire array.

- **Time:** O((m+n) log(m+n)) — dominated by the sort, which ignores that both halves were already individually sorted
- **Space:** O(1) extra (sorting in place), but wastes the pre-sorted structure

## Approach 2: Optimized (Three Pointers, Merge from the Back)
Maintain three pointers: `i` at the last meaningful element of `nums1` (index `m-1`), `j` at the last element of `nums2` (index `n-1`), and `k` at the very last slot of `nums1` (index `m+n-1`). Compare `nums1[i]` and `nums2[j]`; place the larger one at `nums1[k]`, and decrement that pointer along with `k`. Continue until `nums2` is fully placed (any remaining `nums1` elements are already exactly where they belong).

- **Time:** O(m+n) — one pass
- **Space:** O(1) — no extra array, writes directly into `nums1`'s existing space

## Dry Run
`nums1 = [1,2,3,0,0,0]` (m=3), `nums2 = [2,5,6]` (n=3)

| i (nums1) | j (nums2) | compare | placed at k | k after |
|---|---|---|---|---|
| 2 (val 3) | 2 (val 6) | 3<6 | nums1[5]=6 | 4 |
| 2 (val 3) | 1 (val 5) | 3<5 | nums1[4]=5 | 3 |
| 2 (val 3) | 0 (val 2) | 3>2 | nums1[3]=3 | 2 |
| 1 (val 2) | 0 (val 2) | 2<=2 | nums1[2]=2 | 1 |
| 0 (val 1) | -1 (j exhausted) | loop ends (j<0) | - | - |

Result: **[1, 2, 2, 3, 5, 6]**

## Edge Cases
- `nums2` is empty (`n = 0`) -> the loop condition (`j >= 0`) is false immediately, `nums1` is already correct as-is
- `nums1`'s meaningful portion is empty (`m = 0`) -> every element comes from `nums2`, correctly copied over once `i` is exhausted
- All of `nums2`'s elements are smaller than all of `nums1`'s -> `j` finishes placing everything before `i` ever needs to move, still handled correctly by the loop structure

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (copy + sort) | O((m+n) log(m+n)) | O(1) extra |
| Optimized (three pointers, merge from back) | O(m+n) | O(1) |

## Related Problems / Pattern Family
- Merge Two Sorted Lists (Module 5 #2 — the linked-list version, merging from the front since lists don't share storage)
- Squares of a Sorted Array (Module 3 #3 — a related "merge from the correct end" technique)
