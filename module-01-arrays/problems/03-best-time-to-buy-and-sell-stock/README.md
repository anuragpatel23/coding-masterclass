# 3. Best Time to Buy and Sell Stock

**Difficulty:** Easy
**Pattern:** Single-Pass Greedy Tracking
**LeetCode:** https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

## Problem Summary
You're given an array `prices` where `prices[i]` is the stock price on day `i`. You may complete exactly one buy and one sell (buy must happen before sell). Return the maximum profit achievable, or `0` if no profit is possible.

## Example
```
Input:  prices = [7, 1, 5, 3, 6, 4]
Output: 5        (buy at 1, sell at 6)
```

## Pattern Recognition
"Buy low, sell high, buy must come before sell" is a signal for tracking a **running minimum while scanning left to right** — you don't need to know the future, just the cheapest price you've seen *so far*.

## Approach 1: Brute Force
For every buy day, check every possible later sell day and track the best profit.

- **Time:** O(n²)
- **Space:** O(1)
- **Why it's not good enough:** for each day `i`, you're re-scanning all future days even though the best sell-after-`i` profit only depends on the minimum price up to `i` — a value you could maintain incrementally instead of recomputing.

## Approach 2: Optimized (Single Pass)
Track the lowest price seen so far as you scan. At each day, ask: *"if I sold today, having bought at the lowest price so far, what's my profit?"* Keep the best of those.

- **Time:** O(n)
- **Space:** O(1)

## Dry Run
`prices = [7, 1, 5, 3, 6, 4]`

| i | price | minPriceSoFar (before) | profit if sell today | maxProfit |
|---|---|---|---|---|
| 0 | 7 | 7 | — | 0 |
| 1 | 1 | 7 | -6 | 0, minPrice→1 |
| 2 | 5 | 1 | 4 | 4 |
| 3 | 3 | 1 | 2 | 4 |
| 4 | 6 | 1 | 5 | 5 |
| 5 | 4 | 1 | 3 | 5 |

## Edge Cases
- Prices strictly decreasing, e.g. `[7,6,4,3,1]` → no profitable trade, return `0`
- Single-day array → can't buy and sell, return `0`
- All prices equal → profit is `0`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n²) | O(1) |
| Optimized (Single Pass) | O(n) | O(1) |

## Related Problems / Pattern Family
- Best Time to Buy and Sell Stock II (multiple transactions — Module 17, Greedy)
- Best Time to Buy and Sell Stock with Cooldown (Module 15/16 — DP)
- Maximum Subarray (Module 1 #4 — same "running best" mental model, applied to sums instead of prices)
