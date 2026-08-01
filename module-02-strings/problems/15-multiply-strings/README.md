# 15. Multiply Strings

**Difficulty:** Medium
**Pattern:** Digit-by-Digit Multiplication (Grid Convolution with Shared Carry)
**LeetCode:** https://leetcode.com/problems/multiply-strings/

## Problem Summary
Given two non-negative integers represented as strings, return their product, also as a string. You can't convert the inputs directly to native integers (they can be arbitrarily large — the whole point of the problem is to multiply them manually, digit by digit).

## Example
```
Input:  num1 = "123", num2 = "456"
Output: "56088"
```

## Pattern Recognition
This is grade-school long multiplication, translated into code: each digit of `num1` gets multiplied by each digit of `num2`, and the results land at predictable positions based on their place value. Whenever you're asked to implement arithmetic manually on arbitrarily large numbers represented as strings/arrays, think in terms of a **result buffer indexed by combined digit position**, with carries resolved in a final cleanup pass rather than during every individual operation.

## Approach 1: Brute Force (Row by Row, School Method)
For each digit of `num2` (from least significant to most), multiply it against the *entire* `num1` to produce a "partial product" row (with its own carry handling), shift that row left by the appropriate number of zero-placeholders, and add it to a running total using ordinary string addition.

- **Time:** O(n·m) to generate all the partial rows, **plus** up to O(m·(n+m)) to add up to `m` partial rows together one at a time via repeated string addition — so roughly O(n·m + m²) overall
- **Space:** O(n+m) per intermediate row/sum, with several such intermediates created along the way
- **Why it's not good enough:** this mirrors exactly how you'd do it by hand on paper — which is intuitive, but it does redundant work managing carries separately in each row and then again across every addition step, and allocates a new string at every stage.

## Approach 2: Optimized (Single Result Array, Shared Carry Resolution)
Multiplying digit `num1[i]` by digit `num2[j]` contributes to position `i + j + 1` in the result (with any overflow carrying into position `i + j`) — this holds regardless of which other digit pairs you've already processed. So: allocate one integer array of size `n + m`, accumulate **every** digit-pair product directly into its correct position (adding to whatever's already there), and resolve all carries in a single pass at the end when converting to the final string.

- **Time:** O(n · m) — every pair of digits is visited exactly once, with no repeated additions across rows
- **Space:** O(n + m) — just the one result array

## Dry Run
`num1 = "123"`, `num2 = "456"` (both processed right to left; `result` has 6 slots, indices 0-5)

Each `num1[i] * num2[j]` lands at `result[i+j+1]`, carrying into `result[i+j]`:

| i,j | digits | product | position | 
|---|---|---|---|
| 2,2 | 3*6 | 18 | result[5]+=8, result[4]+=1 |
| 2,1 | 3*5 | 15 | result[4]+=6, result[3]+=1 |
| 2,0 | 3*4 | 13 | result[3]+=3, result[2]+=1 |
| 1,2 | 2*6 | 12 | result[4]+=8, result[3]+=1 |
| 1,1 | 2*5 | 14 | result[3]+=4, result[2]+=1 |
| 1,0 | 2*4 | 10 | result[2]+=0, result[1]+=1 |
| 0,2 | 1*6 |  6 | result[3]+=0, result[2]+=1 |
| 0,1 | 1*5 |  5 | result[2]+=6, result[1]+=0 |
| 0,0 | 1*4 |  4 | result[1]+=5, result[0]+=0 |

*(each cell's carry is folded into the position to its left as the pass proceeds, per digit)*

Final digit array (after carries settle): `[0,5,6,0,8,8]` -> trim leading zero -> **"56088"**

## Edge Cases
- Either input is `"0"` -> the answer is `"0"` (handled as an explicit early check, since the general algorithm could otherwise produce a string with leading zeros like `"00"`)
- Result has leading zeros in the raw digit array (e.g. multiplying two single-digit numbers) -> trim them when building the final string, but never trim down to an empty string — fall back to `"0"`
- Very long inputs (hundreds of digits) -> this is exactly why native integer types aren't used; the algorithm must work purely on digit arrays

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (row by row + repeated addition) | O(n*m + m^2) | O(n+m) per intermediate |
| Optimized (single result array) | O(n*m) | O(n+m) |

## Related Problems / Pattern Family
- Add Strings (the addition building block used inside the brute-force row-summing approach)
- Plus One / Add Binary (simpler single-array digit arithmetic with carries)
