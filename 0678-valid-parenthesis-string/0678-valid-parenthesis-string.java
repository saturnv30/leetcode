class Solution {
    public boolean checkValidString(String s) {
        // 核心思路：
        // 用一个范围 [minOpen, maxOpen] 表示"当前还没被匹配的左括号数量"可能的取值
        // minOpen：把 '*' 尽量当成 ')' 时，未匹配左括号的最少数量
        // maxOpen：把 '*' 尽量当成 '(' 时，未匹配左括号的最多数量
        int minOpen = 0;
        int maxOpen = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // 左括号：未匹配数量一定 +1
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                // 右括号：未匹配数量一定 -1
                minOpen--;
                maxOpen--;
            } else {
                // '*'：可以当 ')'（最少 -1），也可以当 '('（最多 +1），也可以当空串（不变）
                minOpen--;
                maxOpen++;
            }

            // 就算把所有 '*' 都当 '('，右括号还是太多，不可能合法
            if (maxOpen < 0) {
                return false;
            }

            // 未匹配左括号数量不能为负数
            // minOpen < 0 说明某些 '*' 当 ')' 当多了，把它们改成空串即可
            minOpen = Math.max(minOpen, 0);
        }

        // 最后要求所有左括号都能被匹配，也就是 0 在可能的范围内
        return minOpen == 0;
    }
}