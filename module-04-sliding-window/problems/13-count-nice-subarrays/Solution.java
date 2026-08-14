import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) space
    public int bruteForce(int[] nums, int k) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int oddCount = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] % 2 != 0) oddCount++;
                if (oddCount == k) count++;
                else if (oddCount > k) break;
            }
        }
        return count;
    }

    private int atMostK(int[] nums, int k) {
        if (k < 0) return 0;
        int left = 0, oddCount = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] % 2 != 0) oddCount++;

            while (oddCount > k) {
                if (nums[left] % 2 != 0) oddCount--;
                left++;
            }
            count += right - left + 1;
        }
        return count;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (exactly(k) = atMost(k) - atMost(k-1))
    public int optimized(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;

        System.out.println("Brute Force -> " + sol.bruteForce(nums, k));
        System.out.println("Optimized   -> " + sol.optimized(nums, k));
    }
}
