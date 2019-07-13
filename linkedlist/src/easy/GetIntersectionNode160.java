package easy;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class GetIntersectionNode160 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 如果不对s和l初始化，那么当遇到两个链表长度不一致时，会出现问题
     * 思考一个问题的时候，应该考虑清楚它的所有状况
     * 总结时考虑每个题型的所有状况！！！！！！！
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != null && pB !=null){
            pA = pA.next;
            pB = pB.next;
        }

        ListNode p = null;
        ListNode s = headA;
        ListNode l = headB;
        if (pA != null){
            p = pA;
            l = headA;
            s = headB;
        }
        else if (pB != null){
            p =pB;
            l = headB;
            s = headA;
        }

        while (p != null){
            p = p.next;
            l = l.next;
        }

        while (l != s){
            l = l.next;
            s = s.next;
        }

        return l;



    }


    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        int lenA = length(headA), lenB = length(headB);
        // move headA and headB to the same start point
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenA < lenB) {
            headB = headB.next;
            lenB--;
        }
        // find the intersection until end
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private int length(ListNode node) {
        int length = 0;
        while (node != null) {
            node = node.next;
            length++;
        }
        return length;
    }
}
