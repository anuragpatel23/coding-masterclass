# 2. Min Stack

**Difficulty:** Medium
**Pattern:** Stack + Auxiliary Tracking
**LeetCode:** https://leetcode.com/problems/min-stack/

## Problem Summary
Design a stack that supports `push`, `pop`, `top`, and retrieving the minimum element, all in O(1) time.

## Example
```
push(-2), push(0), push(-3)
getMin() -> -3
pop()
top() -> 0
getMin() -> -2
```

## Pattern Recognition
The challenge is that the minimum can change on every `pop` — the "second lowest" value needs to be ready to become the new minimum instantly. The fix: don't compute the minimum on demand — **maintain it incrementally** with a second, parallel stack that always has the correct "minimum so far" sitting on top, growing and shrinking in lockstep with the main stack.

## Approach 1: Brute Force
A single stack for the data. `push`, `pop`, and `top` are trivially O(1). For `getMin`, scan the entire stack.

- **Time:** O(1) for push/pop/top, **O(n) for getMin**
- **Space:** O(n)
- **Why it's not good enough:** the problem specifically requires getMin in O(1) — scanning defeats that requirement even though every other operation is already optimal.

## Approach 2: Optimized (Parallel Min-Tracking Stack)
Maintain a second stack, `minStack`, alongside the main one. Every time you push a value, also push the *current minimum including this new value* onto `minStack` (i.e., `min(value, minStack.peek())`, or just `value` if `minStack` is empty). Every `pop` removes from both stacks together, so `minStack`'s top is always correct for whatever's left in the main stack.

- **Time:** O(1) for every operation
- **Space:** O(n) — the second stack, same size as the first

## Dry Run
`push(-2)`, `push(0)`, `push(-3)`, `getMin()`, `pop()`, `top()`, `getMin()`

| operation | stack | minStack | getMin() result |
|---|---|---|---|
| push(-2) | [-2] | [-2] | - |
| push(0) | [-2,0] | [-2,-2] | - |
| push(-3) | [-2,0,-3] | [-2,-2,-3] | - |
| getMin() | | | **-3** |
| pop() | [-2,0] | [-2,-2] | - |
| top() | | | **0** |
| getMin() | | | **-2** |

## Edge Cases
- Pushing the same value as the current minimum multiple times -> `minStack` correctly stores a duplicate entry each time, so popping one copy still leaves the correct minimum for the rest
- A single element -> both stacks have exactly one entry, trivially correct
- Popping down to empty -> both stacks empty together, no desync between them

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (scan for getMin) | O(1) push/pop/top, O(n) getMin | O(n) |
| Optimized (parallel min stack) | O(1) all operations | O(n) |

## Related Problems / Pattern Family
- Max Stack (the same idea, tracking a running maximum instead of minimum)
- Sliding Window Maximum (Module 4 #15 — a related "maintain the extreme value incrementally" idea, using a deque instead)
