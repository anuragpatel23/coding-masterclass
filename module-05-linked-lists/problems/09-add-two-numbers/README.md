# 9. Add Two Numbers

**Difficulty:** Medium
**Pattern:** Dummy Head + Carry Simulation
**LeetCode:** https://leetcode.com/problems/add-two-numbers/

## Problem Summary
Two non-negative integers are represented as linked lists, with digits stored in **reverse order** (the 1's digit is the head). Add the two numbers and return the sum in the same reversed-digit format.

## Example
```
Input:  l1 = 2 -> 4 -> 3 (represents 342),  l2 = 5 -> 6 -> 4 (represents 465)
Output: 7 -> 0 -> 8 (represents 807)
```

## Pattern Recognition
The reversed-digit storage isn't an inconvenience — it's exactly the order elementary-school addition works in: you add the 1's digits first, carry if needed, then the 10's digits, and so on. This maps directly onto a single pass down both lists simultaneously, tracking a `carry` value exactly like doing addition by hand.

## Approach 1: Brute Force
Convert each list into an actual number (using `BigInteger` to safely handle lists representing arbitrarily large numbers), add them, then convert the sum back into a reversed-digit linked list.

- **Time:** O(n+m) to read the digits, plus the cost of `BigInteger` addition (efficient in practice)
- **Space:** O(n+m) — the string/BigInteger representations, plus new nodes
- **Why it's not good enough:** it works, but converting to and from a completely different numeric representation is more machinery than the problem actually needs — the digit-by-digit structure is already exactly what elementary addition wants.

## Approach 2: Optimized (Simultaneous Traversal + Carry)
Walk both lists at once (continuing as long as either still has digits, or there's a leftover carry). At each step, sum the current digits (treating a missing digit as 0) plus the carry from the previous step; the new digit is `sum % 10`, and the new carry is `sum / 10`.

- **Time:** O(max(n, m)) — one pass
- **Space:** O(1) extra (excluding the output list, which is required)

## Dry Run
`l1 = 2 -> 4 -> 3` (342), `l2 = 5 -> 6 -> 4` (465)

| step | l1 digit | l2 digit | carry in | sum | new digit | carry out |
|---|---|---|---|---|---|---|
| 1 | 2 | 5 | 0 | 7 | 7 | 0 |
| 2 | 4 | 6 | 0 | 10 | 0 | 1 |
| 3 | 3 | 4 | 1 | 8 | 8 | 0 |

Result: **7 -> 0 -> 8** (807 = 342 + 465)

## Edge Cases
- Lists of different lengths -> the shorter list is treated as having `0`s once it's exhausted, handled naturally by the null checks
- A final carry after both lists are exhausted, e.g. `5 -> 9` (five... wait, `9999 + 1`) -> the loop condition explicitly continues "while carry != 0" even after both lists end, correctly appending one more digit
- Both inputs are `0 -> null` -> sum is `0`, correctly returns a single node with value `0`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (BigInteger conversion) | O(n+m) | O(n+m) |
| Optimized (simultaneous traversal + carry) | O(max(n,m)) | O(1) extra |

## Related Problems / Pattern Family
- Add Two Numbers II (same idea, but digits stored in forward order — usually solved with a stack or by reversing first)
- Multiply Strings (Module 2 #15 — the same digit-by-digit-with-carry idea, applied to strings instead of lists)
