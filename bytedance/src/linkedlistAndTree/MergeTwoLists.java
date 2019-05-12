package linkedlistAndTree;

/**
 * @program: leetcode
 * @description: 合并两个有序链表
 * @author: Liu Hanyi
 * @create: 2019-04-12 16:21
 **/

public class MergeTwoLists {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        System.out.println(new MergeTwoLists().mergeTwoLists(l1,l2));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode last = res;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                last.next = l1;
                l1 = l1.next;
                last = last.next;
            } else {
                last.next = l2;
                l2 = l2.next;
                last = last.next;
            }
        }

        if (l1 == null)
            last.next = l2;
        else last.next = l1;

        return res.next;
    }
}
