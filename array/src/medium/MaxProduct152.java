package medium;

/**
 * @program: leetcode
 * @description: Maximum Product Subarray
 * @author: Liu Hanyi
 * @create: 2019-05-07 09:40
 **/

public class MaxProduct152 {
    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
        System.out.println(new MaxProduct152().maxProduct(nums));
    }

    public int wrongMethod(int[] nums){
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (dp[i-1] * nums[i] > 0)
                dp[i] = dp[i-1] * nums[i];
            else if (dp[i-1] * nums[i] < 0)
                dp[i] = nums[i];
            else if (nums[i] > 0 && dp[i-1] == 0)
                dp[i] = nums[i];
            else if (dp[i-1] == 0 && nums[i] <= 0)
                dp[i] = dp[i-1];
        }

        int max = Integer.MIN_VALUE;
        for (int num:dp) {
            max = Math.max(num,max);
        }
        return max;
    }

    public int maxProduct(int[] nums) {
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(Math.max(dpMax[i-1]*nums[i],dpMin[i-1]*nums[i]),nums[i]);
            dpMin[i] = Math.min(Math.min(dpMax[i-1]*nums[i],dpMin[i-1]*nums[i]),nums[i]);
            res = Math.max(res,dpMax[i]);
        }
        return res;
    }

    /**
     *
     * @param A
     * @return
     */
    public int maxProduct2(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int max = A[0], min = A[0], result = A[0];
        for (int i = 1; i < A.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * A[i], min * A[i]), A[i]);
            min = Math.min(Math.min(temp * A[i], min * A[i]), A[i]);
            if (max > result) {
                result = max;
            }
        }
        return result;
    }


    /**
     * https://leetcode.com/problems/maximum-product-subarray/discuss/48302/2-Passes-scan-beats-99
     * @param nums
     * @return
     */
    public int maxProduct3(int[] nums) {
        int max = Integer.MIN_VALUE, product = 1;
        int len = nums.length;

        for(int i = 0; i < len; i++) {
            max = Math.max(product *= nums[i], max);
            if (nums[i] == 0) product = 1;
        }

        product = 1;
        for(int i = len - 1; i >= 0; i--) {
            max = Math.max(product *= nums[i], max);
            if (nums[i] == 0) product = 1;
        }

        return max;
    }
}
