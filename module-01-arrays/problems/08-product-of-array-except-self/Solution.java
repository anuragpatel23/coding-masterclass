import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) extra space (excluding output)
    public int[] bruteForce(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = 0; j < n; j++) {
                if (j != i) product *= nums[j];
            }
            result[i] = product;
        }
        return result;
    }

    // Approach 2: Optimized -> O(n) time, O(1) extra space (prefix then suffix in output array)
    public int[] optimized(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1]; // prefix product
        }

        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffix; // multiply in suffix product
            suffix *= nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 2, 3, 4};

        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(nums)));
        System.out.println("Optimized   -> " + Arrays.toString(sol.optimized(nums)));
    }
}
