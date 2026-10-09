import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2 log n) time, O(n^2) space (flatten + sort)
    public int bruteForce(int[][] matrix, int k) {
        int n = matrix.length;
        int[] flat = new int[n * n];
        int idx = 0;
        for (int[] row : matrix) {
            for (int val : row) flat[idx++] = val;
        }
        Arrays.sort(flat);
        return flat[k - 1];
    }

    private int countLessEqual(int[][] matrix, int target) {
        int n = matrix.length;
        int count = 0;
        int row = n - 1, col = 0;
        while (row >= 0 && col < n) {
            if (matrix[row][col] <= target) {
                count += row + 1;
                col++;
            } else {
                row--;
            }
        }
        return count;
    }

    // Approach 2: Optimized -> O(n log(max-min)) time, O(1) extra space
    // (binary search on value range + staircase counting)
    public int optimized(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0], right = matrix[n - 1][n - 1];

        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countLessEqual(matrix, mid);
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int k = 8;

        System.out.println("Brute Force -> " + sol.bruteForce(matrix, k));
        System.out.println("Optimized   -> " + sol.optimized(matrix, k));
    }
}
