import java.util.*;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O(n) time, O(n) space (HashSet of visited nodes)
    public boolean bruteForce(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            if (visited.contains(current)) return true;
            visited.add(current);
            current = current.next;
        }
        return false;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (Floyd's fast/slow cycle detection)
    public boolean optimized(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    // Builds 3 -> 2 -> 0 -> -4 -> (back to the node holding 2)
    private static ListNode buildCyclicList() {
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2; // creates the cycle
        return n1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println("Brute Force -> " + sol.bruteForce(buildCyclicList()));
        System.out.println("Optimized   -> " + sol.optimized(buildCyclicList()));
    }
}
