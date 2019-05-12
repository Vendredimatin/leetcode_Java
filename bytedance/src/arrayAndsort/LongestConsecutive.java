package arrayAndsort;

import java.util.HashSet;

/**
 * @program: leetcode
 * @description: 最长连续序列
 * @author: Liu Hanyi
 * @create: 2019-04-09 21:11
 **/

public class LongestConsecutive {

    /**
     * tag:array,并查集
     * method1:使用一个ｓｅｔ，对每一个数，检查是否有它的前继和后继，如果有则推进，感觉相当于滑动窗口的变种，使用remove方法可以减少遍历的数据量
     * method2:使用ｓｏｒｔ，实际上速度更快
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (int num:nums) {
            if (set.remove(num)){
                int prev = num-1;
                int next = num+1;
                while (set.remove(prev)) prev--;
                while (set.remove(next)) next++;

                res = Math.max(res, next-prev-1);
            }
        }

        return res;
    }
}
