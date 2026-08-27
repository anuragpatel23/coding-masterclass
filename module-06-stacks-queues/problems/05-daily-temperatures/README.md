# 5. Daily Temperatures

**Difficulty:** Medium
**Pattern:** Monotonic Stack
**LeetCode:** https://leetcode.com/problems/daily-temperatures/

## Problem Summary
Given a list of daily temperatures, return an array where each position holds the number of days you'd have to wait for a warmer temperature. If there's no future day that's warmer, put `0`.

## Example
```
Input:  temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
```

## Pattern Recognition
"For each element, find the next element to the right that's bigger" is the textbook signature of a **monotonic stack**. Keep a stack of *indices* whose temperatures are decreasing from bottom to top — meaning every index still on the stack is still waiting for its "warmer day." The moment you see a temperature high enough to resolve one or more of them, pop and record.

## Approach 1: Brute Force
For each day, scan forward until a warmer day is found.

- **Time:** O(n^2) — for every day, a fresh forward scan
- **Space:** O(1) extra

## Approach 2: Optimized (Monotonic Decreasing Stack)
Walk the temperatures once, maintaining a stack of indices whose temperatures form a decreasing sequence. For each new day, while the stack isn't empty and the current temperature is higher than the temperature at the index on top of the stack, pop that index and record the day-distance (`current index - popped index`) as its answer. Then push the current index.

- **Time:** O(n) — each index is pushed once and popped at most once
- **Space:** O(n) — the stack, worst case a strictly decreasing sequence

## Dry Run
`temperatures = [73,74,75,71,69,72,76,73]`

| i | temp | stack action | resolved |
|---|---|---|---|
| 0 | 73 | push 0 | - |
| 1 | 74 | 74>73: pop 0, result[0]=1-0=1. push 1 | day 0 -> 1 |
| 2 | 75 | 75>74: pop 1, result[1]=2-1=1. push 2 | day 1 -> 1 |
| 3 | 71 | 71<75: push 3 | - |
| 4 | 69 | 69<71: push 4 | - |
| 5 | 72 | 72>69: pop4,result[4]=1. 72>71: pop3,result[3]=2. 72<75: push 5 | day4->1, day3->2 |
| 6 | 76 | 76>72: pop5,result[5]=1. 76>75: pop2,result[2]=4. push 6 | day5->1, day2->4 |
| 7 | 73 | 73<76: push 7 | - |

Remaining stack (indices 6,7) never find a warmer day -> their results stay `0`.

Result: **[1,1,4,2,1,1,0,0]**

## Edge Cases
- Strictly decreasing temperatures -> every result is `0`, since no day ever has a warmer future day
- Strictly increasing temperatures -> every day resolves on the very next iteration, giving mostly `1`s
- All temperatures equal -> no day is ever strictly warmer than another, every result is `0`

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) extra |
| Optimized (monotonic stack) | O(n) | O(n) |

## Related Problems / Pattern Family
- Next Greater Element I (Module 6 #6 — the same monotonic stack technique, applied across two arrays)
- Largest Rectangle in Histogram (Module 6 #12 — a harder monotonic stack application)
