import java.util.*;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O(n) time, O(n) space (array copy + two pointers)
    public boolean bruteForce(ListNode head) {
        List<Integer> values = new ArrayList<>();
        for (ListNode c = head; c != null; c = c.next) values.add(c.val);

        int left = 0, right = values.size() - 1;
        while (left < right) {
            if (!values.get(left).equals(values.get(right))) return false;
            left++;
            right--;
        }
        return true;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (find middle, reverse, compare)
    public boolean optimized(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode prev = null;
        ListNode current = slow;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        ListNode firstHalf = head;
        ListNode secondHalf = prev;
        boolean isPalindrome = true;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                isPalindrome = false;
                break;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return isPalindrome;
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

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] values = {1, 2, 2, 1};

        System.out.println("Brute Force -> " + sol.bruteForce(buildList(values)));
        System.out.println("Optimized   -> " + sol.optimized(buildList(values)));
    }
}
