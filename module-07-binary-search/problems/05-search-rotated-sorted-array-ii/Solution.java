import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(1) space
    public boolean bruteForce(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) return true;
        }
        return false;
    }

    // Approach 2: Optimized -> O(log n) avg, O(n) worst case, O(1) space
    public boolean optimized(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return true;

            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2, 5, 6, 0, 0, 1, 2};
        int target = 0;

        System.out.println("Brute Force -> " + sol.bruteForce(nums, target));
        System.out.println("Optimized   -> " + sol.optimized(nums, target));
    }
}
