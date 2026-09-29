class Solution {
    // 上、下、左、右四个方向
    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int swimInWater(int[][] grid) {
        int n = grid.length;

        // visited[r][c] = true 表示到达 (r, c) 所需的最低水位已经确定
        boolean[][] visited = new boolean[n][n];

        // 堆中元素：{到达该格子所需的最低水位, 行, 列}
        // 按水位从小到大排序，每次优先扩展"最容易到达"的格子
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // 起点本身也要被淹没，所以初始水位是 grid[0][0]
        minHeap.offer(new int[] { grid[0][0], 0, 0 });

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int waterLevel = current[0];
            int row = current[1];
            int col = current[2];

            // 同一个格子可能多次入堆，只有第一次弹出时的水位是最低的
            if (visited[row][col]) {
                continue;
            }
            visited[row][col] = true;

            // 终点第一次弹出时，它的水位就是答案
            if (row == n - 1 && col == n - 1) {
                return waterLevel;
            }

            for (int[] dir : DIRECTIONS) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];

                // 越界或已确定的格子跳过
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n
                        || visited[nextRow][nextCol]) {
                    continue;
                }

                // 走到邻居需要的水位 = 路径上到目前为止的最高点
                int nextWaterLevel = Math.max(waterLevel, grid[nextRow][nextCol]);
                minHeap.offer(new int[] { nextWaterLevel, nextRow, nextCol });
            }
        }

        return -1; // 网格一定连通，不会走到这里
    }
}