import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(n) space (HashSet)
    public List<Integer> bruteForce(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) seen.add(num);

        List<Integer> missing = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (!seen.contains(i)) missing.add(i);
        }
        return missing;
    }

    // Approach 2: Optimized -> O(n) time, O(1) extra space (in-place index marking)
    public List<Integer> optimized(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        List<Integer> missing = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                missing.add(i + 1);
            } else {
                nums[i] = -nums[i]; // restore original array (good practice)
            }
        }
        return missing;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Brute Force -> " + sol.bruteForce(nums1));

        int[] nums2 = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Optimized   -> " + sol.optimized(nums2));
    }
}
