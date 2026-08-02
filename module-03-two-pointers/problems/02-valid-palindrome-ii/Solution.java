import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(n) space (try every single removal)
    public boolean bruteForce(String s) {
        if (isPalindrome(s, 0, s.length() - 1)) return true;

        for (int i = 0; i < s.length(); i++) {
            String removed = s.substring(0, i) + s.substring(i + 1);
            if (isPalindrome(removed, 0, removed.length() - 1)) return true;
        }
        return false;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (two pointers, branch on first mismatch)
    public boolean optimized(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abca";

        System.out.println("Brute Force -> " + sol.bruteForce(s));
        System.out.println("Optimized   -> " + sol.optimized(s));
    }
}
