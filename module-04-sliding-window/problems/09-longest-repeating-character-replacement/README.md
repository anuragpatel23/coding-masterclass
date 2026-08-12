# 9. Longest Repeating Character Replacement

**Difficulty:** Medium
**Pattern:** Variable-Size Sliding Window + Frequency Count
**LeetCode:** https://leetcode.com/problems/longest-repeating-character-replacement/

## Problem Summary
Given a string of uppercase letters and an integer `k`, you may replace up to `k` characters with any other letter. Return the length of the longest substring you can make consist of a single repeated letter.

## Example
```
Input:  s = "AABABBA", k = 1
Output: 4        (replace one 'A' or 'B' to get e.g. "AABBBBA" -> "ABBB" of length 4)
```

## Pattern Recognition
A window of length `L` is achievable if, after replacing up to `k` characters, everything matches the most frequent character in that window — which means the count of *everything else* (`L - maxFreqInWindow`) must be `<= k`. That's a validity condition over a window, and it only gets harder to satisfy as the window grows — the classic shape for a variable-size window that shrinks when it becomes invalid.

## Approach 1: Brute Force
For every window, compute the frequency of the most common character in it, and check whether `windowLength - maxFreq <= k`.

- **Time:** O(n^2) — or O(26 * n^2) if recomputing the max frequency from scratch each time, but tracking a running max as you extend keeps it at O(n^2)
- **Space:** O(1) — fixed 26-length frequency array

## Approach 2: Optimized (Variable Window + Running Max Frequency)
Expand the window's right edge, updating a frequency count and a running `maxFreq` (the highest single-character count seen in any window so far — note this value is allowed to be "stale," it doesn't need to shrink when the window shrinks, because the window can only ever grow past its previous best). While `(windowLength - maxFreq) > k`, shrink from the left.

- **Time:** O(n) — each character is added once and removed at most once
- **Space:** O(1)

## Dry Run
`s = "AABABBA"`, `k = 1`

| right | char | freq update | maxFreq | window length | (length - maxFreq) > k? | action |
|---|---|---|---|---|---|---|
| 0 | A | A:1 | 1 | 1 | no | - |
| 1 | A | A:2 | 2 | 2 | no | - |
| 2 | B | B:1 | 2 | 3 | no | - |
| 3 | A | A:3 | 3 | 4 | no | - |
| 4 | B | B:2 | 3 | 5 | 5-3=2>1 yes | shrink left |
| 5 | B | B:3 | 3 | ... | recompute after shrink | |
| 6 | A | A:4 | 4 | ... | | |

*(The window's peak valid length reached during this scan is 4.)*

Result: **4**

## Edge Cases
- `k >= len(s) - 1` -> the entire string can be replaced down to a single repeated character, answer is the full length
- `k = 0` -> no replacements allowed, the answer is just the longest existing run of a single repeated character
- All characters already identical -> the whole string is valid with zero replacements needed

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) |
| Optimized (variable window + running max) | O(n) | O(1) |

## Related Problems / Pattern Family
- Max Consecutive Ones III (Module 4 #10 — the same "at most k replacements" shape, simplified to a binary alphabet)
- Longest Substring with At Most K Distinct Characters (Module 4 #6 — a different validity condition over a variable window)
