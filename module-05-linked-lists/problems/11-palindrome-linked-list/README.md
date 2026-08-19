# 11. Palindrome Linked List

**Difficulty:** Easy
**Pattern:** Fast/Slow (Find Middle) + In-Place Reversal
**LeetCode:** https://leetcode.com/problems/palindrome-linked-list/

## Problem Summary
Given the head of a singly linked list, return `true` if it reads the same forward and backward.

## Example
```
Input:  1 -> 2 -> 2 -> 1
Output: true
```

## Pattern Recognition
A palindrome check normally uses two pointers converging from both ends (Module 2's Valid Palindrome) — but a singly linked list has no "walk backward from the end" ability. The fix combines two techniques you already know: use **fast/slow pointers** (Module 3) to find the middle, then **reverse** (#1) the second half so you *can* walk it "backward" — after which the problem becomes a standard two-list comparison.

## Approach 1: Brute Force
Copy every value into an array/list, then use two pointers converging from both ends of that array (exactly like Module 2's Valid Palindrome) to check for a mismatch.

- **Time:** O(n)
- **Space:** O(n) — the copied values
- **Why it's not good enough:** it works, and it's linear time — but it uses memory proportional to the list's length just to get "backward" access that the list itself doesn't offer.

## Approach 2: Optimized (Find Middle, Reverse Second Half, Compare)
1. Use fast/slow pointers to find the middle of the list.
2. Reverse the second half of the list in place (starting from the middle).
3. Walk the first half and the reversed second half simultaneously, comparing values.

- **Time:** O(n) — each phase is a single linear pass
- **Space:** O(1) — only the existing nodes are rewired

## Dry Run
`1 -> 2 -> 2 -> 1`

Fast/slow finds `slow` at the second `2` (the standard "second middle" for even length). Reverse from there: the second half `2 -> 1` becomes `1 -> 2`.

Compare first half (`1 -> 2`) against reversed second half (`1 -> 2`): both match at every position.

Result: **true**

## Edge Cases
- Empty list or single node -> trivially a palindrome, no comparison needed
- Odd-length list, e.g. `1 -> 2 -> 3 -> 2 -> 1` -> the middle node (`3`) doesn't need to match anything; the comparison loop naturally stops once the (shorter) reversed second half is exhausted
- Not a palindrome, e.g. `1 -> 2` -> the comparison fails on the first mismatch, correctly returns `false`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (array copy) | O(n) | O(n) |
| Optimized (find middle + reverse + compare) | O(n) | O(1) |

## Related Problems / Pattern Family
- Reverse Linked List (Module 5 #1 — the reversal building block used here)
- Middle of the Linked List (Module 3 #11 — the middle-finding building block used here)
- Reorder List (Module 5 #12 — combines the exact same two building blocks for a different goal)
