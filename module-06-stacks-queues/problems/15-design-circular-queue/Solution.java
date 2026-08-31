import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(1) all operations, O(k) space (Deque + manual capacity check)
    static class CircularQueueBruteForce {
        private Deque<Integer> deque = new ArrayDeque<>();
        private int capacity;

        public CircularQueueBruteForce(int k) { this.capacity = k; }

        public boolean enQueue(int value) {
            if (deque.size() == capacity) return false;
            deque.addLast(value);
            return true;
        }

        public boolean deQueue() {
            if (deque.isEmpty()) return false;
            deque.removeFirst();
            return true;
        }

        public int Front() { return deque.isEmpty() ? -1 : deque.peekFirst(); }
        public int Rear() { return deque.isEmpty() ? -1 : deque.peekLast(); }
        public boolean isEmpty() { return deque.isEmpty(); }
        public boolean isFull() { return deque.size() == capacity; }
    }

    // Approach 2: Optimized -> O(1) all operations, O(k) space (raw array, head/count tracking)
    static class CircularQueueOptimized {
        private int[] data;
        private int head;
        private int count;
        private int capacity;

        public CircularQueueOptimized(int k) {
            this.capacity = k;
            this.data = new int[k];
            this.head = 0;
            this.count = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) return false;
            int tail = (head + count) % capacity;
            data[tail] = value;
            count++;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) return false;
            head = (head + 1) % capacity;
            count--;
            return true;
        }

        public int Front() { return isEmpty() ? -1 : data[head]; }
        public int Rear() { return isEmpty() ? -1 : data[(head + count - 1) % capacity]; }
        public boolean isEmpty() { return count == 0; }
        public boolean isFull() { return count == capacity; }
    }

    public static void main(String[] args) {
        CircularQueueBruteForce bf = new CircularQueueBruteForce(3);
        bf.enQueue(1); bf.enQueue(2); bf.enQueue(3);
        System.out.println("Brute Force -> isFull=" + bf.isFull() + ", Rear=" + bf.Rear());
        bf.deQueue(); bf.enQueue(4);
        System.out.println("Brute Force -> Rear after wraparound=" + bf.Rear());

        CircularQueueOptimized opt = new CircularQueueOptimized(3);
        opt.enQueue(1); opt.enQueue(2); opt.enQueue(3);
        System.out.println("Optimized   -> isFull=" + opt.isFull() + ", Rear=" + opt.Rear());
        opt.deQueue(); opt.enQueue(4);
        System.out.println("Optimized   -> Rear after wraparound=" + opt.Rear());
    }
}
