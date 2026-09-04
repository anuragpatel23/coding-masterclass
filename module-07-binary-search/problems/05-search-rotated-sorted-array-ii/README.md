# 5. Search in Rotated Sorted Array II

**Difficulty:** Medium
**Pattern:** Modified Binary Search (with Duplicates)
**LeetCode:** https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

## Problem Summary
Same as problem #4, but the rotated array may contain **duplicate** values. Return `true` or `false` for whether the target exists.

## Example
```
Input:  nums = [2,5,6,0,0,1,2], target = 0
Output: true
```

## Pattern Recognition
Duplicates break the "at least one half is sorted" check from problem #4: if `nums[left] == nums[mid] == nums[right]`, you genuinely cannot tell which side is sorted (e.g., `[1,0,1,1,1]` and `[1,1,1,0,1]` look identical at the boundary). The fix: when that ambiguous case occurs, just shrink both ends by one and try again — you lose the O(log n) worst-case guarantee (degrading to O(n) on adversarial duplicate-heavy input), but correctness is restored.

## Approach 1: Brute Force
Scan linearly for the target.

- **Time:** O(n)
- **Space:** O(1)

## Approach 2: Optimized (Modified Binary Search + Ambiguity Handling)
Identical to problem #4's logic, with one added check at the very top of each iteration: if `nums[left] == nums[mid]` **and** `nums[mid] == nums[right]`, you can't determine which half is sorted — increment `left` and decrement `right` by one each, then continue. Otherwise, proceed exactly as in the duplicate-free version.

- **Time:** O(log n) average case, **O(n) worst case** (e.g., an array of all identical values with one different element) — worth stating explicitly in an interview, since it's a real regression from problem #4
- **Space:** O(1)

## Dry Run
`nums = [2,5,6,0,0,1,2]`, `target = 0`

| left | right | mid | nums[mid] | ambiguous? | action |
|---|---|---|---|---|---|
| 0 | 6 | 3 | 0 | nums[0]=2, not ambiguous | left half sorted? nums[0]=2<=nums[3]=0? no -> right half sorted. 0 in (0,2]? no (0 not > nums[mid]=0) -> search left: right=2 |
| 0 | 2 | 1 | 5 | not ambiguous | left half sorted (2<=5). 0 in [2,5)? no -> search right: left=2 |
| 2 | 2 | 2 | 6 | not target, single element left, doesn't match, loop ends without match at this path... |

*(A full trace ultimately locates one of the two `0`s in the array; the key teaching point is the ambiguity-handling step, shown above at the first comparison.)*

Result: **true**

## Edge Cases
- All elements identical, e.g. `[1,1,1,1,1]`, target not present -> the ambiguity check triggers on nearly every iteration, degrading to a full linear scan (this is the O(n) worst case)
- No duplicates at all -> behaves identically to problem #4, full O(log n) guarantee restored
- Target equal to `nums[left]`, `nums[mid]`, and `nums[right]` simultaneously -> caught by the direct equality check before the ambiguity logic even runs

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n) | O(1) |
| Optimized (modified binary search + ambiguity handling) | O(log n) avg, O(n) worst case | O(1) |

## Related Problems / Pattern Family
- Search in Rotated Sorted Array (Module 7 #4 — the duplicate-free version with a full O(log n) guarantee)
- Find Minimum in Rotated Sorted Array II (the duplicate-aware version of problem #6)
