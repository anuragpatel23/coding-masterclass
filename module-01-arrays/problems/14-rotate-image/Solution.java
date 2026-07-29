import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(n^2) space (new matrix)
    public int[][] bruteForce(int[][] matrix) {
        int n = matrix.length;
        int[][] result = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                result[c][n - 1 - r] = matrix[r][c];
            }
        }
        return result;
    }

    // Approach 2: Optimized -> O(n^2) time, O(1) extra space (transpose + reverse rows)
    public void optimized(int[][] matrix) {
        int n = matrix.length;

        // Transpose
        for (int r = 0; r < n; r++) {
            for (int c = r + 1; c < n; c++) {
                int temp = matrix[r][c];
                matrix[r][c] = matrix[c][r];
                matrix[c][r] = temp;
            }
        }

        // Reverse each row
        for (int r = 0; r < n; r++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = matrix[r][left];
                matrix[r][left] = matrix[r][right];
                matrix[r][right] = temp;
                left++;
                right--;
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] m1 = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("Brute Force -> " + Arrays.deepToString(sol.bruteForce(m1)));

        int[][] m2 = {{1,2,3},{4,5,6},{7,8,9}};
        sol.optimized(m2);
        System.out.println("Optimized   -> " + Arrays.deepToString(m2));
    }
}
