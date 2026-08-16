# 3. Remove Duplicates from Sorted List

**Difficulty:** Easy
**Pattern:** Single-Pass Pointer
**LeetCode:** https://leetcode.com/problems/remove-duplicates-from-sorted-list/

## Problem Summary
Given the head of a sorted linked list, delete all duplicates so each value appears only once, and return the list.

## Example
```
Input:  1 -> 1 -> 2 -> 3 -> 3
Output: 1 -> 2 -> 3
```

## Pattern Recognition
Because the list is sorted, duplicates are always **adjacent** — you never need to remember values you saw several nodes ago. A single pointer comparing each node to the very next one is enough to catch every duplicate.

## Approach 1: Brute Force
Collect the distinct values (in order) using a `LinkedHashSet`, then build an entirely new list from them.

- **Time:** O(n)
- **Space:** O(n) — the set, plus new nodes
- **Why it's not good enough:** sortedness already guarantees duplicates sit right next to each other — a general-purpose "have I seen this anywhere" structure is more than the problem actually requires.

## Approach 2: Optimized (Single Pointer, Compare to Next)
Walk the list with one pointer, `current`. If `current.val == current.next.val`, splice the duplicate out by pointing `current.next` past it (skip). Otherwise, advance `current` normally.

- **Time:** O(n) — one pass
- **Space:** O(1) — nodes are rewired in place, no new allocation

## Dry Run
`1 -> 1 -> 2 -> 3 -> 3`

| current | current.next | equal? | action |
|---|---|---|---|
| 1 (first) | 1 (second) | yes | skip: current.next = second's next (node 2) |
| 1 (first) | 2 | no | advance: current = 2 |
| 2 | 3 (first) | no | advance: current = 3 (first) |
| 3 (first) | 3 (second) | yes | skip: current.next = null |

Result: **1 -> 2 -> 3**

## Edge Cases
- Empty list -> the loop condition (`current != null && current.next != null`) is false immediately, nothing to do
- All values identical -> every comparison matches, ends with a single-node list
- No duplicates at all -> every comparison fails, `current` simply walks to the end unchanged

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (LinkedHashSet) | O(n) | O(n) |
| Optimized (single pointer, in-place) | O(n) | O(1) |

## Related Problems / Pattern Family
- Remove Duplicates from Sorted List II (Module 5 #8 — removes duplicates entirely rather than keeping one copy)
- Remove Duplicates from Sorted Array (Module 3 #4 — the array version of this exact idea)
