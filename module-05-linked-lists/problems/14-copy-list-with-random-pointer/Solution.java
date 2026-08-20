import java.util.*;

public class Solution {

    static class Node {
        int val;
        Node next;
        Node random;
        Node(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O(n) time, O(n) space (HashMap mapping, two passes)
    public Node bruteForce(Node head) {
        if (head == null) return null;
        Map<Node, Node> mapping = new HashMap<>();

        for (Node c = head; c != null; c = c.next) {
            mapping.put(c, new Node(c.val));
        }
        for (Node c = head; c != null; c = c.next) {
            mapping.get(c).next = mapping.get(c.next);
            mapping.get(c).random = mapping.get(c.random);
        }
        return mapping.get(head);
    }

    // Approach 2: Optimized -> O(n) time, O(1) extra space (interweaving, three passes)
    public Node optimized(Node head) {
        if (head == null) return null;

        for (Node c = head; c != null; c = c.next.next) {
            Node copy = new Node(c.val);
            copy.next = c.next;
            c.next = copy;
        }

        for (Node c = head; c != null; c = c.next.next) {
            if (c.random != null) {
                c.next.random = c.random.next;
            }
        }

        Node dummy = new Node(0);
        Node copyTail = dummy;
        for (Node c = head; c != null; c = c.next) {
            Node copy = c.next;
            copyTail.next = copy;
            copyTail = copy;
            c.next = copy.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Build A -> B -> C, with A.random = C, B.random = A
        Node c = new Node(3);
        Node b = new Node(2);
        Node a = new Node(1);
        a.next = b;
        b.next = c;
        a.random = c;
        b.random = a;

        Node copy1 = sol.bruteForce(a);
        System.out.println("Brute Force -> vals=" + copy1.val + "," + copy1.next.val + "," + copy1.next.next.val
                + " | A'.random=" + copy1.random.val + " B'.random=" + copy1.next.random.val);

        Node copy2 = sol.optimized(a);
        System.out.println("Optimized   -> vals=" + copy2.val + "," + copy2.next.val + "," + copy2.next.next.val
                + " | A'.random=" + copy2.random.val + " B'.random=" + copy2.next.random.val);
    }
}
