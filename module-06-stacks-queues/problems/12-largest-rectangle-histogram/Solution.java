import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) extra space
    public int bruteForce(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            int left = i;
            while (left > 0 && heights[left - 1] >= heights[i]) left--;
            int right = i;
            while (right < n - 1 && heights[right + 1] >= heights[i]) right++;
            maxArea = Math.max(maxArea, heights[i] * (right - left + 1));
        }
        return maxArea;
    }

    // Approach 2: Optimized -> O(n) time, O(n) space (monotonic increasing stack)
    public int optimized(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>(); // indices, increasing heights
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] heights = {2, 1, 5, 6, 2, 3};

        System.out.println("Brute Force -> " + sol.bruteForce(heights));
        System.out.println("Optimized   -> " + sol.optimized(heights));
    }
}
