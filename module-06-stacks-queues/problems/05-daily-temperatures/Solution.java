import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) extra space
    public int[] bruteForce(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }
        return result;
    }

    // Approach 2: Optimized -> O(n) time, O(n) space (monotonic decreasing stack)
    public int[] optimized(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(); // indices, decreasing temperature

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = i - idx;
            }
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};

        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(temperatures)));
        System.out.println("Optimized   -> " + Arrays.toString(sol.optimized(temperatures)));
    }
}
