import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n*k) time, O(1) extra space
    public int[] bruteForce(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];

        for (int i = 0; i + k <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        return result;
    }

    // Approach 2: Optimized -> O(n) time, O(k) space (monotonic decreasing deque of indices)
    public int[] optimized(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); // indices, values decreasing front-to-back

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);

            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(nums, k)));
        System.out.println("Optimized   -> " + Arrays.toString(sol.optimized(nums, k)));
    }
}
