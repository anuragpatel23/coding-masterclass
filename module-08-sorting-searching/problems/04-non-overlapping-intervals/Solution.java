import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(n) space (longest non-overlapping chain DP)
    public int bruteForce(int[][] intervals) {
        int n = intervals.length;
        if (n == 0) return 0;
        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (a, b) -> a[0] - b[0]);

        int[] chainLength = new int[n];
        Arrays.fill(chainLength, 1);
        int maxChain = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (sorted[j][1] <= sorted[i][0]) {
                    chainLength[i] = Math.max(chainLength[i], chainLength[j] + 1);
                }
            }
            maxChain = Math.max(maxChain, chainLength[i]);
        }
        return n - maxChain;
    }

    // Approach 2: Optimized -> O(n log n) time, O(1) extra space (greedy, sort by end time)
    public int optimized(int[][] intervals) {
        if (intervals.length == 0) return 0;
        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (a, b) -> a[1] - b[1]);

        int removals = 0;
        int lastEnd = sorted[0][1];
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i][0] < lastEnd) {
                removals++;
            } else {
                lastEnd = sorted[i][1];
            }
        }
        return removals;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};

        System.out.println("Brute Force -> " + sol.bruteForce(intervals.clone()));
        System.out.println("Optimized   -> " + sol.optimized(intervals.clone()));
    }
}
