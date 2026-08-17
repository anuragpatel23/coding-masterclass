# 6. Odd Even Linked List

**Difficulty:** Medium
**Pattern:** Two-Pointer Partitioning
**LeetCode:** https://leetcode.com/problems/odd-even-linked-list/

## Problem Summary
Given a linked list, group all nodes at odd indices together, followed by all nodes at even indices (1-indexed positions), preserving relative order within each group. Do this in-place.

## Example
```
Input:  1 -> 2 -> 3 -> 4 -> 5
Output: 1 -> 3 -> 5 -> 2 -> 4
```

## Pattern Recognition
"Partition into two interleaved groups, preserving each group's internal order" is a two-pointer job: maintain one pointer chaining together the odd-position nodes and another chaining together the even-position nodes, advancing both in lockstep through the original list, then join the two resulting chains at the end.

## Approach 1: Brute Force
Walk the list once, sorting each node's value into an "odd" list or an "even" list based on its position. Concatenate the two lists of values, then build an entirely new linked list.

- **Time:** O(n)
- **Space:** O(n) — two value lists, plus new nodes
- **Why it's not good enough:** the existing nodes already have everything needed — you're just re-linking their `next` pointers, not creating new data.

## Approach 2: Optimized (Two-Pointer In-Place Partitioning)
Keep an `odd` pointer starting at the head and an `even` pointer starting at the second node (saving a reference to this even-chain's head, since it needs to be attached at the very end). Alternate: connect `odd` to the node two steps ahead (skipping the even node in between), advance `odd`; do the same for `even`. Once the even pointer runs out, attach the saved even-chain head to the end of the odd chain.

- **Time:** O(n) — one pass
- **Space:** O(1) — nodes are rewired in place

## Dry Run
`1 -> 2 -> 3 -> 4 -> 5`

odd=1, even=2, evenHead=2 (saved).

| step | odd.next set to | even.next set to | odd after | even after |
|---|---|---|---|---|
| 1 | 3 (odd=1 connects to 3) | 4 (even=2 connects to 4) | 3 | 4 |
| 2 | 5 (odd=3 connects to 5) | null (even=4's next was null) | 5 | null |

Loop ends (`even == null`). Attach `odd.next = evenHead`: node 5's next becomes node 2.

Result: **1 -> 3 -> 5 -> 2 -> 4**

## Edge Cases
- Empty list or single node -> nothing to partition, returned unchanged
- Exactly two nodes -> one odd, one even; the odd chain becomes `[node1]`, even chain `[node2]`, joined as `1 -> 2` (unchanged in this specific case)
- Odd total length -> the last node naturally ends up in the odd chain, no special casing needed

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (two value lists, new nodes) | O(n) | O(n) |
| Optimized (two-pointer, in-place) | O(n) | O(1) |

## Related Problems / Pattern Family
- Partition List (a related in-place partitioning problem, splitting by value instead of by index)
- Sort Colors (Module 3 #6 — a different partitioning shape, on an array instead of a list)
