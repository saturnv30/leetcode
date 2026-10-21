class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;

        // 1. 统计每个条形码的出现次数，同时找出出现次数最多的那个
        //    题目约束：1 <= barcodes[i] <= 10000，所以直接用数组计数
        int[] freq = new int[10001];
        int mostFrequent = barcodes[0];
        for (int code : barcodes) {
            freq[code]++;
            if (freq[code] > freq[mostFrequent]) {
                mostFrequent = code;
            }
        }

        int[] result = new int[n];
        int index = 0; // 下一个要填的位置：先走偶数位 0,2,4,...，填满后走奇数位 1,3,5,...

        // 2. 先把出现次数最多的条形码放到偶数位
        while (freq[mostFrequent] > 0) {
            result[index] = mostFrequent;
            freq[mostFrequent]--;
            index += 2;
        }

        // 3. 其余条形码按任意顺序接着填，偶数位填满就跳到奇数位
        for (int code = 1; code < freq.length; code++) {
            while (freq[code] > 0) {
                if (index >= n) {
                    index = 1; // 偶数位已填满，从第一个奇数位开始
                }
                result[index] = code;
                freq[code]--;
                index += 2;
            }
        }
        return result;
    }
}