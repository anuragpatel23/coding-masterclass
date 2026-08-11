import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(k) space
    public int bruteForce(String s, int k) {
        int n = s.length();
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            Set<Character> distinct = new HashSet<>();
            for (int j = i; j < n; j++) {
                distinct.add(s.charAt(j));
                if (distinct.size() > k) break;
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        return maxLen;
    }

    // Approach 2: Optimized -> O(n) time, O(k) space (variable window + frequency map)
    public int optimized(String s, int k) {
        if (k == 0) return 0;

        Map<Character, Integer> counts = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            counts.merge(c, 1, Integer::sum);

            while (counts.size() > k) {
                char leftChar = s.charAt(left);
                counts.put(leftChar, counts.get(leftChar) - 1);
                if (counts.get(leftChar) == 0) counts.remove(leftChar);
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "eceba";
        int k = 2;

        System.out.println("Brute Force -> " + sol.bruteForce(s, k));
        System.out.println("Optimized   -> " + sol.optimized(s, k));
    }
}
