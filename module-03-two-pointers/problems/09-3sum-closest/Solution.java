import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^3) time, O(1) space
    public int bruteForce(int[] nums, int target) {
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                        closestSum = sum;
                    }
                }
            }
        }
        return closestSum;
    }

    // Approach 2: Optimized -> O(n^2) time, O(1) extra space (sort + two pointers)
    public int optimized(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }

                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return closestSum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {-1, 2, 1, -4};
        int target = 1;

        System.out.println("Brute Force -> " + sol.bruteForce(nums.clone(), target));
        System.out.println("Optimized   -> " + sol.optimized(nums.clone(), target));
    }
}
