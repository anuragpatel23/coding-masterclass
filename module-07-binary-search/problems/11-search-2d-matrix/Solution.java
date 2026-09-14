import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(m*n) time, O(1) space
    public boolean bruteForce(int[][] matrix, int target) {
        for (int[] row : matrix) {
            for (int val : row) {
                if (val == target) return true;
            }
        }
        return false;
    }

    // Approach 2: Optimized -> O(log(m*n)) time, O(1) space (binary search, flattened index)
    public boolean optimized(int[][] matrix, int target) {
        int rows = matrix.length, cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int value = matrix[mid / cols][mid % cols];
            if (value == target) return true;
            else if (value < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 3;

        System.out.println("Brute Force -> " + sol.bruteForce(matrix, target));
        System.out.println("Optimized   -> " + sol.optimized(matrix, target));
    }
}
