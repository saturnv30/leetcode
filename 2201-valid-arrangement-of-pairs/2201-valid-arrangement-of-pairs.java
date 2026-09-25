class Solution {
    // 邻接表：点 -> 从它出发、还没走过的边的终点
    private Map<Integer, Deque<Integer>> graph = new HashMap<>();
    // 按“后序”收集的边，最后要反转
    private List<int[]> path = new ArrayList<>();

    public int[][] validArrangement(int[][] pairs) {
        // 1. 建图，同时统计每个点的 (出度 - 入度)
        Map<Integer, Integer> degree = new HashMap<>();
        for (int[] pair : pairs) {
            int from = pair[0], to = pair[1];
            graph.computeIfAbsent(from, k -> new ArrayDeque<>()).push(to);
            degree.merge(from, 1, Integer::sum); // 出度 +1
            degree.merge(to, -1, Integer::sum); // 入度 +1
        }

        // 2. 找起点：出度比入度多 1 的点
        //    找不到说明是欧拉回路，从任意一条边的起点出发即可
        int start = pairs[0][0];
        for (Map.Entry<Integer, Integer> entry : degree.entrySet()) {
            if (entry.getValue() == 1) {
                start = entry.getKey();
                break;
            }
        }

        // 3. DFS 走完所有边，再反转得到正确顺序
        dfs(start);
        Collections.reverse(path);
        return path.toArray(new int[0][]);
    }

    // Hierholzer：沿着没走过的边一直走，回溯时才记录这条边
    private void dfs(int node) {
        Deque<Integer> nextNodes = graph.get(node);
        while (nextNodes != null && !nextNodes.isEmpty()) {
            int next = nextNodes.pop(); // 删掉这条边，保证只走一次
            dfs(next); // 先把后面的路走完
            path.add(new int[] { node, next }); // 后序记录：先卡住的边排在最后
        }
    }
}