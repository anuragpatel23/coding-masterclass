import java.util.*;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O(n) time, O(n) space (LinkedHashMap counting)
    public ListNode bruteForce(ListNode head) {
        Map<Integer, Integer> counts = new LinkedHashMap<>();
        for (ListNode c = head; c != null; c = c.next) {
            counts.merge(c.val, 1, Integer::sum);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                tail.next = new ListNode(entry.getKey());
                tail = tail.next;
            }
        }
        return dummy.next;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (dummy head + look-ahead skip)
    public ListNode optimized(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode current = head;

        while (current != null) {
            if (current.next != null && current.val == current.next.val) {
                int duplicateVal = current.val;
                while (current != null && current.val == duplicateVal) {
                    current = current.next;
                }
                prev.next = current;
            } else {
                prev = current;
                current = current.next;
            }
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
        int[] values = {1, 2, 3, 3, 4, 4, 5};

        System.out.println("Brute Force -> " + toList(sol.bruteForce(buildList(values))));
        System.out.println("Optimized   -> " + toList(sol.optimized(buildList(values))));
    }
}
