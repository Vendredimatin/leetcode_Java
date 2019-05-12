package medium;

import java.util.Arrays;

/**
 * @program: leetcode
 * @description: leetcode 289
 * @author: Liu Hanyi
 * @create: 2019-02-12 17:06
 **/

public class GameOfLife289 {
    public static void main(String[] args) {
        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        new GameOfLife289().gameOfLife(board);
    }

    /**
     *
     * tag:array 使用有限状态机的思想
     * 使用一个dp数组，记录每个元素的活邻居
     * 1.少于两个活邻居的活细胞将死去
     * 2.有两个活邻居或三个活邻居的活细胞能够继续活下去
     * 3.任何有3三个以上的活邻居的活细胞将死去
     * 4.有三个活邻居的死细胞能够复活
     * @param board
     */
    public void gameOfLife(int[][] board) {
        int[][] dp = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dp[i][j] += searchLive(board,i-1,j-1)+searchLive(board,i-1,j)+searchLive(board,i-1,j+1);
                dp[i][j] += searchLive(board,i,j-1) + searchLive(board,i,j+1);
                dp[i][j] += searchLive(board,i+1,j-1)+searchLive(board,i+1,j)+searchLive(board,i+1,j+1);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //live cell
                if (board[i][j] == 1){
                    if (dp[i][j] < 2)
                        board[i][j] = 0;
                    else if (dp[i][j] > 3)
                        board[i][j] = 0;
                }else{
                    if (dp[i][j] == 3)
                        board[i][j] = 1;
                }
            }
        }

        System.out.println(Arrays.toString(board));
    }

    public int searchLive(int[][] dp, int i, int j){
        if (i == -1 || j == -1 || i == dp.length || j == dp[0].length)
            return 0;

        if (dp[i][j] == 0)
            return 0;
        else return 1;
    }

    /**
     * https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation
     * 更优秀的解法，通过位操作，解决了O（n）的空间问题
     * 因为总共就四种状态0-》0（00），0-》1（10），1-》0（01），1-》1（11）
     * 只需考虑10和11,即复活和保持生存，对应于十进制分别是2和3
     * 巧妙地将原本的0和1值与代表转换的四个状态的值对应起来，非常smart
     */
    public void gameOfLife2(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = liveNeighbors(board, m, n, i, j);

                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when will the 2nd bit become 1.
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }
    }

    public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }
}

