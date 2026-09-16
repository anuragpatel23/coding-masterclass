import java.util.*;

public class Solution {

    private int subarraysNeeded(int[] nums, int maxSum) {
        int subarrays = 1, currentSum = 0;
        for (int num : nums) {
            if (currentSum + num > maxSum) {
                subarrays++;
                currentSum = 0;
            }
            currentSum += num;
        }
        return subarrays;
    }

    // Approach 1: Brute Force -> O(sum * n) time, O(1) space (linear scan over candidates)
    public int bruteForce(int[] nums, int m) {
        int maxElement = Arrays.stream(nums).max().getAsInt();
        int totalSum = Arrays.stream(nums).sum();

        for (int candidate = maxElement; candidate <= totalSum; candidate++) {
            if (subarraysNeeded(nums, candidate) <= m) {
                return candidate;
            }
        }
        return totalSum;
    }

    // Approach 2: Optimized -> O(n log(sum)) time, O(1) space (binary search on candidate answer)
    public int optimized(int[] nums, int m) {
        int left = Arrays.stream(nums).max().getAsInt();
        int right = Arrays.stream(nums).sum();

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (subarraysNeeded(nums, mid) <= m) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;

        System.out.println("Brute Force -> " + sol.bruteForce(nums, m));
        System.out.println("Optimized   -> " + sol.optimized(nums, m));
    }
}
