class Solution {
    // 邻接表：出发城市 -> 可去的目的地（小顶堆，保证每次先取字典序最小的）
    private Map<String, PriorityQueue<String>> graph = new HashMap<>();
    // 最终行程（先倒序收集，最后再翻转）
    private LinkedList<String> itinerary = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        // 1. 建图：每张机票是一条有向边 from -> to
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph.computeIfAbsent(from, k -> new PriorityQueue<>()).offer(to);
        }

        // 2. 从 JFK 出发做 DFS
        dfs("JFK");

        return itinerary;
    }

    private void dfs(String city) {
        PriorityQueue<String> destinations = graph.get(city);

        // 只要当前城市还有没用过的机票，就一直往下飞
        // poll() 同时完成两件事：取出字典序最小的目的地 + 把这张票"用掉"
        while (destinations != null && !destinations.isEmpty()) {
            String nextCity = destinations.poll();
            dfs(nextCity);
        }

        // 所有出边都走完了，才把当前城市加入结果（后序）
        // 用 addFirst 插到头部，省去最后 reverse 的步骤
        itinerary.addFirst(city);
    }
}