# 11. Basic Calculator II

**Difficulty:** Medium
**Pattern:** Stack-Based Expression Evaluation
**LeetCode:** https://leetcode.com/problems/basic-calculator-ii/

## Problem Summary
Evaluate a string expression containing non-negative integers and the operators `+`, `-`, `*`, `/` (no parentheses), respecting standard operator precedence (`*` and `/` before `+` and `-`).

## Example
```
Input:  s = "3+2*2"
Output: 7
```

## Pattern Recognition
Precedence is the whole challenge here: you can't just evaluate left to right. The stack-based trick: track the sign *before* each number, and immediately apply high-precedence operators (`*`, `/`) the moment you see them — since they only ever need the number that was just parsed and whatever's on top of the stack. Lower-precedence operators (`+`, `-`) just get pushed (with their sign already applied) and summed at the very end.

## Approach 1: Brute Force (Two Explicit Passes)
Tokenize the expression into numbers and operators. **Pass 1:** scan left to right resolving every `*` and `/` immediately (splicing the three-token group into a single result), leaving only numbers and `+`/`-`. **Pass 2:** sum what remains.

- **Time:** O(n^2) — the splicing in pass 1 shifts remaining tokens on every resolution
- **Space:** O(n) — the token list

## Approach 2: Optimized (Single Pass, Sign-Tracking Stack)
Walk the string once, building up the current number digit by digit. The moment you hit an operator (or the end of the string), you know the *previous* operator and the number it applies to — push `num` (if `+`), `-num` (if `-`), or fold it directly into the stack's top via multiplication/division (if `*` or `/`, since those apply immediately rather than waiting). Update the "pending operator" to the one just seen, and reset the number. At the end, sum the entire stack.

- **Time:** O(n) — one pass
- **Space:** O(n) — the stack, worst case all additions

## Dry Run
`s = "3+2*2"`

| i | char | num | pending op resolved? | stack after |
|---|---|---|---|---|
| 0 | 3 | 3 | - | - |
| 1 | + | (resolve '+' with num=3) | push 3 | [3] |
| 2 | 2 | 2 | - | - |
| 3 | * | (resolve '+' with num=2, since sign was set to '+' at i=1... wait, sign updates to '*' here) | push 2 | [3,2] |
| 4 | 2 (last char) | 2 | (resolve '*' with num=2) | pop 2, push 2*2=4 | [3,4] |

Sum stack: 3+4 = **7**

## Edge Cases
- Expression with only `+`/`-`, no precedence conflicts -> behaves like straightforward left-to-right summation
- Division that truncates, e.g. `"14-3/2"` -> `3/2` truncates to `1`, giving `14-1=13`
- Whitespace within the expression, e.g. `" 3 + 2 "` -> must be explicitly skipped during parsing, since it's neither a digit nor an operator

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (two-pass, splicing) | O(n^2) | O(n) |
| Optimized (single pass, sign-tracking stack) | O(n) | O(n) |

## Related Problems / Pattern Family
- Evaluate Reverse Polish Notation (Module 6 #4 — a related stack-based evaluator, but for postfix notation where precedence is already resolved by ordering)
- Basic Calculator (a harder variant that adds parentheses, needing a stack for nested scopes too)
