# 10. Intersection of Two Linked Lists

**Difficulty:** Easy
**Pattern:** Two Pointers (Switch-Heads Trick)
**LeetCode:** https://leetcode.com/problems/intersection-of-two-linked-lists/

## Problem Summary
Given the heads of two singly linked lists, return the node at which they intersect (the same node object, by reference — not just equal values), or `null` if they don't intersect.

## Example
```
Input:  listA and listB share a common tail starting at node with value 8
Output: the node with value 8
```

## Pattern Recognition
The two lists might have different lengths before they merge, which makes naive simultaneous walking miss the intersection. The elegant fix: when a pointer reaches the end of its own list, redirect it to the **head of the other list**. Walking both pointers this way means each one travels exactly `lengthA + lengthB` total steps — perfectly synchronizing them so they arrive at the intersection point (or both hit `null`) at the same time.

## Approach 1: Brute Force
Store every node from list A in a `HashSet`, then walk list B checking each node for membership in that set.

- **Time:** O(n+m)
- **Space:** O(n) — the set of list A's nodes
- **Why it's not good enough:** correct and linear, but the switch-heads trick achieves the same result with no extra memory at all.

## Approach 2: Optimized (Two Pointers, Switch Heads on Exhaustion)
Start one pointer at `headA` and another at `headB`. Advance both one step at a time. Whenever a pointer reaches `null`, redirect it to the *other* list's head instead of stopping. Continue until the two pointers are equal — either both `null` (no intersection) or both pointing at the actual intersection node.

- **Time:** O(n+m) — each pointer traverses at most both lists once
- **Space:** O(1)

## Dry Run
`listA = a1 -> a2 -> c1 -> c2 -> c3`, `listB = b1 -> c1 -> c2 -> c3` (shared tail starts at `c1`)

| pointerA | pointerB |
|---|---|
| a1 | b1 |
| a2 | c1 |
| c1 | c2 |
| c2 | c3 |
| c3 | a1 (B exhausted, switched to headA) |
| a1 (A exhausted, switched to headB) | b1 |
| ... | ... |

Eventually both pointers land on `c1` at the same step (the extra steps from switching exactly compensate for the length difference between the two lists).

Result: **the node with value matching `c1`** (the intersection point)

## Edge Cases
- No intersection at all -> both pointers eventually become `null` simultaneously after switching once each, and the loop correctly returns `null`
- Lists of equal length -> the pointers may never even need to switch heads, converging directly
- One list is empty -> no intersection is possible; the empty list's pointer is `null` immediately, and the loop terminates correctly at `null == null` only after the other pointer also exhausts (or immediately if both are empty)

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (HashSet) | O(n+m) | O(n) |
| Optimized (switch-heads two pointers) | O(n+m) | O(1) |

## Related Problems / Pattern Family
- Linked List Cycle (Module 3 #12 — a different fast/slow application, same "no direct length comparison" family of problems)
