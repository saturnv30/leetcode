class Solution {
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int perimeter = 0;

        // 四个方向：上、下、左、右
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // 只看陆地格子
                if (grid[r][c] != 1) {
                    continue;
                }

                // 检查这块陆地的四条边
                for (int[] dir : directions) {
                    int neighborRow = r + dir[0];
                    int neighborCol = c + dir[1];

                    boolean outOfBounds = neighborRow < 0 || neighborRow >= rows
                            || neighborCol < 0 || neighborCol >= cols;

                    // 邻居是边界或水，这条边就属于周长
                    if (outOfBounds || grid[neighborRow][neighborCol] == 0) {
                        perimeter++;
                    }
                }
            }
        }

        return perimeter;
    }
}
// 复杂度： 时间 O(m × n)，每个格子只看一次，每次检查 4 个邻居；空间 O(1)，只用了几个变量，不修改原数组，也没有递归。

// 一句话讲解思路： 周长由"陆地挨着水或边界"的边组成，所以遍历每块陆地，数它四周有几个方向是水或出界，加起来就是答案。