package medium;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
@SuppressWarnings("Duplicates")
public class AddTwoNumbers2 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(-1);
        int carry = 0;

        ListNode p = l3;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = ((l1 == null) ? 0 : l1.val) + ((l2 == null) ? 0 : l2.val) + carry;
            p.next = new ListNode(sum % 10);
            carry = sum / 10;
            p = p.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return l3.next;
    }
}
