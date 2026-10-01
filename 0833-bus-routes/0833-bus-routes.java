class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        // 已经在终点，不用坐车
        if (source == target) {
            return 0;
        }

        // 建立反向索引：车站 -> 经过这个车站的所有公交车编号
        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();
        for (int bus = 0; bus < routes.length; bus++) {
            for (int stop : routes[bus]) {
                stopToBuses.computeIfAbsent(stop, k -> new ArrayList<>()).add(bus);
            }
        }

        boolean[] busUsed = new boolean[routes.length]; // 每辆车只需要坐一次
        Set<Integer> visitedStops = new HashSet<>(); // 已经到达过的车站
        Queue<Integer> queue = new ArrayDeque<>(); // BFS 队列，存车站
        queue.offer(source);
        visitedStops.add(source);

        int busesTaken = 0; // 当前 BFS 层数 = 已经坐了几辆车

        while (!queue.isEmpty()) {
            busesTaken++; // 从当前这一层的车站出发，再坐一辆车
            int size = queue.size();

            for (int k = 0; k < size; k++) {
                int stop = queue.poll();

                // 在当前车站，尝试上每一辆经过这里的车
                for (int bus : stopToBuses.getOrDefault(stop, new ArrayList<>())) {
                    if (busUsed[bus]) {
                        continue; // 这辆车之前坐过，再坐不会更优
                    }
                    busUsed[bus] = true;

                    // 坐上这辆车，可以在它路线上的任意一站下车
                    for (int nextStop : routes[bus]) {
                        if (nextStop == target) {
                            return busesTaken;
                        }
                        if (visitedStops.add(nextStop)) { // add 返回 true 表示第一次访问
                            queue.offer(nextStop);
                        }
                    }
                }
            }
        }

        return -1; // 怎么坐都到不了终点
    }
}
// 复杂度： 时间和空间都是 O(S)，S 是所有路线的车站总数。原因是每辆车最多被遍历一次，所以每个车站在路线上的每次出现也只会被处理一次。

// 讲解时的一句话思路： 把车站当节点，坐一辆车能到达的所有车站算"一步"，那么 BFS 的层数就是坐车的数量。用 busUsed 保证每辆车只坐一次，因为第一次坐上某辆车时，BFS 层数一定是最小的