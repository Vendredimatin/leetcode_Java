package dpAndGreedy;

/**
 * @program: leetcode
 * @description: 寻找最大正方形
 * @author: Liu Hanyi
 * @create: 2019-04-13 20:41
 **/

public class MaximalSquare {
    public static void main(String[] args) {
        char[][] maxtrix = /*{{'0','0','0','0','0'},
                {'1','0','0','0','0'},
                {'0','0','0','0','0'},
                {'0','0','0','0','0'}};*/
                {{'1','0','1','0','0'},
                            {'1','0','1','1','1'},
                            {'1','1','1','1','1'},
                            {'1','0','0','1','0'}};
        System.out.println(new MaximalSquare().maximalSquare(maxtrix));
    }


    /**
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1'){
                    int maxSide = Math.min(matrix.length-i,matrix[i].length-j);
                    max = Math.max(max,getSide(matrix,i,j,maxSide));
                }
            }
        }
        return max*max;
    }

    public int getSide(char[][] matrix, int i, int j, int maxSide){
        for (int k = i; k < i+maxSide; k++) {
            for (int l = j; l < j+maxSide; l++) {
                if (matrix[k][l] == '0'){
                    int max = Math.max(k-i,l-j);
                    return getSide(matrix,i,j,max);
                }
            }
        }

        return maxSide;
    }

    /**
     *
     * dp[i][j]表示以第i行第j列为右下角所能构成的最大正方形边长, 则递推式为:
     *         dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]);
     * 太秀了这个办法
     *method2:动态规划
     */
    public int maximalSquare2(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }

    /**
     * https://leetcode.com/problems/maximal-square/solution/
     * 对ｄｐ的进一步优化
     */
}
