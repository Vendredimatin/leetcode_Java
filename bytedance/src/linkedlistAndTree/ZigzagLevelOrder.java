package linkedlistAndTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: leetcode
 * @description: 二叉树的锯齿形遍历
 * @author: Liu Hanyi
 * @create: 2019-04-13 02:08
 **/

public class ZigzagLevelOrder {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        TreeNode t1 = new TreeNode(20);
        t1.left = new TreeNode(15);
        t1.right = new TreeNode(7);
        treeNode.right = t1;
        System.out.println(new ZigzagLevelOrder().zigzagLevelOrder(treeNode));
    }

    /**
     * tag:stack,tree,Breadth-First-Search
     * method1: 使用头插入法，当正序的时候正常添加，当逆序的时候头插入法
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        boolean leftToRight = true;
        queue.offer(root);
        queue.offer(null);

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode p = queue.poll();
            if (p == null){
                if (list.size() == 0)
                    break;

                res.add(list);
                list = new ArrayList<>();
                leftToRight = !leftToRight;
                queue.offer(null);
            }else {
                if (leftToRight)
                    list.add(p.val);
                else list.add(0,p.val);
                if (p.left != null) queue.offer(p.left);
                if (p.right != null)queue.offer(p.right);
            }
        }

        return res;
    }
}
