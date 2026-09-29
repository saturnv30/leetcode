class Solution {
    // 所有从 0 到 n-1 的路径
    private List<List<Integer>> allPaths = new ArrayList<>();
    // 当前正在走的路径
    private List<Integer> currentPath = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        currentPath.add(0); // 起点固定是 0
        dfs(graph, 0);
        return allPaths;
    }

    /**
     * 从 node 出发，继续往下走，找所有能到终点的路径
     */
    private void dfs(int[][] graph, int node) {
        int target = graph.length - 1;

        // 1. 到达终点：把当前路径拷贝一份存起来
        if (node == target) {
            allPaths.add(new ArrayList<>(currentPath));
            return;
        }

        // 2. 尝试走向每一个邻居
        for (int neighbor : graph[node]) {
            currentPath.add(neighbor); // 做选择
            dfs(graph, neighbor); // 往下走
            currentPath.remove(currentPath.size() - 1); // 撤销选择（回溯）
        }
    }
}