import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(m+n) time, O(m+n) space (merge, then read middle)
    public double bruteForce(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) merged[k++] = nums1[i++];
            else merged[k++] = nums2[j++];
        }
        while (i < nums1.length) merged[k++] = nums1[i++];
        while (j < nums2.length) merged[k++] = nums2[j++];

        int n = merged.length;
        if (n % 2 == 1) {
            return merged[n / 2];
        } else {
            return (merged[n / 2 - 1] + merged[n / 2]) / 2.0;
        }
    }

    // Approach 2: Optimized -> O(log(min(m,n))) time, O(1) space (binary search on partition)
    public double optimized(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return optimized(nums2, nums1);
        }

        int m = nums1.length, n = nums2.length;
        int left = 0, right = m;
        int halfLen = (m + n + 1) / 2;

        while (left <= right) {
            int partition1 = left + (right - left) / 2;
            int partition2 = halfLen - partition1;

            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];

            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                if ((m + n) % 2 == 1) {
                    return Math.max(maxLeft1, maxLeft2);
                } else {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                }
            } else if (maxLeft1 > minRight2) {
                right = partition1 - 1;
            } else {
                left = partition1 + 1;
            }
        }
        throw new IllegalArgumentException("Input arrays are not sorted");
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};

        System.out.println("Brute Force -> " + sol.bruteForce(nums1, nums2));
        System.out.println("Optimized   -> " + sol.optimized(nums1, nums2));
    }
}
