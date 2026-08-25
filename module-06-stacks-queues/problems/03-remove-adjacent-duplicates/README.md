# 3. Remove All Adjacent Duplicates In String

**Difficulty:** Easy
**Pattern:** Stack Matching
**LeetCode:** https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/

## Problem Summary
Given a string, repeatedly remove pairs of adjacent identical characters until no such pairs remain. Return the final string.

## Example
```
Input:  s = "abbaca"
Output: "ca"
```

## Pattern Recognition
Just like Valid Parentheses (#1), this is "does the most recently added thing cancel out with what comes next" — a stack tracks exactly that. Push characters on; whenever the incoming character matches what's on top, they cancel each other out (pop instead of push).

## Approach 1: Brute Force
Repeatedly scan left to right for the first adjacent duplicate pair, remove it, and restart the scan.

- **Time:** O(n^2) — each removal potentially triggers a full re-scan
- **Space:** O(n) — the mutable string buffer

## Approach 2: Optimized (Stack Matching)
Walk the string once. For each character, check the stack's top: if it matches the current character, pop (they cancel); otherwise, push the current character. What remains on the stack at the end, read bottom-to-top, is the answer.

- **Time:** O(n) — one pass
- **Space:** O(n) — the stack, worst case no cancellations at all

## Dry Run
`s = "abbaca"`

| char | stack top | action | stack after |
|---|---|---|---|
| a | (empty) | push | [a] |
| b | a | push | [a,b] |
| b | b | match, pop | [a] |
| a | a | match, pop | [] |
| c | (empty) | push | [c] |
| a | c | push | [c,a] |

Stack bottom-to-top: `c, a`.

Result: **"ca"**

## Edge Cases
- The entire string cancels out, e.g. `"abba"` -> stack ends up empty, result is `""`
- No adjacent duplicates at all -> every character gets pushed, nothing ever cancels, the original string is returned unchanged
- Cancellations cascade (removing one pair exposes a new adjacent pair), e.g. `"abba"` collapsing fully -> the stack naturally handles this since the newly-exposed top is re-checked on the very next character

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(n) |
| Optimized (stack matching) | O(n) | O(n) |

## Related Problems / Pattern Family
- Valid Parentheses (Module 6 #1 — the same cancel-on-match mechanics, matching bracket pairs instead of identical characters)
- Remove All Adjacent Duplicates in String II (a harder variant: cancel groups of exactly k identical characters)
