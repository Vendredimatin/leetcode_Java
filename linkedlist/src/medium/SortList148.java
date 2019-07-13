package medium;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
@SuppressWarnings("Duplicates")
public class SortList148 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        ListNode res = new SortList148().sortList(head);
        System.out.println(res);
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode newNode = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(newNode);

        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode head = new ListNode(0);
        ListNode p = head;

        while (left != null && right != null){
            if (left.val > right.val){
                p.next = right;
                right = right.next;
            }else {
                p.next = left;
                left = left.next;
            }
            p = p.next;
        }

        if (left != null){
            p.next = left;
        }else p.next = right;

        return head.next;
    }
}
