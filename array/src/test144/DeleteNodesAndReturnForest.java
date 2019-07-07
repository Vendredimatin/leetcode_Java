package test144;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class DeleteNodesAndReturnForest {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        /*TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);*/
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);

        new DeleteNodesAndReturnForest().delNodes(root, new int[]{2,1});

    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        nextIsExist(root, res, null, to_delete);
        return res;
    }

    public boolean nextIsExist(TreeNode root, List<TreeNode> res, TreeNode parent, int[] to_delete) {
        if (root == null) return false;

        int index = -1;
        for (int i = 0; i < to_delete.length; i++) {
            if (to_delete[i] == root.val){
                index = i;
                break;
            }
        }


        TreeNode nextParent = root;
        if (index > -1) nextParent = null;
        boolean leftExist = nextIsExist(root.left, res, nextParent, to_delete);
        if (leftExist) root.left = null;
        boolean rightExist = nextIsExist(root.right, res, nextParent, to_delete);
        if (rightExist) root.right = null;

        if (parent == null && index <= -1)
            res.add(root);

        if (index > -1) return true;
        else return false;
    }
}
