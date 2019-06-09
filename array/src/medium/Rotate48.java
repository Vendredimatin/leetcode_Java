package medium;

/**
 * @program: leetcode
 * @description: leetcode 48
 * @author: Liu Hanyi
 * @create: 2019-02-12 11:52
 **/

public class Rotate48 {
    public static void main(String[] args) {
        int[][] matrix = {{5, 1, 9,11},{2, 4, 8,10},{13, 3, 6, 7},{15,14,12,16}};
        new Rotate48().rotate(matrix);
    }

    /**
     * tag:array
     * summary:之前就找到了规律了，先根据斜对称轴对折，再根据中线y轴对折，
     * 但是想的太复杂，想一口气弄完两次对折，应该像这样，一次一次弄，分别弄开
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        int mid = matrix.length/2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < mid; j++) {
                int tmp = matrix[i][j];
                int symmetricalY = n-1-j;
                matrix[i][j] = matrix[i][symmetricalY];
                matrix[i][symmetricalY] = tmp;
            }
        }

    }

}
