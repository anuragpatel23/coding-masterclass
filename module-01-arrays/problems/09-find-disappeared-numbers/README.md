# 9. Find All Numbers Disappeared in an Array

**Difficulty:** Easy
**Pattern:** Index Marking
**LeetCode:** https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/

## Problem Summary
Given an array of `n` integers where every value is in the range `[1, n]`, some values appear twice and others are missing entirely. Return a list of all the integers in `[1, n]` that never appear — without using extra space beyond the output list.

## Example
```
Input:  nums = [4,3,2,7,8,2,3,1]
Output: [5,6]
```

## Pattern Recognition
The moment values are constrained to the range `[1, n]` for an array of length `n`, that's a strong signal: **the values themselves can be used as indices.** This "index marking" family (sometimes called cyclic sort's cousin) lets you record "have I seen this value" directly inside the input array, for O(1) extra space.

## Approach 1: Brute Force
Put every number into a `HashSet`, then check which numbers from `1` to `n` are missing.

- **Time:** O(n)
- **Space:** O(n) — the set is the part that isn't truly "extra-space-free"
- **Why it's not good enough:** it's already O(n) time, so the issue here is purely space — this is a case where the *optimized* solution isn't faster, just tighter on memory, which many interviewers will still push you toward.

## Approach 2: Optimized (In-Place Index Marking)
For each value `v` you encounter, go to index `|v| - 1` and negate whatever is stored there (using absolute value on read, since a slot may already have been negated by an earlier duplicate). A negative sign at index `i` after this pass means "the value `i+1` was seen." Any index still positive at the end means its corresponding value (`index + 1`) never appeared.

- **Time:** O(n)
- **Space:** O(1) extra (the array itself is repurposed as marker storage; optionally restore original signs afterward)

## Dry Run
`nums = [4,3,2,7,8,2,3,1]` (1-indexed values, 0-indexed array)

Marking pass — for each `v`, negate index `|v|-1` if not already negative:
| Read v | index to mark | array after |
|---|---|---|
| 4 | 3 | [4,3,2,**-7**,8,2,3,1] |
| 3 | 2 | [4,3,**-2**,-7,8,2,3,1] |
| 2 | 1 | [4,**-3**,-2,-7,8,2,3,1] |
| 7 | 6 | [4,-3,-2,-7,8,2,**-3**,1] |
| 8 | 7 | [4,-3,-2,-7,8,2,-3,**-1**] |
| 2 | 1 (already negative, skip) | unchanged |
| 3 | 2 (already negative, skip) | unchanged |
| 1 | 0 | [**-4**,-3,-2,-7,8,2,-3,-1] |

Final array: `[-4,-3,-2,-7,8,2,-3,-1]`. Indices still positive: `4` (value 8) and `5` (value 2, wait — check value at index 5).

Indices `4` and `5` are positive → missing values are `4+1=5` and `5+1=6`.

Result: **[5, 6]**

## Edge Cases
- No numbers missing → every index gets negated, return an empty list
- All numbers identical, e.g. `[1,1,1]` → only index `0` ever gets marked, indices `1` and `2` remain positive → missing = `[2, 3]`
- If asked to preserve the original array, remember to restore signs in a final pass (shown in the code)

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (HashSet) | O(n) | O(n) |
| Optimized (Index Marking) | O(n) | O(1) extra |

## Related Problems / Pattern Family
- Find the Duplicate Number (same index-marking family, Floyd's cycle variant also applies)
- First Missing Positive (a harder cyclic-sort variant)
- Missing Number (Module 1 #10 — a related but distinct math-based approach)
