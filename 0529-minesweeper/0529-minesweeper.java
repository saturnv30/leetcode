class Solution {

    // 8 个方向：上、下、左、右、左上、右上、左下、右下
    int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
    int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };

    public char[][] updateBoard(char[][] board, int[] click) {

        // 点击位置的行和列
        int row = click[0];
        int col = click[1];

        // 情况 1：点击到雷
        if (board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        }

        // 情况 2：点击到空地
        dfs(board, row, col);

        return board;
    }

    // 从 (row, col) 开始揭开格子
    private void dfs(char[][] board, int row, int col) {

        // 统计当前格子周围有多少个雷
        int mineCount = 0;

        for (int direction = 0; direction < 8; direction++) {

            int nextRow = row + dx[direction];
            int nextCol = col + dy[direction];

            // 越界
            if (nextRow < 0 || nextRow >= board.length
                    || nextCol < 0 || nextCol >= board[0].length) {
                continue;
            }

            // 如果相邻格子是雷
            if (board[nextRow][nextCol] == 'M') {
                mineCount++;
            }
        }

        // 如果周围有雷：
        // 当前格子显示雷的数量，不再继续 DFS
        if (mineCount > 0) {
            board[row][col] = (char) ('0' + mineCount);
            return;
        }

        // 如果周围没有雷：
        // 当前格子显示为 B
        board[row][col] = 'B';

        // 继续搜索周围 8 个方向的未揭开空地
        for (int direction = 0; direction < 8; direction++) {

            int nextRow = row + dx[direction];
            int nextCol = col + dy[direction];

            // 越界
            if (nextRow < 0 || nextRow >= board.length
                    || nextCol < 0 || nextCol >= board[0].length) {
                continue;
            }

            // 只继续搜索 E
            if (board[nextRow][nextCol] == 'E') {
                dfs(board, nextRow, nextCol);
            }
        }
    }
}
// DFS 最多访问每个 E 格子一次。O（m*n）
// board 是输入，不算额外空间。 真正的额外空间来自 DFS 递归调用栈。 最坏情况下，如果整个棋盘都是可以继续扩散的 E
// https://leetcode.cn/problems/minesweeper/solutions/381937/cong-qi-dian-kai-shi-dfs-bfs-bian-li-yi-bian-ji-ke