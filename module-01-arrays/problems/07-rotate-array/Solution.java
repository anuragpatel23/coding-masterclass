import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(n) space (extra array)
    public int[] bruteForce(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[(i + k) % n] = nums[i];
        }
        return result;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (reversal trick)
    public void optimized(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(nums1, 3)));

        int[] nums2 = {1, 2, 3, 4, 5, 6, 7};
        sol.optimized(nums2, 3);
        System.out.println("Optimized   -> " + Arrays.toString(nums2));
    }
}
