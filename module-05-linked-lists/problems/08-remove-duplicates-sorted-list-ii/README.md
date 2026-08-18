# 8. Remove Duplicates from Sorted List II

**Difficulty:** Medium
**Pattern:** Dummy Head + Skip-Entire-Run Logic
**LeetCode:** https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

## Problem Summary
Given a sorted linked list, remove **all** nodes that have a duplicate value, leaving only the numbers that appeared exactly once in the original list.

## Example
```
Input:  1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
Output: 1 -> 2 -> 5
```

## Pattern Recognition
This looks like problem #3, but it's fundamentally different: instead of *collapsing* a run of duplicates down to one, you need to **delete the entire run**, including the first occurrence. That means you can't decide whether to keep a node until you've looked ahead to confirm whether it's part of a duplicate run — which is exactly the kind of edge case the dummy head is built to simplify (especially since the very first node might itself need to be deleted).

## Approach 1: Brute Force
Count every value's occurrences (using a `LinkedHashMap` to preserve sorted order), then rebuild the list keeping only values whose count is exactly 1.

- **Time:** O(n)
- **Space:** O(n) — the counting map, plus new nodes

## Approach 2: Optimized (Dummy Head + Look-Ahead Skip)
Keep a `prev` pointer (starting at the dummy head) and a `current` pointer (starting at the real head). If `current`'s value matches the *next* node's value, you've found a duplicate run — advance `current` through the entire run, then connect `prev.next` directly to whatever comes after the run (skipping it entirely). Otherwise, `current` is a keeper — advance both `prev` and `current` normally.

- **Time:** O(n) — each node is visited once
- **Space:** O(1) — nodes are rewired in place

## Dry Run
`1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5`

| current | is duplicate run? | action |
|---|---|---|
| 1 | no (next is 2) | keep: prev=1, current=2 |
| 2 | no (next is 3) | keep: prev=2, current=3 |
| 3 (first) | yes (next is also 3) | skip entire run: prev.next = 4 (first), current=4 (first) |
| 4 (first) | yes (next is also 4) | skip entire run: prev.next = 5, current=5 |
| 5 | no (next is null) | keep: prev=5, current=null |

Result: **1 -> 2 -> 5**

## Edge Cases
- The head itself is part of a duplicate run -> the dummy head means `prev` starts *before* the real head, so skipping the run still correctly updates what becomes the new head
- Every value in the list is duplicated -> the entire list collapses to empty, `dummy.next` stays `null`
- No duplicates at all -> every node is kept, list returned effectively unchanged

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (LinkedHashMap) | O(n) | O(n) |
| Optimized (dummy head + look-ahead skip) | O(n) | O(1) |

## Related Problems / Pattern Family
- Remove Duplicates from Sorted List (Module 5 #3 — keeps one copy instead of deleting the whole run)
- Remove Nth Node From End of List (Module 5 #4 — another dummy-head-powered removal problem)
