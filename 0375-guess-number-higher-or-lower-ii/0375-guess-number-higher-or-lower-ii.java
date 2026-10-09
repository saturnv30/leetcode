class Solution {
    // memo[left][right]：答案在 [left, right] 区间内时，保证猜中所需的最少钱数
    // 0 表示还没算过（区间里有 ≥2 个数时，答案一定大于 0，所以不会冲突）
    private int[][] memo;

    public int getMoneyAmount(int n) {
        memo = new int[n + 2][n + 2];
        return minCost(1, n);
    }

    // 返回：答案在 [left, right] 内时，保证猜中所需的最少钱数
    private int minCost(int left, int right) {
        // 区间里最多剩一个数，直接猜就对，不花钱
        if (left >= right) {
            return 0;
        }
        if (memo[left][right] != 0) {
            return memo[left][right];
        }

        int best = Integer.MAX_VALUE;
        // 枚举第一次猜的数 guess
        for (int guess = left; guess <= right; guess++) {
            // 最坏情况：猜错，先付 guess 元，
            // 答案落在花费更大的那一侧（左边或右边，取 max）
            int worstCase = guess + Math.max(minCost(left, guess - 1),
                    minCost(guess + 1, right));
            // 在所有猜法中，选最坏情况花费最小的那个
            best = Math.min(best, worstCase);
        }

        memo[left][right] = best;
        return best;
    }
}
// 区间dp： 状态是一个区间 [left, right]，大区间的结果由它切分出的小区间推出来。决策思想是 minimax（极小化极大）。minimax：对手（最坏情况）在左右两侧取 max，我们在所有猜法里取 min。，转移时内层取 max 表示最坏情况，外层取 min 表示最优策略。
// 一共 O(n²) 个区间，每个区间里的 for 循环是 O(n)，所以时间复杂度是 O(n³)；空间上 memo 占 O(n²)，递归栈深度 O(n)。