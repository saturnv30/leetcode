class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;

        // count[v] = how many times value v appears in nums (values are 1..n)
        int[] count = new int[n + 1];
        for (int num : nums) {
            count[num]++;
        }

        int duplicate = -1; // the number that appears twice
        int missing = -1;   // the number that never appears

        // Check every value from 1 to n
        for (int value = 1; value <= n; value++) {
            if (count[value] == 2) {
                duplicate = value;
            } else if (count[value] == 0) {
                missing = value;
            }
        }

        return new int[]{duplicate, missing};
    }
}
// 复杂度：时间 O(n)，因为数组只遍历两遍。空间 O(n)，用于计数数组 count。