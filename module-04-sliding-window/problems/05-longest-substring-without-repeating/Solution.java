import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(min(n, charset)) space
    public int bruteForce(String s) {
        int n = s.length();
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            Set<Character> seen = new HashSet<>();
            for (int j = i; j < n; j++) {
                if (!seen.add(s.charAt(j))) break;
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        return maxLen;
    }

    // Approach 2: Optimized -> O(n) time, O(min(n, charset)) space (variable window)
    public int optimized(String s) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        int maxLen = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= left) {
                left = lastSeen.get(c) + 1;
            }
            lastSeen.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abcabcbb";

        System.out.println("Brute Force -> " + sol.bruteForce(s));
        System.out.println("Optimized   -> " + sol.optimized(s));
    }
}
