import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(sqrt(x)) time, O(1) space
    public int bruteForce(int x) {
        if (x < 2) return x;
        long i = 1;
        while (i * i <= x) i++;
        return (int) (i - 1);
    }

    // Approach 2: Optimized -> O(log x) time, O(1) space (binary search on the answer)
    public int optimized(int x) {
        if (x < 2) return x;
        long left = 1, right = x / 2;
        long result = 1;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;
            if (square == x) {
                return (int) mid;
            } else if (square < x) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int x = 8;

        System.out.println("Brute Force -> " + sol.bruteForce(x));
        System.out.println("Optimized   -> " + sol.optimized(x));
    }
}
