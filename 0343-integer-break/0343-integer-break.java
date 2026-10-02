class Solution {
    public int integerBreak(int n) {
        // maxProduct[i]：把 i 拆成至少两个正整数后，能得到的最大乘积
        int[] maxProduct = new int[n + 1];
        maxProduct[2] = 1; // 2 = 1 + 1，乘积为 1

        for (int i = 3; i <= n; i++) {
            // 枚举拆出来的第一段 first，剩余部分为 i - first
            for (int first = 1; first < i; first++) {
                int rest = i - first;

                // 拆出第一段 first 之后，剩下的 rest 有两种选择：
                // 情况一：剩余部分不再拆，直接相乘
                int notSplitRest = first * rest;
                // 情况二：剩余部分继续拆，用已算好的最优结果
                // 因为rest < i, 可直接查表
                int splitRest = first * maxProduct[rest];

                maxProduct[i] = Math.max(maxProduct[i], Math.max(notSplitRest, splitRest));
            }
        }
        return maxProduct[n];
    }
}
// 复杂度：外层遍历 n 个数，内层对每个数枚举所有拆分点，时间复杂度 O(n²)；只用了一个长度为 n+1 的数组，空间复杂度 O(n)。