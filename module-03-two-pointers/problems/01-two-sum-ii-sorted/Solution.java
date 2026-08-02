import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) space (ignores sortedness)
    public int[] bruteForce(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        throw new IllegalArgumentException("No solution found");
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (opposite-direction two pointers)
    public int[] optimized(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        throw new IllegalArgumentException("No solution found");
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] numbers = {2, 7, 11, 15};
        int target = 9;

        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(numbers, target)));
        System.out.println("Optimized   -> " + Arrays.toString(sol.optimized(numbers, target)));
    }
}
