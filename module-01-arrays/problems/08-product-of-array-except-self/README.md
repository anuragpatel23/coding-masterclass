# 8. Product of Array Except Self

**Difficulty:** Medium
**Pattern:** Prefix / Suffix Product
**LeetCode:** https://leetcode.com/problems/product-of-array-except-self/

## Problem Summary
Given an array `nums`, return an array `result` where `result[i]` is the product of every element in `nums` except `nums[i]`. You must do this in O(n) time **without using division**, in O(1) extra space (the output array doesn't count).

## Example
```
Input:  nums = [1, 2, 3, 4]
Output: [24, 12, 8, 6]
```

## Pattern Recognition
"Product/sum of everything except this index" is the classic **prefix/suffix** setup. Whenever you need, for every index, an aggregate of "everything to my left" combined with "everything to my right," compute both directions in separate passes and combine them — you never need to look at the whole array again per index.

## Approach 1: Brute Force
For each index `i`, loop through the whole array multiplying everything except `nums[i]`.

- **Time:** O(n²)
- **Space:** O(1) extra (excluding output)
- **Why it's not good enough:** the products for index `i` and index `i+1` share almost the entire same set of factors — recomputing from scratch every time throws that overlap away.
- *(Dividing by `nums[i]` from a running total product would be O(n), but the problem explicitly disallows division — and it also breaks if any element is `0`.)*

## Approach 2: Optimized (Prefix then Suffix, combined in the output array)
Two passes, reusing the output array itself as storage:
1. **Left-to-right pass:** `result[i]` = product of everything *before* index `i` (prefix product).
2. **Right-to-left pass:** multiply `result[i]` by the product of everything *after* index `i` (suffix product), tracked in a single running variable.

- **Time:** O(n)
- **Space:** O(1) extra (the output array is required by the problem, so it isn't counted)

## Dry Run
`nums = [1, 2, 3, 4]`

**Prefix pass** (`result[i]` = product of nums[0..i-1]):
| i | result[i] |
|---|---|
| 0 | 1 (empty product) |
| 1 | 1 |
| 2 | 1·2 = 2 |
| 3 | 1·2·3 = 6 |

**Suffix pass** (multiply in product of nums[i+1..end], right to left):
| i | suffix so far | result[i] before | result[i] after |
|---|---|---|---|
| 3 | 1 | 6 | 6·1 = 6 |
| 2 | 4 | 2 | 2·4 = 8 |
| 1 | 4·3=12 | 1 | 1·12 = 12 |
| 0 | 12·2=24 | 1 | 1·24 = 24 |

Result: **[24, 12, 8, 6]**

## Edge Cases
- Exactly one zero in the array → every result is `0` except at the zero's own index, which becomes the product of all other (non-zero) elements — the prefix/suffix approach handles this correctly with no special casing
- Two or more zeros → every result is `0`
- Negative numbers → sign is handled naturally by regular multiplication

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n²) | O(1) extra |
| Optimized (Prefix/Suffix) | O(n) | O(1) extra |

## Related Problems / Pattern Family
- Trapping Rain Water (Module 3 — Two Pointers, also solvable with prefix/suffix max arrays)
- Maximum Subarray (Module 1 #4 — running aggregate, one direction only)
