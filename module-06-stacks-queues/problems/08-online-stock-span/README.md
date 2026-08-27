# 8. Online Stock Span

**Difficulty:** Medium
**Pattern:** Monotonic Stack (Streaming / Online)
**LeetCode:** https://leetcode.com/problems/online-stock-span/

## Problem Summary
Design a class that receives daily stock prices one at a time (an "online" or streaming setting) and, for each new price, returns its **span** — the number of consecutive days (including today) where the price was less than or equal to today's price.

## Example
```
next(100) -> 1
next(80)  -> 1
next(60)  -> 1
next(70)  -> 2
next(60)  -> 1
next(75)  -> 4
next(85)  -> 6
```

## Pattern Recognition
This is Daily Temperatures (#5) run in reverse and streamed one call at a time: instead of precomputing an answer for a fixed array, you maintain a monotonic stack across calls, where each stack entry bundles a price with the "span" it already accumulated — letting you skip over runs of smaller prices in O(1) amortized time per call, even though the stream is unbounded.

## Approach 1: Brute Force
Store every price seen so far in a list. For each new price, scan backward counting consecutive days with price `<=` today's, stopping at the first larger price.

- **Time:** O(n) per call in the worst case, O(n^2) total across n calls
- **Space:** O(n) — the growing price history

## Approach 2: Optimized (Monotonic Stack of (price, span) Pairs)
Maintain a stack of `(price, span)` pairs, decreasing in price from bottom to top. For each new price, pop every entry whose price is `<=` the new price, **accumulating** their spans into the new entry's span (since all those days are now "absorbed" into today's streak). Push the resulting `(price, span)` pair.

- **Time:** O(1) amortized per call — each entry is pushed once and popped at most once across the whole stream
- **Space:** O(n) — the stack, worst case strictly increasing prices

## Dry Run
`next(100)`, `next(80)`, `next(60)`, `next(70)`, `next(60)`, `next(75)`, `next(85)`

| call | stack action | span returned |
|---|---|---|
| 100 | push (100,1) | 1 |
| 80 | 80<=100? no pop. push (80,1) | 1 |
| 60 | push (60,1) | 1 |
| 70 | pop(60,1): 60<=70, span=1+1=2. 80<=70? no. push (70,2) | 2 |
| 60 | push (60,1) | 1 |
| 75 | pop(60,1)->span=1+1=2. pop(70,2)->span=2+2=4. 80<=75?no. push(75,4) | 4 |
| 85 | pop(75,4)->span=1+4=5. pop(80,1)->span=5+1=6. push(85,6) | 6 |

Result sequence: **1, 1, 1, 2, 1, 4, 6**

## Edge Cases
- Strictly increasing prices -> every call absorbs the entire stack, spans grow to match the full day count so far
- Strictly decreasing prices -> nothing ever gets absorbed, every span stays `1`
- The very first call -> the stack is empty, span is trivially `1`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n) per call, O(n^2) total | O(n) |
| Optimized (monotonic stack of price/span pairs) | O(1) amortized per call | O(n) |

## Related Problems / Pattern Family
- Daily Temperatures (Module 6 #5 — the same monotonic stack idea, offline instead of streaming)
- Largest Rectangle in Histogram (Module 6 #12 — a harder monotonic stack application)
