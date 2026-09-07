import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(maxPile * n) time, O(1) space
    public int bruteForce(int[] piles, int h) {
        int maxPile = Arrays.stream(piles).max().getAsInt();
        for (int speed = 1; speed <= maxPile; speed++) {
            if (hoursNeeded(piles, speed) <= h) {
                return speed;
            }
        }
        return maxPile;
    }

    private long hoursNeeded(int[] piles, int speed) {
        long hours = 0;
        for (int pile : piles) {
            hours += (pile + speed - 1) / speed; // ceiling division
        }
        return hours;
    }

    // Approach 2: Optimized -> O(n log(maxPile)) time, O(1) space (binary search on speed)
    public int optimized(int[] piles, int h) {
        int left = 1, right = Arrays.stream(piles).max().getAsInt();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (hoursNeeded(piles, mid) <= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] piles = {3, 6, 7, 11};
        int h = 8;

        System.out.println("Brute Force -> " + sol.bruteForce(piles, h));
        System.out.println("Optimized   -> " + sol.optimized(piles, h));
    }
}
