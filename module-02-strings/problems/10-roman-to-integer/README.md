# 10. Roman to Integer

**Difficulty:** Easy
**Pattern:** HashMap Lookup + Lookahead Subtraction
**LeetCode:** https://leetcode.com/problems/roman-to-integer/

## Problem Summary
Given a Roman numeral string, convert it to its integer value. Roman numerals normally add up left to right, except for six special subtractive pairs (IV=4, IX=9, XL=40, XC=90, CD=400, CM=900) where a smaller symbol before a larger one means "subtract."

## Example
```
Input:  s = "MCMXCIV"
Output: 1994
```

## Pattern Recognition
"Mostly additive, with special-cased exceptions based on order" is a strong signal for a **single pass with a one-step lookahead**: at each position, you only need to peek at the *next* character to decide whether the current one should be added or subtracted — you never need to look further than that.

## Approach 1: Brute Force (Normalize, Then Sum)
Replace each of the six subtractive pairs with an equivalent additive expansion (e.g. `"IV"` -> `"IIII"`) using string replacement, so the resulting string is purely additive. Then sum the value of each individual character via a lookup map.

- **Time:** O(n) — but with several full passes over the string (one `replace()` call per subtractive pair, plus a final summation pass), so the constant factor is meaningfully higher
- **Space:** O(n) — each `replace()` call can produce a new, longer intermediate string
- **Why it's not good enough:** it's still linear time, but it does noticeably more work — up to seven full string scans/allocations instead of one — to sidestep a check that a single lookahead comparison handles directly.

## Approach 2: Optimized (Single Pass with Lookahead)
Walk the string once. At each index `i`, look up the value of `s[i]`. If it's smaller than the value of `s[i+1]` (the very next character), it's part of a subtractive pair — subtract it from the running total. Otherwise, add it.

- **Time:** O(n) — exactly one pass, one comparison per character
- **Space:** O(1) — the value lookup map has a fixed size of 7 symbols

## Dry Run
`s = "MCMXCIV"`

| i | s[i] | value | next value | subtract? | running total |
|---|---|---|---|---|---|
| 0 | M | 1000 | 100 (C) | no (1000 >= 100) | 1000 |
| 1 | C | 100 | 1000 (M) | yes (100 < 1000) | 900 |
| 2 | M | 1000 | 100 (X) | no | 1900 |
| 3 | X | 10 | 100 (C) | yes | 1890 |
| 4 | C | 100 | 1 (I) | no | 1990 |
| 5 | I | 1 | 5 (V) | yes | 1989 |
| 6 | V | 5 | (none, last char) | no | 1994 |

Result: **1994**

## Edge Cases
- Single-symbol input, e.g. `"X"` -> the loop's lookahead simply never triggers a subtraction, value is `10`
- A value at the very last position -> there's no "next" character to compare against, so it's always added, never subtracted (handled by the boundary check `i + 1 < n`)
- Valid Roman numerals never repeat a subtractive pattern in a way that breaks this logic, since the problem guarantees well-formed input

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (normalize then sum) | O(n) (higher constant) | O(n) |
| Optimized (single pass, lookahead) | O(n) | O(1) |

## Related Problems / Pattern Family
- Integer to Roman (Module 2 #11 — the exact inverse conversion)
