package medium;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class RemoveNthFromEnd19 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(new RemoveNthFromEnd19().removeNthFromEnd(head, 2));
    }

    /**
     * 一次遍历的话，使用快慢指针的思想
     * 要考虑清楚所有的情况
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;

        ListNode p = head;
        ListNode q = head;

        int i = 0;
        while (i++ < n) p = p.next;

        // n 很大时， p可能为null
        if (p == null) return head.next;

        while (p.next != null) {
            p = p.next;
            q = q.next;
        }

        q.next = q.next.next;

        return head;
    }

    /**
     * 引入头节点，就不需要考虑一些额外的情况
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}
