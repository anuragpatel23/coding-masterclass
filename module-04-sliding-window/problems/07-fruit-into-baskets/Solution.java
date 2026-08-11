import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) space
    public int bruteForce(int[] fruits) {
        int n = fruits.length;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> types = new HashSet<>();
            for (int j = i; j < n; j++) {
                types.add(fruits[j]);
                if (types.size() > 2) break;
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        return maxLen;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (variable window, at most 2 distinct)
    public int optimized(int[] fruits) {
        Map<Integer, Integer> counts = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < fruits.length; right++) {
            counts.merge(fruits[right], 1, Integer::sum);

            while (counts.size() > 2) {
                int leftType = fruits[left];
                counts.put(leftType, counts.get(leftType) - 1);
                if (counts.get(leftType) == 0) counts.remove(leftType);
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] fruits = {1, 2, 1};

        System.out.println("Brute Force -> " + sol.bruteForce(fruits));
        System.out.println("Optimized   -> " + sol.optimized(fruits));
    }
}
