# 14. Happy Number

**Difficulty:** Easy
**Pattern:** Fast/Slow Pointers (Cycle Detection Without a Literal List)
**LeetCode:** https://leetcode.com/problems/happy-number/

## Problem Summary
A number is "happy" if repeatedly replacing it with the sum of the squares of its digits eventually reaches `1`. If the process enters a cycle that never includes `1`, the number is not happy. Determine whether a given number is happy.

## Example
```
Input:  n = 19
Output: true
       19 -> 1^2+9^2=82 -> 8^2+2^2=68 -> 6^2+8^2=100 -> 1^2+0^2+0^2=1
```

## Pattern Recognition
This is Linked List Cycle (#12) in disguise: there's no literal `next` pointer, but the "digit-square-sum" function plays exactly the same role — each number deterministically produces the "next" number in a sequence. If that sequence is going to loop forever without hitting `1`, it must eventually revisit a number it's already seen (since sums of squared digits are bounded, the set of possible values is finite) — which means the sequence has a cycle, and fast/slow pointers can detect it without ever storing the full history.

## Approach 1: Brute Force
Repeatedly apply the digit-square-sum transformation, storing every value seen in a `HashSet`. If you reach `1`, it's happy. If you see a repeated value, you're in a cycle that doesn't include `1` — not happy.

- **Time:** O(log n) per transformation step (bounded by the number of digits), across a number of steps that's small in practice but not obviously bounded without the cycle-detection insight
- **Space:** O(k) where k is the number of distinct values visited before either reaching 1 or repeating
- **Why it's not good enough:** functionally correct, but — just like Linked List Cycle — it spends memory to remember history that fast/slow pointers can avoid entirely.

## Approach 2: Optimized (Floyd's Cycle Detection on the Sequence)
Treat the "next number" function exactly like a linked list's `next` pointer. Run `slow` (one transformation per step) and `fast` (two transformations per step) simultaneously. If they ever become equal, you've found a cycle — check whether that shared value is `1`.

- **Time:** O(log n) per transformation, and the cycle-detection argument bounds the total number of steps without needing extra memory to prove it
- **Space:** O(1)

## Dry Run
`n = 19`

| step | slow | fast |
|---|---|---|
| start | 19 | 19 |
| 1 | 82 | 68 |
| 2 | 68 | 1 |
| 3 | 100 | 1 |
| 4 | 1 | 1 |

`slow == fast == 1` -> happy.

Result: **true**

## Edge Cases
- `n = 1` -> already happy by definition, the loop condition matches immediately
- A genuinely unhappy number, e.g. `n = 2` -> the sequence enters a known cycle (4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20 -> 4 -> ...) that never includes `1`; `slow` and `fast` will meet somewhere inside that cycle, and the meeting value won't be `1`
- Single-digit numbers -> handled the same as any other input, no special casing needed

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (HashSet) | O(log n) per step, bounded steps | O(k) visited values |
| Optimized (fast/slow) | O(log n) per step, bounded steps | O(1) |

## Related Problems / Pattern Family
- Linked List Cycle (Module 3 #12 — the literal-list version of this exact same idea)
- Linked List Cycle II (Module 3 #13 — finding *where* a cycle starts, same technique)
