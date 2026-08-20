# 13. Rotate List

**Difficulty:** Medium
**Pattern:** Circular Link + Break
**LeetCode:** https://leetcode.com/problems/rotate-list/

## Problem Summary
Given the head of a linked list, rotate the list to the right by `k` places.

## Example
```
Input:  1 -> 2 -> 3 -> 4 -> 5, k = 2
Output: 4 -> 5 -> 1 -> 2 -> 3
```

## Pattern Recognition
Rotating a linked list is really just "pick a different node to be the head, and break the circle there." That reframing suggests a clean trick: temporarily connect the **tail back to the head**, making the list circular, then walk to the correct new tail position and cut the circle open at exactly that point.

## Approach 1: Brute Force
Copy every value into a list, compute the effective rotation (`k % length`), rebuild the rotated order, and construct a new linked list from it.

- **Time:** O(n)
- **Space:** O(n) — the value list, plus new nodes

## Approach 2: Optimized (Make Circular, Then Break)
1. Walk the list once to find its length and its current tail.
2. Compute the effective rotation `k % length` (rotating by the full length is a no-op).
3. Connect `tail.next = head`, making the list circular.
4. Walk `length - k` steps from the head to find the new tail; the node right after it is the new head.
5. Cut the circle by setting the new tail's `next` to `null`.

- **Time:** O(n) — one pass to measure, one partial pass to find the new tail
- **Space:** O(1)

## Dry Run
`1 -> 2 -> 3 -> 4 -> 5`, `k = 2`

Length = 5. Effective `k = 2 % 5 = 2`. Connect tail (`5`) back to head (`1`): circular now. New tail sits `length - k = 3` steps from the head: `1 -> 2 -> 3` — so the new tail is node `3`, and the new head is `3.next = 4`.

Cut: `3.next = null`.

Result: **4 -> 5 -> 1 -> 2 -> 3**

## Edge Cases
- `k` is a multiple of the list's length (including `k = 0`) -> effective rotation is `0`, list returned unchanged (after an early-return check, since making it circular and immediately re-cutting at the same spot would also work but is wasted effort)
- Single-node list -> any rotation is a no-op, handled by the same early check
- `k` larger than the list's length -> the `k % length` step handles this without any special casing

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (value list, new nodes) | O(n) | O(n) |
| Optimized (circular link + break) | O(n) | O(1) |

## Related Problems / Pattern Family
- Rotate Array (Module 1 #7 — the same underlying idea, on an index-based structure instead of pointers)
- Linked List Cycle (Module 3 #12 — a different use of a circular structure)
