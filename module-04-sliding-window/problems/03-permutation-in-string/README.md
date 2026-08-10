# 3. Permutation in String

**Difficulty:** Medium
**Pattern:** Fixed-Size Sliding Window + Frequency Count
**LeetCode:** https://leetcode.com/problems/permutation-in-string/

## Problem Summary
Given two strings `s1` and `s2`, return `true` if `s2` contains a permutation of `s1` — that is, some contiguous substring of `s2` that's an anagram of `s1`.

## Example
```
Input:  s1 = "ab", s2 = "eidbaooo"
Output: true        ("ba" is an anagram of "ab")
```

## Pattern Recognition
"Some contiguous substring is an anagram of a fixed pattern" combines two things you've already seen: a **fixed-size window** (the substring must be exactly `len(s1)` long) and **frequency counting** (Valid Anagram, Module 2 #3). Slide a window of that fixed size across `s2`, and compare its character-frequency signature to `s1`'s.

## Approach 1: Brute Force
For every window of size `len(s1)` in `s2`, build a fresh frequency count and compare it to `s1`'s frequency count.

- **Time:** O(n * m) — n possible windows, each requiring O(m) work to build and compare a frequency array (m = `len(s1)`)
- **Space:** O(1) — fixed 26-length arrays
- **Why it's not good enough:** rebuilding the entire frequency count from scratch for every window ignores that consecutive windows share all but two characters.

## Approach 2: Optimized (Sliding Window, Incremental Frequency Update)
Build the frequency array for `s1` once. Build the frequency array for the first window of `s2`. Compare them. Then slide: for each new position, increment the count for the character entering and decrement the count for the character leaving, and compare again.

- **Time:** O(n) — the frequency array comparison is O(26) = O(1), and each slide is O(1)
- **Space:** O(1) — fixed 26-length arrays

## Dry Run
`s1 = "ab"`, `s2 = "eidbaooo"`

| window | frequency matches s1's {a:1,b:1}? |
|---|---|
| "ei" | no |
| "id" | no |
| "db" | no |
| "ba" | **yes** -> return true |

Result: **true**

## Edge Cases
- `s1` longer than `s2` -> no window of that size can even exist, return `false` immediately
- `s1` and `s2` are identical -> the only window is the whole string, and it trivially matches
- No permutation exists anywhere -> every window comparison fails, correctly returns `false` after scanning all of `s2`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n*m) | O(1) |
| Optimized (incremental window) | O(n) | O(1) |

## Related Problems / Pattern Family
- Find All Anagrams in a String (Module 4 #4 — the exact same technique, collecting every match instead of stopping at the first)
- Valid Anagram (Module 2 #3 — the frequency-comparison building block)
