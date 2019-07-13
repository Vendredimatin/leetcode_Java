package hard;

import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class MergeKLists23 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);

        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);

        new MergeKLists23().mergeKLists1(new ListNode[]{head1,head2,head3});
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = helper(lists, 0, lists.length - 1);
        return res;
    }

    private ListNode helper(ListNode[] lists, int i, int j) {
        if (i == j) return lists[i];

        int mid = (i + j) / 2;
        ListNode left = helper(lists, i, mid);
        ListNode right = helper(lists, mid + 1, j);
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


    public ListNode mergeKLists1(ListNode[] lists) {
        //为了降低排序的时间，使用优先队列
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (ListNode node: lists) {
            while (node != null){
                queue.add(node.val);
                node = node.next;
            }
        }

        ListNode res = new ListNode(0);
        ListNode p = res;

        while (!queue.isEmpty()){
            p.next = new ListNode(queue.poll());
            p = p.next;
        }

        return res.next;
    }
}
