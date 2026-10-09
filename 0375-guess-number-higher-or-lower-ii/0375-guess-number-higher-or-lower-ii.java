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
// 区间dp
//