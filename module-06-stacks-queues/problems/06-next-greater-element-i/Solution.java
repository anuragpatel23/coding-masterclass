import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n*m) time, O(1) extra space
    public int[] bruteForce(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            int pos = -1;
            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == nums1[i]) { pos = j; break; }
            }
            result[i] = -1;
            for (int j = pos + 1; j < nums2.length; j++) {
                if (nums2[j] > nums1[i]) { result[i] = nums2[j]; break; }
            }
        }
        return result;
    }

    // Approach 2: Optimized -> O(n+m) time, O(m) space (monotonic stack + hashmap)
    public int[] optimized(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreater = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>(); // values, decreasing

        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                nextGreater.put(stack.pop(), num);
            }
            stack.push(num);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreater.getOrDefault(nums1[i], -1);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};

        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(nums1, nums2)));
        System.out.println("Optimized   -> " + Arrays.toString(sol.optimized(nums1, nums2)));
    }
}
