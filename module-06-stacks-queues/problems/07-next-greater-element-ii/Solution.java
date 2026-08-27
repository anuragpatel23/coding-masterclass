import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) extra space
    public int[] bruteForce(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        for (int i = 0; i < n; i++) {
            for (int offset = 1; offset < n; offset++) {
                int j = (i + offset) % n;
                if (nums[j] > nums[i]) {
                    result[i] = nums[j];
                    break;
                }
            }
        }
        return result;
    }

    // Approach 2: Optimized -> O(n) time, O(n) space (monotonic stack, circular via 2 passes)
    public int[] optimized(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>(); // indices, decreasing values

        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            while (!stack.isEmpty() && nums[idx] > nums[stack.peek()]) {
                result[stack.pop()] = nums[idx];
            }
            if (i < n) {
                stack.push(idx);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 2, 1};

        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(nums)));
        System.out.println("Optimized   -> " + Arrays.toString(sol.optimized(nums)));
    }
}
