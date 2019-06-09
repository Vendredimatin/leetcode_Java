package test140;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class InsufficientNodesinRoottoLeafPaths {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, -99, -99, 7, 8, 9, -99, -99, 12, 13, -99, 14};
        InsufficientNodesinRoottoLeafPaths insufficientNodesinRoottoLeafPaths = new InsufficientNodesinRoottoLeafPaths();
        TreeNode root = insufficientNodesinRoottoLeafPaths.initBinTree(arr);
        root = insufficientNodesinRoottoLeafPaths.sufficientSubset(root, 1);
        System.out.println(root);
    }

    public TreeNode initBinTree(int[] arr) {
        if (arr.length == 1) {
            return new TreeNode(arr[0]);
        }
        List<TreeNode> nodeList = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            nodeList.add(new TreeNode(arr[i]));
        }
        int temp = 0;
        while (temp <= (arr.length - 2) / 2) {  //注意这里，数组的下标是从零开始的
            if (2 * temp + 1 < arr.length) {
                nodeList.get(temp).left = nodeList.get(2 * temp + 1);
            }
            if (2 * temp + 2 < arr.length) {
                nodeList.get(temp).right = nodeList.get(2 * temp + 2);
            }
            temp++;
        }
        return nodeList.get(0);
    }

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        TreeNode p = root;
        int sum = 0;
        boolean res = backtrack(p, sum, limit);
        if (!res) root = null;
        return root;
    }

    private boolean backtrack(TreeNode p, int sum, int limit) {
        if (p == null) return false;
        if (p.left == null && p.right == null){
            sum += p.val;
            if (sum >= limit)
                return true;
            else return false;
        }
        sum += p.val;
        boolean resLeft = backtrack(p.left, sum, limit);
        if (!resLeft) p.left = null;
        boolean resRight = backtrack(p.right, sum, limit);
        if (!resRight) p.right = null;

        boolean res = resRight | resLeft;
        return res;

    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
