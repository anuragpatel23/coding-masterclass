# 11. Middle of the Linked List

**Difficulty:** Easy
**Pattern:** Fast/Slow Pointers *(a preview of Module 5's Linked Lists — full deep dive there)*
**LeetCode:** https://leetcode.com/problems/middle-of-the-linked-list/

## Problem Summary
Given the head of a singly linked list, return the middle node. If there are two middle nodes (even length), return the **second** one.

## Example
```
Input:  1 -> 2 -> 3 -> 4 -> 5 -> 6
Output: node with value 4
```

## Pattern Recognition
A linked list has no random access — you can't jump straight to "the middle" the way you would with `array[n/2]`. But you *can* move two pointers at different speeds: if one pointer (`fast`) moves twice as fast as another (`slow`), then by the time `fast` reaches the end, `slow` has covered exactly half the distance. This tortoise-and-hare idea is the single most important trick for linked list problems, and it shows up again immediately in the next two problems.

## Approach 1: Brute Force (Two Passes)
First pass: walk the entire list to count its length. Second pass: walk again, this time stopping at index `length / 2`.

- **Time:** O(n) — but two full passes over the list
- **Space:** O(1)
- **Why it's not good enough:** it's already linear, but doing two separate traversals is unnecessary — the fast/slow technique gets the same answer in a single pass.

## Approach 2: Optimized (Fast/Slow Pointers, Single Pass)
Start both `slow` and `fast` at the head. On each step, move `slow` forward by one node and `fast` forward by two nodes. Stop when `fast` reaches the end (or has no next node to jump to). At that point, `slow` is sitting exactly at the middle.

- **Time:** O(n) — a single pass
- **Space:** O(1)

## Dry Run
`1 -> 2 -> 3 -> 4 -> 5 -> 6`

| step | slow | fast |
|---|---|---|
| start | 1 | 1 |
| 1 | 2 | 3 |
| 2 | 3 | 5 |
| 3 | 4 | null (5.next.next) |

`fast` is now null (or `fast.next` would be), loop stops. `slow` is at node **4**.

Result: **node with value 4** (the second of the two middle nodes, as required)

## Edge Cases
- Single-node list -> `slow` and `fast` both start there; the loop condition (`fast != null && fast.next != null`) is immediately false, so `slow` is correctly returned as-is
- Two-node list -> `fast` takes one double-step and lands past the end; `slow` correctly ends on the second node
- Odd-length list -> there's a single unambiguous middle, and the same loop logic finds it without any special casing

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (two passes) | O(n) | O(1) |
| Optimized (fast/slow, single pass) | O(n) | O(1) |

## Related Problems / Pattern Family
- Linked List Cycle (Module 3 #12 — the same fast/slow mechanics, different stopping condition)
- Palindrome Linked List (Module 5 — uses this exact middle-finding technique as its first step)
- Reorder List (Module 5 — finds the middle, then reverses the second half)
