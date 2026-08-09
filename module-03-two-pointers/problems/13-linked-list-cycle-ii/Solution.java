import java.util.*;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O(n) time, O(n) space (HashSet of visited nodes)
    public ListNode bruteForce(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            if (visited.contains(current)) return current;
            visited.add(current);
            current = current.next;
        }
        return null;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (Floyd's algorithm, two phases)
    public ListNode optimized(ListNode head) {
        ListNode slow = head, fast = head;
        boolean hasCycle = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
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
        n4.next = n2; // creates the cycle, starting at n2
        return n1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        ListNode result1 = sol.bruteForce(buildCyclicList());
        System.out.println("Brute Force -> cycle starts at node value " + (result1 != null ? result1.val : "null"));

        ListNode result2 = sol.optimized(buildCyclicList());
        System.out.println("Optimized   -> cycle starts at node value " + (result2 != null ? result2.val : "null"));
    }
}
