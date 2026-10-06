class Solution {
    public int scoreOfParentheses(String s) {
        int depth = 0; // 当前括号嵌套深度（未闭合的 '(' 个数）
        int score = 0; // 累计总分

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
                // 因为 () 自己贡献的是基础分 1，外面每一层才贡献一次 ×2。
                // 只有紧挨着的 "()" 才产生分数：
                // 它外面还包着 depth 层括号，每层乘 2，所以贡献 2^depth
                if (s.charAt(i - 1) == '(') {
                    score += (1 << depth);
                    // 等价于
                    // score = score + (int) Math.pow(2, depth);
                }
            }
        }
        return score;
    }
}
// 数学观察：(A)=2A 对加法满足分配律，可以把每层的 ×2 一直分配到最内层的 ()，所以每个 () 的贡献就是 2 的"外层括号数"次方。
// 复杂度：只遍历字符串一次，时间 O(n)；只用了两个整型变量，空间 O(1)。
// 所以 1 << depth 就是 2^depth。Java 没有 ^ 表示幂（^ 是异或），而 Math.pow 返回 double 还要强转，所以用左移最简洁。
// << 优先级低于 +，但这里 += 是赋值运算符，优先级最低，所以先算 1 << depth 再累加，不用加括号。如果面试时担心看不清，可以写成 score += (1 << depth);，更易读。