# 5. Longest Substring Without Repeating Characters

**Difficulty:** Medium
**Pattern:** Variable-Size Sliding Window + HashMap
**LeetCode:** https://leetcode.com/problems/longest-substring-without-repeating-characters/

## Problem Summary
Given a string, find the length of the longest substring without any repeating characters.

## Example
```
Input:  s = "abcabcbb"
Output: 3        ("abc")
```

## Pattern Recognition
This is the first **variable-size** window in the module: instead of a fixed length, the window grows as long as its content stays valid (no repeats), and shrinks only when a repeat is discovered. Whenever a problem asks for the longest/shortest contiguous range satisfying some condition, and the condition can flip from valid to invalid as you add one more element, that's a variable window.

## Approach 1: Brute Force
Check every substring, verifying uniqueness with a fresh set each time.

- **Time:** O(n^2) — with an O(1) amortized uniqueness check per extension using an early-exit set, or O(n^3) if you check strictly all substrings independently
- **Space:** O(min(n, charset))
- **Why it's not good enough:** the moment a duplicate is found while extending from a given start, you know for certain no longer substring from that start can work — but that insight alone still leaves you re-scanning from every possible start.

## Approach 2: Optimized (Variable Window with Last-Seen-Index Map)
Expand the window's right edge one character at a time. Track the last index each character was seen at. If the current character was already seen **inside the current window**, jump the window's left edge to just past that previous occurrence — no need to shrink one step at a time.

- **Time:** O(n) — each character is visited a bounded number of times
- **Space:** O(min(n, charset))

## Dry Run
`s = "abcabcbb"`

| right | char | last seen (in window?) | left after | window length |
|---|---|---|---|---|
| 0 | a | no | 0 | 1 |
| 1 | b | no | 0 | 2 |
| 2 | c | no | 0 | 3 |
| 3 | a | yes (index 0) | 1 | 3 |
| 4 | b | yes (index 1) | 2 | 3 |
| 5 | c | yes (index 2) | 3 | 3 |
| 6 | b | yes (index 4, but that's before left=3... recheck: last seen 4 >= left 3) | 5 | 2 |
| 7 | b | yes (index 6) | 7 | 1 |

Longest window found: length **3**

## Edge Cases
- Empty string -> length 0
- All identical characters, e.g. `"aaaa"` -> the window can never grow past length 1
- All unique characters -> the window grows to cover the entire string

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) to O(n^3) | O(min(n, charset)) |
| Optimized (variable window + last-seen map) | O(n) | O(min(n, charset)) |

## Related Problems / Pattern Family
- Longest Substring with At Most K Distinct Characters (Module 4 #6 — a generalized variable window)
- Minimum Window Substring (Module 4 #14 — a variable window with a different, more complex validity condition)
