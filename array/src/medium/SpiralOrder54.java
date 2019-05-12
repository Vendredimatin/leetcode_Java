package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: leetcode
 * @description: Spiral Matrix
 * @author: Liu Hanyi
 * @create: 2019-05-06 17:24
 **/

public class SpiralOrder54 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9,10,11,12}
        };

        System.out.println(new SpiralOrder54().spiralOrder(matrix));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        boolean right = true, down = false, left = false, up = false;
        boolean[][] visit = new boolean[matrix.length][matrix[0].length];

        List<Integer> list = new ArrayList<>();
        int total = matrix.length * matrix[0].length;
        int i = 0, j = 0;
        while (true) {
            if (total == 0)
                break;

            if (right) {
                if (j == matrix[i].length || visit[i][j]) {
                    right = false;
                    down = true;
                    i++;
                    j--;
                    continue;
                } else {
                    list.add(matrix[i][j]);
                    visit[i][j] = true;
                    j++;
                }
            }

            if (down) {
                if (i == matrix.length || visit[i][j]) {
                    down = false;
                    left = true;
                    i--;
                    j--;
                    continue;
                } else {
                    list.add(matrix[i][j]);
                    visit[i][j] = true;
                    i++;
                }
            }

            if (left) {
                if (j == -1 || visit[i][j]) {
                    left = false;
                    up = true;
                    j++;
                    i--;
                    continue;
                } else {
                    list.add(matrix[i][j]);
                    visit[i][j] = true;
                    j--;
                }
            }

            if (up) {
                if (i == -1 || visit[i][j]) {
                    up = false;
                    right = true;
                    i++;
                    j++;
                    continue;
                } else {
                    list.add(matrix[i][j]);
                    visit[i][j] = true;
                    i--;
                }
            }

            total--;
        }

        return list;
    }
}
