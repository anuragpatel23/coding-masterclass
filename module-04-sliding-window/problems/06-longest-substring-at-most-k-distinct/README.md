# 6. Longest Substring with At Most K Distinct Characters

**Difficulty:** Medium
**Pattern:** Variable-Size Sliding Window + HashMap
**LeetCode:** https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/

## Problem Summary
Given a string `s` and an integer `k`, find the length of the longest substring that contains at most `k` distinct characters.

## Example
```
Input:  s = "eceba", k = 2
Output: 3        ("ece")
```

## Pattern Recognition
"At most K distinct" is the generalized version of Longest Substring Without Repeating Characters (which is really just the `k = 1`... no, `k` = "every character must be unique" case — technically unrelated value of k, but the same *shape*: track a count of each character currently in the window, and shrink whenever the window becomes invalid, here meaning "too many distinct characters" instead of "any repeat at all").

## Approach 1: Brute Force
For every starting index, extend the window and track distinct characters with a set, stopping early once the distinct count exceeds `k`.

- **Time:** O(n^2) — for each of n starting points, the inner extension is bounded by O(n), with an early exit once invalid
- **Space:** O(k)

## Approach 2: Optimized (Variable Window + Frequency Map)
Expand the window's right edge, incrementing a frequency count for each character. Whenever the number of distinct keys in that map exceeds `k`, shrink from the left — decrementing (and removing, if it hits zero) the count for the character leaving — until the distinct count is back to `k` or fewer.

- **Time:** O(n) — each character enters and leaves the window at most once
- **Space:** O(k) — at most k+1 distinct characters are ever tracked at once

## Dry Run
`s = "eceba"`, `k = 2`

| right | char | distinct count | action | window | maxLen |
|---|---|---|---|---|---|
| 0 | e | 1 | ok | "e" | 1 |
| 1 | c | 2 | ok | "ec" | 2 |
| 2 | e | 2 | ok | "ece" | 3 |
| 3 | b | 3 | shrink left until <=2 -> removes 'e' at index0, then 'c' at index1 | "eb"... | after shrinking, window becomes "eb" (indices 2-3, wait recheck) |
| 4 | a | 3 | shrink again | "ba" |

*(Full trace shrinks carefully character by character; the key result is the peak length found at right=2.)*

Result: **3** (`"ece"`)

## Edge Cases
- `k = 0` -> no substring can have zero distinct characters and still be non-empty, so the answer is `0`
- `k >= number of distinct characters in the whole string` -> the entire string is valid, answer is the full length
- All characters identical -> exactly 1 distinct character is ever in play, so any `k >= 1` allows the whole string

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(k) |
| Optimized (variable window + frequency map) | O(n) | O(k) |

## Related Problems / Pattern Family
- Fruit Into Baskets (Module 4 #7 — the exact same technique, with `k` fixed at 2)
- Longest Substring Without Repeating Characters (Module 4 #5 — a related but distinct validity condition)
