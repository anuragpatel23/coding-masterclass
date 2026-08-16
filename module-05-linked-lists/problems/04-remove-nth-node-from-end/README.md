# 4. Remove Nth Node From End of List

**Difficulty:** Medium
**Pattern:** Two-Pointer Gap + Dummy Head
**LeetCode:** https://leetcode.com/problems/remove-nth-node-from-end-of-list/

## Problem Summary
Given the head of a linked list, remove the `n`th node from the **end** of the list, and return the head.

## Example
```
Input:  1 -> 2 -> 3 -> 4 -> 5, n = 2
Output: 1 -> 2 -> 3 -> 5
```

## Pattern Recognition
A linked list can't be indexed from the end directly — you don't know the length until you've walked it. The two-pointer fix: give one pointer a **head start of `n` steps**, then move both pointers together. When the lead pointer reaches the end, the trailing pointer is exactly `n` nodes behind it — sitting right where the removal needs to happen.

## Approach 1: Brute Force (Two Passes)
First pass: walk the list to find its length. Second pass: walk again to the node just before position `length - n`, and unlink the following node.

- **Time:** O(n) — but two separate traversals
- **Space:** O(1)
- **Why it's not good enough:** it's already linear, but doing the length calculation and the removal as two separate passes is unnecessary — the gap technique gets there in one.

## Approach 2: Optimized (Gap of N, Single Pass, Dummy Head)
Create a dummy head pointing at the real head (this handles the edge case of removing the head itself cleanly). Advance a `fast` pointer `n` steps ahead of a `slow` pointer, both starting from the dummy. Then move both forward together until `fast` reaches the last node. At that point, `slow` is sitting just before the node to remove.

- **Time:** O(n) — one pass
- **Space:** O(1)

## Dry Run
`1 -> 2 -> 3 -> 4 -> 5`, `n = 2`

Dummy -> 1 -> 2 -> 3 -> 4 -> 5. Advance `fast` 2 steps from dummy: `fast` lands on node `2`. Now advance both `slow` (from dummy) and `fast` together until `fast.next == null`:

| slow | fast |
|---|---|
| dummy | 2 |
| 1 | 3 |
| 2 | 4 |
| 3 | 5 (fast.next is null, stop) |

`slow` is at node `3`. Remove `slow.next` (node `4`): `slow.next = slow.next.next`.

Result: **1 -> 2 -> 3 -> 5**

## Edge Cases
- Removing the head itself (`n == length`) -> the dummy head handles this cleanly; `slow` stays at the dummy, and `dummy.next` gets updated correctly
- Single-node list, `n = 1` -> removes the only node, returns an empty list
- `n` equal to the list's exact length -> already covered by the head-removal case above, no special casing needed beyond the dummy head

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (two passes) | O(n) | O(1) |
| Optimized (gap + dummy head, one pass) | O(n) | O(1) |

## Related Problems / Pattern Family
- Middle of the Linked List (Module 3 #11 — a related fixed-gap idea, gap of "half the list" instead of a fixed n)
- Remove Duplicates from Sorted List II (Module 5 #8 — another dummy-head-powered removal problem)
