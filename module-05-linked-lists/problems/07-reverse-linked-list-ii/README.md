# 7. Reverse Linked List II

**Difficulty:** Medium
**Pattern:** Bounded In-Place Reversal (Head-Insertion Technique)
**LeetCode:** https://leetcode.com/problems/reverse-linked-list-ii/

## Problem Summary
Given a linked list and two positions `left` and `right` (1-indexed), reverse only the nodes from position `left` to `right`, leaving the rest of the list untouched.

## Example
```
Input:  1 -> 2 -> 3 -> 4 -> 5, left = 2, right = 4
Output: 1 -> 4 -> 3 -> 2 -> 5
```

## Pattern Recognition
This extends Reverse Linked List (#1) to a **sub-range**. The key extra step: you need to correctly reconnect both ends of the reversed section back to the untouched parts of the list — the node just before `left` needs to end up pointing at what was originally the `right` node, and the original `left` node needs to end up pointing at whatever followed `right`.

## Approach 1: Brute Force
Read every value into a list, reverse just the sub-range `[left-1, right-1]` within that list, then build an entirely new linked list from the result.

- **Time:** O(n)
- **Space:** O(n) — the value list, plus new nodes
- **Why it's not good enough:** as with every "in-place" linked list problem, materializing the whole list as an array throws away the ability to just rewire a handful of pointers.

## Approach 2: Optimized (Head-Insertion Reversal)
Use a dummy head, and walk `prev` to the node just before position `left`. Then repeatedly take the node right after `prev` (call it `current`, which never changes throughout this process) and move the node *after* `current` to be inserted right after `prev` instead — effectively pulling each subsequent node to the front of the reversed section one at a time. Repeat `right - left` times.

- **Time:** O(n) — one pass to reach `left`, then a bounded number of pointer rewires
- **Space:** O(1)

## Dry Run
`1 -> 2 -> 3 -> 4 -> 5`, `left=2, right=4`

Walk `prev` to node `1` (just before position 2). `current = prev.next` = node `2` (this stays fixed).

| iteration | node moved to front | chain after |
|---|---|---|
| 1 | node 3 | 1 -> 3 -> 2 -> 4 -> 5 |
| 2 | node 4 | 1 -> 4 -> 3 -> 2 -> 5 |

Result: **1 -> 4 -> 3 -> 2 -> 5**

## Edge Cases
- `left == right` -> zero iterations of the inner loop occur, list is returned unchanged
- `left == 1` -> the reversal includes the head itself; the dummy head handles this cleanly with no special casing
- `right` equal to the list's length -> the reversed section extends all the way to the original tail, which is handled the same as any other range

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (value list, new nodes) | O(n) | O(n) |
| Optimized (head-insertion, in-place) | O(n) | O(1) |

## Related Problems / Pattern Family
- Reverse Linked List (Module 5 #1 — the whole-list special case of this exact idea)
- Reverse Nodes in k-Group (repeats this bounded reversal on every group of k nodes)
