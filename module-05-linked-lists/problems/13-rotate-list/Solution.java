import java.util.*;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O(n) time, O(n) space (value list, new nodes)
    public ListNode bruteForce(ListNode head, int k) {
        if (head == null) return null;
        List<Integer> values = new ArrayList<>();
        for (ListNode c = head; c != null; c = c.next) values.add(c.val);

        int n = values.size();
        k = k % n;
        if (k == 0) return head;

        List<Integer> rotated = new ArrayList<>();
        rotated.addAll(values.subList(n - k, n));
        rotated.addAll(values.subList(0, n - k));

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : rotated) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (circular link, then break)
    public ListNode optimized(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        k = k % length;
        if (k == 0) return head;

        tail.next = head; // make circular
        int stepsToNewTail = length - k;
        ListNode newTail = head;
        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
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
        int k = 2;

        System.out.println("Brute Force -> " + toList(sol.bruteForce(buildList(values), k)));
        System.out.println("Optimized   -> " + toList(sol.optimized(buildList(values), k)));
    }
}
