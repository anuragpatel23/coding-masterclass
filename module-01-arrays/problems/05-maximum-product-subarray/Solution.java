import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) space
    public int bruteForce(int[] nums) {
        int maxProduct = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int currentProduct = 1;
            for (int j = i; j < nums.length; j++) {
                currentProduct *= nums[j];
                maxProduct = Math.max(maxProduct, currentProduct);
            }
        }
        return maxProduct;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (track running max & min)
    public int optimized(int[] nums) {
        int maxProduct = nums[0];
        int currentMax = nums[0];
        int currentMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num < 0) {
                int temp = currentMax;
                currentMax = currentMin;
                currentMin = temp;
            }
            currentMax = Math.max(num, currentMax * num);
            currentMin = Math.min(num, currentMin * num);
            maxProduct = Math.max(maxProduct, currentMax);
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2, 3, -2, 4};

        System.out.println("Brute Force -> " + sol.bruteForce(nums));
        System.out.println("Optimized   -> " + sol.optimized(nums));
    }
}
