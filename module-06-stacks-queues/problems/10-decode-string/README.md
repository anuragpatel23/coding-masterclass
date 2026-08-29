# 10. Decode String

**Difficulty:** Medium
**Pattern:** Stack-Based Nested Parsing
**LeetCode:** https://leetcode.com/problems/decode-string/

## Problem Summary
Given an encoded string like `"3[a2[c]]"`, decode it by expanding each `count[content]` group (which can nest). `"3[a2[c]]"` decodes to `"accaccacc"`.

## Example
```
Input:  s = "3[a2[c]]"
Output: "accaccacc"
```

## Pattern Recognition
Nested brackets mean nested *contexts* — everything inside a `[...]` needs to be fully resolved before it can be repeated and handed back to whatever was building the string one level up. That's precisely what a call stack (recursion) or an explicit stack (iteration) is for: each `[` opens a new context, and each `]` closes it, folding the finished result back into the context below.

## Approach 1: Brute Force (Recursive Descent)
Use recursion with a shared, mutable position pointer. At each level, build up the current segment character by character; when a digit is found, recursively decode everything inside the matching brackets, then repeat that result the given number of times and append it.

- **Time:** O(n * maxRepeat) — where the output length itself can be exponential in pathological inputs (that's inherent to the problem, not the algorithm)
- **Space:** O(d) call stack depth, where d is the nesting depth
- **Why it's presented as "brute force" here:** it's a perfectly valid approach, but very deep nesting can risk a stack overflow in the underlying call stack — the iterative version avoids that risk entirely, which is the actual improvement being demonstrated.

## Approach 2: Optimized (Iterative, Explicit Stack)
Maintain two parallel stacks: one for pending repeat counts, one for pending string segments (whatever was built up *before* entering the current bracket). Walk the string once: accumulate digits into a running count, accumulate letters into a running current-string. On `[`, push the count and current-string, then reset both. On `]`, pop the count and previous string, repeat the current string that many times, and append it to the popped previous string.

- **Time:** O(n * maxRepeat) — same output-size consideration as above, but no recursive call stack
- **Space:** O(d) for the two stacks

## Dry Run
`s = "3[a2[c]]"`

| char | action | count/current | stacks after |
|---|---|---|---|
| 3 | digit | count=3 | - |
| [ | push count=3, push current="" | reset count=0, current="" | counts:[3], strings:[""] |
| a | append | current="a" | - |
| 2 | digit | count=2 | - |
| [ | push count=2, push current="a" | reset count=0, current="" | counts:[3,2], strings:["","a"] |
| c | append | current="c" | - |
| ] | pop count=2, pop prev="a" | current = "a" + "c"*2 = "acc" | counts:[3], strings:[""] |
| ] | pop count=3, pop prev="" | current = "" + "acc"*3 = "accaccacc" | counts:[], strings:[] |

Result: **"accaccacc"**

## Edge Cases
- No brackets at all, e.g. `"abc"` -> the character-accumulation loop runs with the stacks never touched, returning the string unchanged
- Multiple sibling groups, e.g. `"2[a]3[b]"` -> each `]` resolves independently, and the results concatenate naturally as the current-string accumulates across both
- Deep nesting -> the iterative version handles arbitrary depth without risking a stack overflow, unlike the recursive version

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (recursive descent) | O(n * maxRepeat) | O(d) call stack |
| Optimized (iterative, explicit stacks) | O(n * maxRepeat) | O(d) explicit stacks |

## Related Problems / Pattern Family
- Basic Calculator II (Module 6 #11 — a different stack-based parsing problem, arithmetic instead of repetition)
- Valid Parentheses (Module 6 #1 — the simpler "just check nesting is balanced" version of bracket handling)
