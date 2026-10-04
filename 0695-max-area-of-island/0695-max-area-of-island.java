class Solution {
    // 四个方向：上、下、左、右
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;

        // 遍历每个格子，遇到没访问过的陆地，就说明发现了一个新岛屿
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    int area = dfs(grid, row, col);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    // 返回从 (row, col) 出发能访问到的陆地格子数，并把访问过的格子标记为 0
    private int dfs(int[][] grid, int row, int col) {
        // 越界，或者是水 / 已访问过，贡献面积为 0
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0) {
            return 0;
        }

        // 先标记为已访问，再递归，防止在相邻格子之间来回递归
        grid[row][col] = 0;

        // 当前格子算 1，再加上四个方向能扩展到的面积
        int area = 1;
        for (int[] direction : DIRECTIONS) {
            area += dfs(grid, row + direction[0], col + direction[1]);
        }
        return area;
    }
}