import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(1) push/pop/top, O(n) getMin, O(n) space
    static class MinStackBruteForce {
        private Deque<Integer> stack = new ArrayDeque<>();

        public void push(int val) { stack.push(val); }
        public void pop() { stack.pop(); }
        public int top() { return stack.peek(); }

        public int getMin() {
            int min = Integer.MAX_VALUE;
            for (int v : stack) min = Math.min(min, v);
            return min;
        }
    }

    // Approach 2: Optimized -> O(1) for every operation, O(n) space (parallel min stack)
    static class MinStackOptimized {
        private Deque<Integer> stack = new ArrayDeque<>();
        private Deque<Integer> minStack = new ArrayDeque<>();

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            } else {
                minStack.push(minStack.peek());
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() { return stack.peek(); }
        public int getMin() { return minStack.peek(); }
    }

    public static void main(String[] args) {
        MinStackBruteForce bf = new MinStackBruteForce();
        bf.push(-2); bf.push(0); bf.push(-3);
        System.out.println("Brute Force -> getMin=" + bf.getMin());
        bf.pop();
        System.out.println("Brute Force -> top=" + bf.top() + ", getMin=" + bf.getMin());

        MinStackOptimized opt = new MinStackOptimized();
        opt.push(-2); opt.push(0); opt.push(-3);
        System.out.println("Optimized   -> getMin=" + opt.getMin());
        opt.pop();
        System.out.println("Optimized   -> top=" + opt.top() + ", getMin=" + opt.getMin());
    }
}
