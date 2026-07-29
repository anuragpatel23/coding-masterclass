# 3. Valid Anagram

**Difficulty:** Easy
**Pattern:** Character Frequency Counting
**LeetCode:** https://leetcode.com/problems/valid-anagram/

## Problem Summary
Given two strings `s` and `t`, return `true` if `t` is an anagram of `s` (uses exactly the same characters, same counts, any order).

## Example
```
Input:  s = "anagram", t = "nagaram"
Output: true
```

## Pattern Recognition
"Same characters, same counts, order doesn't matter" is the definition of a frequency-count comparison. Any time order is explicitly irrelevant but composition matters, counting beats sorting or direct comparison.

## Approach 1: Brute Force (Sorting)
Sort both strings' characters and compare the results for equality.

- **Time:** O(n log n) — dominated by the sort
- **Space:** O(n) — for the sorted character arrays
- **Why it's not good enough:** sorting is overkill for a problem that's really just "do these two multisets of characters match" — counting gets you there in linear time.

## Approach 2: Optimized (Frequency Array)
If the alphabet is small and known (lowercase English letters), use a fixed-size `int[26]` array instead of a `HashMap`. Increment counts for every character in `s`, decrement for every character in `t`. If any count ends up nonzero, they're not anagrams.

- **Time:** O(n)
- **Space:** O(1) — the array size is fixed at 26 regardless of input size

## Dry Run
`s = "anagram"`, `t = "nagaram"`

Increment for `s`: a:3, n:1, g:1, r:1, m:1
Decrement for `t`: n(-1→0), a(-1→2,-1→1,-1→0), g(-1→0), r(-1→0), m(-1→0)

All counts land back at 0 → **true**

## Edge Cases
- Different lengths → return `false` immediately (a quick early check before doing any counting work)
- Non-lowercase-letter inputs (Unicode, uppercase) → switch to a `HashMap<Character, Integer>` instead of a fixed `int[26]` array
- Empty strings → both are trivially anagrams of each other (`true`)

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (sorting) | O(n log n) | O(n) |
| Optimized (frequency array) | O(n) | O(1) (fixed alphabet) |

## Related Problems / Pattern Family
- Group Anagrams (Module 2 #6 — the same frequency-signature idea, extended to grouping many strings)
- Find All Anagrams in a String (Module 4 — Sliding Window, frequency counting inside a moving window)
