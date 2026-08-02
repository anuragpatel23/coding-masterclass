# 2. Valid Palindrome II

**Difficulty:** Easy
**Pattern:** Two Pointers + One Allowed Deletion
**LeetCode:** https://leetcode.com/problems/valid-palindrome-ii/

## Problem Summary
Given a string, return `true` if it can become a palindrome after deleting **at most one** character.

## Example
```
Input:  s = "abca"
Output: true        (delete 'b' or 'c' to get "aca" or "aba")
```

## Pattern Recognition
This builds directly on Valid Palindrome (Module 2 #2). The twist — "at most one deletion allowed" — means that when your two pointers hit their *first* mismatch, you don't immediately fail. Instead, you get exactly one "free pass": try skipping the left character, try skipping the right character, and see if either resulting substring is a full palindrome on its own.

## Approach 1: Brute Force
For every index, try removing that single character and check whether the resulting string is a full palindrome.

- **Time:** O(n^2) — n possible removals, each requiring an O(n) palindrome check
- **Space:** O(n) — each removal creates a new string
- **Why it's not good enough:** you're trying removals at *every* position, even ones nowhere near where the actual mismatch occurs. The moment two pointers converging from the ends hit a mismatch, that tells you exactly which two removals are worth trying — no need to consider any others.

## Approach 2: Optimized (Two Pointers, Branch on First Mismatch)
Run the standard two-pointer palindrome check. The moment `s[left] != s[right]`, you know a deletion (if one is going to save you) must remove either `s[left]` or `s[right]` — nothing else could possibly be the culprit for *this* mismatch. Check both possibilities:
- Is `s[left+1 .. right]` a palindrome (skip the left character)?
- Is `s[left .. right-1]` a palindrome (skip the right character)?

If either is `true`, the original string is palindrome-able with one deletion. If the pointers never hit a mismatch at all, it was already a palindrome with zero deletions needed.

- **Time:** O(n) — the main scan is O(n), and the two branch checks triggered by the first mismatch are also O(n) combined, but this only happens once
- **Space:** O(1)

## Dry Run
`s = "abca"`

| left | right | chars | match? |
|---|---|---|---|
| 0 | 3 | 'a','a' | yes, continue |
| 1 | 2 | 'b','c' | **no** -> branch |

Branch check 1 — skip left (check `s[2..2]` = "c"): trivially a palindrome (single character) -> **true**

Result: **true**

## Edge Cases
- Already a palindrome, e.g. `"aba"` -> pointers never mismatch, zero deletions needed, returns `true`
- Needs exactly two deletions to fix, e.g. `"abccbxa"`... wait — needs to actually verify with the specific one-deletion branch logic; if both branches fail, correctly returns `false`
- Single character or empty string -> trivially `true`, loop never finds a mismatch

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(n) |
| Optimized (two pointers + branch) | O(n) | O(1) |

## Related Problems / Pattern Family
- Valid Palindrome (Module 2 #2 — the zero-deletion base case this problem extends)
- Palindromic Substrings (Module 2 #14 — different technique, Expand Around Center)
