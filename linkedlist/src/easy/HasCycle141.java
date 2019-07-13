package easy;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class HasCycle141 {
    private class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
    public boolean hasCycle(ListNode head) {
        if(head == null|| head.next == null)
            return false;

        ListNode fast = head;
        ListNode slow = head;

        do{
            fast = fast.next.next;
            slow = slow.next;
        }while (fast.next != null && fast.val != slow.val);

        if (fast.next.next == null || fast.val != slow.val)
            return false;

        return true;
    }
}
