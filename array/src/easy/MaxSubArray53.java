package easy;

public class MaxSubArray53 {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new MaxSubArray53().maxSubArray3(nums));
    }

    /**
     * tag:Array,divide and Conquer,dynamic programming
     * 方法一：贪心，每加一个数都比较大小，当和小于0时，已经没必要继续加了，这也保证了最大子数组是连续的
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i];
            max = Math.max(max, cur);

            if (cur <= 0)
                cur = 0;

        }

        return max;
    }

    /**
     * 方法二：动态规划，dp[i]表示以nums[i]结尾的最大子数组和，所以dp[i] = (dp[i-1] + nums[i],nums[i])
     * 而不是表示为dp[i,j]表示从i到j的最大子数组，有两个参数的时候更灵活，计算更复杂，而dp[i]只需考虑是dp[i-1]大还是dp[i-1]+nums[i]大
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(dp[i], max);
        }

        return max;
    }

    /**
     * 方法三：分治法
     * 参考https://www.geeksforgeeks.org/maximum-subarray-sum-using-divide-and-conquer-algorithm/
     * 将一个数组分为两份，那么最大子数组只能从三个地方得到
     * 1、在左边的最大子数组
     * 2、在右边的最大子数组
     * 3、穿过中间节点的最大子数组
     * @param nums
     * @return
     */
    public int maxSubArray3(int[] nums){
        return maxSubArraySum(nums,0,nums.length-1);
    }

    private int maxSubArraySum(int[] nums, int l, int h){
        if (l == h)
            return nums[l];

        int m = (l+h)/2;

        return Math.max(Math.max(maxSubArraySum(nums,l,m),maxSubArraySum(nums,m+1,h)),
                crossMidSum(nums,l,m,h));
    }

    private int crossMidSum(int[] nums,int l, int m, int h){
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = m; i >= l; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum,sum);
        }

        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        for (int i = m+1; i <= h; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum,sum);
        }

        return leftSum+rightSum;
    }
}
