package linkedlistAndTree;

/**
 * @program: leetcode
 * @description: 二叉树的最近公共祖先
 * @author: Liu Hanyi
 * @create: 2019-04-13 01:31
 **/

public class LowestCommonAncestor {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/
     * 递归搜索左右子树，如果左子树和右子树都不为空，说明最近父节点一定在根节点。
     *
     * 反之，如果左子树为空，说明两个节点一定在右子树；
     *
     * 同理如果右子树为空，说明两个节点一定在左子树。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        if (left != null && right != null) return root;
        else if (left == null) return right;
        else if (right == null) return left;
        else return null;
    }
}