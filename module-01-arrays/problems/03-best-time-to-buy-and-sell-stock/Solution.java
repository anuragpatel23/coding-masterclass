import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) space
    public int bruteForce(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }
        return maxProfit;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (track running minimum)
    public int optimized(int[] prices) {
        if (prices.length == 0) return 0;
        int minPriceSoFar = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minPriceSoFar);
            minPriceSoFar = Math.min(minPriceSoFar, prices[i]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] prices = {7, 1, 5, 3, 6, 4};

        System.out.println("Brute Force -> " + sol.bruteForce(prices));
        System.out.println("Optimized   -> " + sol.optimized(prices));
    }
}
