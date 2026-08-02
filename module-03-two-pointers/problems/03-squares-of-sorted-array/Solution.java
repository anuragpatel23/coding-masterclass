import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n log n) time, O(n) space (square then sort)
    public int[] bruteForce(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i] * nums[i];
        }
        Arrays.sort(result);
        return result;
    }

    // Approach 2: Optimized -> O(n) time, O(n) output space, O(1) extra (two pointers)
    public int[] optimized(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                result[i] = leftSquare;
                left++;
            } else {
                result[i] = rightSquare;
                right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {-4, -1, 0, 3, 10};

        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(nums)));
        System.out.println("Optimized   -> " + Arrays.toString(sol.optimized(nums)));
    }
}
