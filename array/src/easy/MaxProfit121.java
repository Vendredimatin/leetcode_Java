package easy;

public class MaxProfit121 {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(new MaxProfit121().maxProfit1(prices));
    }

    /**
     * minPrice记录最小值，遍历的过程中每个值都减最小值看看能不能得到更大的利润，贪心
     * summary：还是找山峰山谷的思想
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice,prices[i]);
            maxProfit = Math.max(maxProfit,prices[i] - minPrice);
        }

        return maxProfit;
    }


}
