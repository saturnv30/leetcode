class Solution {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        // memo[i][j]：顶点 i..j 围成的多边形（底边 i-j）的最低剖分得分，-1 表示未计算
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return minScore(0, n - 1, values, memo);
    }

    // 返回顶点 left..right 围成的多边形的最低剖分得分
    private int minScore(int left, int right, int[] values, int[][] memo) {
        // 只有两个顶点，构不成三角形
        if (right - left < 2) {
            return 0;
        }
        if (memo[left][right] != -1) {
            return memo[left][right];
        }

        int best = Integer.MAX_VALUE;
        // 底边 left-right 一定属于某个三角形，枚举这个三角形的第三个顶点 mid
        for (int mid = left + 1; mid < right; mid++) {
            int triangle = values[left] * values[mid] * values[right];
            int leftPart = minScore(left, mid, values, memo);   // 左边子多边形 left..mid
            int rightPart = minScore(mid, right, values, memo); // 右边子多边形 mid..right
            best = Math.min(best, leftPart + triangle + rightPart);
        }

        memo[left][right] = best;
        return best;
    }
}
// 复杂度：共 O(n²) 个区间状态，每个状态枚举 O(n) 个分割点，所以时间 O(n³)；memo 数组和递归栈占用空间 O(n²)。
/**
底边 left—right 在任何剖分里都恰好属于一个三角形，枚举它的第三个顶点 mid。
这个三角形把多边形分成 left..mid 和 mid..right 两个更小的子多边形，递归求解，mid 是两边共用的顶点。
只剩两个点时得分为 0；同一区间会被重复访问，所以用 memo 缓存。

 */