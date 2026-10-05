class Solution {
    public int[][] transpose(int[][] matrix) {
        int rows = matrix.length;       // 原矩阵行数
        int cols = matrix[0].length;    // 原矩阵列数

        // 转置后行列互换：新矩阵是 cols 行、rows 列
        int[][] result = new int[cols][rows];

        // 遍历原矩阵每个位置 (row, col)，放到新矩阵的 (col, row)
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                result[col][row] = matrix[row][col];
            }
        }

        return result;
    }
}
// 时间 O(mn)：每个元素访问一次。
// 空间 O(1)（不算返回值），返回的 ans 本身占 O(mn)，这是题目要求的输出，一般不计入额外空间。