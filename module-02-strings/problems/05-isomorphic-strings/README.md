# 5. Isomorphic Strings

**Difficulty:** Easy
**Pattern:** Bijective HashMap Mapping (two-way consistency check)
**LeetCode:** https://leetcode.com/problems/isomorphic-strings/

## Problem Summary
Given two strings `s` and `t`, determine if they are isomorphic: every character in `s` can be replaced to get `t`, with each character mapping to exactly one other character, and no two characters mapping to the same character (a true one-to-one, onto mapping).

## Example
```
Input:  s = "egg", t = "add"
Output: true        (e->a, g->d, consistently)
```
```
Input:  s = "foo", t = "bar"
Output: false       (o would need to map to both 'a' and 'r')
```

## Pattern Recognition
Whenever a problem needs a mapping to be **consistent in both directions** (`foo`/`bar` fails specifically because two different source characters would need to map to the same target), a single one-way map isn't enough — you need to verify both "does `s[i]` always map to the same `t[i]`" *and* "is that target character already claimed by a different source character."

## Approach 1: Brute Force
For each index `i`, check that every previous occurrence of `s[i]` earlier in the string maps to the same `t[i]`, by re-scanning the processed prefix each time.

- **Time:** O(n²) — a re-scan from index `i` back through everything already processed
- **Space:** O(1)
- **Why it's not good enough:** you already saw the mapping for a repeated character the first time it appeared — there's no reason to re-derive it by scanning backward every single time.

## Approach 2: Optimized (Two HashMaps, One Pass)
Maintain two maps: `sToT` (character in `s` -> character in `t`) and `tToS` (the reverse). At each index `i`:
- If `sToT` already has a mapping for `s[i]`, it must equal `t[i]` — otherwise fail.
- If `tToS` already has a mapping for `t[i]`, it must equal `s[i]` — otherwise fail (this is what catches the `foo`/`bar` case).
- Otherwise, record both mappings and continue.

- **Time:** O(n)
- **Space:** O(k) where k is the alphabet size (bounded, so effectively O(1) for typical inputs)

## Dry Run
`s = "foo"`, `t = "bar"`

| i | s[i] | t[i] | check | result |
|---|---|---|---|---|
| 0 | f | b | neither map has an entry -> record f->b, b->f | continue |
| 1 | o | a | neither map has an entry -> record o->a, a->o | continue |
| 2 | o | r | `sToT` already has o->a, but t[i] is r, not a -> **mismatch** | return false |

Result: **false**

## Edge Cases
- Equal-length strings guaranteed by the problem, but always check length first if that guarantee isn't given
- Same character mapping to itself throughout, e.g. `s = "abc", t = "abc"` -> trivially isomorphic
- A character that maps to itself in one string but a repeated different character shows up later, e.g. `s = "ab", t = "aa"` -> fails on the reverse-map check, since `a` can't be the target for both `a` and `b`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) |
| Optimized (two hashmaps) | O(n) | O(k), bounded alphabet |

## Related Problems / Pattern Family
- Word Pattern (identical bijective-mapping idea, applied to words instead of characters)
- Valid Anagram (Module 2 #3 — composition matters but mapping/order does not, a useful contrast)
