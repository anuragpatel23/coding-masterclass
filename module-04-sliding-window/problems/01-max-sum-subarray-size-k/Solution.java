import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n*k) time, O(1) space
    public int bruteForce(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i + k <= nums.length; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) sum += nums[j];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (fixed-size sliding window)
    public int optimized(int[] nums, int k) {
        int windowSum = 0;
        for (int i = 0; i < k; i++) windowSum += nums[i];

        int maxSum = windowSum;
        for (int i = k; i < nums.length; i++) {
            windowSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2, 1, 5, 1, 3, 2};
        int k = 3;

        System.out.println("Brute Force -> " + sol.bruteForce(nums, k));
        System.out.println("Optimized   -> " + sol.optimized(nums, k));
    }
}
