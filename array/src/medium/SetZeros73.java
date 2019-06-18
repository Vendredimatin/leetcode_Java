package medium;

import java.util.Arrays;

/**
 * @program: leetcode
 * @description: Set Matrixs Zeros
 * @author: Liu Hanyi
 * @create: 2019-04-30 09:25
 **/

public class SetZeros73 {
    public static void main(String[] args) {
        int[][] matrix = {{1,1,1},
                {1,0,1},
                {1,1,1}};
        new SetZeros73().setZeroes(matrix);
    }

    public void setZeroes(int[][] matrix) {
        boolean isRowZero = false;
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) isRowZero = true;
        }

        boolean isColZero = false;
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[j][0] == 0) isColZero = true;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0){
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (isRowZero){
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if (isColZero){
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

    }
}
