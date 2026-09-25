class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        // 剪枝：总个数不能被 k 整除，一定分不成
        if (nums.length % k != 0) return false;

        // 统计每个数出现的次数，TreeMap 按数值从小到大自动排序
        TreeMap<Integer, Integer> numCount = new TreeMap<>();
        for (int num : nums) {
            numCount.put(num, numCount.getOrDefault(num, 0) + 1);
        }

        while (!numCount.isEmpty()) {
            // 贪心：剩下的最小数只能当一组的开头
            int start = numCount.firstKey();

            // 这一组必须是 start, start+1, ..., start+k-1
            for (int num = start; num < start + k; num++) {
                Integer count = numCount.get(num);
                if (count == null) return false;         // 缺这个数，凑不成一组

                if (count == 1) numCount.remove(num);    // 用完了就移除
                else numCount.put(num, count - 1);       // 否则次数减 1
            }
        }
        return true;
    }
}