package dpAndGreedy;

/**
 * @program: leetcode
 * @description: 最大子序和
 * @author: Liu Hanyi
 * @create: 2019-04-13 21:27
 **/

public class MaxSubArray {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new MaxSubArray().maxSubArray(nums));
    }

    /**
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];

            maxSum = Math.max(maxSum,curSum);

            if (curSum <= 0)
                curSum = 0;
        }

        return maxSum;
    }
}
