import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(n) space (cleaned copy + reverse compare)
    public boolean bruteForce(String s) {
        StringBuilder cleaned = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        String forward = cleaned.toString();
        String backward = cleaned.reverse().toString();
        return forward.equals(backward);
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (two pointers, skip non-alphanumeric)
    public boolean optimized(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "A man, a plan, a canal: Panama";

        System.out.println("Brute Force -> " + sol.bruteForce(s));
        System.out.println("Optimized   -> " + sol.optimized(s));
    }
}
