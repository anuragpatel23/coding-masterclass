# 10. Largest Number

**Difficulty:** Medium
**Pattern:** Custom Comparator
**LeetCode:** https://leetcode.com/problems/largest-number/

## Problem Summary
Given a list of non-negative integers, arrange them to form the largest possible number, returned as a string.

## Example
```
Input:  nums = [3,30,34,5,9]
Output: "9534330"
```

## Pattern Recognition
Sorting by numeric value descending gives the wrong answer here (`30` vs `3`: is `330` or `303` bigger?). The fix is a genuinely different **custom comparator**: instead of comparing two numbers directly, compare the two possible *concatenations* — for numbers `a` and `b`, `a` should come before `b` exactly when `a+b > b+a` as strings. This comparator is provably consistent (transitive), which is what makes a standard sort work correctly with it.

## Approach 1: Brute Force
Generate every permutation of the numbers (as strings), concatenate each one, and keep the lexicographically largest result (which, for same-length strings built from the same characters, corresponds exactly to the largest number).

- **Time:** O(n! * n) — factorial, only viable for tiny inputs
- **Space:** O(n! * n) — every permutation

## Approach 2: Optimized (Custom Comparator Sort)
Convert every number to a string. Sort the strings using the comparator `(a, b) -> (b+a).compareTo(a+b)` — this places whichever ordering produces the larger concatenation first. Join the sorted strings. Handle one edge case: if the result starts with `'0'`, every number was zero, so the answer should just be `"0"`.

- **Time:** O(n log n * m) — where `m` is the average digit-string length, since each comparison does an O(m) string comparison
- **Space:** O(n * m)

## Dry Run
`nums = [3,30,34,5,9]` -> as strings: `["3","30","34","5","9"]`

Compare `"3"` vs `"30"`: `"330"` vs `"303"` -> `"330" > "303"` -> `"3"` comes before `"30"`.
Compare `"34"` vs `"3"`: `"343"` vs `"334"` -> `"343" > "334"` -> `"34"` comes before `"3"`.

After sorting fully by this rule: `["9","5","34","3","30"]`.

Concatenate: **"9534330"**

## Edge Cases
- All zeros, e.g. `[0,0]` -> without the special-case check, concatenation would give `"00"`; the check catches this and returns `"0"` instead
- A single number -> trivially itself, as a string
- Numbers of very different lengths, e.g. `[1, 12]` -> the comparator correctly reasons about `"112"` vs `"121"` rather than naive length or numeric comparison

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (all permutations) | O(n! * n) | O(n! * n) |
| Optimized (custom comparator sort) | O(n log n * m) | O(n * m) |

## Related Problems / Pattern Family
- Custom Sort String (Module 8 #12 — a different custom-ordering problem, solved without any comparator at all)
- Relative Sort Array (Module 8 #13 — another custom-order problem, using counting sort instead)
