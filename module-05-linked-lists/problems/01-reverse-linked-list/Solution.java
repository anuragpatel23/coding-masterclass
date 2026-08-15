import java.util.*;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O(n) time, O(n) space (new nodes from a reversed value list)
    public ListNode bruteForce(ListNode head) {
        List<Integer> values = new ArrayList<>();
        for (ListNode c = head; c != null; c = c.next) values.add(c.val);
        Collections.reverse(values);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : values) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (iterative in-place reversal)
    public ListNode optimized(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
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

        System.out.println("Brute Force -> " + toList(sol.bruteForce(buildList(values))));
        System.out.println("Optimized   -> " + toList(sol.optimized(buildList(values))));
    }
}
