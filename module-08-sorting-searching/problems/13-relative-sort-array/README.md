# 13. Relative Sort Array

**Difficulty:** Easy
**Pattern:** Counting Sort
**LeetCode:** https://leetcode.com/problems/relative-sort-array/

## Problem Summary
Given two arrays `arr1` and `arr2` (where `arr2` contains distinct elements, all of which appear in `arr1`), sort `arr1` so its elements appear in the same relative order as `arr2`. Elements not present in `arr2` go at the end, in ascending order.

## Example
```
Input:  arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
Output: [2,2,2,1,4,3,3,9,6,7,19]
```

## Pattern Recognition
Because the constraints guarantee bounded values (`0 <= arr1[i] <= 1000`), this is another case — like Custom Sort String — where **counting sort beats comparison sort**: instead of comparing elements pairwise, count how many times each value appears, then read the counts back out in the order you need.

## Approach 1: Brute Force (Custom Comparator Sort)
Build a rank map from `arr2`. Sort `arr1` using a comparator: elements present in `arr2` sort by their rank; elements absent from `arr2` sort after all of those, by their natural numeric value.

- **Time:** O(n log n)
- **Space:** O(n)

## Approach 2: Optimized (Counting Sort)
Count the occurrences of every value in `arr1` using a fixed-size array (indexed `0` to `1000`, per the problem's constraints). Walk through `arr2`; for each value, append it to the result as many times as it was counted, decrementing as you go. Then sweep the count array from `0` to `1000`, appending any remaining values (those not in `arr2`) in natural ascending order.

- **Time:** O(n + range) — no comparisons needed
- **Space:** O(range) for the count array, O(n) for the output

## Dry Run
`arr1 = [2,3,1,3,2,4,6,7,9,2,19]`, `arr2 = [2,1,4,3,9,6]`

Counts: `{1:1, 2:3, 3:2, 4:1, 6:1, 7:1, 9:1, 19:1}`.

Walk `arr2`: `2` -> append `2,2,2`. `1` -> append `1`. `4` -> append `4`. `3` -> append `3,3`. `9` -> append `9`. `6` -> append `6`.

Remaining (not in `arr2`, ascending): `7`, `19`.

Result: **[2,2,2,1,4,3,3,9,6,7,19]**

## Edge Cases
- Every element of `arr1` is present in `arr2` -> the final ascending sweep contributes nothing
- Values at the constraint boundary (e.g., `0` or `1000`) -> handled the same as any other value, since the count array spans the full valid range
- `arr2` is empty (an edge case outside the problem's typical guarantees, but worth considering) -> the whole result becomes the ascending sweep of `arr1`'s values

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (comparator sort) | O(n log n) | O(n) |
| Optimized (counting sort) | O(n + range) | O(range + n) |

## Related Problems / Pattern Family
- Custom Sort String (Module 8 #12 — the same counting-instead-of-sorting technique, applied to characters)
- Sort Colors (Module 3 #6 — a related fixed-small-range counting/partitioning idea)
