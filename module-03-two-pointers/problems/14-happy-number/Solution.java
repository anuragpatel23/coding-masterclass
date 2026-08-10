import java.util.*;

public class Solution {

    private int squareDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    // Approach 1: Brute Force -> O(log n) per step, O(k) space (HashSet of visited values)
    public boolean bruteForce(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = squareDigitSum(n);
        }
        return n == 1;
    }

    // Approach 2: Optimized -> O(log n) per step, O(1) space (Floyd's fast/slow on the sequence)
    public boolean optimized(int n) {
        int slow = n, fast = n;
        do {
            slow = squareDigitSum(slow);
            fast = squareDigitSum(squareDigitSum(fast));
        } while (slow != fast);
        return slow == 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 19;

        System.out.println("Brute Force -> " + sol.bruteForce(n));
        System.out.println("Optimized   -> " + sol.optimized(n));
    }
}
