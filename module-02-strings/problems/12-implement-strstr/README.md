# 12. Implement strStr() (Find the Index of the First Occurrence in a String)

**Difficulty:** Easy (the brute force); the optimal solution (KMP) is a classic Medium/Hard algorithm
**Pattern:** Brute Force Substring Match -> Knuth-Morris-Pratt (KMP)
**LeetCode:** https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/

## Problem Summary
Given two strings `haystack` and `needle`, return the index of the first occurrence of `needle` in `haystack`, or `-1` if `needle` isn't part of `haystack`.

## Example
```
Input:  haystack = "sadbutsad", needle = "sad"
Output: 0
```

## Pattern Recognition
"Find a substring inside a larger string" is the textbook use case for **KMP**, whose entire trick is: when a match attempt fails partway through, don't restart the haystack pointer from scratch — the pattern itself tells you exactly how far you can safely skip, because you already know which prefix of the pattern you'd just matched.

## Approach 1: Brute Force
For every starting index in `haystack`, check character-by-character whether `needle` matches starting there.

- **Time:** O(n · m) where `n = haystack.length()`, `m = needle.length()` — in the worst case (e.g. haystack `"aaaa...a"`, needle `"aaa...ab"`), you redo almost the same comparisons from every starting position
- **Space:** O(1)
- **Why it's not good enough:** every failed match attempt throws away information. If you'd already matched the first `k` characters of `needle` before failing, KMP can prove that a large chunk of the next few starting positions can't possibly work either — but brute force checks them anyway.

## Approach 2: Optimized (Knuth-Morris-Pratt)
1. **Precompute the LPS array** ("longest proper prefix that's also a suffix") for `needle`: for each position in the pattern, how long is the longest prefix of the pattern that also appears as a suffix ending at that position? This captures "if I fail here, how much of what I've already matched can I reuse?"
2. **Scan `haystack` once**, using two pointers (`i` for haystack, `j` for needle). On a match, advance both. On a mismatch, instead of resetting `i` back to the start, use the LPS array to jump `j` backward to the right resumption point — `i` never moves backward.

- **Time:** O(n + m) — the LPS array costs O(m) to build, and the main scan is O(n), with no backtracking on the haystack pointer
- **Space:** O(m) — the LPS array

## Dry Run
`needle = "sad"` has LPS array `[0, 0, 0]` (no proper prefix of "sad" is also a suffix, at any length).

`haystack = "sadbutsad"`:

| i (haystack idx) | j (needle idx) | comparison | action |
|---|---|---|---|
| 0 | 0 | 's' == 's' | match, i=1, j=1 |
| 1 | 1 | 'a' == 'a' | match, i=2, j=2 |
| 2 | 2 | 'd' == 'd' | match, i=3, j=3=m -> found! return i-j=0 |

Result: **0**

## Edge Cases
- `needle` is an empty string -> conventionally return `0` (an empty string matches at the start)
- `needle` longer than `haystack` -> no match possible, return `-1` immediately as an optimization
- `needle` doesn't appear at all -> KMP's main loop finishes with `j` never reaching `m`, return `-1`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n * m) | O(1) |
| Optimized (KMP) | O(n + m) | O(m) |

## Related Problems / Pattern Family
- Repeated Substring Pattern (uses the KMP LPS array directly)
- Shortest Palindrome (KMP-based construction trick)
- Longest Common Prefix (Module 2 #7 — a simpler cousin, no backtracking needed)
