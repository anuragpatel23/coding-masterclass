# 12. Linked List Cycle

**Difficulty:** Easy
**Pattern:** Fast/Slow Pointers (Floyd's Cycle Detection)
**LeetCode:** https://leetcode.com/problems/linked-list-cycle/

## Problem Summary
Given the head of a linked list, determine if it contains a cycle (some node's `next` pointer eventually points back to a node earlier in the list, rather than to `null`).

## Example
```
Input:  3 -> 2 -> 0 -> -4 -> (back to the node with value 2)
Output: true
```

## Pattern Recognition
"Does this sequence loop back on itself" is the defining use case for fast/slow pointers. The intuition: think of two runners on a circular track, one twice as fast as the other. If the track is a loop, the faster runner *must* eventually lap the slower one and they'll meet. If the track is a straight line (no loop), the faster runner simply reaches the end first — no meeting ever happens.

## Approach 1: Brute Force
Walk the list, storing every visited node in a `HashSet`. If you ever encounter a node that's already in the set, there's a cycle. If you reach `null`, there isn't.

- **Time:** O(n)
- **Space:** O(n) — every node visited gets stored
- **Why it's not good enough:** it works, and it's linear time — but it uses memory proportional to the list's length just to answer a yes/no question. Floyd's algorithm answers the same question with no extra memory at all.

## Approach 2: Optimized (Floyd's Cycle Detection)
Move `slow` one step at a time and `fast` two steps at a time. If there's no cycle, `fast` reaches `null` and you're done (no cycle). If there **is** a cycle, `fast` will eventually "lap" `slow` from behind and they'll land on the exact same node — that's your signal that a cycle exists.

- **Time:** O(n) — even accounting for the "lapping" behavior inside a cycle, the total work is bounded by a small multiple of the list's length
- **Space:** O(1) — no extra data structure at all

## Dry Run
`3 -> 2 -> 0 -> -4 -> (back to 2)`

| step | slow | fast |
|---|---|---|
| start | 3 | 3 |
| 1 | 2 | 0 |
| 2 | 0 | 2 (fast wrapped through the cycle) |
| 3 | -4 | -4 |

`slow == fast` -> a cycle exists.

Result: **true**

## Edge Cases
- Empty list (`head == null`) -> the loop condition is false immediately, correctly returns `false`
- Single node with no self-loop -> `fast.next` becomes `null` on the first check, loop ends, returns `false`
- Single node that points to itself (a cycle of length 1) -> `fast` and `slow` meet immediately on the first step, correctly returns `true`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (HashSet) | O(n) | O(n) |
| Optimized (Floyd's fast/slow) | O(n) | O(1) |

## Related Problems / Pattern Family
- Linked List Cycle II (Module 3 #13 — extends this to find *where* the cycle begins)
- Happy Number (Module 3 #14 — applies the exact same cycle-detection idea to a number sequence instead of a linked list)
- Middle of the Linked List (Module 3 #11 — the same fast/slow mechanics, different goal)
