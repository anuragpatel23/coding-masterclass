import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(1) space
    public int bruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }
        return -1;
    }

    // Approach 2: Optimized -> O(log n) time, O(1) space (binary search)
    public int optimized(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;

        System.out.println("Brute Force -> " + sol.bruteForce(nums, target));
        System.out.println("Optimized   -> " + sol.optimized(nums, target));
    }
}
