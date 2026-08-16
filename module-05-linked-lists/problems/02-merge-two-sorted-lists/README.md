# 2. Merge Two Sorted Lists

**Difficulty:** Easy
**Pattern:** Dummy Head + Merge Technique
**LeetCode:** https://leetcode.com/problems/merge-two-sorted-lists/

## Problem Summary
Given the heads of two sorted linked lists, merge them into a single sorted list and return its head.

## Example
```
Input:  l1 = 1 -> 2 -> 4,  l2 = 1 -> 3 -> 4
Output: 1 -> 1 -> 2 -> 3 -> 4 -> 4
```

## Pattern Recognition
This is the linked-list version of the classic "merge step" from merge sort. The **dummy head** technique is what makes the code clean: instead of special-casing "what's the very first node of the result," you create a placeholder node before the list even starts, build the result after it, and return `dummy.next` at the end — no special case needed for the head.

## Approach 1: Brute Force
Collect every value from both lists, sort them, and build an entirely new list from the sorted values.

- **Time:** O((n+m) log(n+m)) — dominated by the sort, which ignores that both inputs are already sorted
- **Space:** O(n+m) — the value list, plus new nodes
- **Why it's not good enough:** sorting from scratch throws away the fact that both lists arrive pre-sorted — merging two sorted sequences should never need a general-purpose sort.

## Approach 2: Optimized (Dummy Head + Two-Pointer Merge)
Create a dummy head node and a `tail` pointer starting there. Compare the current heads of both lists; attach whichever is smaller to `tail.next`, advance that list's pointer, and advance `tail`. Once one list is exhausted, attach the remainder of the other list directly (it's already sorted, no further comparison needed).

- **Time:** O(n+m) — each node is visited exactly once
- **Space:** O(1) extra — existing nodes are rewired, not recreated

## Dry Run
`l1 = 1 -> 2 -> 4`, `l2 = 1 -> 3 -> 4`

| l1 head | l2 head | attach | 
|---|---|---|
| 1 | 1 | l1's 1 (tie goes to l1 via `<=`) |
| 2 | 1 | l2's 1 |
| 2 | 3 | l1's 2 |
| 4 | 3 | l2's 3 |
| 4 | null | l1's 4, then attach remaining l2's 4 directly |

Result: **1 -> 1 -> 2 -> 3 -> 4 -> 4**

## Edge Cases
- One list is empty -> the loop never runs, and the entire non-empty list is attached directly as "the remainder"
- Both lists empty -> `dummy.next` stays `null`, correctly returns an empty list
- Duplicate values across both lists -> the `<=` tie-breaking rule ensures both copies are kept, output length correctly equals the sum of both input lengths

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (sort from scratch) | O((n+m) log(n+m)) | O(n+m) |
| Optimized (dummy head + merge) | O(n+m) | O(1) extra |

## Related Problems / Pattern Family
- Merge K Sorted Lists (Module 5 #15 — this exact merge used as a subroutine, applied pairwise via divide and conquer)
- Sort List (Module 8 — Sorting & Searching, uses this merge step inside a full merge sort on a linked list)
