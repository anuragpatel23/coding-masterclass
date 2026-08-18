import java.util.*;
import java.math.BigInteger;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O(n+m) time, O(n+m) space (BigInteger conversion)
    public ListNode bruteForce(ListNode l1, ListNode l2) {
        StringBuilder sb1 = new StringBuilder();
        for (ListNode c = l1; c != null; c = c.next) sb1.append(c.val);
        StringBuilder sb2 = new StringBuilder();
        for (ListNode c = l2; c != null; c = c.next) sb2.append(c.val);

        BigInteger num1 = new BigInteger(sb1.reverse().toString());
        BigInteger num2 = new BigInteger(sb2.reverse().toString());
        String sum = num1.add(num2).toString();

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int i = sum.length() - 1; i >= 0; i--) {
            tail.next = new ListNode(sum.charAt(i) - '0');
            tail = tail.next;
        }
        return dummy.next;
    }

    // Approach 2: Optimized -> O(max(n,m)) time, O(1) extra space (carry simulation)
    public ListNode optimized(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) { sum += l1.val; l1 = l1.next; }
            if (l2 != null) { sum += l2.val; l2 = l2.next; }
            carry = sum / 10;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
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
        int[] v1 = {2, 4, 3};
        int[] v2 = {5, 6, 4};

        System.out.println("Brute Force -> " + toList(sol.bruteForce(buildList(v1), buildList(v2))));
        System.out.println("Optimized   -> " + toList(sol.optimized(buildList(v1), buildList(v2))));
    }
}
