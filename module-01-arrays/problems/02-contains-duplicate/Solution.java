import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) space
    public boolean bruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    // Approach 2: Optimized -> O(n) time, O(n) space (HashSet)
    public boolean optimized(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) return true; // add() returns false if already present
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 2, 3, 1};

        System.out.println("Brute Force -> " + sol.bruteForce(nums));
        System.out.println("Optimized   -> " + sol.optimized(nums));
    }
}
