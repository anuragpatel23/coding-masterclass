# 14. Implement Stack using Queues

**Difficulty:** Easy
**Pattern:** Single-Queue Rotation Simulation
**LeetCode:** https://leetcode.com/problems/implement-stack-using-queues/

## Problem Summary
Implement a LIFO stack using only queue operations (enqueue, dequeue, front, empty).



## Example
```
push(1), push(2), top() -> 2, pop() -> 2, empty() -> false
```

## Pattern Recognition
A queue naturally preserves FIFO order; you need LIFO. The trick: after enqueueing a new element, **rotate the queue** so that the newly-added element ends up at the front — that's the "most recently added" position a stack's `pop` needs.

## Approach 1: Two Queues
Enqueue the new element into an empty second queue, then drain the first queue into the second (placing the new element at the front), then swap which queue is "active."

- **Time:** O(n) per `push`, O(1) for `pop`/`top`
- **Space:** O(n) — two queues

## Approach 2: Optimized (Single Queue, Rotate After Every Push)
Enqueue the new element onto the back of a single queue as usual, then rotate the queue by dequeuing and re-enqueueing every *other* element (everything that was already there before this push) — this walks the new element around to the front without needing a second queue at all.

- **Time:** O(n) per `push`, O(1) for `pop`/`top` (same complexity as the two-queue version, but with half the space)
- **Space:** O(n) — a single queue

## Dry Run
`push(1)`, `push(2)`, `push(3)`, `pop()`

| operation | queue after |
|---|---|
| push(1) | [1] (size 1, 0 rotations) |
| push(2) | enqueue 2: [1,2]. rotate 1 time: dequeue 1, enqueue 1 -> [2,1] |
| push(3) | enqueue 3: [2,1,3]. rotate 2 times: dequeue2,enqueue2->[1,3,2]; dequeue1,enqueue1->[3,2,1] |
| pop() | dequeue front = **3** |

## Edge Cases
- A single element -> `push` enqueues it with zero rotations needed, `pop`/`top` trivially access it
- Many pushes in a row -> each one independently rotates the *entire* current queue, so the total work across n pushes is O(n^2) — the same total cost as the two-queue version, just consolidated into one structure
- `pop`/`top` called on an empty stack -> should be guarded against per the problem's usage contract

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Two queues | O(n) push, O(1) pop/top | O(n) (two queues) |
| Optimized (single queue, rotation) | O(n) push, O(1) pop/top | O(n) (one queue) |

## Related Problems / Pattern Family
- Implement Queue using Stacks (Module 6 #13 — the mirror-image problem)
