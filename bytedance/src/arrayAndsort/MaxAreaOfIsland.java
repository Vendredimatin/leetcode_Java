package arrayAndsort;

/**
 * @program: leetcode
 * @description: leetcode 岛屿最大面积
 * @author: Liu Hanyi
 * @create: 2019-04-09 19:54
 **/

public class MaxAreaOfIsland {


    public static void main(String[] args) {
        int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        System.out.println(new MaxAreaOfIsland().maxAreaOfIsland(grid));
    }

    /**
     * tag:array,dps
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        boolean[][] isCaculated = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                max = Math.max(max, getArea(i, j, grid,isCaculated));
            }
        }

        return max;
    }

    public int getArea(int i, int j, int[][] grid, boolean[][] isCaculated) {
        if (grid[i][j] == 0 || isCaculated[i][j])
            return 0;

        int area = 1;
        isCaculated[i][j] = true;

        area += (i == 0) ? 0 : getArea(i - 1, j, grid,isCaculated);
        area += (i == grid.length - 1) ? 0 : getArea(i + 1, j, grid,isCaculated);
        area += (j == 0) ? 0 : getArea(i, j - 1, grid,isCaculated);
        area += (j == grid[i].length - 1) ? 0 : getArea(i, j + 1, grid,isCaculated);

        return area;
    }
}
