import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n log n) time, O(n) space (custom comparator sort)
    public String bruteForce(String order, String s) {
        Map<Character, Integer> rank = new HashMap<>();
        for (int i = 0; i < order.length(); i++) rank.put(order.charAt(i), i);

        Character[] chars = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) chars[i] = s.charAt(i);

        Arrays.sort(chars, (a, b) -> {
            int rankA = rank.getOrDefault(a, Integer.MAX_VALUE);
            int rankB = rank.getOrDefault(b, Integer.MAX_VALUE);
            return rankA - rankB;
        });

        StringBuilder sb = new StringBuilder();
        for (char c : chars) sb.append(c);
        return sb.toString();
    }

    // Approach 2: Optimized -> O(n) time, O(1) extra space (counting, no sort)
    public String optimized(String order, String s) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) counts[c - 'a']++;

        StringBuilder sb = new StringBuilder();
        for (char c : order.toCharArray()) {
            while (counts[c - 'a'] > 0) {
                sb.append(c);
                counts[c - 'a']--;
            }
        }
        for (char c = 'a'; c <= 'z'; c++) {
            while (counts[c - 'a'] > 0) {
                sb.append(c);
                counts[c - 'a']--;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String order = "cba", s = "abcd";

        System.out.println("Brute Force -> \"" + sol.bruteForce(order, s) + "\"");
        System.out.println("Optimized   -> \"" + sol.optimized(order, s) + "\"");
    }
}
