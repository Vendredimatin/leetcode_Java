package dpAndGreedy;

/**
 * @program: leetcode
 * @description: 买卖股票的最佳时机||
 * @author: Liu Hanyi
 * @create: 2019-04-13 20:32
 **/

public class MaxProfit {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(new MaxProfit().maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}
