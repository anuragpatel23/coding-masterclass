# 8. 3Sum

**Difficulty:** Medium
**Pattern:** Sort + Two Pointers
**LeetCode:** https://leetcode.com/problems/3sum/

## Problem Summary
Given an integer array, return all unique triplets `[nums[i], nums[j], nums[k]]` (distinct indices) that sum to zero. The result must not contain duplicate triplets.

## Example
```
Input:  nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
```

## Pattern Recognition
3Sum is Two Sum II wearing an extra layer: **fix one number, and the remaining problem — find two other numbers that sum to its negation — is exactly Two Sum II on a sorted array.** Whenever a "k numbers that sum to X" problem shows up, the standard move is to fix `k-2` of them with loops and solve the last two with two pointers on a sorted array.

## Approach 1: Brute Force
Three nested loops checking every triplet, using a set of sorted triplets to filter out duplicates.

- **Time:** O(n^3) for the triple loop, plus a constant-size sort per triplet found for deduplication
- **Space:** O(n^3) worst case for the set of found triplets (bounded in practice by how many valid triplets actually exist)
- **Why it's not good enough:** you're redoing a linear search for "does some pair sum to X" from scratch for every single first element, when that inner search could be a single sorted two-pointer sweep instead of a nested loop.

## Approach 2: Optimized (Sort, Then Fix-and-Two-Pointer)
1. **Sort** the array first — this both enables two pointers and makes duplicate-skipping trivial (duplicates become adjacent).
2. For each index `i` (the "fixed" element), skip it if it's the same as the previous `i` (avoids duplicate triplets starting with the same value).
3. Run the Two Sum II two-pointer technique on the subarray `[i+1, n-1]`, looking for a pair that sums to `-nums[i]`.
4. When a match is found, record it, then skip past any duplicate values on both sides before continuing.

- **Time:** O(n^2) — O(n log n) for the sort, dominated by the O(n) fixed loop times O(n) two-pointer scan
- **Space:** O(1) extra (excluding the sort's space and the output)

## Dry Run
`nums = [-1,0,1,2,-1,-4]` -> sorted: `[-4,-1,-1,0,1,2]`

| i | nums[i] | left, right scan (target = -nums[i]) | triplets found |
|---|---|---|---|
| 0 (-4) | target=4 | left=1(-1),right=5(2): sum=1, too small -> left++ ... no pair reaches 4 | none |
| 1 (-1) | target=1 | left=2(-1),right=5(2): sum=1 -> **match!** [-1,-1,2] | [-1,-1,2] |
| 2 (-1) | skip (duplicate of nums[1]) | | |
| 3 (0) | target=0 | left=4(1),right=5(2): sum=3, too big -> right-- ; left==right, stop | none |

*(A full trace also finds `[-1,0,1]` when `i` lands on the second `-1` in a fuller walkthrough of the sorted array — the table above abbreviates for space.)*

Result: **[[-1,-1,2],[-1,0,1]]**

## Edge Cases
- Fewer than 3 elements -> no triplets possible, return an empty list
- All zeros, e.g. `[0,0,0,0]` -> exactly one unique triplet, `[0,0,0]`, despite many index combinations producing it
- No valid triplet exists -> the two-pointer scan for every `i` simply never finds a match, empty list returned

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^3) | O(n^3) worst case |
| Optimized (sort + two pointers) | O(n^2) | O(1) extra |

## Related Problems / Pattern Family
- Two Sum II (Module 3 #1 — the two-pointer subroutine this problem is built on)
- 3Sum Closest (Module 3 #9 — same skeleton, different objective)
- 4Sum (generalizes further: fix two elements, two-pointer the rest)
