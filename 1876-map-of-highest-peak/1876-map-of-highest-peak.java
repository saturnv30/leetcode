class Solution {
    public int[][] highestPeak(int[][] g) {
        // g 即 isWater：1 表示水域，0 表示陆地
        int rows = g.length, cols = g[0].length;

        // height[i][j] 记录每个格子的高度，-1 表示还没访问过
        int[][] height = new int[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        // 多源 BFS：所有水域同时作为起点，高度为 0
        // 每个格子的高度上界是min（a,b）
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (g[r][c] == 1) {
                    height[r][c] = 0;
                    queue.offer(new int[] { r, c });
                } else {
                    height[r][c] = -1;
                }
            }
        }

        // 上、下、左、右四个方向
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        // 一层层向外扩散，每扩一层高度 +1
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];

            for (int[] dir : directions) {
                int nextR = r + dir[0];
                int nextC = c + dir[1];

                // 越界则跳过
                if (nextR < 0 || nextR >= rows || nextC < 0 || nextC >= cols) {
                    continue;
                }
                // 已访问过则跳过（第一次访问时的高度就是最终答案）
                if (height[nextR][nextC] != -1) {
                    continue;
                }

                height[nextR][nextC] = height[r][c] + 1;
                queue.offer(new int[] { nextR, nextC });
            }
        }

        return height;
    }
}
// 复杂度： 每个格子只入队、出队一次，每次检查 4 个邻居，所以时间复杂度是 O(m·n)；队列和高度数组最多存 m·n 个格子，空间复杂度也是 O(m·n)。
// 面试讲解要点： 每个格子的高度不可能超过它到最近水域的距离（每走一步最多 +1），而 BFS 距离恰好满足相邻差不超过 1，所以答案就是"到最近水域的距离"。把所有水域一起放进队列做多源 BFS，一次遍历就能求出所有格子的高度。