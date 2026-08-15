# 1. Reverse Linked List

**Difficulty:** Easy
**Pattern:** In-Place Reversal
**LeetCode:** https://leetcode.com/problems/reverse-linked-list/

## Problem Summary
Given the head of a singly linked list, reverse it and return the new head.

## Example
```
Input:  1 -> 2 -> 3 -> 4 -> 5
Output: 5 -> 4 -> 3 -> 2 -> 1
```

## Pattern Recognition
A linked list has no random access, so "reverse it" can't mean swapping index `i` with index `n-1-i` the way Module 2's Reverse String did. Instead, reversal means **flipping every `next` pointer to point backward** — which requires walking the list once while carrying along a reference to the node you just came from.

## Approach 1: Brute Force
Read every value into a list, reverse that list, then build a brand-new linked list from the reversed values.

- **Time:** O(n)
- **Space:** O(n) — the value list, plus entirely new nodes, when the original nodes could have been reused
- **Why it's not good enough:** you already have n perfectly good nodes — there's no need to allocate n new ones just to reorder them.

## Approach 2: Optimized (Iterative Pointer Reversal)
Walk the list with three references: `prev` (starts `null`), `curr` (starts at `head`), and a temporary `next` to avoid losing the rest of the list. At each step: save `curr.next`, point `curr.next` backward to `prev`, then shift both `prev` and `curr` forward by one.

- **Time:** O(n) — one pass
- **Space:** O(1) — reuses the existing nodes, no new allocation

## Dry Run
`1 -> 2 -> 3 -> null`

| prev | curr | next (saved) | curr.next set to | after step |
|---|---|---|---|---|
| null | 1 | 2 | null | prev=1, curr=2 |
| 1 | 2 | 3 | 1 | prev=2, curr=3 |
| 2 | 3 | null | 2 | prev=3, curr=null |

Loop ends (`curr == null`). Return `prev` (node 3) as the new head.

Result: **3 -> 2 -> 1 -> null**

## Edge Cases
- Empty list (`head == null`) -> the loop never runs, `prev` stays `null`, correctly returned as-is
- Single node -> the loop runs once, correctly returns that same node as the (unchanged) head
- Already computing in-place means the original `head` node ends up as the new *tail*, with `next = null` — a common point of confusion worth double-checking

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (new nodes) | O(n) | O(n) |
| Optimized (iterative, in-place) | O(n) | O(1) |

## Related Problems / Pattern Family
- Reverse Linked List II (Module 5 #7 — reversal bounded to a sub-range instead of the whole list)
- Palindrome Linked List (Module 5 #11 — uses this exact reversal as a building block)
- Reorder List (Module 5 #12 — combines this with finding the middle)
