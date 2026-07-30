# 6. Group Anagrams

**Difficulty:** Medium
**Pattern:** Hashing by Canonical Key
**LeetCode:** https://leetcode.com/problems/group-anagrams/

## Problem Summary
Given an array of strings, group the anagrams together. You can return the answer in any order.

## Example
```
Input:  strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["eat","tea","ate"],["tan","nat"],["bat"]]
```

## Pattern Recognition
"Group items that share some property" is a hashmap-grouping problem the moment you can express that property as a single, comparable key. For anagrams, the key is: any two anagrams produce the **same sorted string** (or the same character-frequency signature). Once you see that a comparison between two items can be replaced by comparing a derived key, you've found your hashmap.

## Approach 1: Brute Force
For each string, compare it against the first string in every existing group (using a full anagram check) to decide which group it belongs to; if none match, start a new group.

- **Time:** O(m² · k) where `m` = number of strings, `k` = average string length (comparing against up to `m` existing groups, each comparison costing O(k))
- **Space:** O(m · k)
- **Why it's not good enough:** you're repeatedly asking "is this an anagram of that?" through direct comparison, when every anagram of a given string already computes to the *exact same* sorted form — that shared form is a key you can look up instead of compare against.

## Approach 2: Optimized (Sorted String as HashMap Key)
For each string, sort its characters to produce a canonical key (all anagrams sort to the identical key). Use that key to group strings in a `HashMap<String, List<String>>`.

- **Time:** O(m · k log k) — dominated by sorting each string
- **Space:** O(m · k)

*(A further refinement: use a character-frequency signature — e.g. a 26-length count array turned into a string — as the key instead of a sorted string. That avoids the `log k` sort cost entirely, getting you to O(m · k).)*

## Dry Run
`strs = ["eat","tea","tan","ate","nat","bat"]`

| string | sorted key | groups after |
|---|---|---|
| eat | aet | {aet: [eat]} |
| tea | aet | {aet: [eat, tea]} |
| tan | ant | {aet: [eat,tea], ant: [tan]} |
| ate | aet | {aet: [eat,tea,ate], ant: [tan]} |
| nat | ant | {aet: [...], ant: [tan, nat]} |
| bat | abt | {aet: [...], ant: [...], abt: [bat]} |

Result: **[[eat,tea,ate], [tan,nat], [bat]]**

## Edge Cases
- Empty input array -> return an empty list of groups
- All strings are anagrams of each other -> a single group containing everything
- No two strings are anagrams -> every string ends up in its own group of size 1

## Complexity Summary
| Approach | Time | Space |
|---|---|---|
| Brute Force | O(m^2 * k) | O(m * k) |
| Optimized (sorted-string key) | O(m * k log k) | O(m * k) |
| Optimized (frequency-signature key) | O(m * k) | O(m * k) |

## Related Problems / Pattern Family
- Valid Anagram (Module 2 #3 — the pairwise version of this exact grouping idea)
- Group Shifted Strings (a different canonical key, based on relative character offsets)
