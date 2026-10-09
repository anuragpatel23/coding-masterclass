import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n log n) time, O(n) space (custom comparator sort)
    public int[] bruteForce(int[] arr1, int[] arr2) {
        Map<Integer, Integer> rank = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) rank.put(arr2[i], i);

        Integer[] boxed = new Integer[arr1.length];
        for (int i = 0; i < arr1.length; i++) boxed[i] = arr1[i];

        Arrays.sort(boxed, (a, b) -> {
            boolean aInArr2 = rank.containsKey(a);
            boolean bInArr2 = rank.containsKey(b);
            if (aInArr2 && bInArr2) return rank.get(a) - rank.get(b);
            if (aInArr2) return -1;
            if (bInArr2) return 1;
            return a - b;
        });

        int[] result = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) result[i] = boxed[i];
        return result;
    }

    // Approach 2: Optimized -> O(n + range) time, O(range + n) space (counting sort)
    public int[] optimized(int[] arr1, int[] arr2) {
        int maxVal = 1000; // per problem constraints (0 <= arr1[i] <= 1000)
        int[] counts = new int[maxVal + 1];
        for (int num : arr1) counts[num]++;

        int[] result = new int[arr1.length];
        int idx = 0;

        for (int num : arr2) {
            while (counts[num] > 0) {
                result[idx++] = num;
                counts[num]--;
            }
        }
        for (int num = 0; num <= maxVal; num++) {
            while (counts[num] > 0) {
                result[idx++] = num;
                counts[num]--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};

        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(arr1, arr2)));
        System.out.println("Optimized   -> " + Arrays.toString(sol.optimized(arr1, arr2)));
    }
}
