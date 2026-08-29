import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(n) space (repeated adjacent-pair resolution)
    public int[] bruteForce(int[] asteroids) {
        List<Integer> list = new ArrayList<>();
        for (int a : asteroids) list.add(a);

        boolean collided = true;
        while (collided) {
            collided = false;
            for (int i = 0; i + 1 < list.size(); i++) {
                int left = list.get(i), right = list.get(i + 1);
                if (left > 0 && right < 0) {
                    collided = true;
                    if (Math.abs(left) == Math.abs(right)) {
                        list.remove(i + 1);
                        list.remove(i);
                    } else if (Math.abs(left) > Math.abs(right)) {
                        list.remove(i + 1);
                    } else {
                        list.remove(i);
                    }
                    break;
                }
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) result[i] = list.get(i);
        return result;
    }

    // Approach 2: Optimized -> O(n) time, O(n) space (stack simulation, single pass)
    public int[] optimized(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int asteroid : asteroids) {
            boolean alive = true;
            while (alive && asteroid < 0 && !stack.isEmpty() && stack.peek() > 0) {
                int top = stack.peek();
                if (top < -asteroid) {
                    stack.pop(); // top asteroid destroyed, current continues
                } else if (top == -asteroid) {
                    stack.pop(); // both destroyed
                    alive = false;
                } else {
                    alive = false; // current destroyed
                }
            }
            if (alive) stack.push(asteroid);
        }

        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] asteroids = {5, 10, -5};

        System.out.println("Brute Force -> " + Arrays.toString(sol.bruteForce(asteroids)));
        System.out.println("Optimized   -> " + Arrays.toString(sol.optimized(asteroids)));
    }
}
