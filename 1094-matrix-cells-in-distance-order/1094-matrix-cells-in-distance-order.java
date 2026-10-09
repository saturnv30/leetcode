class Solution {
    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        // 曼哈顿距离最大为 rows + cols - 2（中心在角落时）
        int maxDist = rows + cols - 2;

        // buckets[d] 存放所有到中心距离为 d 的格子
        List<List<int[]>> buckets = new ArrayList<>();
        for (int d = 0; d <= maxDist; d++) {
            buckets.add(new ArrayList<>());
        }

        // 遍历每个格子，计算曼哈顿距离，放入对应的桶
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int dist = Math.abs(row - rCenter) + Math.abs(col - cCenter); // 分桶/分层
                buckets.get(dist).add(new int[]{row, col});
            }
        }

        // 按距离从小到大依次取出桶中的格子
        int[][] result = new int[rows * cols][];
        int resultIndex = 0;
        for (List<int[]> bucket : buckets) {
            for (int[] cell : bucket) {
                result[resultIndex++] = cell;
            }
        }
        return result;
    }
}

// 每个格子只计算一次距离、放入桶一次，桶的个数是 rows + cols - 1，所以时间复杂度是 O(rows × cols)。桶和结果数组一共存了所有格子，所以空间复杂度也是 O(rows × cols)。