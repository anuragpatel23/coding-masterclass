# 14. Minimum Window Substring

**Difficulty:** Hard
**Pattern:** Variable-Size Sliding Window + HashMap
**LeetCode:** https://leetcode.com/problems/minimum-window-substring/

## Problem Summary
Given strings `s` and `t`, find the smallest substring of `s` that contains every character of `t` (including matching multiplicity — if `t` has two `'a'`s, the window needs at least two). Return `""` if no such window exists.

## Example
```
Input:  s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
```

## Pattern Recognition
This is the module's capstone because it combines everything: a **variable window** (Minimum Size Subarray Sum's shrink-while-valid shape) with a **frequency-map validity condition** (Permutation in String's character-counting) that's more complex than a single number — you need to track not just "how many characters have I matched" but "have I matched the *required count* of each distinct character."

## Approach 1: Brute Force
For every starting index, extend rightward until the window contains all of `t`'s characters (checked via a fresh frequency comparison each time), then stop — extending further can only make that particular window longer.

- **Time:** O(n^3) (loose bound) — n starting points, up to n ending points, each containment check costing up to O(n) to build and compare frequency maps
- **Space:** O(n) for the frequency maps built per check

## Approach 2: Optimized (Variable Window + "Formed vs. Required" Counter)
Build a frequency map of `t`'s required characters. Expand the window's right edge, updating a "window frequency" map. Track `formed` — the number of *distinct* characters in `t` whose required count has been fully met in the current window — versus `required` — the total distinct characters `t` needs. The window is valid exactly when `formed == required`.

Whenever the window is valid, try to shrink it from the left as far as possible while it *stays* valid, recording the smallest valid window seen.

- **Time:** O(n + m) — each character in `s` is visited a bounded number of times, plus O(m) to build the initial requirement map
- **Space:** O(m) for the requirement map, O(k) for the window map (k = distinct characters in the current window)

## Dry Run
`s = "ADOBECODEBANC"`, `t = "ABC"` (need A:1, B:1, C:1)

The window expands until it first contains all of A, B, C (this happens around index 9, giving `"ADOBECODEB...C"` roughly). From there, the left edge shrinks as far as possible while the window remains valid, eventually converging on the tightest valid window found across the whole scan.

Result: **"BANC"**

## Edge Cases
- `t` longer than `s` -> no valid window can exist, return `""` immediately
- `t` contains a character not present anywhere in `s` -> `formed` can never reach `required`, correctly returns `""` after the full scan
- `s` itself equals `t` -> the only valid window is the entire string

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^3) (loose) | O(n) |
| Optimized (variable window + formed/required) | O(n + m) | O(m + k) |

## Related Problems / Pattern Family
- Minimum Size Subarray Sum (Module 4 #8 — the same shrink-while-valid shape, a simpler numeric condition)
- Permutation in String (Module 4 #3 — frequency-map comparison, but on a fixed-size window)
