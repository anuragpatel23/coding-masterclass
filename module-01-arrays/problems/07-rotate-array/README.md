# 7. Rotate Array

**Difficulty:** Medium
**Pattern:** In-Place Rotation via Reversal
**LeetCode:** https://leetcode.com/problems/rotate-array/

## Problem Summary
Given an array, rotate it to the right by `k` steps, in-place.

## Example
```
Input:  nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
```

## Pattern Recognition
Rotation problems that demand O(1) extra space have a well-known trick: **reversing sub-ranges reorders elements without extra storage.** Whenever you see "rotate an array in-place," think reversal, not shifting.

## Approach 1: Brute Force
Allocate a new array. Each element at index `i` belongs at index `(i + k) % n` in the result.

- **Time:** O(n)
- **Space:** O(n) — again, correct but not in-place
- *(A more naive version — rotate one position at a time, `k` times — is O(n·k) and should be avoided entirely; it's worth knowing it exists so you can immediately rule it out.)*

## Approach 2: Optimized (Triple Reversal)
Three reversals accomplish the rotation with zero extra space:
1. Reverse the **entire array**.
2. Reverse the **first `k` elements**.
3. Reverse the **remaining `n - k` elements**.

Reversing the whole array flips every element's relative position; reversing the two segments separately un-flips the internal order within each segment while keeping the segments themselves swapped to the front/back — which is exactly a rotation.

- **Time:** O(n)
- **Space:** O(1)

## Dry Run
`nums = [1,2,3,4,5,6,7]`, `k = 3`

| Step | Array |
|---|---|
| Start | [1,2,3,4,5,6,7] |
| 1. Reverse all | [7,6,5,4,3,2,1] |
| 2. Reverse first k=3 | [5,6,7,4,3,2,1] |
| 3. Reverse remaining n-k=4 | [5,6,7,1,2,3,4] |

Result: **[5,6,7,1,2,3,4]**

## Edge Cases
- `k > n` — always take `k = k % n` first, otherwise you reverse an out-of-bounds range
- `k == 0` or `k == n` — no visible rotation, but the algorithm still runs safely
- Array of length 1 — any rotation is a no-op

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Naive (rotate one at a time) | O(n·k) | O(1) |
| Brute Force (extra array) | O(n) | O(n) |
| Optimized (triple reversal) | O(n) | O(1) |

## Related Problems / Pattern Family
- Reverse Words in a String (Module 2 — same reversal-composition idea)
- Rotate List (Module 5 — Linked Lists, same concept on pointers instead of indices)
