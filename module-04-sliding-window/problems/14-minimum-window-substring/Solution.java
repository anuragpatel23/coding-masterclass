import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^3) (loose bound), O(n) space
    public String bruteForce(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";

        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) need.merge(c, 1, Integer::sum);

        int minLen = Integer.MAX_VALUE;
        String minWindow = "";

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String candidate = s.substring(i, j + 1);
                if (containsAll(candidate, need)) {
                    if (candidate.length() < minLen) {
                        minLen = candidate.length();
                        minWindow = candidate;
                    }
                    break; // extending further from i can only get longer
                }
            }
        }
        return minWindow;
    }

    private boolean containsAll(String s, Map<Character, Integer> need) {
        Map<Character, Integer> have = new HashMap<>();
        for (char c : s.toCharArray()) have.merge(c, 1, Integer::sum);
        for (Map.Entry<Character, Integer> entry : need.entrySet()) {
            if (have.getOrDefault(entry.getKey(), 0) < entry.getValue()) return false;
        }
        return true;
    }

    // Approach 2: Optimized -> O(n+m) time, O(m+k) space (variable window, formed vs required)
    public String optimized(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";

        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) need.merge(c, 1, Integer::sum);

        Map<Character, Integer> window = new HashMap<>();
        int required = need.size();
        int formed = 0;

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            window.merge(c, 1, Integer::sum);
            if (need.containsKey(c) && window.get(c).intValue() == need.get(c).intValue()) {
                formed++;
            }

            while (formed == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }
                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);
                if (need.containsKey(leftChar) && window.get(leftChar) < need.get(leftChar)) {
                    formed--;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "ADOBECODEBANC", t = "ABC";

        System.out.println("Brute Force -> \"" + sol.bruteForce(s, t) + "\"");
        System.out.println("Optimized   -> \"" + sol.optimized(s, t) + "\"");
    }
}
