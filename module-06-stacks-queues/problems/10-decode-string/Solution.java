import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n * maxRepeat) time, O(d) call stack space (recursion)
    public String bruteForce(String s) {
        int[] pos = {0};
        return decodeHelper(s, pos);
    }

    private String decodeHelper(String s, int[] pos) {
        StringBuilder result = new StringBuilder();
        while (pos[0] < s.length() && s.charAt(pos[0]) != ']') {
            char c = s.charAt(pos[0]);
            if (Character.isDigit(c)) {
                int count = 0;
                while (Character.isDigit(s.charAt(pos[0]))) {
                    count = count * 10 + (s.charAt(pos[0]) - '0');
                    pos[0]++;
                }
                pos[0]++; // skip '['
                String inner = decodeHelper(s, pos);
                pos[0]++; // skip ']'
                for (int i = 0; i < count; i++) result.append(inner);
            } else {
                result.append(c);
                pos[0]++;
            }
        }
        return result.toString();
    }

    // Approach 2: Optimized -> O(n * maxRepeat) time, O(d) space (iterative, explicit stacks)
    public String optimized(String s) {
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<StringBuilder> stringStack = new ArrayDeque<>();
        StringBuilder current = new StringBuilder();
        int count = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                count = count * 10 + (c - '0');
            } else if (c == '[') {
                countStack.push(count);
                stringStack.push(current);
                count = 0;
                current = new StringBuilder();
            } else if (c == ']') {
                int repeat = countStack.pop();
                StringBuilder prev = stringStack.pop();
                for (int i = 0; i < repeat; i++) prev.append(current);
                current = prev;
            } else {
                current.append(c);
            }
        }
        return current.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "3[a2[c]]";

        System.out.println("Brute Force -> \"" + sol.bruteForce(s) + "\"");
        System.out.println("Optimized   -> \"" + sol.optimized(s) + "\"");
    }
}
