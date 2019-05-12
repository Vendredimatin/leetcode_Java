package linkedlistAndTree;

/**
 * @program: leetcode
 * @description: 排序链表
 * @author: Liu Hanyi
 * @create: 2019-04-12 22:41
 **/

public class SortList {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(4);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(1);
        l1.next.next.next = new ListNode(3);
        l1.next.next.next.next = new ListNode(6);

        System.out.println(new SortList().sortList(l1));

    }

    /**
     * tag:linked list array
     * method1:从排序中吸取思想，要达到nlogn，在链表的情况下，只适用于归并排序
     * 快慢指针将整个链表分为两部分，快慢指针的标准：fast最后一次循环，不能低于倒数第三个数
     * 为什么能够达到nlogn：只经过一次循环，虽然是很多次的一次循环
     *
     * method2:将链表的数据抽取出来进行排序，然后放进链表中
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head;
        while ( fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(head2);

        return merge(left,right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        if (left == null) return right;
        if (right == null) return left;

        ListNode root = new ListNode(-1);
        ListNode p = root;
        while (left != null && right != null){
            if (left.val < right.val){
                p.next = left;
                left = left.next;
                p = p.next;
            }else {
                p.next = right;
                right = right.next;
                p = p.next;
            }
        }

        if (left != null) p.next = left;
        if (right != null) p.next = right;

        return root.next;
    }
}
