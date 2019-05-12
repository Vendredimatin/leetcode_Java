package linkedlistAndTree;

/**
 * @program: leetcode
 * @description: 环形链表ＩＩ
 * @author: Liu Hanyi
 * @create: 2019-04-13 00:50
 **/

public class DetectCycle {
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
                    '}';
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(2);
       /* l1.next.next = new ListNode(0);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next = l1.next;*/

        System.out.println(new DetectCycle().detectCycle(l1));

    }

    /**
     * tag: two pointers,linked list
     * method1:使用龟兔赛跑的思路，可以画图进行推导
     * https://blog.csdn.net/shinanhualiu/article/details/51907269
     * 快慢指针第一次相遇时,设ｈｅａｄ到环形入口的距离为ｍ，圈的长度为ｎ，第一次相遇的位置离入口有ｋ,假设慢指针走了i步
     * 那么可以得到：
     *     2(m+k) = m + p * n + k
     *   => m =  pn - k => m = (p-1)*n + z
     *
     * method2 : 方法２可以存储每一个ｉ点，然后每走一步对已存储的点比较，直到找到相同的点
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return null;

        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (slow != fast){
            slow = slow.next;
            fast = fast.next.next;

            if (fast.next == null || fast.next.next == null) return null;
        }

        //得到此时他们第一次相遇的位置，记录下来
        ListNode counter = fast;
        slow = head;


        while (counter != slow){
            counter = counter.next;
            slow = slow.next;
        }

        return counter;
    }
}
