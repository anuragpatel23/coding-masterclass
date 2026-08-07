import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^3) time, O(n^3) space worst case (set-based dedup)
    public List<List<Integer>> bruteForce(int[] nums) {
        Set<List<Integer>> uniqueTriplets = new HashSet<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
                        Collections.sort(triplet);
                        uniqueTriplets.add(triplet);
                    }
                }
            }
        }
        return new ArrayList<>(uniqueTriplets);
    }

    // Approach 2: Optimized -> O(n^2) time, O(1) extra space (sort + two pointers)
    public List<List<Integer>> optimized(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicate anchors

            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {-1, 0, 1, 2, -1, -4};

        System.out.println("Brute Force -> " + sol.bruteForce(nums.clone()));
        System.out.println("Optimized   -> " + sol.optimized(nums.clone()));
    }
}
