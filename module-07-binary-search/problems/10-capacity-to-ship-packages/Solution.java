import java.util.*;

public class Solution {

    private int daysNeeded(int[] weights, int capacity) {
        int days = 1, currentLoad = 0;
        for (int w : weights) {
            if (currentLoad + w > capacity) {
                days++;
                currentLoad = 0;
            }
            currentLoad += w;
        }
        return days;
    }

    // Approach 1: Brute Force -> O((sum-max) * n) time, O(1) space
    public int bruteForce(int[] weights, int days) {
        int capacity = Arrays.stream(weights).max().getAsInt();
        int maxCapacity = Arrays.stream(weights).sum();

        for (int c = capacity; c <= maxCapacity; c++) {
            if (daysNeeded(weights, c) <= days) {
                return c;
            }
        }
        return maxCapacity;
    }

    // Approach 2: Optimized -> O(n log(sum)) time, O(1) space (binary search on capacity)
    public int optimized(int[] weights, int days) {
        int left = Arrays.stream(weights).max().getAsInt();
        int right = Arrays.stream(weights).sum();

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (daysNeeded(weights, mid) <= days) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;

        System.out.println("Brute Force -> " + sol.bruteForce(weights, days));
        System.out.println("Optimized   -> " + sol.optimized(weights, days));
    }
}
