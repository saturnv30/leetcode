class Solution {
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // 第一次不相等：只有一次删除机会，删 s[left] 或删 s[right]
                // 外侧已配对，只需检查中间剩余部分是否为回文
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            // 两端相等，向中间收缩
            left++;
            right--;
        }

        // 一路相等，本身就是回文
        return true;
    }

    // 严格判断 s[left..right] 是否为回文（不允许删除）
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}