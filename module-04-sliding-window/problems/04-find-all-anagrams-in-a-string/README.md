# 4. Find All Anagrams in a String

**Difficulty:** Medium
**Pattern:** Fixed-Size Sliding Window + Frequency Count
**LeetCode:** https://leetcode.com/problems/find-all-anagrams-in-a-string/

## Problem Summary
Given strings `s` and `p`, return all starting indices of `p`'s anagrams in `s`.

## Example
```
Input:  s = "cbaebabacd", p = "abc"
Output: [0,6]
```

## Pattern Recognition
This is Permutation in String (#3) with one change: instead of stopping at the first match, collect **every** matching window's starting index. Same fixed-size window, same incremental frequency-count comparison.

## Approach 1: Brute Force
For every window of size `len(p)`, build a fresh frequency count and compare it to `p`'s.

- **Time:** O(n * m)
- **Space:** O(1) — fixed 26-length arrays (plus the output list)

## Approach 2: Optimized (Sliding Window, Incremental Frequency Update)
Same incremental update as Permutation in String — add the entering character's count, remove the leaving character's count — but record every index where the window's frequency matches `p`'s, rather than returning on the first one.

- **Time:** O(n)
- **Space:** O(1) extra (plus the output list)

## Dry Run
`s = "cbaebabacd"`, `p = "abc"` (target frequency: {a:1, b:1, c:1})

| window (start index) | matches? |
|---|---|
| "cba" (0) | **yes** |
| "bae" (1) | no |
| "aeb" (2) | no |
| "eba" (3) | no |
| "bab" (4) | no |
| "aba" (5) | no |
| "bac" (6) | **yes** |
| "acd" (7) | no |

Result: **[0, 6]**

## Edge Cases
- `p` longer than `s` -> no window can exist, return an empty list
- Every window matches (e.g. `s` and `p` are anagrams of the same repeated pattern) -> every valid index gets collected
- No anagram present anywhere -> empty list returned after a full scan

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n*m) | O(1) extra |
| Optimized (incremental window) | O(n) | O(1) extra |

## Related Problems / Pattern Family
- Permutation in String (Module 4 #3 — identical technique, stops at the first match instead of collecting all)
- Group Anagrams (Module 2 #6 — a different problem shape, same underlying frequency-signature idea)
