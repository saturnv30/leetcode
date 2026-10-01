class Solution {
    public int openLock(String[] deadends, String target) {
        // 把死亡点直接放进 visited：死亡点和"走过的点"一样，都不能再走
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));

        // 边界：起点就是死亡点，直接失败
        if (visited.contains("0000")) {
            return -1;
        }

        // BFS 标准初始化：起点入队，并立刻标记为已访问
        Queue<String> queue = new ArrayDeque<>();
        queue.offer("0000");
        visited.add("0000");

        int steps = 0; // 当前层数 = 已经转了几次

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // 当前这一层有多少个状态

            for (int i = 0; i < levelSize; i++) {
                String current = queue.poll();

                // 第一次碰到 target 时的层数，就是最少步数
                if (current.equals(target)) {
                    return steps;
                }

                // 把当前状态的 8 个邻居中没访问过的入队
                for (String next : getNeighbors(current)) {
                    if (!visited.contains(next)) {
                        visited.add(next); // 入队时就标记，防止重复入队
                        queue.offer(next);
                    }
                }
            }

            steps++; // 这一层全部处理完，步数 +1
        }

        return -1; // 所有能到的状态都试过了，还是没到 target
    }

    // 生成一个状态的 8 个邻居：4 个拨轮，每个可以 +1 或 -1
    private List<String> getNeighbors(String lock) {
        List<String> neighbors = new ArrayList<>();
        char[] digits = lock.toCharArray();

        for (int i = 0; i < 4; i++) {
            char original = digits[i];

            // 往上拧：9 的下一个是 0
            digits[i] = (original == '9') ? '0' : (char) (original + 1);
            neighbors.add(new String(digits));

            // 往下拧：0 的下一个是 9
            digits[i] = (original == '0') ? '9' : (char) (original - 1);
            neighbors.add(new String(digits));

            digits[i] = original; // 还原这一位，再处理下一位
        }

        return neighbors;
    }
}