# 7. Fruit Into Baskets

**Difficulty:** Medium
**Pattern:** Variable-Size Sliding Window (k=2 special case)
**LeetCode:** https://leetcode.com/problems/fruit-into-baskets/

## Problem Summary
You're picking fruit from a row of trees (`fruits[i]` is the type of fruit at position `i`) into exactly 2 baskets, each holding only one type of fruit each (but unlimited quantity). Once you start, you must pick from every tree moving right until you can't fit any more. Return the maximum number of fruits you can collect.

## Example
```
Input:  fruits = [1,2,1]
Output: 3        (all three trees fit: two types, unlimited quantity each)
```

## Pattern Recognition
Strip away the fruit-picking story and this is exactly Longest Substring with At Most K Distinct Characters (#6), with `k` fixed at 2: find the longest contiguous run containing at most 2 distinct values. Recognizing when a word problem is secretly a pattern you already know is half the battle in interviews.

## Approach 1: Brute Force
For every starting tree, extend rightward tracking distinct fruit types with a set, stopping once a third type appears.

- **Time:** O(n^2)
- **Space:** O(1) — at most 2-3 types ever tracked

## Approach 2: Optimized (Variable Window + Frequency Map, k=2)
Identical mechanics to problem #6: expand right, track a frequency count per fruit type, and shrink from the left whenever more than 2 distinct types are present in the window.

- **Time:** O(n)
- **Space:** O(1) — bounded by at most 3 map entries at any moment

## Dry Run
`fruits = [1,2,1]`

| right | fruit | distinct types | action | window |
|---|---|---|---|---|
| 0 | 1 | 1 | ok | [1] |
| 1 | 2 | 2 | ok | [1,2] |
| 2 | 1 | 2 | ok | [1,2,1] |

No third type ever appears -> the whole array is valid.

Result: **3**

## Edge Cases
- Only one fruit type in the entire row -> the whole array is trivially valid
- Exactly two types throughout -> same as above, whole array valid
- A third type appears immediately at index 1 -> window shrinks aggressively right from the start

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n^2) | O(1) |
| Optimized (variable window, k=2) | O(n) | O(1) |

## Related Problems / Pattern Family
- Longest Substring with At Most K Distinct Characters (Module 4 #6 — the general-k version of this exact technique)
