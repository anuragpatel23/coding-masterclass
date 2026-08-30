import java.util.*;

public class Solution {

    // Approach 1: Two queues -> O(n) push, O(1) pop/top, O(n) space
    static class StackTwoQueues {
        private Queue<Integer> queue1 = new LinkedList<>();
        private Queue<Integer> queue2 = new LinkedList<>();

        public void push(int x) {
            queue2.offer(x);
            while (!queue1.isEmpty()) queue2.offer(queue1.poll());
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        public int pop() { return queue1.poll(); }
        public int top() { return queue1.peek(); }
        public boolean isEmpty() { return queue1.isEmpty(); }
    }

    // Approach 2: Optimized -> O(n) push, O(1) pop/top, O(n) space (single queue, rotation)
    static class StackOptimizedSingleQueue {
        private Queue<Integer> queue = new LinkedList<>();

        public void push(int x) {
            queue.offer(x);
            int size = queue.size();
            for (int i = 0; i < size - 1; i++) {
                queue.offer(queue.poll());
            }
        }

        public int pop() { return queue.poll(); }
        public int top() { return queue.peek(); }
        public boolean isEmpty() { return queue.isEmpty(); }
    }

    public static void main(String[] args) {
        StackTwoQueues bf = new StackTwoQueues();
        bf.push(1); bf.push(2); bf.push(3);
        System.out.println("Two Queues  -> pop=" + bf.pop());

        StackOptimizedSingleQueue opt = new StackOptimizedSingleQueue();
        opt.push(1); opt.push(2); opt.push(3);
        System.out.println("Single Queue-> pop=" + opt.pop());
    }
}
