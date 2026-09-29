class Solution {
    // 四个方向：上、下、左、右
    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private int rows, cols;
    private int[][] matrix;
    // memo[r][c] = 从 (r, c) 出发的最长递增路径长度；0 表示还没算过
    private int[][] memo;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.memo = new int[rows][cols];

        // 每个格子都可能是起点，逐个尝试，取最大值
        int longest = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                longest = Math.max(longest, dfs(r, c));
            }
        }
        return longest;
    }

    // 返回从 (r, c) 出发的最长递增路径长度
    private int dfs(int r, int c) {
        // 算过就直接返回，保证每个格子只计算一次
        if (memo[r][c] != 0) {
            return memo[r][c];
        }

        // 至少包含自己，长度为 1
        int maxLength = 1;

        for (int[] dir : DIRECTIONS) {
            int nextR = r + dir[0];
            int nextC = c + dir[1];

            // 邻居在界内，且严格更大，才能继续往下走
            if (nextR >= 0 && nextR < rows && nextC >= 0 && nextC < cols
                    && matrix[nextR][nextC] > matrix[r][c]) {
                maxLength = Math.max(maxLength, 1 + dfs(nextR, nextC));
            }
        }

        memo[r][c] = maxLength;
        return maxLength;
    }
}
// 时间 O(mn)：每个格子只完整计算一次，每次看 4 个邻居。空间 O(mn)：memo 数组占 mn，递归栈最坏深度也是 mn，也就是一条蛇形递增路径穿过所有格子的情况。