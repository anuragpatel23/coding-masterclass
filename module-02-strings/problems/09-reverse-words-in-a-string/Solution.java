import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(n) space (split, reverse list, join)
    public String bruteForce(String s) {
        String[] tokens = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = tokens.length - 1; i >= 0; i--) {
            sb.append(tokens[i]);
            if (i > 0) sb.append(' ');
        }
        return sb.toString();
    }

    // Approach 2: Optimized -> O(n) time, O(1) extra space on a char buffer
    // (double reversal: reverse whole array, then reverse each word, collapsing spaces)
    public String optimized(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        reverse(chars, 0, n - 1);

        int writePointer = 0;
        int readPointer = 0;

        while (readPointer < n) {
            // Skip leading spaces before a word
            while (readPointer < n && chars[readPointer] == ' ') readPointer++;
            if (readPointer >= n) break;

            if (writePointer != 0) chars[writePointer++] = ' ';

            int wordStart = writePointer;
            while (readPointer < n && chars[readPointer] != ' ') {
                chars[writePointer++] = chars[readPointer++];
            }
            reverse(chars, wordStart, writePointer - 1);
        }

        return new String(chars, 0, writePointer);
    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "  the sky is blue  ";

        System.out.println("Brute Force -> \"" + sol.bruteForce(s) + "\"");
        System.out.println("Optimized   -> \"" + sol.optimized(s) + "\"");
    }
}
