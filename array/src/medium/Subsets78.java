package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * tag: Array
 * @program: leetcode
 * @description: leetcode 78
 * @author: Liu Hanyi
 * @create: 2019-02-08 17:27
 **/

public class Subsets78 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(new Subsets78().subsets4(nums));
    }

    public List<List<Integer>> m1(int[] nums){
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(nums[i]);
            res.add(tmp);
            recursive(res, new ArrayList<>(tmp), nums, i);
        }

        return res;
    }

    private void recursive(List<List<Integer>> res, List<Integer> tmp, int[] nums, int i) {
        if (i == nums.length-1)
            return;

        for (int j = i+1; j < nums.length; j++) {
            List<Integer> newTmp = new ArrayList<>(tmp);
            newTmp.add(nums[j]);
            res.add(newTmp);
            recursive(res,newTmp, nums, j);
        }



    }


    /**
     * tag: array, backtracking, bit manipulation
     * 方法一，使用回溯/递归的办法，list中每新添加一个数，就将它加入结果集中
     * subsets里面的循环是寻找递归的起点
     * find里面的循环是寻找第n层递归的起点，依次从前往后添加元素，以避免重复，第n层递归时，添加长度为n的子集
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        res.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            res.add(list);
            find(list,nums,i+1,res);
        }

        return res;
    }

    public void find(List<Integer> list,int[] nums, int start,List<List<Integer>> res){
        if (start == nums.length)
            return;

        for (int i = start; i < nums.length; i++) {
            List<Integer> newTmp = new ArrayList<>(list);
            newTmp.add(nums[i]);
            res.add(newTmp);
            find(new ArrayList<>(newTmp),nums,i+1,res);
        }
    }


    /**
     * 对解法一的优化，这样就少些声明，少用些空间并且简洁，和permutation一样，remove新添加的元素以避免创建新的数组
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }


    /**
     * 迭代做法：遍历数组，每次遍历都在前一次迭代的每一个结果后面加上当前遍历的数，当然，加之前要保留之前迭代的每一个结果
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());

        for (int num:nums) {
            int n = list.size();
            for (int i = 0; i < n; i++) {
                List<Integer> newList = new ArrayList<>(list.get(i));
                newList.add(num);
                list.add(newList);
            }
        }

        return list;
    }

    /**
     * 位操作，没太看得懂，留着
     *https://leetcode.com/problems/subsets/discuss/27278/C%2B%2B-RecursiveIterativeBit-Manipulation
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets4(int[] nums) {
        int n = nums.length;
        List<List<Integer>> subsets = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, n); i++)
        {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++)
            {
                int tmp = (i >> j);
                int res = tmp & 1;
                if (res != 0)
                    subset.add(nums[j]);
            }
            Collections.sort(subset);
            subsets.add(subset);
        }
        return subsets;
    }
}
