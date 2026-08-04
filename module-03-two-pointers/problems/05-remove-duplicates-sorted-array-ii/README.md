# 5. Remove Duplicates from Sorted Array II

**Difficulty:** Medium
**Pattern:** Read/Write Two Pointers (Bounded Occurrence Count)
**LeetCode:** https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/

## Problem Summary
Given a sorted array, remove duplicates in-place so each unique element appears **at most twice**, and return the new length.

## Example
```
Input:  nums = [1,1,1,2,2,3]
Output: 5, nums becomes [1,1,2,2,3,...]
```

## Pattern Recognition
This generalizes problem #4 (allow at most 1 copy) to "allow at most `k` copies." The key insight: a candidate value at the read pointer is safe to keep if it's different from whatever was written **`k` positions before** the current write pointer — that comparison is all you need, no explicit counting required.

## Approach 1: Brute Force
Count the occurrences of each value (preserving sorted order with a `LinkedHashMap`), then write back up to 2 copies of each.

- **Time:** O(n)
- **Space:** O(n) — the map
- **Why it's not good enough:** same story as problem #4 — you don't need a general-purpose counting structure when the sorted property already tells you everything about adjacency.

## Approach 2: Optimized (Compare Against `slow - 2`)
Start `slow` at index `2` (the first two elements are always safe to keep, since at most 2 copies are always allowed). Walk `fast` from index `2` onward. Compare `nums[fast]` to `nums[slow - 2]` — the value that was written two positions before the current write pointer:
- If they're **different**, `nums[fast]` is safe to keep (writing it can't create a third copy) — write it at `slow` and advance `slow`.
- If they're the **same**, writing `nums[fast]` would create a third occurrence — skip it.

- **Time:** O(n)
- **Space:** O(1)

## Dry Run
`nums = [1,1,1,2,2,3]`

| fast | nums[fast] | compare to nums[slow-2] | action | slow after |
|---|---|---|---|---|
| 2 | 1 | nums[0]=1, equal | skip | 2 |
| 3 | 2 | nums[0]=1, different | write nums[2]=2 | 3 |
| 4 | 2 | nums[1]=1, different | write nums[3]=2 | 4 |
| 5 | 3 | nums[2]=2, different | write nums[4]=3 | 5 |

Result: new length **5**, array's first 5 elements are `[1,1,2,2,3]`

## Edge Cases
- Array length 2 or less -> every element is trivially allowed (can't exceed 2 copies), return the original length unchanged
- A value appearing more than twice, e.g. `[1,1,1,1]` -> only the first two copies are kept, new length `2`
- No duplicates at all -> every comparison passes, new length equals original length

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (LinkedHashMap) | O(n) | O(n) |
| Optimized (slow-2 comparison) | O(n) | O(1) |

## Related Problems / Pattern Family
- Remove Duplicates from Sorted Array (Module 3 #4 — the `k=1` special case of this same idea)
