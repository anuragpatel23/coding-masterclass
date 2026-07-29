import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n log n) time, O(n) space (sort and compare)
    public boolean bruteForce(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return Arrays.equals(sChars, tChars);
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (fixed-size frequency array)
    public boolean optimized(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] counts = new int[26];
        for (char c : s.toCharArray()) counts[c - 'a']++;
        for (char c : t.toCharArray()) counts[c - 'a']--;

        for (int count : counts) {
            if (count != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "anagram";
        String t = "nagaram";

        System.out.println("Brute Force -> " + sol.bruteForce(s, t));
        System.out.println("Optimized   -> " + sol.optimized(s, t));
    }
}
