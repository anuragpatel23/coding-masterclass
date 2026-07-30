import java.util.*;

public class Solution {

    private Map<Character, Integer> romanValues() {
        Map<Character, Integer> values = new HashMap<>();
        values.put('I', 1);
        values.put('V', 5);
        values.put('X', 10);
        values.put('L', 50);
        values.put('C', 100);
        values.put('D', 500);
        values.put('M', 1000);
        return values;
    }

    // Approach 1: Brute Force -> O(n) time (higher constant), O(n) space
    // Normalize subtractive pairs into purely additive form, then sum.
    public int bruteForce(String s) {
        String normalized = s
                .replace("IV", "IIII")
                .replace("IX", "VIIII")
                .replace("XL", "XXXX")
                .replace("XC", "LXXXX")
                .replace("CD", "CCCC")
                .replace("CM", "DCCCC");

        Map<Character, Integer> values = romanValues();
        int total = 0;
        for (char c : normalized.toCharArray()) {
            total += values.get(c);
        }
        return total;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (single pass with lookahead)
    public int optimized(String s) {
        Map<Character, Integer> values = romanValues();
        int total = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int current = values.get(s.charAt(i));
            if (i + 1 < n && current < values.get(s.charAt(i + 1))) {
                total -= current;
            } else {
                total += current;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "MCMXCIV";

        System.out.println("Brute Force -> " + sol.bruteForce(s));
        System.out.println("Optimized   -> " + sol.optimized(s));
    }
}
