import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(n) space (extra array - violates "in-place")
    public int[] bruteForce(int[] nums) {
        int[] result = new int[nums.length];
        int idx = 0;
        for (int num : nums) {
            if (num != 0) result[idx++] = num;
        }
        while (idx < result.length) result[idx++] = 0;
        return result;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (in-place read/write pointers)
    public void optimized(int[] nums) {
        int writePointer = 0;
        for (int readPointer = 0; readPointer < nums.length; readPointer++) {
            if (nums[readPointer] != 0) {
                int temp = nums[writePointer];
                nums[writePointer] = nums[readPointer];
                nums[readPointer] = temp;
                writePointer++;
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {0, 1, 0, 3, 12};
        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(nums1)));

        int[] nums2 = {0, 1, 0, 3, 12};
        sol.optimized(nums2);
        System.out.println("Optimized   -> " + Arrays.toString(nums2));
    }
}
