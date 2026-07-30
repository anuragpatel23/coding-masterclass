# 8. String Compression

**Difficulty:** Medium
**Pattern:** In-Place Write Pointer *(the same technique as Move Zeroes, Module 1 #6, applied to characters)*
**LeetCode:** https://leetcode.com/problems/string-compression/

## Problem Summary
Given an array of characters, compress it in-place: each group of consecutive repeating characters is replaced by the character followed by the number of repetitions (the count is omitted if it's 1). Return the new length of the array.

## Example
```
Input:  ['a','a','b','b','c','c','c']
Output: 6, array becomes ['a','2','b','2','c','3']
```

## Pattern Recognition
"Modify this array in-place, writing a transformed (usually shorter) version of it as you go" is the read-pointer/write-pointer pattern from Move Zeroes, extended: instead of just filtering, you're now writing *multiple* characters (a letter, plus however many digits its count requires) for every group you consume.

## Approach 1: Brute Force
Build the compressed result in a separate `StringBuilder`, then copy the characters back into the original array at the end.

- **Time:** O(n)
- **Space:** O(n) — the intermediate `StringBuilder`, which isn't truly in-place
- **Why it's not good enough:** the problem specifically wants O(1) extra space; building a whole second representation first defeats that requirement even though the time complexity is already fine.

## Approach 2: Optimized (Read/Write Pointers, Group by Group)
Use a `readPointer` to scan through consecutive equal characters, counting the group's length. Use a separate `writePointer` to write the character and (if the count is more than 1) each digit of the count, directly back into the same array.

- **Time:** O(n) — each character is read once
- **Space:** O(1) extra — only pointers and a counter, no auxiliary structure

## Dry Run
`chars = ['a','a','b','b','c','c','c']`

| group | count | writes | writePointer after |
|---|---|---|---|
| 'a','a' | 2 | write 'a', write '2' | 2 |
| 'b','b' | 2 | write 'b', write '2' | 4 |
| 'c','c','c' | 3 | write 'c', write '3' | 6 |

Result: **new length 6**, array becomes `['a','2','b','2','c','3']`

## Edge Cases
- A count of 10 or more, e.g. 12 repeats of `'a'` -> the count `"12"` must be written as two separate digit characters (`'1'` then `'2'`), not a single character
- A group of size 1, e.g. a single `'x'` -> write just `'x'`, with no count character at all
- Entire array is one repeated character -> a single group covering the whole input

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (StringBuilder) | O(n) | O(n) |
| Optimized (read/write pointers) | O(n) | O(1) extra |

## Related Problems / Pattern Family
- Move Zeroes (Module 1 #6 — the same read/write pointer skeleton, simpler payload)
- Run-Length Encoding variants (this problem, generalized)
