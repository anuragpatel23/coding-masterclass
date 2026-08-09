# 13. Linked List Cycle II

**Difficulty:** Medium
**Pattern:** Fast/Slow Pointers (Floyd's Cycle Detection, Extended to Find the Entry Point)
**LeetCode:** https://leetcode.com/problems/linked-list-cycle-ii/

## Problem Summary
Given the head of a linked list, return the node where the cycle begins, or `null` if there is no cycle.

## Example
```
Input:  3 -> 2 -> 0 -> -4 -> (back to the node with value 2)
Output: the node with value 2
```

## Pattern Recognition
This extends Linked List Cycle (#12) with one more question: not just "is there a cycle," but "where does it start." Floyd's algorithm has a second phase for exactly this, backed by a clean piece of pointer arithmetic: once `slow` and `fast` meet inside the cycle, resetting one pointer to the head and advancing *both* remaining pointers one step at a time guarantees they'll meet again — precisely at the cycle's starting node.

## Approach 1: Brute Force
Walk the list, storing visited nodes in a `HashSet`. The moment you encounter a node you've already seen, that node is the cycle's entry point.

- **Time:** O(n)
- **Space:** O(n)
- **Why it's not good enough:** correct and simple, but — as with problem #12 — it spends memory proportional to the list length to answer something Floyd's two-phase approach can determine with none.

## Approach 2: Optimized (Floyd's Algorithm, Phase 2)
**Phase 1:** run standard fast/slow pointers until they meet (exactly like problem #12). If `fast` reaches `null`, there's no cycle — return `null`.

**Phase 2:** reset one pointer (say `slow`) back to `head`. Leave `fast` where the two pointers met. Now advance **both** pointers one step at a time. The node where they meet *this* time is the start of the cycle.

*(Why this works: if the distance from the head to the cycle's start is `a`, and the meeting point in Phase 1 is `b` steps into the cycle, the math of Floyd's algorithm guarantees that walking `a` more steps from the meeting point — while also walking `a` steps from the head — lands both pointers on the cycle's entry node simultaneously.)*

- **Time:** O(n) — both phases are linear
- **Space:** O(1)

## Dry Run
`3 -> 2 -> 0 -> -4 -> (back to 2)`

**Phase 1:** (identical to problem #12's dry run) `slow` and `fast` meet at the node with value `-4`.

**Phase 2:** reset `slow` to `head` (node `3`). Advance both one step at a time:
| step | slow | fast |
|---|---|---|
| start | 3 | -4 |
| 1 | 2 | 2 |

They meet at the node with value **2** — that's the cycle's start.

Result: **node with value 2**

## Edge Cases
- No cycle at all -> Phase 1 ends with `fast` reaching `null`, return `null` immediately without ever entering Phase 2
- The cycle starts at the head itself -> Phase 2's reset pointer starts exactly where it needs to be, and the two pointers meet on the very first comparison (0 additional steps)
- Single node with a self-loop -> the meeting point in Phase 1 *is* the head, and Phase 2 confirms it immediately

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (HashSet) | O(n) | O(n) |
| Optimized (Floyd's, two-phase) | O(n) | O(1) |

## Related Problems / Pattern Family
- Linked List Cycle (Module 3 #12 — Phase 1 of this exact algorithm, in isolation)
- Happy Number (Module 3 #14 — the same fast/slow idea, applied outside of a literal linked list)
