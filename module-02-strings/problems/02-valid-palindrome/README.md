# 2. Valid Palindrome

**Difficulty:** Easy
**Pattern:** Two-Pointer + Character Filtering
**LeetCode:** https://leetcode.com/problems/valid-palindrome/

## Problem Summary
Given a string, determine if it's a palindrome after converting all uppercase letters to lowercase and removing all non-alphanumeric characters.

## Example
```
Input:  "A man, a plan, a canal: Panama"
Output: true
```

## Pattern Recognition
This is Reverse String's two-pointer idea, plus a filtering step. Whenever a two-pointer comparison needs to "skip over" characters that don't count, the fix is simple: advance the pointer past invalid characters *before* doing the comparison at each step, rather than pre-processing the whole string first.

## Approach 1: Brute Force
Build a cleaned string containing only lowercase alphanumeric characters, then compare it to its own reverse.

- **Time:** O(n)
- **Space:** O(n) — the cleaned string (and its reverse)
- **Why it's not good enough:** you don't actually need to materialize a cleaned copy — everything the cleaned string would tell you can be determined by comparing characters directly from the original string with two smart pointers.

## Approach 2: Optimized (Two Pointers with Skip Logic)
Start `left` at index 0 and `right` at the last index. At each step:
1. Advance `left` forward while it points to a non-alphanumeric character.
2. Move `right` backward while it points to a non-alphanumeric character.
3. Compare the lowercase versions of `s[left]` and `s[right]`. If they differ, it's not a palindrome.
4. Move both pointers inward and repeat.

- **Time:** O(n)
- **Space:** O(1)

## Dry Run
`"A man, a plan, a canal: Panama"` (spaces/punctuation skipped, case-insensitive)

Effectively comparing: `a-m-a-n-a-p-l-a-n-a-c-a-n-a-l-p-a-n-a-m-a` (the letters read the same forwards and backwards) → pointers meet in the middle without ever finding a mismatch.

Result: **true**

## Edge Cases
- Empty string or all-punctuation string (e.g. `",."`) → after skipping, pointers cross immediately → considered a valid palindrome (vacuously true)
- Single character → trivially a palindrome
- Mixed case, e.g. `"Aa"` → must lowercase before comparing, or the naive comparison would incorrectly say "not equal"

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (cleaned copy) | O(n) | O(n) |
| Optimized (two pointers + skip) | O(n) | O(1) |

## Related Problems / Pattern Family
- Reverse String (Module 2 #1 — the base two-pointer swap, no filtering)
- Valid Palindrome II (allows removing up to one character — Module 3, Two Pointers)
- Longest Palindromic Substring (Module 2 #13 — a different technique, Expand Around Center)
