import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n*m + m^2) time, O(n+m) space per intermediate
    // (row-by-row school multiplication, summed via repeated string addition)
    public String bruteForce(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        String result = "0";
        int m = num2.length();

        for (int j = m - 1; j >= 0; j--) {
            int digit = num2.charAt(j) - '0';
            String partial = multiplyBySingleDigit(num1, digit);

            StringBuilder shifted = new StringBuilder(partial);
            for (int shift = 0; shift < (m - 1 - j); shift++) {
                shifted.append('0');
            }
            result = addStrings(result, shifted.toString());
        }
        return result;
    }

    private String multiplyBySingleDigit(String num, int digit) {
        if (digit == 0) return "0";
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            int product = (num.charAt(i) - '0') * digit + carry;
            sb.append(product % 10);
            carry = product / 10;
        }
        if (carry > 0) sb.append(carry);
        return sb.reverse().toString();
    }

    private String addStrings(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }

    // Approach 2: Optimized -> O(n*m) time, O(n+m) space (single result array, shared carries)
    public String optimized(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        int n = num1.length(), m = num2.length();
        int[] result = new int[n + m];

        for (int i = n - 1; i >= 0; i--) {
            int d1 = num1.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int d2 = num2.charAt(j) - '0';
                int sum = d1 * d2 + result[i + j + 1];
                result[i + j + 1] = sum % 10;
                result[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int digit : result) {
            if (!(sb.length() == 0 && digit == 0)) {
                sb.append(digit);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String num1 = "123", num2 = "456";

        System.out.println("Brute Force -> " + sol.bruteForce(num1, num2));
        System.out.println("Optimized   -> " + sol.optimized(num1, num2));
    }
}
