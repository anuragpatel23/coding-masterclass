import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) space
    public int bruteForce(int[] nums, int k) {
        if (k <= 1) return 0;
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = i; j < n; j++) {
                product *= nums[j];
                if (product < k) count++;
                else break;
            }
        }
        return count;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (variable window + counting trick)
    public int optimized(int[] nums, int k) {
        if (k <= 1) return 0;

        int left = 0;
        long product = 1;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            while (product >= k) {
                product /= nums[left];
                left++;
            }
            count += right - left + 1;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {10, 5, 2, 6};
        int k = 100;

        System.out.println("Brute Force -> " + sol.bruteForce(nums, k));
        System.out.println("Optimized   -> " + sol.optimized(nums, k));
    }
}
