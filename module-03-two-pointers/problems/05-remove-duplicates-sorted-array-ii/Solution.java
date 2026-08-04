import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(n) space (LinkedHashMap counting, cap at 2)
    public int bruteForce(int[] nums) {
        Map<Integer, Integer> counts = new LinkedHashMap<>();
        for (int num : nums) counts.merge(num, 1, Integer::sum);

        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int value = entry.getKey();
            int copies = Math.min(entry.getValue(), 2);
            for (int k = 0; k < copies; k++) {
                nums[idx++] = value;
            }
        }
        return idx;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (compare against slow-2)
    public int optimized(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int slow = 2;
        for (int fast = 2; fast < n; fast++) {
            if (nums[fast] != nums[slow - 2]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int len1 = sol.bruteForce(nums1);
        System.out.println("Brute Force -> length=" + len1 + ", " + Arrays.toString(Arrays.copyOf(nums1, len1)));

        int[] nums2 = {1, 1, 1, 2, 2, 3};
        int len2 = sol.optimized(nums2);
        System.out.println("Optimized   -> length=" + len2 + ", " + Arrays.toString(Arrays.copyOf(nums2, len2)));
    }
}
