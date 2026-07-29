import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(n) space (new array)
    public char[] bruteForce(char[] s) {
        char[] result = new char[s.length];
        for (int i = 0; i < s.length; i++) {
            result[i] = s[s.length - 1 - i];
        }
        return result;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (two-pointer swap)
    public void optimized(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        char[] s1 = {'h', 'e', 'l', 'l', 'o'};
        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(s1)));

        char[] s2 = {'h', 'e', 'l', 'l', 'o'};
        sol.optimized(s2);
        System.out.println("Optimized   -> " + Arrays.toString(s2));
    }
}
