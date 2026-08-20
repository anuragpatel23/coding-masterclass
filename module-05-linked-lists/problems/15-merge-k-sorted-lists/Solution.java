import java.util.*;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Approach 1: Brute Force -> O(N log N) time, O(N) space (collect, sort, rebuild)
    public ListNode bruteForce(ListNode[] lists) {
        List<Integer> values = new ArrayList<>();
        for (ListNode list : lists) {
            for (ListNode c = list; c != null; c = c.next) values.add(c.val);
        }
        Collections.sort(values);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : values) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

    // Approach 2: Optimized -> O(N log k) time, O(1) extra space (divide and conquer)
    public ListNode optimized(ListNode[] lists) {
        if (lists.length == 0) return null;

        List<ListNode> current = new ArrayList<>(Arrays.asList(lists));
        while (current.size() > 1) {
            List<ListNode> merged = new ArrayList<>();
            for (int i = 0; i < current.size(); i += 2) {
                ListNode l1 = current.get(i);
                ListNode l2 = (i + 1 < current.size()) ? current.get(i + 1) : null;
                merged.add(mergeTwoLists(l1, l2));
            }
            current = merged;
        }
        return current.get(0);
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
        ListNode[] lists1 = {buildList(new int[]{1,4,5}), buildList(new int[]{1,3,4}), buildList(new int[]{2,6})};
        System.out.println("Brute Force -> " + toList(sol.bruteForce(lists1)));

        ListNode[] lists2 = {buildList(new int[]{1,4,5}), buildList(new int[]{1,3,4}), buildList(new int[]{2,6})};
        System.out.println("Optimized   -> " + toList(sol.optimized(lists2)));
    }
}
