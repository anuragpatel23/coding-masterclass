import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n*m) time, O(1) space
    public int bruteForce(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if (m == 0) return 0;

        for (int i = 0; i + m <= n; i++) {
            int j = 0;
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == m) return i;
        }
        return -1;
    }

    // Approach 2: Optimized -> O(n+m) time, O(m) space (Knuth-Morris-Pratt)
    public int optimized(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if (m == 0) return 0;

        int[] lps = computeLPS(needle);
        int i = 0, j = 0;

        while (i < n) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == m) return i - j;
            } else if (j != 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return -1;
    }

    private int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String haystack = "sadbutsad";
        String needle = "sad";

        System.out.println("Brute Force -> " + sol.bruteForce(haystack, needle));
        System.out.println("Optimized   -> " + sol.optimized(haystack, needle));
    }
}
