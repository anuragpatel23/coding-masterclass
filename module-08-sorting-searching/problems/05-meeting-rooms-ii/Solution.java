import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) extra space
    // (checks concurrency at each interval's start time, since the peak concurrency must
    // occur at one of those instants -- simply counting pairwise overlaps per interval is
    // NOT equivalent: two meetings can each overlap a third without overlapping each other)
    public int bruteForce(int[][] intervals) {
        int maxRooms = 0;
        for (int[] interval : intervals) {
            int checkpoint = interval[0];
            int count = 0;
            for (int[] other : intervals) {
                if (other[0] <= checkpoint && checkpoint < other[1]) {
                    count++;
                }
            }
            maxRooms = Math.max(maxRooms, count);
        }
        return maxRooms;
    }

    // Approach 2: Optimized -> O(n log n) time, O(n) space (two sorted arrays, two-pointer sweep)
    public int optimized(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        int rooms = 0, maxRooms = 0;
        int startPtr = 0, endPtr = 0;

        while (startPtr < n) {
            if (starts[startPtr] < ends[endPtr]) {
                rooms++;
                startPtr++;
            } else {
                rooms--;
                endPtr++;
            }
            maxRooms = Math.max(maxRooms, rooms);
        }
        return maxRooms;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};

        System.out.println("Brute Force -> " + sol.bruteForce(intervals));
        System.out.println("Optimized   -> " + sol.optimized(intervals));
    }
}
