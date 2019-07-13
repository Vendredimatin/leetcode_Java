package easy;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class IsPalindrome234 {
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
        //head.next.next = new ListNode(2);
        //head.next.next.next = new ListNode(1);
        System.out.println(new IsPalindrome234().isPalindrome(head));
    }

    /**
     *  为了达到O（1）space，又要比较回文，那么只能将后半段reverse，因为reverse不需要更多的space
     *  用n来判断是个煞笔行为
     *  应该判断fast是否为null
     *  快慢指针下，如果fast.next == null ,fast != null
     *  此时说明链表中有奇数个节点
     *  如果fast == null 说明链表中有偶数个节点
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        if (head.next == null) return true;

        ListNode fast = head;
        ListNode slow = head;
        // 记住快慢指针范式
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null) slow = slow.next;

        //reverse
        ListNode prev = slow;
        ListNode cur = slow.next;
        prev.next = null;
        while (cur != null){
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }

        // compare prev and head
        while (prev != null){
            if (prev.val != head.val)
                return false;
            prev = prev.next;
            head = head.next;
        }

        return true;

    }
}
