class Solution {
    public int subsetXORSum(int[] nums) {
        // 从第 0 个元素开始，当前已选元素的异或值为 0（空集）
        return dfs(nums, 0, 0);
    }

    /**
     * 返回：在已选元素异或值为 currXor 的前提下，
     *      对 nums[index..] 做所有「选/不选」组合后，所有子集异或值之和
     */
     // currXor 是「到目前为止已选元素的异或值」，
    private int dfs(int[] nums, int index, int currXor) {
        // 所有元素都决定完了，得到一个完整子集，返回它的异或值
        if (index == nums.length) {
            return currXor;
        }

        // 选当前元素：异或值更新为 currXor ^ nums[index]
        int pick = dfs(nums, index + 1, currXor ^ nums[index]);

        // 不选当前元素：异或值保持不变
        int skip = dfs(nums, index + 1, currXor);

        return pick + skip;
    }
}
/***
时间 O(2ⁿ)：递归树有 n+1 层，第 k 层有 2ᵏ 个节点，总节点数 1 + 2 + 4 + … + 2ⁿ = 2ⁿ⁺¹ − 1，每个节点只做一次异或和一次加法，是 O(1)，所以总共 O(2ⁿ)。
空间 O(n)：没有额外数组，只有递归调用栈。同一时刻栈里最多压着从根到叶子的一条路径，深度为 n，所以 O(n)。
 */