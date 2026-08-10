# 2. Repeated DNA Sequences

**Difficulty:** Medium
**Pattern:** Fixed-Size Sliding Window + Hashing
**LeetCode:** https://leetcode.com/problems/repeated-dna-sequences/

## Problem Summary
Given a DNA string (containing only `A`, `C`, `G`, `T`), find all 10-character-long substrings that occur more than once.

## Example
```
Input:  s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]
```

## Pattern Recognition
Every candidate is a **fixed-size** (10-character) window — that's the sliding window half of the pattern. "Has this exact window occurred before" is a membership/frequency question — that's the hashing half. Combine them: slide a size-10 window across the string, and use a hashmap to count how many times each window's content has appeared.

## Approach 1: Brute Force
For every starting index, extract the 10-character substring and compare it against every other starting index's substring.

- **Time:** O(n^2) — n starting positions, each compared against up to n others, with an O(10) string comparison each time
- **Space:** O(1) extra beyond the result set
- **Why it's not good enough:** you're repeating the same substring comparisons from every possible pair, when a single pass with a running count per substring tells you everything in one shot.

## Approach 2: Optimized (Sliding Window + HashMap Counting)
Slide a window of size 10 across the string. At each position, look up the current 10-character substring in a hashmap of counts. Increment its count; the moment a substring's count reaches exactly 2, add it to the result (this "exactly 2" check prevents adding the same sequence to the result more than once).

- **Time:** O(n) — one pass, O(10) work extracting each substring, which is a constant
- **Space:** O(n) — the hashmap of substring counts

## Dry Run
`s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"` (abbreviated)

The 10-character window `"AAAAACCCCC"` appears starting at index 0 and again later in the string — the second time its count reaches 2, it's added to the result. `"CCCCCAAAAA"` is found the same way.

Result: **["AAAAACCCCC", "CCCCCAAAAA"]**

## Edge Cases
- String shorter than 10 characters -> no valid windows exist, return an empty list
- No repeated sequence at all -> every substring's count stays at 1, empty result
- A sequence repeated 3+ times -> only added to the result once (on the transition from count 1 to count 2), not duplicated in the output

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) extra |
| Optimized (window + hashmap) | O(n) | O(n) |

## Related Problems / Pattern Family
- Maximum Sum Subarray of Size K (Module 4 #1 — the pure fixed-window mechanics, without hashing)
- Find All Anagrams in a String (Module 4 #4 — fixed window with frequency counting instead of exact-match hashing)
