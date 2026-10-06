import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n! * n) time, O(n! * n) space (all permutations)
    public String bruteForce(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) strs[i] = String.valueOf(nums[i]);

        List<String> allPermutations = new ArrayList<>();
        permute(strs, 0, allPermutations);

        String best = "";
        for (String candidate : allPermutations) {
            if (best.isEmpty() || candidate.compareTo(best) > 0) {
                best = candidate;
            }
        }
        return best.charAt(0) == '0' ? "0" : best;
    }

    private void permute(String[] arr, int start, List<String> result) {
        if (start == arr.length) {
            result.add(String.join("", arr));
            return;
        }
        for (int i = start; i < arr.length; i++) {
            swapStr(arr, start, i);
            permute(arr, start + 1, result);
            swapStr(arr, start, i);
        }
    }

    private void swapStr(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Approach 2: Optimized -> O(n log n * m) time, O(n * m) space (custom comparator sort)
    public String optimized(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) strs[i] = String.valueOf(nums[i]);

        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));

        if (strs[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();
        for (String s : strs) sb.append(s);
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3, 30, 34, 5, 9};

        System.out.println("Brute Force -> " + sol.bruteForce(nums));
        System.out.println("Optimized   -> " + sol.optimized(nums));
    }
}
