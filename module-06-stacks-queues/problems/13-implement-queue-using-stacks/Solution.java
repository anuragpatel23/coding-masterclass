import java.util.*;

public class Solution {

    // Approach 1: Push-heavy -> O(n) push, O(1) pop/peek, O(n) space (reorder on every push)
    static class QueueBruteForce {
        private Deque<Integer> stack = new ArrayDeque<>();

        public void push(int x) {
            Deque<Integer> temp = new ArrayDeque<>();
            while (!stack.isEmpty()) temp.push(stack.pop());
            stack.push(x);
            while (!temp.isEmpty()) stack.push(temp.pop());
        }

        public int pop() { return stack.pop(); }
        public int peek() { return stack.peek(); }
        public boolean isEmpty() { return stack.isEmpty(); }
    }

    // Approach 2: Optimized -> O(1) amortized for all operations, O(n) space (two stacks)
    static class QueueOptimized {
        private Deque<Integer> inStack = new ArrayDeque<>();
        private Deque<Integer> outStack = new ArrayDeque<>();

        public void push(int x) { inStack.push(x); }

        public int pop() {
            transferIfNeeded();
            return outStack.pop();
        }

        public int peek() {
            transferIfNeeded();
            return outStack.peek();
        }

        public boolean isEmpty() { return inStack.isEmpty() && outStack.isEmpty(); }

        private void transferIfNeeded() {
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) outStack.push(inStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        QueueBruteForce bf = new QueueBruteForce();
        bf.push(1); bf.push(2);
        System.out.println("Brute Force -> peek=" + bf.peek() + ", pop=" + bf.pop());

        QueueOptimized opt = new QueueOptimized();
        opt.push(1); opt.push(2);
        System.out.println("Optimized   -> peek=" + opt.peek() + ", pop=" + opt.pop());
    }
}
