# 13. Longest Palindromic Substring

**Difficulty:** Medium
**Pattern:** Expand Around Center
**LeetCode:** https://leetcode.com/problems/longest-palindromic-substring/

## Problem Summary
Given a string `s`, return the longest substring of `s` that is a palindrome.

## Example
```
Input:  s = "babad"
Output: "bab"        ("aba" is also a valid answer)
```

## Pattern Recognition
Every palindrome has a center — either a single character (odd length, like `"aba"`) or a gap between two characters (even length, like `"abba"`). Instead of checking every substring from the outside in, you can check every possible **center** and grow outward while the two sides keep matching. This flips the problem from "generate and verify" to "grow and measure," and is the standard technique whenever a problem is about palindromic substrings specifically.

## Approach 1: Brute Force
Generate every possible substring (using two nested loops for start and end index), check whether each one is a palindrome, and keep the longest one found.

- **Time:** O(n³) — O(n²) substrings, each requiring up to O(n) to verify as a palindrome
- **Space:** O(1) extra (beyond the substrings themselves)
- **Why it's not good enough:** the palindrome check for one substring shares almost all of its work with the checks for overlapping substrings, but nothing is reused between them.

## Approach 2: Optimized (Expand Around Center)
For every index in the string, treat it as a potential center and expand outward in both directions while the characters match — once for an **odd-length** palindrome centered on that single index, and once for an **even-length** palindrome centered on the gap between that index and the next. Track the longest expansion found across all `2n - 1` possible centers.

- **Time:** O(n²) — n centers, each expansion taking up to O(n)
- **Space:** O(1) extra

## Dry Run
`s = "babad"`

| center | type | expansion | length found |
|---|---|---|---|
| index 0 ('b') | odd | just "b" | 1 |
| index 1 ('a') | odd | "bab" (expands: a -> b,b match) | 3 |
| index 2 ('b') | odd | "aba" (expands: b -> a,a match) | 3 |
| index 3 ('a') | odd | "ada"? -> checks index2='b' vs index4='d', no match beyond "a" | 1 |
| index 4 ('d') | odd | just "d" | 1 |
| (even-length centers) | even | no adjacent equal pairs found in this example | 0 |

Longest found: length 3, first one encountered is **"bab"** (starting at index 0)

## Edge Cases
- Single-character string -> trivially a palindrome of length 1
- Entire string is a palindrome, e.g. `"racecar"` -> the center expansion at the middle character grows all the way to both ends
- All characters distinct, e.g. `"abcde"` -> the longest palindromic substring is any single character (length 1)

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^3) | O(1) extra |
| Optimized (Expand Around Center) | O(n^2) | O(1) extra |

*(A further optimization, Manacher's Algorithm, solves this in O(n) — worth knowing exists, rarely required to implement from scratch in an interview.)*

## Related Problems / Pattern Family
- Palindromic Substrings (Module 2 #14 — the exact same expansion technique, counting instead of tracking the longest)
- Valid Palindrome (Module 2 #2 — checking one specific substring rather than searching for the best one)
- Longest Palindromic Subsequence (Module 16 — Dynamic Programming, a trickier "subsequence" variant)
