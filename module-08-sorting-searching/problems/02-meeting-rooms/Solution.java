import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) extra space
    public boolean bruteForce(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[i][0] < intervals[j][1] && intervals[j][0] < intervals[i][1]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Approach 2: Optimized -> O(n log n) time, O(n) space (sort + adjacent check)
    public boolean optimized(int[][] intervals) {
        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (a, b) -> a[0] - b[0]);
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i][0] < sorted[i - 1][1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};

        System.out.println("Brute Force -> " + sol.bruteForce(intervals));
        System.out.println("Optimized   -> " + sol.optimized(intervals));
    }
}
