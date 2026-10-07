import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) space
    public int bruteForce(int[] citations) {
        int n = citations.length;
        for (int h = n; h >= 0; h--) {
            int count = 0;
            for (int c : citations) {
                if (c >= h) count++;
            }
            if (count >= h) return h;
        }
        return 0;
    }

    // Approach 2: Optimized -> O(n log n) time, O(n) space (sort + linear scan)
    public int optimized(int[] citations) {
        int[] sorted = citations.clone();
        Arrays.sort(sorted);
        int n = sorted.length;

        for (int i = 0; i < n; i++) {
            int papersWithAtLeastThisMany = n - i;
            if (sorted[i] >= papersWithAtLeastThisMany) {
                return papersWithAtLeastThisMany;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] citations = {3, 0, 6, 1, 5};

        System.out.println("Brute Force -> " + sol.bruteForce(citations));
        System.out.println("Optimized   -> " + sol.optimized(citations));
    }
}
