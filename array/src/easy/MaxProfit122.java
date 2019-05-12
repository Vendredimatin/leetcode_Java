package easy;

public class MaxProfit122 {
    public static void main(String[] args) {
        int[] prices = {1,2,3,4,5};
        System.out.println(new MaxProfit122().maxProfit(prices));
    }

    /**
     * tag: array,greedy
     * 方法一：采用贪心，minPrice记录遍历过程中出现的最小值，maxPrice记录遍历过程中出现的最大值
     * curPrice记录遍历过程中出现的最大利润，如果maxPrice>当前出售价格，那么就应该卖了，此时curPrice记录的也是maxPrice-minPrice的值
     * 采取的贪心策略就是每当出现峰值时，便卖出
     *
     * summary：
     * 1.采用贪心，在最小值后面紧找最大值，参考方法二
     * 2.在遍历过程中记录结果，也是贪心，参考方法一
     * 3.考虑有限状态转换机的思想，考虑每一个步骤都有哪些状态，参考方法四
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices.length == 0)
            return 0;

        int maxProfit = 0;
        int curProfit = 0;
        int minPrice = prices[0];
        int maxPrice = prices[0];

        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice,prices[i]);
            maxPrice = Math.max(maxPrice,prices[i]);
            curProfit = Math.max(curProfit,prices[i] - minPrice);

            if (maxPrice >= prices[i]){
                maxProfit += curProfit;
                curProfit = 0;
                minPrice = prices[i];
                maxPrice = prices[i];
            }
        }

        return maxProfit;
    }

    /**
     * 方法二： Peak Valley Approach，找紧跟谷底值之后的峰值，将每一次的差值加起来就是最大利润，通过陪图解释
     * 可以知道，当每次skip峰值想找到更大的峰值时，往往得到的利润其实更小
     * 所以该算法先找谷底值，在其基础上往后遍历找峰值
     */
    public int maxProfit1(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            maxprofit += peak - valley;
        }
        return maxprofit;
    }

    public int maxProfit2(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

    /**
     * 方法四：动态规划
     */
}
