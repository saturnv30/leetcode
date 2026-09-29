class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        // 合并只会取 max，数值只增不减
        // 所以：任何一位超过 target 的三元组都不能用
        //       其余三元组全部合并也不会超过 target
        // 最后看合并结果是否恰好等于 target

        int maxFirst = 0; // 所有可用三元组中，第 1 位的最大值
        int maxSecond = 0; // 所有可用三元组中，第 2 位的最大值
        int maxThird = 0; // 所有可用三元组中，第 3 位的最大值

        for (int[] triplet : triplets) {
            // 只要有一位超过 target，合并后就再也降不下来，跳过
            boolean canUse = triplet[0] <= target[0]
                    && triplet[1] <= target[1]
                    && triplet[2] <= target[2];
            if (!canUse) {
                continue;
            }

            // 可用的三元组，全部合并（逐位取最大值）
            maxFirst = Math.max(maxFirst, triplet[0]);
            maxSecond = Math.max(maxSecond, triplet[1]);
            maxThird = Math.max(maxThird, triplet[2]);
        }

        // 每一位都恰好达到 target，才能拼出来
        return maxFirst == target[0]
                && maxSecond == target[1]
                && maxThird == target[2];
    }
}
// 只遍历一遍数组，每个三元组做常数次比较和取 max，所以时间 O(n)；只用了三个变量记录每一位的最大值，所以额外空间 O(1)。
// 操作具有单调性 贪心