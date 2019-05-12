/**
 * @program: leetcode
 * @description: 字符串大数乘法43
 * @author: Liu Hanyi
 * @create: 2019-03-26 16:22
 **/

public class Multiply {
    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        System.out.println(new Multiply().multiply(num1, num2));
    }


    /**
     * brilliant method:通过观察规律可得出，第ｉ位＊第ｊ位，可以得到［ｉ＋ｊ，ｉ＋ｊ＋１］的结果
     * 具体参见https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation
     * ｓｕｍｍａｒｙ：观察位数之间的关系
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();

        int[] pos = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int num = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                num += pos[i + j + 1];

                pos[i + j + 1] = (num%10);
                pos[i + j] += (num/10);
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int num : pos) {
            if (num != 0) flag = true;
            if (flag) sb.append(num);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
