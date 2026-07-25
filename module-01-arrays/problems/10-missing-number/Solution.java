import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time, O(n) space (HashSet)
    public int bruteForce(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) seen.add(num);

        for (int i = 0; i <= nums.length; i++) {
            if (!seen.contains(i)) return i;
        }
        return -1; // unreachable given the problem's guarantees
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (Gauss sum formula)
    public int optimized(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3, 0, 1};

        System.out.println("Brute Force -> " + sol.bruteForce(nums));
        System.out.println("Optimized   -> " + sol.optimized(nums));
    }
}
