class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        // rank[c] = 字母 c 在外星字母表中的位置，越小越靠前
        // rank 是把外星字母表"翻译"成数字，让两个字母可以直接比大小。
        int[] rank = new int[26];
        for (int i = 0; i < order.length(); i++) {
            rank[order.charAt(i) - 'a'] = i;
        }

        // 只需检查每一对相邻单词是否有序（有序具有传递性）
        for (int i = 0; i + 1 < words.length; i++) {
            if (!inOrder(words[i], words[i + 1], rank)) {
                return false;
            }
        }
        return true;
    }

    // 判断 first 是否应排在 second 前面（或相等）
    private boolean inOrder(String first, String second, int[] rank) {
        int minLen = Math.min(first.length(), second.length());
        for (int i = 0; i < minLen; i++) {// O(L)
            char a = first.charAt(i);
            char b = second.charAt(i);
            // 第一个不同的字符决定先后顺序
            if (a != b) {
                return rank[a - 'a'] < rank[b - 'a'];
            }
        }
        // 前 minLen 位都相同：短的应在前，例如 "app" 必须在 "apple" 前
        return first.length() <= second.length();
    }
}
// 复杂度： 时间 O(C)，C 为所有单词的总字符数，因为每个字符最多参与两次相邻比较；空间 O(1)，只用了一个固定大小 26 的数组。
