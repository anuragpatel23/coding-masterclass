# 7. Longest Common Prefix

**Difficulty:** Easy
**Pattern:** Horizontal / Vertical Scanning
**LeetCode:** https://leetcode.com/problems/longest-common-prefix/

## Problem Summary
Given an array of strings, find the longest string that is a prefix of every string in the array. If there's no common prefix, return an empty string.

## Example
```
Input:  strs = ["flower","flow","flight"]
Output: "fl"
```

## Pattern Recognition
Comparing "the same position across every string" versus "one whole string against another" is a choice between **vertical scanning** (compare column-by-column, across all strings, one position at a time) and **horizontal scanning** (shrink a running prefix by comparing it against one string at a time). Both are valid; the difference matters for *when* you can exit early.

## Approach 1: Brute Force (Horizontal Scanning)
Start with the first string as the candidate prefix. Compare it against the second string, shrinking the candidate from the end until it's actually a prefix of that string. Repeat against the third string, and so on.

- **Time:** O(S) in the worst case, where S is the sum of all characters across all strings — but it can end up re-comparing large portions of the (shrinking) prefix against every single string, since each string is only compared to the accumulated result, not examined column-by-column across the whole set at once.
- **Space:** O(1) extra (beyond the prefix string itself)
- **Why it's worth improving:** it works correctly, but it fully processes each string against the current prefix before moving to the next, even when a mismatch would have been obvious from checking the very first differing column across all strings simultaneously.

## Approach 2: Optimized (Vertical Scanning)
Walk position `j` from `0` upward. At each position, check the character at index `j` in **every** string. If any string is shorter than `j+1`, or its character at `j` doesn't match the first string's character at `j`, stop immediately — the prefix ends at `j`.

This exits at the very first mismatched column across the *entire* set, without needing to have fully processed any single string first.

- **Time:** O(S) worst case (if every string shares a long common prefix, you still must scan through it) — but with an early exit that's usually far ahead of the horizontal approach in practice, since it detects a mismatch on the very first column where it appears, for every string at once.
- **Space:** O(1) extra

*Note: this is one of those cases (like a few in Module 1) where the improvement is about early termination and simplicity of reasoning about correctness, not a change in worst-case Big-O class — both approaches are O(S) in the worst case.*

## Dry Run
`strs = ["flower","flow","flight"]`

| position j | char in "flower" | char in "flow" | char in "flight" | match? |
|---|---|---|---|---|
| 0 | f | f | f | yes |
| 1 | l | l | l | yes |
| 2 | o | o | i | **no** -> stop |

Result: **"fl"**

## Edge Cases
- Empty array of strings -> return `""` immediately (no strings to compare)
- One of the strings is empty -> the common prefix must also be empty, since nothing can be a prefix of an empty string except itself
- All strings are identical -> the entire string is the common prefix

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (horizontal scanning) | O(S) | O(1) extra |
| Optimized (vertical scanning) | O(S) | O(1) extra |

## Related Problems / Pattern Family
- Longest Common Subsequence (Module 16 — Dynamic Programming, a much harder relative of this idea)
- Implement strStr() (Module 2 #12 — also about matching one string's content within another)
