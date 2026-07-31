import java.util.*;

public class Solution {

    private static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] SYMBOLS =
            {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    // Approach 1: Greedy subtraction with full symbol table -> O(1) time, O(1) space
    public String bruteForce(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < VALUES.length; i++) {
            while (num >= VALUES[i]) {
                sb.append(SYMBOLS[i]);
                num -= VALUES[i];
            }
        }
        return sb.toString();
    }

    private static final String[] THOUSANDS = {"", "M", "MM", "MMM"};
    private static final String[] HUNDREDS  = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private static final String[] TENS      = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] ONES      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    // Approach 2: Optimized -> O(1) time, O(1) space (direct digit lookup, no loops)
    public String optimized(int num) {
        return THOUSANDS[num / 1000]
                + HUNDREDS[(num % 1000) / 100]
                + TENS[(num % 100) / 10]
                + ONES[num % 10];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int num = 1994;

        System.out.println("Brute Force -> " + sol.bruteForce(num));
        System.out.println("Optimized   -> " + sol.optimized(num));
    }
}
