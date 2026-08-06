import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) space
    public int bruteForce(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = Math.min(height[i], height[j]) * (j - i);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (two pointers, move the shorter side)
    public int optimized(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        System.out.println("Brute Force -> " + sol.bruteForce(height));
        System.out.println("Optimized   -> " + sol.optimized(height));
    }
}
