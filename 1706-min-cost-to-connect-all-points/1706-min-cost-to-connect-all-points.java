class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        // minDist[i]：点 i 到“已连通的树”的最短距离
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0; // 从点 0 开始建树

        // inTree[i]：点 i 是否已经加入树
        boolean[] inTree = new boolean[n];

        int totalCost = 0;

        // 每轮把一个点加入树，共 n 轮
        for (int round = 0; round < n; round++) {

            // 第 1 步：在树外的点里，找离树最近的那个
            int closest = -1;
            for (int i = 0; i < n; i++) {
                if (!inTree[i] && (closest == -1 || minDist[i] < minDist[closest])) {
                    closest = i;
                }
            }

            // 第 2 步：把它加入树，累加连接它的费用
            inTree[closest] = true;
            totalCost += minDist[closest];

            // 第 3 步：树变大了，用新加入的点更新其他点到树的距离
            for (int other = 0; other < n; other++) {
                if (!inTree[other]) {
                    int cost = manhattan(points[closest], points[other]);
                    minDist[other] = Math.min(minDist[other], cost);
                }
            }
        }
        return totalCost;
    }

    // 两点之间的曼哈顿距离
    private int manhattan(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
// https://claude.ai/code/session_01GQFEQ3UtTttX7ukRmSmj8b
// 对比prim和kruskal 