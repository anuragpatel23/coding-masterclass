import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n log n) time, O(n) space (sort by frequency)
    public int[] bruteForce(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) counts.merge(num, 1, Integer::sum);

        List<Integer> sorted = new ArrayList<>(counts.keySet());
        sorted.sort((a, b) -> counts.get(b) - counts.get(a));

        int[] result = new int[k];
        for (int i = 0; i < k; i++) result[i] = sorted.get(i);
        return result;
    }

    // Approach 2: Optimized -> O(n) time, O(n) space (bucket sort by frequency)
    public int[] optimized(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) counts.merge(num, 1, Integer::sum);

        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) buckets.add(new ArrayList<>());

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }

        int[] result = new int[k];
        int idx = 0;
        for (int freq = buckets.size() - 1; freq >= 0 && idx < k; freq--) {
            for (int num : buckets.get(freq)) {
                if (idx == k) break;
                result[idx++] = num;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(nums, k)));
        System.out.println("Optimized   -> " + Arrays.toString(sol.optimized(nums, k)));
    }
}
