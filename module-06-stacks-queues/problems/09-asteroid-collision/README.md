# 9. Asteroid Collision

**Difficulty:** Medium
**Pattern:** Stack Simulation
**LeetCode:** https://leetcode.com/problems/asteroid-collision/

## Problem Summary
Given an array of asteroids (positive values move right, negative values move left, magnitude is size), simulate collisions: when two meet, the smaller explodes; if equal, both explode. Return the state after all collisions resolve.

## Example
```
Input:  asteroids = [5,10,-5]
Output: [5,10]
```

## Pattern Recognition
Collisions only ever happen between a **right-moving asteroid that's already been placed** and a **left-moving asteroid arriving next** — that's a "does the most recent thing survive contact with the new thing" question, which is exactly what a stack tracks. Push right-movers; when a left-mover arrives, resolve it against the stack's top repeatedly until it's destroyed, destroys everything in its way, or the stack no longer threatens it (empty or also moving left... which can't happen if the stack only ever holds right-movers or survivors).

## Approach 1: Brute Force
Repeatedly scan for the first adjacent colliding pair (a positive value immediately followed by a negative value), resolve that single collision, and restart the scan.

- **Time:** O(n^2) — each collision resolution can trigger a full re-scan
- **Space:** O(n) — the working list

## Approach 2: Optimized (Stack Simulation, Single Pass)
Walk the asteroids once, maintaining a stack of survivors. For each new asteroid: if it's moving left and the stack's top is moving right (a collision is possible), resolve it — pop the top if it's smaller, destroy the current asteroid if the top is bigger, or pop and discard both if they're equal size — repeating against the new top as needed. If the current asteroid survives all of that, push it.

- **Time:** O(n) — each asteroid is pushed once and popped at most once
- **Space:** O(n) — the stack, worst case no collisions at all

## Dry Run
`asteroids = [5,10,-5]`

| asteroid | stack action |
|---|---|
| 5 | no collision possible (stack empty) -> push 5. Stack: [5] |
| 10 | top(5) is positive, current(10) is also positive -> no collision -> push 10. Stack: [5,10] |
| -5 | top(10) positive, current(-5) negative -> collision! \|10\| > \|-5\| -> current destroyed, top survives. Stack unchanged: [5,10] |

Result: **[5, 10]**

## Edge Cases
- All asteroids moving the same direction -> no collisions possible at all, the array is returned unchanged
- A left-mover destroys an entire chain of smaller right-movers before finally being stopped (or reaching the bottom of the stack) -> the `while` loop naturally continues resolving against each new top
- Two equal-sized asteroids collide -> both are destroyed, and the loop correctly stops trying to resolve further (the current asteroid no longer exists to threaten anything else)

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(n) |
| Optimized (stack simulation) | O(n) | O(n) |

## Related Problems / Pattern Family
- Valid Parentheses (Module 6 #1 — a different stack-cancellation rule, exact matches instead of size comparisons)
- Remove All Adjacent Duplicates in String (Module 6 #3 — the same "does the new thing cancel with the stack's top" shape)
