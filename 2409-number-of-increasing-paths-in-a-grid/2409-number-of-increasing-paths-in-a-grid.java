class Solution {
    private static final int MOD = 1_000_000_007;
    // 四个方向：上、下、左、右
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int[][] grid;
    private int rows, cols;
    // pathCount[r][c] = 从 (r, c) 出发的严格递增路径数；0 表示还没算过
    private int[][] pathCount;

    public int countPaths(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.pathCount = new int[rows][cols];

        // 按起点分类：总路径数 = 每个格子出发的路径数之和
        long total = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                total += dfs(r, c);
            }
        }
        return (int) (total % MOD);
    }

    // 返回从 (r, c) 出发的严格递增路径数
    private int dfs(int r, int c) {
        // 已经算过，直接返回
        if (pathCount[r][c] != 0) {
            return pathCount[r][c];
        }

        // 只包含自己的那一条路径
        long count = 1;

        // 走向更大的邻居，接上从邻居出发的所有路径
        for (int[] dir : DIRECTIONS) {
            int nextR = r + dir[0];
            int nextC = c + dir[1];
            boolean inBounds = nextR >= 0 && nextR < rows && nextC >= 0 && nextC < cols;
            if (inBounds && grid[nextR][nextC] > grid[r][c]) {
                count += dfs(nextR, nextC);
            }
        }

        // 存入记忆化数组
        pathCount[r][c] = (int) (count % MOD);
        return pathCount[r][c];
    }
}
// 每个格子只完整计算一次，每次看 4 个邻居，时间 O(mn)；记忆化数组 O(mn)，递归栈最坏是一条贯穿全图的递增链，也是 O(mn)，所以空间 O(mn)