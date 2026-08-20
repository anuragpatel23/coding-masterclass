# 12. Reorder List

**Difficulty:** Medium
**Pattern:** Fast/Slow (Find Middle) + Reversal + Merge
**LeetCode:** https://leetcode.com/problems/reorder-list/

## Problem Summary
Given a linked list `L0 -> L1 -> ... -> Ln`, reorder it in place to `L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...`.

## Example
```
Input:  1 -> 2 -> 3 -> 4
Output: 1 -> 4 -> 2 -> 3
```

## Pattern Recognition
The target pattern — alternating from the front and the back — is exactly what you'd get by: splitting the list into two halves, reversing the second half (so its nodes come out in back-to-front order), and then **merging** the two halves by alternating one node from each. This is three techniques you already have (find middle, reverse, merge) chained together.

## Approach 1: Brute Force
Store every node reference in a list. Build the new order by alternating from the front and back of that list, then relink `next` pointers accordingly.

- **Time:** O(n)
- **Space:** O(n) — the list of node references
- **Why it's not good enough:** it works, and reuses the existing nodes rather than copying values — but the O(n) auxiliary storage isn't necessary once you notice this decomposes into three O(1)-space techniques.

## Approach 2: Optimized (Split, Reverse Second Half, Merge Alternately)
1. Find the middle using fast/slow pointers (using the "first middle" variant here, so the first half is never shorter than the second).
2. Split the list into two halves at the middle, and reverse the second half in place.
3. Merge the two halves by alternating: one node from the first half, one from the second, repeating until the (shorter or equal) second half is exhausted.

- **Time:** O(n) — each phase is a single linear pass
- **Space:** O(1)

## Dry Run
`1 -> 2 -> 3 -> 4`

**Split:** first half `1 -> 2`, second half `3 -> 4`.
**Reverse second half:** `4 -> 3`.
**Merge alternately:** take `1`, then `4`, then `2`, then `3`.

Result: **1 -> 4 -> 2 -> 3**

## Edge Cases
- Empty list or single node -> nothing to reorder, list returned unchanged
- Two nodes -> the "second half" is a single node, merges trivially as `L0 -> L1`
- Odd length, e.g. `1 -> 2 -> 3 -> 4 -> 5` -> using the "first middle" split variant, the first half (`1,2,3`) is one node longer than the second (`4,5` reversed to `5,4`), and the merge loop naturally stops once the second half runs out, leaving the extra first-half node in place at the end

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (list of node references) | O(n) | O(n) |
| Optimized (split + reverse + merge) | O(n) | O(1) |

## Related Problems / Pattern Family
- Palindrome Linked List (Module 5 #11 — the same find-middle-and-reverse building blocks, different final step)
- Merge Two Sorted Lists (Module 5 #2 — a different merge, sorted-order instead of strict alternation)
