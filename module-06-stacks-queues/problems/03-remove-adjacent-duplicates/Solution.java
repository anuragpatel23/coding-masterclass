import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(n) space (repeated pair removal)
    public String bruteForce(String s) {
        StringBuilder sb = new StringBuilder(s);
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i + 1 < sb.length(); i++) {
                if (sb.charAt(i) == sb.charAt(i + 1)) {
                    sb.delete(i, i + 2);
                    changed = true;
                    break;
                }
            }
        }
        return sb.toString();
    }

    // Approach 2: Optimized -> O(n) time, O(n) space (stack matching)
    public String optimized(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abbaca";

        System.out.println("Brute Force -> \"" + sol.bruteForce(s) + "\"");
        System.out.println("Optimized   -> \"" + sol.optimized(s) + "\"");
    }
}
