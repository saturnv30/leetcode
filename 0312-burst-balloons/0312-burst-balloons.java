class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;

        // 两端补上值为 1 的虚拟气球，省去越界判断
        int[] balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }

        // dp[left][right]：戳破开区间 (left, right) 内所有气球的最大硬币数
        // left 和 right 本身不戳；区间内没有气球时为 0（默认值）
        int[][] dp = new int[n + 2][n + 2];

        // left 从下往上：保证 dp[last][right]（下方的行）已算好
        for (int left = n; left >= 0; left--) {
            // right 从左往右：保证 dp[left][last]（同一行左侧）已算好
            for (int right = left + 2; right <= n + 1; right++) {

                // 枚举区间内"最后一个被戳破"的气球 last
                for (int last = left + 1; last < right; last++) {
                    // last 最后戳时，它的邻居只剩 left 和 right
                    int coins = balloons[left] * balloons[last] * balloons[right];
                    // 左右两段在 last 戳破前互不影响，可以分别求最优
                    int total = dp[left][last] + dp[last][right] + coins;
                    dp[left][right] = Math.max(dp[left][right], total);
                }
            }
        }

        // 整个区间 (0, n+1) 就是全部真实气球
        return dp[0][n + 1];
    }
}
// 复杂度：共有 O(n²) 个区间，每个区间枚举 O(n) 个最后戳破的气球，所以时间复杂度是 O(n³)；dp 表占 O(n²) 空间。