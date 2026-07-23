import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) space
    public int[] bruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution found");
    }

    // Approach 2: Optimized -> O(n) time, O(n) space (HashMap)
    public int[] optimized(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>(); // value -> index
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }
            seen.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution found");
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(nums, target)));
        System.out.println("Optimized   -> " + Arrays.toString(sol.optimized(nums, target)));
    }
}
