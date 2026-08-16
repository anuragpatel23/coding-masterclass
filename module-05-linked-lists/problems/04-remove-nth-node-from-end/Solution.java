import java.util.*;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O(n) time (two passes), O(1) space
    public ListNode bruteForce(ListNode head, int n) {
        int length = 0;
        for (ListNode c = head; c != null; c = c.next) length++;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        for (int i = 0; i < length - n; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        return dummy.next;
    }

    // Approach 2: Optimized -> O(n) time (one pass), O(1) space (gap of n + dummy head)
    public ListNode optimized(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    private static ListNode buildList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : values) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    private static List<Integer> toList(ListNode head) {
        List<Integer> result = new ArrayList<>();
        for (ListNode c = head; c != null; c = c.next) result.add(c.val);
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] values = {1, 2, 3, 4, 5};
        int n = 2;

        System.out.println("Brute Force -> " + toList(sol.bruteForce(buildList(values), n)));
        System.out.println("Optimized   -> " + toList(sol.optimized(buildList(values), n)));
    }
}
