# 13. Implement Queue using Stacks

**Difficulty:** Easy
**Pattern:** Two-Stack Simulation
**LeetCode:** https://leetcode.com/problems/implement-queue-using-stacks/

## Problem Summary
Implement a FIFO queue using only stack operations (push, pop, peek at top, check empty).

## Example
```
push(1), push(2), peek() -> 1, pop() -> 1, isEmpty() -> false
```

## Pattern Recognition
A single stack reverses order (LIFO); you need FIFO. The fix: use a second stack as a "reversal buffer." Popping everything off one stack and pushing it onto another **flips the order** — do that once, and the oldest element ends up on top of the second stack, exactly where FIFO needs it.

## Approach 1: "Push-Heavy" — Reorder on Every Push
Maintain a single stack, always kept in FIFO order (oldest on top). On every `push`, pour the whole stack into a temporary stack, push the new element, then pour everything back — restoring FIFO order with the new element correctly placed at the bottom.

- **Time:** O(n) per `push`, O(1) for `pop`/`peek`
- **Space:** O(n)

## Approach 2: Optimized (Two Stacks, Amortized O(1))
Maintain an `inStack` (for incoming pushes) and an `outStack` (for outgoing pops). `push` always just pushes onto `inStack` — O(1), no reordering. `pop`/`peek` check `outStack` first; if it's empty, pour the *entire* `inStack` into it (reversing order once), then operate on `outStack`'s top.

Each element is moved from `inStack` to `outStack` **at most once** over its lifetime, no matter how many `push`/`pop` calls happen — that's what makes this amortized O(1) despite occasional O(n) transfers.

- **Time:** O(1) amortized per operation
- **Space:** O(n)

## Dry Run
`push(1)`, `push(2)`, `pop()`, `push(3)`, `pop()`

| operation | inStack | outStack | result |
|---|---|---|---|
| push(1) | [1] | [] | - |
| push(2) | [1,2] | [] | - |
| pop() | transfer: [] -> outStack=[2,1] (reversed). pop outStack top=1 | [] | [2] | **1** |
| push(3) | [3] | [2] | - |
| pop() | outStack not empty, pop top=2 | [3] | [] | **2** |

## Edge Cases
- `pop`/`peek` called when both stacks are empty -> should be guarded against per the problem's usage contract (calls are only made on a non-empty queue)
- Interleaved push/pop calls -> the amortized analysis holds regardless of the specific sequence, since each element still only ever transfers once
- A long run of only pushes, then only pops -> the single big transfer happens exactly once, at the first pop

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Push-heavy (two stacks, reorder every push) | O(n) push, O(1) pop/peek | O(n) |
| Optimized (two stacks, amortized) | O(1) amortized for all operations | O(n) |

## Related Problems / Pattern Family
- Implement Stack using Queues (Module 6 #14 — the mirror-image problem)
- Min Stack (Module 6 #2 — a different two-structure design trick)
