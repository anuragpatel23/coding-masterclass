import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(n) space (two explicit passes)
    public int bruteForce(String s) {
        List<String> tokens = tokenize(s);

        boolean found = true;
        while (found) {
            found = false;
            for (int i = 0; i < tokens.size(); i++) {
                if (tokens.get(i).equals("*") || tokens.get(i).equals("/")) {
                    int a = Integer.parseInt(tokens.get(i - 1));
                    int b = Integer.parseInt(tokens.get(i + 1));
                    int result = tokens.get(i).equals("*") ? a * b : a / b;
                    tokens.subList(i - 1, i + 2).clear();
                    tokens.add(i - 1, String.valueOf(result));
                    found = true;
                    break;
                }
            }
        }

        int total = Integer.parseInt(tokens.get(0));
        for (int i = 1; i < tokens.size(); i += 2) {
            int val = Integer.parseInt(tokens.get(i + 1));
            if (tokens.get(i).equals("+")) total += val;
            else total -= val;
        }
        return total;
    }

    private List<String> tokenize(String s) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isWhitespace(c)) {
                i++;
            } else if (Character.isDigit(c)) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                tokens.add(String.valueOf(num));
            } else {
                tokens.add(String.valueOf(c));
                i++;
            }
        }
        return tokens;
    }

    // Approach 2: Optimized -> O(n) time, O(n) space (single pass, sign-tracking stack)
    public int optimized(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char sign = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if ((!Character.isDigit(c) && !Character.isWhitespace(c)) || i == s.length() - 1) {
                if (sign == '+') stack.push(num);
                else if (sign == '-') stack.push(-num);
                else if (sign == '*') stack.push(stack.pop() * num);
                else if (sign == '/') stack.push(stack.pop() / num);
                sign = c;
                num = 0;
            }
        }

        int result = 0;
        while (!stack.isEmpty()) result += stack.pop();
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "3+2*2";

        System.out.println("Brute Force -> " + sol.bruteForce(s));
        System.out.println("Optimized   -> " + sol.optimized(s));
    }
}
