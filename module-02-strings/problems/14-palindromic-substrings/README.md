# 14. Palindromic Substrings

**Difficulty:** Medium
**Pattern:** Expand Around Center
**LeetCode:** https://leetcode.com/problems/palindromic-substrings/

## Problem Summary
Given a string `s`, return the total number of palindromic substrings it contains (different positions count as different substrings, even if the text is identical).

## Example
```
Input:  s = "aaa"
Output: 6        ("a","a","a","aa","aa","aaa")
```

## Pattern Recognition
This is the exact same "every palindrome has a center" insight as Longest Palindromic Substring — the only difference is what you do with each successful expansion: instead of tracking the *longest* one, you **count every successful expansion step**, since each step outward represents one more valid palindromic substring centered there.

## Approach 1: Brute Force
Check every possible substring (via two nested loops for start/end index) and count how many are palindromes.

- **Time:** O(n³) — O(n²) substrings, each verified in up to O(n)
- **Space:** O(1) extra

## Approach 2: Optimized (Expand Around Center, Count Every Match)
For each of the `2n - 1` possible centers (n single-character centers for odd-length palindromes, n-1 between-character gaps for even-length ones), expand outward while characters match. Every successful expansion step is itself a valid palindromic substring — increment the count each time, not just once per center.

- **Time:** O(n²)
- **Space:** O(1) extra

## Dry Run
`s = "aaa"`

| center | type | expansions (each one a palindrome found) | count from this center |
|---|---|---|---|
| index 0 | odd | "a" | 1 |
| gap 0-1 | even | "aa" | 1 |
| index 1 | odd | "a", then "aaa" | 2 |
| gap 1-2 | even | "aa" | 1 |
| index 2 | odd | "a" | 1 |

Total: 1+1+2+1+1 = **6**

## Edge Cases
- Single character -> exactly 1 palindromic substring (itself)
- All distinct characters, e.g. `"abc"` -> exactly `n` palindromic substrings (each single character; no longer ones exist)
- Entire string is one repeated character, e.g. `"aaaa"` -> every substring is a palindrome, giving the maximum possible count of `n(n+1)/2`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^3) | O(1) extra |
| Optimized (Expand Around Center) | O(n^2) | O(1) extra |

## Related Problems / Pattern Family
- Longest Palindromic Substring (Module 2 #13 — identical technique, different bookkeeping)
- Palindrome Partitioning (Module 10 — Recursion & Backtracking, builds on palindrome-checking as a subroutine)
