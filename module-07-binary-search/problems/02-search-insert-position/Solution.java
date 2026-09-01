import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(1) space
    public int bruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) return i;
        }
        return nums.length;
    }

    // Approach 2: Optimized -> O(log n) time, O(1) space (binary search, lower bound)
    public int optimized(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 3, 5, 6};
        int target = 2;

        System.out.println("Brute Force -> " + sol.bruteForce(nums, target));
        System.out.println("Optimized   -> " + sol.optimized(nums, target));
    }
}
