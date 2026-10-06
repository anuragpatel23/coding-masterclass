import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n log n) time, O(n) space (full sort)
    public int bruteForce(int[] nums, int k) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        return sorted[sorted.length - k];
    }

    // Approach 2: Optimized -> O(n) average, O(n^2) worst case, O(1) extra space (quickselect)
    public int optimized(int[] nums, int k) {
        int[] arr = nums.clone();
        int targetIndex = arr.length - k;
        return quickSelect(arr, 0, arr.length - 1, targetIndex);
    }

    private int quickSelect(int[] arr, int left, int right, int targetIndex) {
        if (left == right) return arr[left];

        int pivotIndex = partition(arr, left, right);
        if (pivotIndex == targetIndex) {
            return arr[pivotIndex];
        } else if (pivotIndex < targetIndex) {
            return quickSelect(arr, pivotIndex + 1, right, targetIndex);
        } else {
            return quickSelect(arr, left, pivotIndex - 1, targetIndex);
        }
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;

        System.out.println("Brute Force -> " + sol.bruteForce(nums, k));
        System.out.println("Optimized   -> " + sol.optimized(nums, k));
    }
}
