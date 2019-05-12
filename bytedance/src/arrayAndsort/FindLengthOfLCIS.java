package arrayAndsort;

/**
 * @program: leetcode
 * @description: 最长连续递增序列
 * @author: Liu Hanyi
 * @create: 2019-04-09 21:06
 **/

public class FindLengthOfLCIS {

    /**
     * 可以理解为贪心
     * 也可以理解为滑动窗口，每一个连续子递增序列都是一个窗口
     * tag:array
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length == 0)
            return 0;

        int max = 1;
        int curMax = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]){
                curMax++;
                max = Math.max(max,curMax);
            }else
                curMax = 1;
        }

        return max;
    }
}
