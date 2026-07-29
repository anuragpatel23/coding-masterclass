# 4. First Unique Character in a String

**Difficulty:** Easy
**Pattern:** Frequency Counting + Single Pass
**LeetCode:** https://leetcode.com/problems/first-unique-character-in-a-string/

## Problem Summary
Given a string, find the index of the first character that does not repeat anywhere else in the string. If none exists, return -1.

## Example
```
Input:  s = "leetcode"
Output: 0        ('l' appears only once, and it's the first such character)
```

## Pattern Recognition
"First character that satisfies some global property (uniqueness)" is a two-phase signal: you need to know something about the *whole* string (how many times each character appears) before you can answer a question about *position*. That two-phase shape — count everything first, then scan again to answer — is extremely common once you start looking for it.

## Approach 1: Brute Force
For each character, scan the entire string to count how many times it appears. Return the first index where that count is 1.

- **Time:** O(n²) — a full O(n) scan triggered from every one of the n positions
- **Space:** O(1)
- **Why it's not good enough:** you're recomputing the same frequency information over and over. The count of `'e'` doesn't change no matter which index you're currently asking about — compute it once, not n times.

## Approach 2: Optimized (Count Once, Scan Once)
**Pass 1:** build a frequency map of every character in the string.
**Pass 2:** walk the string again in order, and return the index of the first character whose frequency is exactly 1.

- **Time:** O(n) — two linear passes, not one squared
- **Space:** O(1) — bounded alphabet (26 lowercase letters, or O(k) for a general alphabet of size k)

## Dry Run
`s = "leetcode"`

**Pass 1 (counts):** l:1, e:3, t:1, c:1, o:1, d:1

**Pass 2 (find first count==1):**
| i | char | count | is it 1? |
|---|---|---|---|
| 0 | l | 1 | yes -> return 0 |

Result: **0**

## Edge Cases
- No unique character exists, e.g. `"aabb"` -> return `-1` after the second pass finds nothing
- Single-character string -> that character is trivially unique, return `0`
- All characters unique, e.g. `"abcd"` -> returns `0` (the very first character)

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) |
| Optimized (count then scan) | O(n) | O(1) (bounded alphabet) |

## Related Problems / Pattern Family
- Valid Anagram (Module 2 #3 — the same frequency-counting building block)
- Contains Duplicate (Module 1 #2 — membership rather than frequency, but the same "remember what you've seen" mindset)
