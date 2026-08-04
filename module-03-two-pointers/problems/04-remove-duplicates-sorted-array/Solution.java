import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(n) space (LinkedHashSet, preserves order)
    public int bruteForce(int[] nums) {
        LinkedHashSet<Integer> unique = new LinkedHashSet<>();
        for (int num : nums) unique.add(num);

        int i = 0;
        for (int num : unique) {
            nums[i++] = num;
        }
        return unique.size();
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (read/write pointers)
    public int optimized(int[] nums) {
        if (nums.length == 0) return 0;

        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int len1 = sol.bruteForce(nums1);
        System.out.println("Brute Force -> length=" + len1 + ", " + Arrays.toString(Arrays.copyOf(nums1, len1)));

        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int len2 = sol.optimized(nums2);
        System.out.println("Optimized   -> length=" + len2 + ", " + Arrays.toString(Arrays.copyOf(nums2, len2)));
    }
}
