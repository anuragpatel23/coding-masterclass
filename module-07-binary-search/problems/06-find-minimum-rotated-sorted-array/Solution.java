import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(1) space
    public int bruteForce(int[] nums) {
        int min = nums[0];
        for (int num : nums) min = Math.min(min, num);
        return min;
    }

    // Approach 2: Optimized -> O(log n) time, O(1) space (binary search toward the pivot)
    public int optimized(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        System.out.println("Brute Force -> " + sol.bruteForce(nums));
        System.out.println("Optimized   -> " + sol.optimized(nums));
    }
}
