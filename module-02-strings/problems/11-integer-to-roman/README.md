# 11. Integer to Roman

**Difficulty:** Medium
**Pattern:** Greedy Symbol Table / Digit Lookup Table
**LeetCode:** https://leetcode.com/problems/integer-to-roman/

## Problem Summary
Convert an integer (1 to 3999) into its Roman numeral representation.

## Example
```
Input:  num = 1994
Output: "MCMXCIV"
```

## Pattern Recognition
Because the input is tightly bounded (`num <= 3999`), this problem doesn't have a meaningfully different Big-O story between a "naive" and "optimized" solution — both run in a small constant amount of work. The real interview signal here is whether you can build the **correct symbol table** (including all six subtractive pairs) and whether you know a second, even more direct technique: precomputed **digit lookup tables**.

## Approach 1: "Brute Force" (Greedy Subtraction with Full Symbol Table)
Keep a table of all 13 (value, symbol) pairs, from largest to smallest, including the six subtractive combinations (`CM`, `CD`, `XC`, `XL`, `IX`, `IV`). Walk the table from the top: while the remaining number is still at least the current value, append the symbol and subtract the value.

- **Time:** O(1) — bounded by the fixed table size (13 entries) and a small number of subtractions, since `num <= 3999`
- **Space:** O(1) extra (fixed-size table)
- **Note:** this is already quite efficient — it's labeled "Approach 1" here mainly to introduce the digit-table technique below as a genuinely different, branch-free way to solve the same problem.

## Approach 2: Optimized (Digit-by-Digit Lookup Tables)
Precompute four small lookup tables — one each for the thousands, hundreds, tens, and ones digit — containing the correct Roman representation for every digit `0`-`9` (or `0`-`3` for thousands, since `num <= 3999`). Then directly index into each table using the corresponding digit of `num`, with zero loops or comparisons against candidate symbols.

- **Time:** O(1) — four direct array lookups and a string concatenation, no iteration over symbol candidates at all
- **Space:** O(1) extra (fixed-size tables)

## Dry Run
`num = 1994`

**Approach 2 (digit tables):**
- thousands digit = `1994 / 1000 = 1` -> `"M"`
- hundreds digit = `(1994 % 1000) / 100 = 9` -> `"CM"`
- tens digit = `(1994 % 100) / 10 = 9` -> `"XC"`
- ones digit = `1994 % 10 = 4` -> `"IV"`

Concatenate: `"M" + "CM" + "XC" + "IV"` = **"MCMXCIV"**

## Edge Cases
- `num = 3999` (the maximum) -> `"MMMCMXCIX"`, exercising every table's largest entry
- `num` with a zero digit somewhere, e.g. `500` -> hundreds digit `5` -> `"D"`, and the (empty) tens/ones table entries for `0` contribute nothing, correctly producing just `"D"`
- Single-digit numbers, e.g. `num = 4` -> `"IV"` directly from the ones table

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Greedy subtraction (full symbol table) | O(1), bounded by ~13 table entries | O(1) |
| Optimized (digit lookup tables) | O(1), exactly 4 lookups | O(1) |

## Related Problems / Pattern Family
- Roman to Integer (Module 2 #10 — the exact inverse conversion)
