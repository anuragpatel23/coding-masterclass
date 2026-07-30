import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(m^2 * k) time, O(m*k) space
    public List<List<String>> bruteForce(String[] strs) {
        List<List<String>> groups = new ArrayList<>();

        for (String s : strs) {
            boolean placed = false;
            for (List<String> group : groups) {
                if (isAnagram(s, group.get(0))) {
                    group.add(s);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                List<String> newGroup = new ArrayList<>();
                newGroup.add(s);
                groups.add(newGroup);
            }
        }
        return groups;
    }

    private boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        int[] counts = new int[26];
        for (char c : a.toCharArray()) counts[c - 'a']++;
        for (char c : b.toCharArray()) counts[c - 'a']--;
        for (int count : counts) {
            if (count != 0) return false;
        }
        return true;
    }

    // Approach 2: Optimized -> O(m * k log k) time, O(m*k) space (sorted-string key)
    public List<List<String>> optimized(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(groups.values());
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println("Brute Force -> " + sol.bruteForce(strs));
        System.out.println("Optimized   -> " + sol.optimized(strs));
    }
}
