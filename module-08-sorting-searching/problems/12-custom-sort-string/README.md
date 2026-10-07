# 12. Custom Sort String

**Difficulty:** Medium
**Pattern:** Counting + Custom Order (No Sorting Needed)
**LeetCode:** https://leetcode.com/problems/custom-sort-string/

## Problem Summary
Given a string `order` defining a custom character ordering, and a string `s`, rearrange `s` so its characters follow the relative order given by `order`. Characters not present in `order` can go anywhere, typically appended at the end.

## Example
```
Input:  order = "cba", s = "abcd"
Output: "cbad"
```

## Pattern Recognition
A comparator-based sort works here, but it's overkill: since the "sort key" is really just a fixed small set of buckets (one per character in `order`, plus one for everything else), you can skip comparison-based sorting entirely and go straight to **counting**: count how many of each character `s` has, then emit them in the exact sequence `order` specifies.

## Approach 1: Brute Force (Custom Comparator Sort)
Build a rank lookup for each character in `order` (its position). Sort the characters of `s` using a comparator based on that rank (characters not in `order` get a rank of "infinity," placing them last).

- **Time:** O(n log n) — a standard comparison sort
- **Space:** O(n)

## Approach 2: Optimized (Counting, No Sort At All)
Count the frequency of every character in `s` using a fixed 26-length array. Walk through `order` character by character; for each one, append it to the result as many times as it was counted in `s` (decrementing the count as you go). Finally, append any remaining characters (those never mentioned in `order`) in any order — a simple `a` to `z` sweep works.

- **Time:** O(n + 26) = O(n) — no comparisons needed at all
- **Space:** O(1) extra (the fixed 26-length count array), O(n) for the output

## Dry Run
`order = "cba"`, `s = "abcd"`

Counts: `{a:1, b:1, c:1, d:1}`.

Walk `order`: `c` -> append 1 `c`. `b` -> append 1 `b`. `a` -> append 1 `a`.

Remaining (never mentioned in `order`): `d` -> append it.

Result: **"cbad"**

## Edge Cases
- Characters in `s` that don't appear in `order` at all -> collected in the final cleanup sweep, appended after everything else
- `order` contains characters that never appear in `s` -> simply contributes nothing when its count is `0`, no error
- `s` is empty -> both loops produce nothing, correctly returns `""`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (comparator sort) | O(n log n) | O(n) |
| Optimized (counting, no sort) | O(n) | O(1) extra |

## Related Problems / Pattern Family
- Relative Sort Array (Module 8 #13 — the same counting-instead-of-sorting idea, applied to integers)
- Sort Characters By Frequency (a related counting-based rearrangement, ordered by frequency instead of a custom sequence)
