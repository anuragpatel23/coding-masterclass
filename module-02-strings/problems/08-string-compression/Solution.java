import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(n) space (StringBuilder, then copy back)
    public int bruteForce(char[] chars) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < chars.length) {
            char current = chars[i];
            int count = 0;
            while (i < chars.length && chars[i] == current) {
                count++;
                i++;
            }
            sb.append(current);
            if (count > 1) sb.append(count);
        }

        for (int j = 0; j < sb.length(); j++) {
            chars[j] = sb.charAt(j);
        }
        return sb.length();
    }

    // Approach 2: Optimized -> O(n) time, O(1) extra space (read/write pointers)
    public int optimized(char[] chars) {
        int writePointer = 0;
        int readPointer = 0;

        while (readPointer < chars.length) {
            char current = chars[readPointer];
            int count = 0;

            while (readPointer < chars.length && chars[readPointer] == current) {
                count++;
                readPointer++;
            }

            chars[writePointer++] = current;
            if (count > 1) {
                for (char digit : String.valueOf(count).toCharArray()) {
                    chars[writePointer++] = digit;
                }
            }
        }
        return writePointer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        char[] chars1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int len1 = sol.bruteForce(chars1);
        System.out.println("Brute Force -> length=" + len1 + ", " + Arrays.toString(Arrays.copyOf(chars1, len1)));

        char[] chars2 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int len2 = sol.optimized(chars2);
        System.out.println("Optimized   -> length=" + len2 + ", " + Arrays.toString(Arrays.copyOf(chars2, len2)));
    }
}
