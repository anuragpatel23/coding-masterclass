# 5. Swap Nodes in Pairs

**Difficulty:** Medium
**Pattern:** In-Place Pointer Manipulation + Dummy Head
**LeetCode:** https://leetcode.com/problems/swap-nodes-in-pairs/

## Problem Summary
Given a linked list, swap every two adjacent nodes and return the head. You must do it by rewiring nodes, not just swapping their values.

## Example
```
Input:  1 -> 2 -> 3 -> 4
Output: 2 -> 1 -> 4 -> 3
```

## Pattern Recognition
"Swap by rewiring, not by copying values" is a signal that you need to carefully track **three** references at once: the node before the pair, and the two nodes in the pair — because once you repoint one `next` pointer, you'll lose access to whatever it used to point to if you didn't save it first.

## Approach 1: Brute Force
Read every value into a list, swap adjacent pairs of *values* in that list, then build an entirely new linked list from the result.

- **Time:** O(n)
- **Space:** O(n) — the value list, plus new nodes, when the existing nodes could be reused
- **Why it's not good enough:** the problem is really testing pointer manipulation; swapping values sidesteps that skill even though it produces the same visible output.

## Approach 2: Optimized (Dummy Head + Three-Reference Rewiring)
Use a dummy head so the very first pair is handled the same way as every other pair. For each pair, save references to both nodes in the pair, rewire the first node's `next` to skip past the second, rewire the second node's `next` to point at the first, and rewire the *previous* node's `next` to point at the second (the new head of this pair). Advance to the next pair.

- **Time:** O(n) — one pass
- **Space:** O(1) — existing nodes are rewired in place

## Dry Run
`1 -> 2 -> 3 -> 4`, dummy -> 1 -> 2 -> 3 -> 4

**Pair (1,2):** first=1, second=2. `first.next = second.next` (1.next=3). `second.next = first` (2.next=1). `prev.next = second` (dummy.next=2). Chain so far: dummy->2->1->3->4. `prev` advances to `first` (node 1).

**Pair (3,4):** first=3, second=4. `first.next = second.next` (3.next=null). `second.next = first` (4.next=3). `prev.next = second` (1.next=4). Chain: dummy->2->1->4->3->null.

Result: **2 -> 1 -> 4 -> 3**

## Edge Cases
- Odd number of nodes -> the loop condition (`prev.next != null && prev.next.next != null`) stops before the final unpaired node, leaving it untouched at the end
- Empty list or single node -> the loop condition is false immediately, list returned unchanged
- Exactly two nodes -> a single swap, handled the same as any other pair

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (value swap, new nodes) | O(n) | O(n) |
| Optimized (dummy head, in-place rewiring) | O(n) | O(1) |

## Related Problems / Pattern Family
- Reverse Nodes in k-Group (a harder generalization: reverse every group of k instead of swapping pairs)
- Reverse Linked List II (Module 5 #7 — another bounded-range pointer rewiring problem)
