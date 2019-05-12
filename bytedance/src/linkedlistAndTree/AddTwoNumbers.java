package linkedlistAndTree;

/**
 * @program: leetcode
 * @description: 两数相加
 * @author: Liu Hanyi
 * @create: 2019-04-12 21:31
 **/

public class AddTwoNumbers {
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
        ListNode l1 = new ListNode(5);
        // l1.next = new ListNode(4);
        // l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        // l2.next = new ListNode(6);
        // l2.next.next = new ListNode(4);

        System.out.println(new AddTwoNumbers().addTwoNumbers2(l1, l2));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode l3 = new ListNode(-1);
        ListNode p = l3;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            p.next = new ListNode(sum % 10);
            carry = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
            p = p.next;
        }

        ListNode tmp = null;
        if (l1 != null) tmp = l1;
        else if (l2 != null) tmp = l2;

        while (tmp != null) {
            int sum = tmp.val + carry;
            p.next = new ListNode(sum % 10);
            carry = sum / 10;
            tmp = tmp.next;
            p = p.next;
        }

        if (carry != 0) {
            p.next = new ListNode(carry);
        }

        return l3.next;
    }

    /**
     * 优化后更简略
     * 可以将条件一起放进ｗｈｉｌｅ中的，但是要多出一些判断
     * @param l1
     * @param l2
     * @return
     */
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
