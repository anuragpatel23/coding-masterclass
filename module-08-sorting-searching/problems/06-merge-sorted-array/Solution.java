import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O((m+n) log(m+n)) time, O(1) extra space (copy + sort)
    public void bruteForce(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    // Approach 2: Optimized -> O(m+n) time, O(1) space (three pointers, merge from the back)
    public void optimized(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1a = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        sol.bruteForce(nums1a, 3, nums2, 3);
        System.out.println("Brute Force -> " + Arrays.toString(nums1a));

        int[] nums1b = {1, 2, 3, 0, 0, 0};
        sol.optimized(nums1b, 3, nums2, 3);
        System.out.println("Optimized   -> " + Arrays.toString(nums1b));
    }
}
