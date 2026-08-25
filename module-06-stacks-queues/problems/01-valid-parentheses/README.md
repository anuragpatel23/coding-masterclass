# 1. Valid Parentheses

**Difficulty:** Easy
**Pattern:** Stack Matching
**LeetCode:** https://leetcode.com/problems/valid-parentheses/

## Problem Summary
Given a string containing only `(`, `)`, `{`, `}`, `[`, `]`, determine if the brackets are properly matched and nested.

## Example
```
Input:  s = "([{}])"
Output: true
```

## Pattern Recognition
"Properly nested" is the defining signature of a stack: the most recently opened bracket must be the next one closed (Last In, First Out). Whenever a problem involves matching pairs where order and nesting both matter, a stack is almost always the answer.

## Approach 1: Brute Force
Repeatedly scan for and remove any adjacent matching pair (`"()"`, `"[]"`, `"{}"`), restarting after each removal, until no more pairs can be removed.

- **Time:** O(n^2) — each removal is an O(n) string operation, and up to n/2 removals can occur
- **Space:** O(n) — the mutable string buffer
- **Why it's not good enough:** every removal potentially requires re-scanning the whole string from scratch, when a single pass with a stack could resolve everything in one go.

## Approach 2: Optimized (Stack Matching)
Walk the string once. Push every opening bracket onto a stack. For every closing bracket, check that the stack's top is the matching opening bracket — if so, pop it; if not (or the stack is empty), the string is invalid. At the end, the string is valid only if the stack is empty (every opener found its closer).

- **Time:** O(n) — one pass
- **Space:** O(n) — the stack, in the worst case (all openers, no closers)

## Dry Run
`s = "([{}])"`

| char | action | stack after |
|---|---|---|
| ( | push | [(] |
| [ | push | [(,[] |
| { | push | [(,[,{] |
| } | matches top {, pop | [(,[] |
| ] | matches top [, pop | [(] |
| ) | matches top (, pop | [] |

Stack empty at the end -> valid.

Result: **true**

## Edge Cases
- Empty string -> trivially valid (an empty stack at the end)
- Only opening brackets, e.g. `"((("` -> stack never empties, invalid
- A closing bracket appears with an empty stack, e.g. `")("` -> caught immediately, invalid

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(n) |
| Optimized (stack matching) | O(n) | O(n) |

## Related Problems / Pattern Family
- Remove All Adjacent Duplicates in String (Module 6 #3 — the same stack-matching mechanics, a different matching rule)
- Min Stack (Module 6 #2 — a different use of a stack, for O(1) minimum tracking)
- Basic Calculator II (Module 6 #11 — a stack used for expression evaluation instead of matching)
