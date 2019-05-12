package medium;

/**
 * @program: leetcode
 * @description: leetcode 62
 * @author: Liu Hanyi
 * @create: 2019-02-12 16:55
 **/

public class UniquePaths62 {
    public static void main(String[] args) {
        System.out.println(new UniquePaths62().uniquePaths(7,3));
    }

    /**
     * tag:array,dp
     * summary:很显然就使用dp，因为下一步的解决方案显然等于经过right的上一步的解决方案+经过down的上一步解决方案
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i != 0)
                    dp[i][j] += dp[i-1][j];
                if (j != 0)
                    dp[i][j] += dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }
}
