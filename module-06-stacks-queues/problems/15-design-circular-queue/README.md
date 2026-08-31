# 15. Design Circular Queue

**Difficulty:** Medium
**Pattern:** Array-Based Circular Buffer
**LeetCode:** https://leetcode.com/problems/design-circular-queue/

## Problem Summary
Design a fixed-capacity circular queue supporting `enQueue`, `deQueue`, `Front`, `Rear`, `isEmpty`, and `isFull`, all in O(1).

## Example
```
CircularQueue(3): enQueue(1), enQueue(2), enQueue(3), enQueue(4) -> false (full),
Rear() -> 3, isFull() -> true, deQueue() -> true, enQueue(4) -> true, Rear() -> 4
```

## Pattern Recognition
"Fixed capacity" and "O(1) required" together rule out a dynamically-resizing structure — you want a plain fixed-size array, with `head` and `count` tracked manually, and indices wrapping around via modulo arithmetic. That wraparound (index `(head + count) % capacity`) is what makes it "circular": the underlying array never grows, elements just reuse freed-up slots at the front.

## Approach 1: Brute Force (Built-In Deque + Manual Capacity Check)
Use a `Deque` as the backing structure, manually checking `size() == capacity` before every `enQueue` to simulate a fixed limit.

- **Time:** O(1) for all operations
- **Space:** O(k) — but relies on a dynamically-resizing structure under the hood rather than one truly fixed-size array, so it doesn't demonstrate (or get the memory-locality benefit of) an actual circular buffer

## Approach 2: Optimized (Raw Array + Head/Count Tracking)
Allocate a fixed-size array up front. Track `head` (the index of the front element) and `count` (how many elements are currently stored). `enQueue` writes to `(head + count) % capacity` and increments `count`. `deQueue` advances `head` by one (mod capacity) and decrements `count`. `isFull`/`isEmpty` are simple comparisons against `capacity`/`0`.

- **Time:** O(1) for every operation
- **Space:** O(k) — exactly one array of the requested capacity, no more

## Dry Run
`CircularQueue(3)`: capacity=3, data=[_,_,_], head=0, count=0

| operation | tail index used | data after | head, count |
|---|---|---|---|
| enQueue(1) | (0+0)%3=0 | [1,_,_] | 0, 1 |
| enQueue(2) | (0+1)%3=1 | [1,2,_] | 0, 2 |
| enQueue(3) | (0+2)%3=2 | [1,2,3] | 0, 3 (full) |
| deQueue() | - | [1,2,3] (unchanged data, head moves) | 1, 2 |
| enQueue(4) | (1+2)%3=0 | [4,2,3] (slot 0 reused) | 1, 3 (full) |

`Front()` now correctly returns `data[head=1]` = `2`. `Rear()` returns `data[(head+count-1)%capacity] = data[(1+3-1)%3] = data[0] = 4`.

## Edge Cases
- Queue is full -> `enQueue` returns `false` without modifying state
- Queue is empty -> `deQueue`, `Front`, and `Rear` all return `false`/`-1` without modifying state
- Wraparound exactly at the array boundary -> this is the core case the modulo arithmetic is designed to handle correctly, as shown in the dry run above

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (Deque + capacity check) | O(1) all operations | O(k), dynamic structure |
| Optimized (raw array, head/count) | O(1) all operations | O(k), fixed array |

## Related Problems / Pattern Family
- Design Circular Deque (the same technique, supporting insertion/removal at both ends)
- Implement Queue using Stacks (Module 6 #13 — a different queue-design constraint)
