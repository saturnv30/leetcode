class Solution {
    public int slidingPuzzle(int[][] board) {
        String target = "123450";

        // 把 2x3 棋盘按行拼成字符串，作为 BFS 中的一个"状态"
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                sb.append(num);
            }
        }
        String start = sb.toString();

        // neighbors[i]：一维索引 i 在 2x3 棋盘上的相邻格子索引
        //   0 1 2
        //   3 4 5
        int[][] neighbors = {
                { 1, 3 }, // 0
                { 0, 2, 4 }, // 1
                { 1, 5 }, // 2
                { 0, 4 }, // 3
                { 1, 3, 5 }, // 4
                { 2, 4 } // 5
        };

        // BFS：一层代表一步，第一次遇到 target 时的层数就是最少步数
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);

        int steps = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                String curr = queue.poll();
                if (curr.equals(target)) {
                    return steps;
                }

                // 把 0 和每个相邻格子交换，得到下一层的新状态
                int zeroIdx = curr.indexOf('0');
                for (int nextIdx : neighbors[zeroIdx]) {
                    String next = swap(curr, zeroIdx, nextIdx);
                    if (visited.add(next)) { // add 返回 true 说明之前没访问过
                        queue.offer(next);
                    }
                }
            }
            steps++;
        }
        return -1; // 所有可达状态都走完仍未到达 target，无解
    }

    // 交换字符串中 i、j 两个位置的字符，返回新字符串
    private String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}
// 复杂度： 棋盘只有 6 个格子，所以最多有 6! = 720 种状态，每种状态的扩展和字符串操作都是 O(6)。因此时间和空间复杂度都是 O(6! × 6)，推广到 m×n 棋盘是 O((mn)! × mn)。
