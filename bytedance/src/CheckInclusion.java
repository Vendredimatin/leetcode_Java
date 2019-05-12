import java.util.Arrays;

/**
 * @program: leetcode
 * @description: 字符串的排列
 * @author: Liu Hanyi
 * @create: 2019-03-25 20:06
 **/

public class CheckInclusion {
    public static void main(String[] args) {
        String s1 = "adc";
        String s2 = "dcda";
        System.out.println(new CheckInclusion().checkInclusion(s1,s2));
    }

    /**
     * method1: 重复问题－＞hash,字符数组
     * 如果相同长度内的字符串，每个字符的出现频率相同，那么就包含这个字串
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int[] alphabet = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            alphabet[ch - 'a']++;
        }

        for (int i = 0; i < s2.length(); i++) {
            int[] newAlphabet = new int[26];
            for (int j = 0; j < n; j++) {
                int newIndex = i+j;
                if (newIndex > s2.length()-1)
                    return false;
                char ch = s2.charAt(newIndex);
                
                newAlphabet[ch-'a']++;
            }

            if (match(alphabet,newAlphabet))
                return true;
        }

        return false;
    }

    private boolean match(int[] alphabet, int[] newAlphabet) {
        for (int i = 0; i < 26; i++) {
            if (alphabet[i] != newAlphabet[i])
                return false;
        }
        return true;
    }

    /**
     * 滑动窗口：对方法一的优化，方法一每次都要重取s2.length-1位数，但是滑动窗口不用，只需要重取最左边和最右边的即可
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion2(String s1, String s2) {
        int[] c1 = new int[26];
        int[] c2 = new  int[26];

        for (int i = 0; i < s1.length(); i++) {
            c1[s1.charAt(i)-'a']++;
        }

        for (int i = 0; i < s2.length(); i++) {
            if (i >= s1.length())
                --c2[s2.charAt(i- s1.length()) - 'a'];
            c2[s2.charAt(i) - 'a']++;
            if (Arrays.equals(c1,c2))
                return true;
        }

        return false;
    }
}
