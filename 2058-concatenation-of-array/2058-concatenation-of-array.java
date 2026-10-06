class Solution {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        // 结果数组长度是原数组的两倍
        int[] ans = new int[2 * n];

        // ans 相当于把 nums 走两遍：第 i 位对应 nums 的第 i % n 位
        for (int i = 0; i < 2 * n; i++) {
            ans[i] = nums[i % n];
        }

        return ans;
    }
}
// 复杂度： 时间 O(n)，结果数组的 2n 个位置各填一次；空间上除了必须返回的结果数组外只用 O(1) 额外空间。
// 把 ans 看成 nums 循环走了两圈，用 i % n 让下标走到末尾后自动绕回开头。这样一个循环就能完成，而且改成串联 k 次也只需要改数组长度。