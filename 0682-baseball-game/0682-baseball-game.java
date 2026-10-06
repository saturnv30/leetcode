class Solution {
    public int calPoints(String[] ops) {
        // 栈顶永远是"上一次有效得分"
        Deque<Integer> scores = new ArrayDeque<>();

        for (String op : ops) {
            if (op.equals("+")) {
                // 取出最近一次得分，露出倒数第二次
                int last = scores.pop();
                int secondLast = scores.peek();
                // 放回原分数，再压入两者之和
                scores.push(last);
                scores.push(last + secondLast);
            } else if (op.equals("D")) {
                // 最近一次得分翻倍
                scores.push(scores.peek() * 2);
            } else if (op.equals("C")) {
                // 作废最近一次得分
                scores.pop();
            } else {
                // 普通数字（可能为负）直接记录
                scores.push(Integer.parseInt(op));
            }
        }

        // 累加所有有效得分
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }
}
// 复杂度： 时间 O(n)，每个操作都是 O(1) 的栈操作，最后求和遍历一次；空间 O(n)，最坏情况所有操作都是数字，全部入栈。"栈顶存最近的结果，每个操作只依赖栈顶附近的元素，所以每步 O(1)。