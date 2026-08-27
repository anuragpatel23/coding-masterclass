import java.util.*;

public class Solution {

    private static final Set<String> OPERATORS = new HashSet<>(Arrays.asList("+", "-", "*", "/"));

    private int applyOp(int a, int b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
        }
        throw new IllegalArgumentException("Unknown operator: " + op);
    }

    // Approach 1: Brute Force -> O(n^2) time, O(n) space (repeated scan-and-splice, no stack)
    public int bruteForce(String[] tokens) {
        List<String> list = new ArrayList<>(Arrays.asList(tokens));

        while (list.size() > 1) {
            for (int i = 0; i < list.size(); i++) {
                if (OPERATORS.contains(list.get(i))) {
                    int b = Integer.parseInt(list.get(i - 1));
                    int a = Integer.parseInt(list.get(i - 2));
                    int result = applyOp(a, b, list.get(i));
                    list.subList(i - 2, i + 1).clear();
                    list.add(i - 2, String.valueOf(result));
                    break;
                }
            }
        }
        return Integer.parseInt(list.get(0));
    }

    // Approach 2: Optimized -> O(n) time, O(n) space (stack-based evaluation)
    public int optimized(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if (OPERATORS.contains(token)) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(applyOp(a, b, token));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] tokens = {"2", "1", "+", "3", "*"};

        System.out.println("Brute Force -> " + sol.bruteForce(tokens));
        System.out.println("Optimized   -> " + sol.optimized(tokens));
    }
}
