package medium;

/**
 * @program: leetcode
 * @description: Word Search
 * @author: Liu Hanyi
 * @create: 2019-05-06 09:38
 **/

public class Exist79 {
    public static void main(String[] args) {
        char[][] board = {
                {'C', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'}
//                {'A', 'B', 'C', 'E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}
        };
        System.out.println('C' ^ 256);
        System.out.println(new Exist79().exist(board, "AAB"));
    }

    boolean[][] visit;

    public boolean exist(char[][] board, String word) {
        visit = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (exist2(board, word, 0, i, j))
                        return true;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, String word, int index, int i, int j) {
        if (index == word.length())
            return true;

        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || visit[i][j] || board[i][j] != word.charAt(index) || visit[i][j])
            return false;

        visit[i][j] = true;
        if (exist(board, word, index + 1, i + 1, j) || exist(board, word, index + 1, i, j + 1) || exist(board, word, index + 1, i - 1, j) || exist(board, word, index + 1, i, j - 1))
            return true;

        visit[i][j] = false;
        return false;
    }

    private boolean exist2(char[][] board, String word, int index, int y, int x) {
        if (index == word.length()) return true;
        if (y < 0 || x < 0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word.charAt(index)) return false;
        board[y][x] ^= 256;
        boolean exist = exist(board, word, index + 1, y, x + 1)
                || exist(board, word, index + 1, y, x - 1)
                || exist(board, word,index+1,y + 1, x)
                || exist(board, word,index+1,y - 1, x);
        board[y][x] ^= 256;
        return exist;
    }
}
