class NumMatrix {
    // prefix[i][j] = 原矩阵中 第 0..i-1 行、第 0..j-1 列 的矩形元素和
    // 多开一行一列（全为 0），这样查询时不用处理边界
    private int[][] prefix;

    public NumMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        prefix = new int[rows + 1][cols + 1];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 上方矩形 + 左方矩形 - 重复加的左上角矩形 + 当前格子
                prefix[i + 1][j + 1] = prefix[i][j + 1]
                        + prefix[i + 1][j]
                        - prefix[i][j]
                        + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        // 先把四条边界起好名字
        int top = row1, left = col1;
        int bottom = row2 + 1, right = col2 + 1;

        // 整块 - 上面一条 - 左边一条 + 左上角
        return prefix[bottom][right]
                - prefix[top][right]
                - prefix[bottom][left]
                + prefix[top][left];
    }
}
/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */