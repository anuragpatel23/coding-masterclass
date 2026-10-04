import java.util.*;

public class Solution {

    // Approach 1: "Brute Force" -> O(n^2) time, O(n) space (insertion sort on a clone)
    public int[] bruteForce(int[] nums) {
        int[] result = nums.clone();
        for (int i = 1; i < result.length; i++) {
            int key = result[i];
            int j = i - 1;
            while (j >= 0 && result[j] > key) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = key;
        }
        return result;
    }

    // Approach 2: Optimized -> O(n log n) time, O(n) space (merge sort from scratch)
    public int[] optimized(int[] nums) {
        int[] result = nums.clone();
        mergeSort(result, 0, result.length - 1);
        return result;
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {5, 2, 3, 1};

        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(nums)));
        System.out.println("Optimized   -> " + Arrays.toString(sol.optimized(nums)));
    }
}
