class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        // dp[i][j]: 子串 s[i..j]（含两端）中最长回文子序列的长度
        int[][] dp = new int[n][n];

        // dp[i][j] 依赖 dp[i+1][...]（下一行），所以 i 从后往前遍历
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1; // 单个字符本身就是回文

            // dp[i][j] 依赖 dp[i][j-1]（左边一格），所以 j 从前往后遍历
            for (int j = i + 1; j < n; j++) {
                if (chars[i] == chars[j]) {
                    // 两端相同：一起作为回文的最外层，再加上中间部分的结果
                    // 当 j == i+1 时，dp[i+1][j-1] 是空区间，默认值为 0，结果为 2
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // 两端不同：两者不能同时用，丢掉左端或右端，取较大值
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1]; // 整个字符串 s[0..n-1] 的答案
    }
}