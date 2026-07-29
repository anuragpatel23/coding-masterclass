import java.util.*;

public class Solution {

    // Approach 1: Naive -> O(m*n) time, O(m*n) extra space (visited matrix)
    public List<Integer> bruteForce(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[] dRow = {0, 1, 0, -1};
        int[] dCol = {1, 0, -1, 0};

        List<Integer> result = new ArrayList<>();
        int row = 0, col = 0, dir = 0;

        for (int i = 0; i < rows * cols; i++) {
            result.add(matrix[row][col]);
            visited[row][col] = true;

            int nextRow = row + dRow[dir];
            int nextCol = col + dCol[dir];

            if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols || visited[nextRow][nextCol]) {
                dir = (dir + 1) % 4;
                nextRow = row + dRow[dir];
                nextCol = col + dCol[dir];
            }
            row = nextRow;
            col = nextCol;
        }
        return result;
    }

    // Approach 2: Optimized -> O(m*n) time, O(1) extra space (shrinking boundaries)
    public List<Integer> optimized(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            for (int c = left; c <= right; c++) result.add(matrix[top][c]);
            top++;

            for (int r = top; r <= bottom; r++) result.add(matrix[r][right]);
            right--;

            if (top <= bottom) {
                for (int c = right; c >= left; c--) result.add(matrix[bottom][c]);
                bottom--;
            }

            if (left <= right) {
                for (int r = bottom; r >= top; r--) result.add(matrix[r][left]);
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};

        System.out.println("Naive       -> " + sol.bruteForce(matrix));
        System.out.println("Optimized   -> " + sol.optimized(matrix));
    }
}
