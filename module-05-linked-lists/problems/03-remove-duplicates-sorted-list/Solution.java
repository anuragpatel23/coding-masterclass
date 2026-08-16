import java.util.*;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O(n) time, O(n) space (LinkedHashSet, preserves order)
    public ListNode bruteForce(ListNode head) {
        LinkedHashSet<Integer> unique = new LinkedHashSet<>();
        for (ListNode c = head; c != null; c = c.next) unique.add(c.val);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : unique) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (single pointer, compare to next)
    public ListNode optimized(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
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
        int[] values = {1, 1, 2, 3, 3};

        System.out.println("Brute Force -> " + toList(sol.bruteForce(buildList(values))));
        System.out.println("Optimized   -> " + toList(sol.optimized(buildList(values))));
    }
}
