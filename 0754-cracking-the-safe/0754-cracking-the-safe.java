class Solution {
    /*
     * 思路：de Bruijn 序列 + 欧拉回路（Hierholzer 算法）
     *
     * 建图：
     *   点：所有 n-1 位的串，共 k^(n-1) 个
     *   边：点 node 后面接一个数字 digit，得到一个 n 位的串，它就是一个密码
     *       走这条边后，去掉最高位，剩下的 n-1 位就是下一个点
     *
     * 每个密码恰好对应一条边。每个点的入度和出度都是 k，所以一定存在欧拉回路。
     * 把每条边恰好走一次，就覆盖了全部 k^n 个密码，
     * 答案长度是 k^n + (n-1)，达到理论最短。
     */

    private StringBuilder result = new StringBuilder();
    private Set<Integer> visitedEdges = new HashSet<>(); // 已经走过的边，也就是已经覆盖的密码
    private int k;
    private int highestDigitMod; // 等于 10^(n-1)，对它取模可以去掉 n 位数的最高位

    public String crackSafe(int n, int k) {
        this.k = k;
        this.highestDigitMod = (int) Math.pow(10, n - 1);

        // 从全 0 的点出发（n-1 个 0，整数表示就是 0）
        dfs(0);

        // result 是欧拉回路的逆序，所以起点的 n-1 个 0 要补在最后
        // 翻转后的 de Bruijn 序列同样覆盖所有密码，因此不用再 reverse
        result.append("0".repeat(n - 1));
        return result.toString();
    }

    // node：当前点，即最近输入的 n-1 位
    private void dfs(int node) {
        for (int digit = 0; digit < k; digit++) {
            int password = node * 10 + digit; // 当前点 + 新数字 = 一个 n 位密码，也就是这条边

            if (visitedEdges.contains(password)) {
                continue; // 这个密码已经覆盖过了
            }
            visitedEdges.add(password);

            int nextNode = password % highestDigitMod; // 去掉最高位，得到下一个点
            dfs(nextNode);

            // 后序：递归返回后才记录这条边的数字
            // 这样能把中途卡住时的子回路正确拼进主回路（Hierholzer 的关键）
            result.append(digit);
        }
    }
}