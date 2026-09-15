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

    // Approach 2: Optimized -> O(m+n) time, O(1) space (staircase search from top-right)
    public boolean optimized(int[][] matrix, int target) {
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) col--;
            else row++;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] matrix = {{1, 4, 7, 11}, {2, 5, 8, 12}, {3, 6, 9, 16}, {10, 13, 14, 17}};
        int target = 5;

        System.out.println("Brute Force -> " + sol.bruteForce(matrix, target));
        System.out.println("Optimized   -> " + sol.optimized(matrix, target));
    }
}
