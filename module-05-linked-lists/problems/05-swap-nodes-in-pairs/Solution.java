import java.util.*;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O(n) time, O(n) space (swap values, rebuild with new nodes)
    public ListNode bruteForce(ListNode head) {
        List<Integer> values = new ArrayList<>();
        for (ListNode c = head; c != null; c = c.next) values.add(c.val);

        for (int i = 0; i + 1 < values.size(); i += 2) {
            Collections.swap(values, i, i + 1);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : values) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (dummy head, in-place rewiring)
    public ListNode optimized(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = first.next;

            first.next = second.next;
            second.next = first;
            prev.next = second;

            prev = first;
        }
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
        int[] values = {1, 2, 3, 4};

        System.out.println("Brute Force -> " + toList(sol.bruteForce(buildList(values))));
        System.out.println("Optimized   -> " + toList(sol.optimized(buildList(values))));
    }
}
