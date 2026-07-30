import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(S) time, O(1) extra space (horizontal scanning)
    public String bruteForce(String[] strs) {
        if (strs.length == 0) return "";

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    // Approach 2: Optimized -> O(S) time, O(1) extra space (vertical scanning, early exit)
    public String optimized(String[] strs) {
        if (strs.length == 0) return "";

        for (int j = 0; j < strs[0].length(); j++) {
            char charToMatch = strs[0].charAt(j);
            for (int i = 1; i < strs.length; i++) {
                if (j >= strs[i].length() || strs[i].charAt(j) != charToMatch) {
                    return strs[0].substring(0, j);
                }
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] strs = {"flower", "flow", "flight"};

        System.out.println("Brute Force -> \"" + sol.bruteForce(strs) + "\"");
        System.out.println("Optimized   -> \"" + sol.optimized(strs) + "\"");
    }
}
