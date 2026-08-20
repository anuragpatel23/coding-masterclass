# 14. Copy List with Random Pointer

**Difficulty:** Medium
**Pattern:** HashMap Mapping (Brute Force) vs. Interweaving (Optimized)
**LeetCode:** https://leetcode.com/problems/copy-list-with-random-pointer/

## Problem Summary
Each node in a linked list has both a `next` pointer and a `random` pointer (which can point to any node in the list, or `null`). Create a deep copy of the entire list — the copy must be composed of entirely new nodes, with `next` and `random` pointers correctly mirroring the original structure.

## Example
```
Input:  a list where node A's random points to node C, and node B's random points to node A
Output: an entirely separate list with the same value/next/random structure
```

## Pattern Recognition
The hard part isn't copying `next` pointers — that's routine. It's copying `random` pointers, which can point *forward* to a node you haven't created a copy of yet. A hashmap solves this by letting you look up "the copy of any original node" regardless of when it was created. The more advanced trick — weaving each copy directly into the original list, right after its original — sidesteps the need for a hashmap entirely, since "the copy of node X" becomes simply `X.next`.

## Approach 1: Brute Force (HashMap Mapping)
**Pass 1:** walk the original list, creating a copy of every node (with `next`/`random` left unset for now), and record `original -> copy` in a hashmap.
**Pass 2:** walk the original list again; for each node, use the map to set its copy's `next` and `random` pointers to the *copies* of the corresponding original nodes.

- **Time:** O(n) — two linear passes
- **Space:** O(n) — the hashmap, mapping every node to its copy

## Approach 2: Optimized (Interweaving, No Extra Map)
**Pass 1:** for every original node, insert its copy directly after it: `A -> A' -> B -> B' -> ...`.
**Pass 2:** for every original node, set its copy's `random` pointer using `original.random.next` — since the copy of any node `X` is now sitting right at `X.next`.
**Pass 3:** unweave the interleaved list back into two separate lists (original restored, copy extracted).

- **Time:** O(n) — three linear passes
- **Space:** O(1) extra (excluding the required output list)

## Dry Run
`A -> B -> C` where `A.random = C`, `B.random = A`, `C.random = null`

**Interweave:** `A -> A' -> B -> B' -> C -> C'`

**Set random pointers on copies:** `A'.random = A.random.next = C.next = C'`. `B'.random = B.random.next = A.next = A'`. `C'.random = null` (since `C.random` was `null`).

**Detach:** original list restored to `A -> B -> C`; copy list extracted as `A' -> B' -> C'`, with `A'.random = C'` and `B'.random = A'` — correctly mirroring the original structure.

## Edge Cases
- Empty list (`head == null`) -> return `null` immediately
- A node's `random` pointer is `null` -> both approaches naturally propagate `null` through (the hashmap returns `null` for a `null` lookup; the interweaving check explicitly guards `if (c.random != null)`)
- A node's `random` pointer points to itself -> handled correctly by both approaches, since the mapping/interweaving works per-node regardless of what it points to

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (HashMap mapping) | O(n) | O(n) |
| Optimized (interweaving) | O(n) | O(1) extra |

## Related Problems / Pattern Family
- Clone Graph (the graph version of this exact "hashmap of original -> copy" technique — see Module 13)
