# 15. Boats to Save People

**Difficulty:** Medium
**Pattern:** Sort + Two Pointers (Greedy Pairing)
**LeetCode:** https://leetcode.com/problems/boats-to-save-people/

## Problem Summary
Given each person's weight and a weight `limit` per boat (each boat carries at most 2 people), return the minimum number of boats needed to carry everyone across.

## Example
```
Input:  people = [3,2,2,1], limit = 3
Output: 3
```

## Pattern Recognition
"Pair up items from two ends of a sorted range, under a capacity constraint" is a greedy two-pointer signal: always try to pair the **heaviest** remaining person with the **lightest** remaining person. If they fit together, that's strictly the best possible use of the heaviest person's boat (no lighter partner would ever make it *harder* to fit). If even the lightest available person doesn't fit with the heaviest, no one else will either — the heaviest person must go alone.

## Approach 1: Brute Force
Repeatedly scan the remaining people to find the current heaviest and lightest (via linear scans, without pre-sorting), and apply the same pairing rule as the optimized approach: pair them if they fit under the limit, otherwise send the heaviest alone.

- **Time:** O(n^2) — a fresh O(n) scan to find both the min and max, repeated once per boat (up to n boats)
- **Space:** O(n) — the working list of remaining people
- **Why it's not good enough:** re-scanning the entire remaining group from scratch for every single boat throws away the ordering information a single sort would give you for free.

## Approach 2: Optimized (Sort Once, Two Pointers)
Sort the array once. Place `i` at the lightest person and `j` at the heaviest. At each step:
- If `people[i] + people[j] <= limit`, they can share a boat — advance `i`.
- Either way, the current heaviest (`j`) always gets a boat this round — advance `j` backward and count one boat.

Because the array is sorted, `i` always represents the single best possible partner for the current heaviest person — if even the lightest person doesn't fit, nobody heavier will either.

- **Time:** O(n log n) — dominated by the sort
- **Space:** O(1) extra (excluding the sort's space)

## Dry Run
`people = [3,2,2,1]`, `limit = 3` -> sorted: `[1,2,2,3]`

| i | j | people[i]+people[j] | fits? | action | boats |
|---|---|---|---|---|---|
| 0 (1) | 3 (3) | 4 | no | j alone, j-- | 1 |
| 0 (1) | 2 (2) | 3 | yes | pair, i++, j-- | 2 |
| 1 (2) | 1 (2) | i==j, single person left | -- | j alone, j-- | 3 |

Loop ends (`i > j`). Result: **3 boats**

## Edge Cases
- A single person -> always needs exactly 1 boat, regardless of weight (assuming weight <= limit, which the problem guarantees)
- Everyone can pair up, e.g. `people=[1,2], limit=3` -> exactly 1 boat
- No one can pair with anyone, e.g. all weights equal exactly `limit` -> every person needs their own boat, `n` boats total

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force (repeated scans) | O(n^2) | O(n) |
| Optimized (sort + two pointers) | O(n log n) | O(1) extra |

## Related Problems / Pattern Family
- Two Sum II (Module 3 #1 — the same opposite-direction pointer movement, different objective)
- Container With Most Water (Module 3 #7 — another greedy two-pointer proof structure)
