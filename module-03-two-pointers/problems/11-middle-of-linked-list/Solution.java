import java.util.*;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O(n) time (two passes), O(1) space
    public ListNode bruteForce(ListNode head) {
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        current = head;
        for (int i = 0; i < length / 2; i++) {
            current = current.next;
        }
        return current;
    }

    // Approach 2: Optimized -> O(n) time (single pass), O(1) space (fast/slow pointers)
    public ListNode optimized(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static ListNode buildList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int v : values) {
            current.next = new ListNode(v);
            current = current.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] values = {1, 2, 3, 4, 5, 6};

        ListNode list1 = buildList(values);
        System.out.println("Brute Force -> node value " + sol.bruteForce(list1).val);

        ListNode list2 = buildList(values);
        System.out.println("Optimized   -> node value " + sol.optimized(list2).val);
    }
}
