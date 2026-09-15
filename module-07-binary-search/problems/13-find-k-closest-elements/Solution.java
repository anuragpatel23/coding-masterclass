import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n log n) time, O(n) space (sort by distance)
    public List<Integer> bruteForce(int[] arr, int k, int x) {
        List<Integer> sorted = new ArrayList<>();
        for (int num : arr) sorted.add(num);

        sorted.sort((a, b) -> {
            int diffA = Math.abs(a - x), diffB = Math.abs(b - x);
            if (diffA != diffB) return diffA - diffB;
            return a - b;
        });

        List<Integer> result = new ArrayList<>(sorted.subList(0, k));
        Collections.sort(result);
        return result;
    }

    // Approach 2: Optimized -> O(log(n-k)) time, O(1) extra space (binary search on window start)
    public List<Integer> optimized(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) result.add(arr[i]);
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4, x = 3;

        System.out.println("Brute Force -> " + sol.bruteForce(arr, k, x));
        System.out.println("Optimized   -> " + sol.optimized(arr, k, x));
    }
}
