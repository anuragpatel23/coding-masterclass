import java.util.*;

public class Solution {

    // Approach 1: "Brute Force" -> O(n log n) time. The problem disallows library sort;
    // shown here as the naive baseline that motivates the real, single-pass solution below.
    public void bruteForce(int[] nums) {
        Arrays.sort(nums);
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (Dutch National Flag, three pointers)
    public void optimized(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else { // nums[mid] == 2
                swap(nums, mid, high);
                high--;
                // mid is NOT incremented: the swapped-in value must still be examined
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        sol.bruteForce(nums1);
        System.out.println("Brute Force -> " + Arrays.toString(nums1));

        int[] nums2 = {2, 0, 2, 1, 1, 0};
        sol.optimized(nums2);
        System.out.println("Optimized   -> " + Arrays.toString(nums2));
    }
}
