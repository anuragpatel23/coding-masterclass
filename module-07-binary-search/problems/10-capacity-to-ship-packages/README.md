# 10. Capacity To Ship Packages Within D Days

**Difficulty:** Medium
**Pattern:** Binary Search on the Answer Space
**LeetCode:** https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/

## Problem Summary
Given package weights (shipped in order, one ship, no reordering) and a number of days `D`, find the minimum ship capacity such that all packages can be shipped within `D` days (each day loads as many consecutive packages as fit without exceeding capacity).

## Example
```
Input:  weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
```

## Pattern Recognition
Exactly the same shape as Koko Eating Bananas (#9): "minimum capacity such that a feasibility check passes" is a monotonic condition (any capacity larger than a working one also works) over a bounded range — a green light for binary searching the capacity itself instead of the packages.

## Approach 1: Brute Force
Try every possible capacity from `max(weights)` (the minimum viable capacity — must fit the heaviest single package) upward, checking feasibility at each one.

- **Time:** O((sum - max) * n) — up to that many candidate capacities, each with an O(n) feasibility check
- **Space:** O(1)

## Approach 2: Optimized (Binary Search on Capacity)
Search the range `[max(weights), sum(weights)]` — the true answer always lies within these bounds. For each candidate capacity, simulate loading: greedily pack consecutive weights into the current day's load until the next package wouldn't fit, then start a new day. If the total days needed is `<= D`, this capacity works — try smaller. Otherwise, try larger.

- **Time:** O(n log(sum)) — O(log(sum - max)) candidate capacities, each with an O(n) simulation
- **Space:** O(1)

## Dry Run
`weights = [1,2,3,4,5,6,7,8,9,10]`, `days = 5`, search range `[10, 55]`

| left | right | mid (capacity) | days needed | <= 5? | action |
|---|---|---|---|---|---|
| 10 | 55 | 32 | packing greedily needs 2 days | yes | right=32 |
| 10 | 32 | 21 | needs 3 days | yes | right=21 |
| 10 | 21 | 15 | needs 5 days | yes | right=15 |
| 10 | 15 | 12 | needs 6 days | no | left=13 |
| 13 | 15 | 14 | needs 6 days | no | left=15 |

`left == right == 15`.

Result: **15**

## Edge Cases
- `days` equal to the number of packages -> forces the minimum viable capacity, `max(weights)`, since every package must ship on its own day at best
- `days = 1` -> forces the maximum possible capacity, `sum(weights)`, since everything must ship in a single load
- A single package -> trivially, its own weight is both the min and max of the search range

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O((sum-max) * n) | O(1) |
| Optimized (binary search on answer) | O(n log(sum)) | O(1) |

## Related Problems / Pattern Family
- Koko Eating Bananas (Module 7 #9 — the same binary-search-on-answer skeleton)
- Split Array Largest Sum (Module 7 #15 — nearly identical feasibility-check structure)
