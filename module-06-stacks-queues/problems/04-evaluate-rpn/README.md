# 4. Evaluate Reverse Polish Notation

**Difficulty:** Medium
**Pattern:** Stack-Based Evaluation
**LeetCode:** https://leetcode.com/problems/evaluate-reverse-polish-notation/

## Problem Summary
Evaluate an arithmetic expression given in Reverse Polish (postfix) Notation, where operators come after their operands (e.g., `["2","1","+","3","*"]` means `(2 + 1) * 3`).

## Example
```
Input:  tokens = ["2","1","+","3","*"]
Output: 9
```

## Pattern Recognition
Postfix notation is *built* for a stack: numbers get pushed, and whenever you hit an operator, the two values it needs are always the two most recently pushed — exactly what a stack's pop operation gives you. No parsing of precedence or parentheses is ever needed; the order of tokens already encodes it.

## Approach 1: Brute Force
Repeatedly scan the token list left to right for the first operator, apply it to the two numbers immediately before it, splice the result back into the list in their place, and repeat until one token remains.

- **Time:** O(n^2) — each resolved operator requires an O(n) list-splice operation, repeated up to n/2 times
- **Space:** O(n) — the working list
- **Why it's not good enough:** it gets the right answer, but every splice re-shuffles a big chunk of the remaining tokens, when a stack could resolve the same operator in O(1).

## Approach 2: Optimized (Stack-Based Evaluation)
Walk the tokens once. Push every number onto a stack. Whenever a token is an operator, pop the top two values (`b` then `a`, since `b` was pushed more recently), apply the operator as `a op b`, and push the result back. The final remaining stack value is the answer.

- **Time:** O(n) — one pass
- **Space:** O(n) — the stack, worst case all numbers before any operator

## Dry Run
`tokens = ["2","1","+","3","*"]`

| token | action | stack after |
|---|---|---|
| 2 | push | [2] |
| 1 | push | [2,1] |
| + | pop 1, pop 2, push 2+1=3 | [3] |
| 3 | push | [3,3] |
| * | pop 3, pop 3, push 3*3=9 | [9] |

Result: **9**

## Edge Cases
- Division that truncates toward zero, e.g. `["6","-13","/"]` -> `6 / -13` truncates to `0` in integer division per the problem's convention, not `-1` (floor division) — worth double-checking your language's default division behavior
- A single number with no operators, e.g. `["5"]` -> the stack ends with just `5`, correctly returned
- Negative numbers as tokens, e.g. `"-3"` -> must be parsed as a single negative integer, not mistaken for a subtraction operator

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (repeated splicing) | O(n^2) | O(n) |
| Optimized (stack-based) | O(n) | O(n) |

## Related Problems / Pattern Family
- Basic Calculator II (Module 6 #11 — a related stack-based evaluator, for infix expressions with precedence)
- Decode String (Module 6 #10 — a stack used to resolve nested structure rather than arithmetic)
