import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n log n) time, O(n) space (add + full re-sort/merge)
    public int[][] bruteForce(int[][] intervals, int[] newInterval) {
        List<int[]> combined = new ArrayList<>(Arrays.asList(intervals));
        combined.add(newInterval);
        int[][] combinedArray = combined.toArray(new int[0][]);
        Arrays.sort(combinedArray, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        int[] current = combinedArray[0].clone();
        for (int i = 1; i < combinedArray.length; i++) {
            if (combinedArray[i][0] <= current[1]) {
                current[1] = Math.max(current[1], combinedArray[i][1]);
            } else {
                result.add(current);
                current = combinedArray[i].clone();
            }
        }
        result.add(current);
        return result.toArray(new int[0][]);
    }

    // Approach 2: Optimized -> O(n) time, O(n) space (three-phase linear scan)
    public int[][] optimized(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0, n = intervals.length;

        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        int[] merged = newInterval.clone();
        while (i < n && intervals[i][0] <= merged[1]) {
            merged[0] = Math.min(merged[0], intervals[i][0]);
            merged[1] = Math.max(merged[1], intervals[i][1]);
            i++;
        }
        result.add(merged);

        while (i < n) {
            result.add(intervals[i]);
            i++;
        }
        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};

        System.out.println("Brute Force -> " + Arrays.deepToString(sol.bruteForce(intervals, newInterval)));
        System.out.println("Optimized   -> " + Arrays.deepToString(sol.optimized(intervals, newInterval)));
    }
}
