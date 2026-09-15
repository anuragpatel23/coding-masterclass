# 14. Median of Two Sorted Arrays

**Difficulty:** Hard
**Pattern:** Binary Search on a Partition Point
**LeetCode:** https://leetcode.com/problems/median-of-two-sorted-arrays/

## Problem Summary
Given two sorted arrays, find the median of the combined dataset, in O(log(min(m,n))) time.

## Example
```
Input:  nums1 = [1,3], nums2 = [2]
Output: 2.0
```

## Pattern Recognition
This is the module's capstone because it needs binary search on something more abstract than a value or an index — it searches for the correct **partition point** that splits both arrays combined into a left half and a right half of (nearly) equal size, where every element in the left half is `<=` every element in the right half. Once that partition is found, the median is directly computable from the four boundary elements around the cut.

## Approach 1: Brute Force
Merge both sorted arrays (using a standard two-pointer merge), then read off the middle element(s) directly.

- **Time:** O(m+n) — a full merge
- **Space:** O(m+n) — the merged array
- **Why it's not good enough:** this doesn't hit the required O(log(min(m,n))) bound; the problem is specifically testing whether you can avoid a full merge entirely.

## Approach 2: Optimized (Binary Search on the Partition)
Always binary search over the **smaller** array (swap if needed, to guarantee the O(log(min(m,n))) bound). For a candidate partition of the smaller array at index `partition1`, the corresponding partition of the larger array is fully determined: `partition2 = (m+n+1)/2 - partition1`. Check whether the four boundary values around this cut satisfy the ordering condition (`maxLeft1 <= minRight2` and `maxLeft2 <= minRight1`); if not, binary search shifts the partition left or right accordingly. Once found, the median is derived directly from the boundary values — no merging needed.

- **Time:** O(log(min(m,n)))
- **Space:** O(1)

## Dry Run
`nums1 = [1,3]`, `nums2 = [2]`

Since `nums1.length (2) > nums2.length (1)`, swap roles: binary search over `[2]` (now "nums1", m=1) against `[1,3]` (now "nums2", n=2). `halfLen = (1+2+1)/2 = 2`.

| partition1 | partition2 | maxLeft1 | minRight1 | maxLeft2 | minRight2 | valid? |
|---|---|---|---|---|---|---|
| 0 | 2 | -inf | 2 | 3 | +inf | maxLeft2(3) <= minRight1(2)? no -> shift right |
| 1 | 1 | 2 | +inf | 1 | 3 | maxLeft1(2)<=minRight2(3) yes, maxLeft2(1)<=minRight1(+inf) yes -> valid! |

Total length odd (3) -> median = `max(maxLeft1=2, maxLeft2=1)` = **2.0**

## Edge Cases
- One array is empty -> the binary search degenerates to finding the plain median of the non-empty array directly
- Arrays of very different sizes -> always searching the smaller array is exactly what keeps the complexity bound correct
- Combined length is even -> the median averages the two middle boundary values instead of taking a single max

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (merge) | O(m+n) | O(m+n) |
| Optimized (binary search on partition) | O(log(min(m,n))) | O(1) |

## Related Problems / Pattern Family
- Kth Smallest Element in a Sorted Matrix (Module 8 — another "binary search over a derived quantity" Hard-adjacent problem)
- Merge Two Sorted Lists (Module 5 #2 — the plain merge this problem's optimized approach specifically avoids)
