import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(m*n) time, O(m+n) space
    public void bruteForce(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroCols = new HashSet<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 0) {
                    zeroRows.add(r);
                    zeroCols.add(c);
                }
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (zeroRows.contains(r) || zeroCols.contains(c)) {
                    matrix[r][c] = 0;
                }
            }
        }
    }

    // Approach 2: Optimized -> O(m*n) time, O(1) extra space (first row/col as markers)
    public void optimized(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;

        for (int c = 0; c < cols; c++) if (matrix[0][c] == 0) firstRowHasZero = true;
        for (int r = 0; r < rows; r++) if (matrix[r][0] == 0) firstColHasZero = true;

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (matrix[r][c] == 0) {
                    matrix[r][0] = 0;
                    matrix[0][c] = 0;
                }
            }
        }

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (matrix[r][0] == 0 || matrix[0][c] == 0) {
                    matrix[r][c] = 0;
                }
            }
        }

        if (firstRowHasZero) Arrays.fill(matrix[0], 0);
        if (firstColHasZero) {
            for (int r = 0; r < rows; r++) matrix[r][0] = 0;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] m1 = {{1,1,1},{1,0,1},{1,1,1}};
        sol.bruteForce(m1);
        System.out.println("Brute Force -> " + Arrays.deepToString(m1));

        int[][] m2 = {{1,1,1},{1,0,1},{1,1,1}};
        sol.optimized(m2);
        System.out.println("Optimized   -> " + Arrays.deepToString(m2));
    }
}
