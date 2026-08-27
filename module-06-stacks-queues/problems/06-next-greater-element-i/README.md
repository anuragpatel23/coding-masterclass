# 6. Next Greater Element I

**Difficulty:** Easy
**Pattern:** Monotonic Stack + HashMap
**LeetCode:** https://leetcode.com/problems/next-greater-element-i/

## Problem Summary
`nums1` is a subset of `nums2` (all elements distinct). For each element of `nums1`, find its next greater element in `nums2` (the first element to its right in `nums2` that's larger), or `-1` if none exists.

## Example
```
Input:  nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
```

## Pattern Recognition
This is Daily Temperatures (#5) with the query separated from the data: instead of asking "next greater" for every element of the same array, you precompute the next-greater relationship for **all** of `nums2` once (using the same monotonic stack idea), store it in a hashmap, then just look up whatever `nums1` needs.

## Approach 1: Brute Force
For each element in `nums1`, find its position in `nums2` (a scan), then scan forward from there for the first larger value.

- **Time:** O(n * m) — n elements in nums1, each triggering up to an O(m) search plus an O(m) scan
- **Space:** O(1) extra (beyond the output)

## Approach 2: Optimized (Monotonic Stack Over nums2, Then Lookup)
Walk `nums2` once with a monotonic decreasing stack of *values* (not indices, since we only ever need the value here). Whenever the current number is bigger than the stack's top, pop it and record `popped -> current` in a hashmap — that popped value's next-greater element has just been found. After processing all of `nums2`, look up each element of `nums1` in the hashmap (defaulting to `-1` if absent).

- **Time:** O(n + m) — one pass to build the map, one pass to answer queries
- **Space:** O(m) — the hashmap and stack

## Dry Run
`nums2 = [1,3,4,2]`

| num | stack action | map updates |
|---|---|---|
| 1 | push 1 | - |
| 3 | 3>1: pop 1, map[1]=3. push 3 | 1->3 |
| 4 | 4>3: pop 3, map[3]=4. push 4 | 3->4 |
| 2 | 2<4: push 2 | - |

Final map: `{1:3, 3:4}`. Stack leftover (4, 2) never found a greater element -> not in the map.

`nums1 = [4,1,2]` -> lookups: `4` -> not in map -> `-1`. `1` -> `3`. `2` -> not in map -> `-1`.

Result: **[-1, 3, -1]**

## Edge Cases
- An element of `nums1` that's the maximum in `nums2` -> never found a next-greater element, correctly defaults to `-1`
- `nums2` strictly decreasing -> the map ends up empty, every query returns `-1`
- `nums1` equals `nums2` exactly -> every element gets its correctly computed next-greater value (or `-1`)

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n*m) | O(1) extra |
| Optimized (monotonic stack + hashmap) | O(n+m) | O(m) |

## Related Problems / Pattern Family
- Next Greater Element II (Module 6 #7 — the same idea, on a circular array)
- Daily Temperatures (Module 6 #5 — the single-array version of this exact monotonic stack technique)
