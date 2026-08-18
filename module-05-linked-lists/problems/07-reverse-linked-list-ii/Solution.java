import java.util.*;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O(n) time, O(n) space (value list, new nodes)
    public ListNode bruteForce(ListNode head, int left, int right) {
        List<Integer> values = new ArrayList<>();
        for (ListNode c = head; c != null; c = c.next) values.add(c.val);

        int i = left - 1, j = right - 1;
        while (i < j) {
            Collections.swap(values, i, j);
            i++;
            j--;
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : values) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (head-insertion bounded reversal)
    public ListNode optimized(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        ListNode current = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = current.next;
            current.next = next.next;
            next.next = prev.next;
            prev.next = next;
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
        int[] values = {1, 2, 3, 4, 5};
        int left = 2, right = 4;

        System.out.println("Brute Force -> " + toList(sol.bruteForce(buildList(values), left, right)));
        System.out.println("Optimized   -> " + toList(sol.optimized(buildList(values), left, right)));
    }
}
