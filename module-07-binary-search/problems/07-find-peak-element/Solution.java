import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(1) space
    public int bruteForce(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            boolean leftOk = (i == 0) || nums[i] > nums[i - 1];
            boolean rightOk = (i == n - 1) || nums[i] > nums[i + 1];
            if (leftOk && rightOk) return i;
        }
        return -1;
    }

    // Approach 2: Optimized -> O(log n) time, O(1) space (binary search on the slope)
    public int optimized(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 2, 3, 1};

        System.out.println("Brute Force -> " + sol.bruteForce(nums));
        System.out.println("Optimized   -> " + sol.optimized(nums));
    }
}
