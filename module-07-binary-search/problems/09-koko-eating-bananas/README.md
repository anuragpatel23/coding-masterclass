# 9. Koko Eating Bananas

**Difficulty:** Medium
**Pattern:** Binary Search on the Answer Space
**LeetCode:** https://leetcode.com/problems/koko-eating-bananas/

## Problem Summary
Koko has `piles` of bananas and `h` hours to eat them all. She picks an eating speed `k` (bananas per hour, applied per pile — any pile smaller than `k` still takes a full hour). Find the minimum integer `k` such that she can finish all piles within `h` hours.

## Example
```
Input:  piles = [3,6,7,11], h = 8
Output: 4
```

## Pattern Recognition
The direct question — "what's the minimum speed" — is hard to compute directly. But the *indirect* question — "given a candidate speed, can she finish in time?" — is easy to check, and critically, it's **monotonic**: if speed `k` works, any speed faster than `k` also works. That monotonic yes/no structure is the green light for binary searching the space of possible speeds instead of the piles themselves.

## Approach 1: Brute Force
Try every possible speed starting from `1` upward, checking feasibility at each one, until the first speed that works.

- **Time:** O(maxPile * n) — up to `maxPile` candidate speeds, each requiring an O(n) feasibility check
- **Space:** O(1)

## Approach 2: Optimized (Binary Search on Speed)
Search the range `[1, max(piles)]` — no speed faster than the largest pile is ever necessary. For each candidate speed, compute the total hours needed (summing `ceil(pile / speed)` for every pile). If that's `<= h`, this speed works — try something slower (`right = mid`). Otherwise, it's too slow — try faster (`left = mid + 1`).

- **Time:** O(n log(maxPile)) — O(log maxPile) candidate speeds tried, each with an O(n) feasibility check
- **Space:** O(1)

## Dry Run
`piles = [3,6,7,11]`, `h = 8`, search range `[1, 11]`

| left | right | mid (speed) | hours needed | <= 8? | action |
|---|---|---|---|---|---|
| 1 | 11 | 6 | ceil(3/6)+ceil(6/6)+ceil(7/6)+ceil(11/6) = 1+1+2+2=6 | yes | right=6 |
| 1 | 6 | 3 | 1+2+3+4=10 | no | left=4 |
| 4 | 6 | 5 | 1+2+2+3=8 | yes | right=5 |
| 4 | 5 | 4 | 1+2+2+3=8 | yes | right=4 |

`left == right == 4`.

Result: **4**

## Edge Cases
- `h` exactly equal to the number of piles -> forces the maximum possible speed (`max(piles)`), since Koko can only eat one pile per hour at best
- A pile with exactly `1` banana -> still takes a full hour at any speed, correctly handled by the ceiling-division formula
- `h` very large (many more hours than piles) -> the minimum feasible speed converges toward `1`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(maxPile * n) | O(1) |
| Optimized (binary search on answer) | O(n log(maxPile)) | O(1) |

## Related Problems / Pattern Family
- Capacity To Ship Packages Within D Days (Module 7 #10 — the same binary-search-on-answer skeleton, different feasibility check)
- Split Array Largest Sum (Module 7 #15 — a harder variant of this exact technique)
