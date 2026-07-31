import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^3) time, O(1) extra space
    public String bruteForce(String s) {
        int n = s.length();
        String longest = "";

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String candidate = s.substring(i, j + 1);
                if (isPalindrome(candidate) && candidate.length() > longest.length()) {
                    longest = candidate;
                }
            }
        }
        return longest;
    }

    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    // Approach 2: Optimized -> O(n^2) time, O(1) extra space (Expand Around Center)
    public String optimized(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);       // odd-length palindrome
            int len2 = expandAroundCenter(s, i, i + 1);   // even-length palindrome
            int len = Math.max(len1, len2);

            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "babad";

        System.out.println("Brute Force -> \"" + sol.bruteForce(s) + "\"");
        System.out.println("Optimized   -> \"" + sol.optimized(s) + "\"");
    }
}
