import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^3) worst case, O(n) space (no sort, repeated pair scan)
    public int[][] bruteForce(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) result.add(interval);

        boolean merged = true;
        while (merged) {
            merged = false;
            outer:
            for (int i = 0; i < result.size(); i++) {
                for (int j = i + 1; j < result.size(); j++) {
                    int[] a = result.get(i), b = result.get(j);
                    if (a[0] <= b[1] && b[0] <= a[1]) {
                        int[] mergedInterval = {Math.min(a[0], b[0]), Math.max(a[1], b[1])};
                        result.remove(j);
                        result.remove(i);
                        result.add(mergedInterval);
                        merged = true;
                        break outer;
                    }
                }
            }
        }
        // Sort by start so output order matches the optimized version (the *set* of merged
        // intervals is already correct without this; this just normalizes presentation order).
        result.sort((a, b) -> a[0] - b[0]);
        return result.toArray(new int[0][]);
    }

    // Approach 2: Optimized -> O(n log n) time, O(n) space (sort by start, then merge linearly)
    public int[][] optimized(int[][] intervals) {
        if (intervals.length == 0) return intervals;
        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (a, b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();
        int[] current = sorted[0].clone();
        merged.add(current);

        for (int i = 1; i < sorted.length; i++) {
            int[] next = sorted[i];
            if (next[0] <= current[1]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                current = next.clone();
                merged.add(current);
            }
        }
        return merged.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        System.out.println("Brute Force -> " + Arrays.deepToString(sol.bruteForce(intervals.clone())));
        System.out.println("Optimized   -> " + Arrays.deepToString(sol.optimized(intervals.clone())));
    }
}
