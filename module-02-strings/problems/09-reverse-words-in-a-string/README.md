# 9. Reverse Words in a String

**Difficulty:** Medium
**Pattern:** Reversal Composition *(the same idea as Rotate Array, Module 1 #7, applied at two levels)*
**LeetCode:** https://leetcode.com/problems/reverse-words-in-a-string/

## Problem Summary
Given a string `s`, reverse the order of the words. Words are separated by whitespace; the output should have a single space between words, with no leading or trailing spaces, even if the input has extra/irregular spacing.

## Example
```
Input:  "  the sky is blue  "
Output: "blue is sky the"
```

## Pattern Recognition
Just like Rotate Array used **reverse the whole thing, then reverse the pieces** to achieve a rotation with O(1) space, reversing word order can use the exact same composition: reverse the *entire* character sequence, then reverse each *individual word* back to its normal spelling. Whenever you see "reverse the order of these groups, but keep each group's internal content unchanged," think of this two-level reversal trick.

## Approach 1: Brute Force (Split, Reverse, Join)
Split the string on whitespace (which also lets you discard empty tokens from multiple/leading/trailing spaces), reverse the resulting list of words, and join them back together with single spaces.

- **Time:** O(n)
- **Space:** O(n) — for the array of word tokens
- **Why it's fine, but worth contrasting:** this is actually a very reasonable real-world solution (and often what you'd write in production Java, since `String` is immutable anyway). It's presented as "brute force" here specifically to set up the O(1)-extra-space trick below, which is the version interviewers are usually really probing for — especially in languages with mutable strings like C++.

## Approach 2: Optimized (Reverse Whole String, Then Reverse Each Word, With In-Place Space Cleanup)
Working on a mutable `char[]` (conceptually — true in-place mutation isn't possible on Java's immutable `String` type, but the technique is the same one used on a `char[]` or in a language like C++):
1. Reverse the entire character array.
2. Walk through it, reversing each individual word back to its correct spelling, while also collapsing any run of multiple spaces into one and trimming leading/trailing spaces as you go.

- **Time:** O(n)
- **Space:** O(1) extra beyond the character buffer you're already required to hold

## Dry Run
`"  the sky is blue  "`

1. Reverse entire string: `"  eulb si yks eht  "`
2. Reverse each word back to normal spelling, left to right, while skipping/collapsing spaces: `eulb` -> `blue`, `si` -> `is`, `yks` -> `sky`, `eht` -> `the`

Result: **"blue is sky the"**

## Edge Cases
- Multiple consecutive spaces between words -> must collapse to exactly one space in the output
- Leading and/or trailing spaces -> must be fully trimmed
- Single word with surrounding whitespace, e.g. `"  hello  "` -> output is just `"hello"`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (split/reverse/join) | O(n) | O(n) |
| Optimized (double reversal) | O(n) | O(1) extra (on a mutable buffer) |

## Related Problems / Pattern Family
- Rotate Array (Module 1 #7 — the original "reverse the whole, then reverse the pieces" trick)
- Reverse Words in a String III (reverse each word's letters, but keep word order — the mirror-image problem)
