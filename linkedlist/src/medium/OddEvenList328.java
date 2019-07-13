package medium;


/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class OddEvenList328 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(1);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(7);
        System.out.println(new OddEvenList328().oddEvenList(head));
    }

    /**
     * 每当是奇数节点，那么就放到第一个节点之后，自然后面剩下的就是偶数节点
     * 只关注处理一类，当处理完后，另一类也会自然而然地被处理
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        ListNode odd = head;
        ListNode prev = head;
        ListNode p = head.next;
        int n = 2;

        while (p != null){
            if (n % 2 != 0){
                ListNode tmp = odd.next;
                prev.next = p.next;
                odd.next = p;
                p.next = tmp;
                p = prev.next;
                odd = odd.next;
            }else{
                p = p.next;
                prev = prev.next;
            }
            n++;
        }

        return head;

    }

    /**
     *  新起一个偶数节点，当是偶数项节点时，就接到偶书链表的末尾，最后将整个偶数链表接到奇数链表之后
     *  因为是链表，重起一个链表，最后再合并，不会产生额外的空间时间cost
     * @param head
     * @return
     */
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
