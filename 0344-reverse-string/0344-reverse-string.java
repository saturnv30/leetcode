class Solution {
    public void reverseString(char[] s) {
        // 双指针：left 指向开头，right 指向结尾
        int left = 0;
        int right = s.length - 1;

        // 两个指针向中间靠拢，相遇时停止
        while (left < right) {
            // 交换左右两端的字符
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            // 指针向中间移动
            left++;
            right--;
        }
    }
}
// 复杂度： 时间 O(n)，因为总共交换 n/2 次；空间 O(1)，因为是原地交换，只用了一个临时变量。