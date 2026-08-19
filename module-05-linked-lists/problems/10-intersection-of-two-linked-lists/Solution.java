import java.util.*;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O(n+m) time, O(n) space (HashSet of list A's nodes)
    public ListNode bruteForce(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<>();
        for (ListNode c = headA; c != null; c = c.next) visited.add(c);

        for (ListNode c = headB; c != null; c = c.next) {
            if (visited.contains(c)) return c;
        }
        return null;
    }

    // Approach 2: Optimized -> O(n+m) time, O(1) space (switch-heads two pointers)
    public ListNode optimized(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pointerA = headA;
        ListNode pointerB = headB;

        while (pointerA != pointerB) {
            pointerA = (pointerA == null) ? headB : pointerA.next;
            pointerB = (pointerB == null) ? headA : pointerB.next;
        }
        return pointerA;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Build a shared tail: 8 -> 4 -> 5
        ListNode c3 = new ListNode(5);
        ListNode c2 = new ListNode(4);
        c2.next = c3;
        ListNode c1 = new ListNode(8);
        c1.next = c2;

        // listA: 4 -> 1 -> (shared tail)
        ListNode a2 = new ListNode(1);
        a2.next = c1;
        ListNode a1 = new ListNode(4);
        a1.next = a2;

        // listB: 5 -> 6 -> 1 -> (shared tail)
        ListNode b3 = new ListNode(1);
        b3.next = c1;
        ListNode b2 = new ListNode(6);
        b2.next = b3;
        ListNode b1 = new ListNode(5);
        b1.next = b2;

        ListNode result1 = sol.bruteForce(a1, b1);
        System.out.println("Brute Force -> node value " + (result1 != null ? result1.val : "null"));

        ListNode result2 = sol.optimized(a1, b1);
        System.out.println("Optimized   -> node value " + (result2 != null ? result2.val : "null"));
    }
}
