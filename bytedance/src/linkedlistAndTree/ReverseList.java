package linkedlistAndTree;

/**
 * @program: leetcode
 * @description: 反转链表
 * @author: Liu Hanyi
 * @create: 2019-04-12 16:37
 **/

public class ReverseList {
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
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
       // head.next.next = new ListNode(3);
       // head.next.next.next = new ListNode(4);
       // head.next.next.next.next = new ListNode(5);
        System.out.println(new ReverseList().reverseList3(head));
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode res = null;

        while (head.next != null){
            ListNode l1 = head;
            ListNode l2 = head.next;
            head = head.next.next;

            l1.next = res;
            l2.next = l1;
            res = l2;

            if (head == null) return res;
        }

        head.next = res;
        res = head;
        return res;
    }

    public ListNode reverseList1(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null){
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        return pre;
    }

    public ListNode reverseList2(ListNode head){
        ListNode pre = null;
        ListNode cur = head;

        return reverseList(pre,cur);
    }

    public ListNode reverseList(ListNode pre,ListNode cur){
        if (cur == null)
            return pre;
        else {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
            reverseList(pre,cur);
        }

        return null;
    }

    /**
     * 第二种递归
     * @param head
     * @return
     */
    public ListNode reverseList４(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    /**
     * 插入头节点法
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head){
        ListNode res = new ListNode(-1);
        ListNode p = head;

        while (p != null){
            ListNode tmp = p.next;
            p.next = res.next;
            res.next = p;
            p = tmp;
        }

        return res.next;
    }

}
