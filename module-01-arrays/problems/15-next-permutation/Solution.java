import java.util.*;

public class Solution {

    // Approach 1: Conceptual Brute Force -> O(n!) time - generate all permutations, sort,
    // find current, return next. Only viable for tiny n; included for intuition.
    public int[] bruteForce(int[] nums) {
        List<int[]> permutations = new ArrayList<>();
        permute(nums.clone(), 0, permutations);
        permutations.sort((a, b) -> {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) return a[i] - b[i];
            }
            return 0;
        });

        for (int i = 0; i < permutations.size(); i++) {
            if (Arrays.equals(permutations.get(i), nums)) {
                int[] next = (i + 1 < permutations.size()) ? permutations.get(i + 1) : permutations.get(0);
                System.arraycopy(next, 0, nums, 0, nums.length);
                return nums;
            }
        }
        return nums;
    }

    private void permute(int[] arr, int start, List<int[]> result) {
        if (start == arr.length) {
            result.add(arr.clone());
            return;
        }
        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            permute(arr, start + 1, result);
            swap(arr, start, i);
        }
    }

    // Approach 2: Optimized -> O(n) time, O(1) extra space (pivot + successor + reverse)
    public int[] optimized(int[] nums) {
        int n = nums.length;
        int pivot = n - 2;

        // Step 1: find the rightmost index where nums[pivot] < nums[pivot + 1]
        while (pivot >= 0 && nums[pivot] >= nums[pivot + 1]) {
            pivot--;
        }

        // Step 2: if a pivot exists, find the rightmost successor greater than it and swap
        if (pivot >= 0) {
            int successor = n - 1;
            while (nums[successor] <= nums[pivot]) {
                successor--;
            }
            swap(nums, pivot, successor);
        }

        // Step 3: reverse the suffix after the pivot (it's descending -> make it ascending)
        reverse(nums, pivot + 1, n - 1);
        return nums;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {1, 3, 2};
        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(nums1)));

        int[] nums2 = {1, 3, 2};
        System.out.println("Optimized   -> " + Arrays.toString(sol.optimized(nums2)));
    }
}
