import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(n) space (HashMap counting)
    public int bruteForce(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
            if (counts.get(num) > nums.length / 2) return num;
        }
        throw new IllegalArgumentException("No majority element found");
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (Boyer-Moore Voting)
    public int optimized(int[] nums) {
        int candidate = nums[0];
        int count = 0;
        for (int num : nums) {
            if (count == 0) candidate = num;
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2, 2, 1, 1, 1, 2, 2};

        System.out.println("Brute Force -> " + sol.bruteForce(nums));
        System.out.println("Optimized   -> " + sol.optimized(nums));
    }
}
