import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(n) space (repeated pair removal)
    public boolean bruteForce(String s) {
        StringBuilder sb = new StringBuilder(s);
        boolean changed = true;
        while (changed) {
            changed = false;
            int idx = sb.indexOf("()");
            if (idx == -1) idx = sb.indexOf("[]");
            if (idx == -1) idx = sb.indexOf("{}");
            if (idx != -1) {
                sb.delete(idx, idx + 2);
                changed = true;
            }
        }
        return sb.length() == 0;
    }

    // Approach 2: Optimized -> O(n) time, O(n) space (stack matching)
    public boolean optimized(String s) {
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');

        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (pairs.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(c)) return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "([{}])";

        System.out.println("Brute Force -> " + sol.bruteForce(s));
        System.out.println("Optimized   -> " + sol.optimized(s));
    }
}
