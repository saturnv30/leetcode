class Solution {
    public String convertToTitle(int cn) {
        // cn: 列号，从 1 开始（A=1, B=2, ..., Z=26, AA=27）
        StringBuilder title = new StringBuilder();

        while (cn > 0) {
            // Excel 没有表示 0 的字母，所以先减 1，把这一位从 1~26 变成 0~25
            cn--;

            // 取最低位，0~25 对应 'A'~'Z'
            int digit = cn % 26;
            title.append((char) ('A' + digit));

            // 去掉最低位，处理更高一位
            cn /= 26;
        }

        // 上面是从低位往高位拼的，所以要反转
        return title.reverse().toString();
    }
}
// 复杂度：每轮循环把 cn 除以 26，所以时间复杂度是 O(log₂₆ n)。空间复杂度也是 O(log₂₆ n)，用于存放结果字符串。