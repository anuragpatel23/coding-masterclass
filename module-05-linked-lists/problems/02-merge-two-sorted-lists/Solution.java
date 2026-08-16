import java.util.*;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O((n+m) log(n+m)) time, O(n+m) space
    public ListNode bruteForce(ListNode l1, ListNode l2) {
        List<Integer> values = new ArrayList<>();
        for (ListNode c = l1; c != null; c = c.next) values.add(c.val);
        for (ListNode c = l2; c != null; c = c.next) values.add(c.val);
        Collections.sort(values);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : values) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    // Approach 2: Optimized -> O(n+m) time, O(1) extra space (dummy head + merge)
    public ListNode optimized(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = (l1 != null) ? l1 : l2;
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
        int[] v1 = {1, 2, 4};
        int[] v2 = {1, 3, 4};

        System.out.println("Brute Force -> " + toList(sol.bruteForce(buildList(v1), buildList(v2))));
        System.out.println("Optimized   -> " + toList(sol.optimized(buildList(v1), buildList(v2))));
    }
}
