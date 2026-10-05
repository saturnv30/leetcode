class Solution {
    public boolean lemonadeChange(int[] bills) {
        int fives = 0; // 手里 5 元的张数
        int tens = 0;  // 手里 10 元的张数（20 元不能用来找零，不用记）

        for (int bill : bills) {
            if (bill == 5) {
                // 收 5 元，不用找零
                fives++;
            } else if (bill == 10) {
                // 收 10 元，找 5 元
                if (fives == 0) {
                    return false;
                }
                fives--;
                tens++;
            } else {
                // 收 20 元，找 15 元
                // 贪心：优先用 10 + 5，把更通用的 5 元留着
                if (tens > 0 && fives > 0) {
                    tens--;
                    fives--;
                } else if (fives >= 3) {
                    fives -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
// 复杂度： 时间复杂度 O(n)，只遍历一次 bills。空间复杂度 O(1)，只用了两个计数器。
// 贪心：按顺序模拟找零，只统计 5 元和 10 元的张数；收 20 元时贪心地优先找 10+5，因为 5 元用途更广，留着它不会更差；任何一步找不开就返回 false。