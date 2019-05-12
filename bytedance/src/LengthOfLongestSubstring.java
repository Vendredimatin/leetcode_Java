import java.util.HashSet;
import java.util.Set;

/**
 * @program: leetcode
 * @description: 无重复字符的最长子串
 * @author: Liu Hanyi
 * @create: 2019-03-25 16:20
 **/

public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring2(s));
    }

    /**
     * method1 : 声明一个字符串来保存探索的结果，当遇到的新字符在字符串中有的话，那么就从所在位置＋１的位置重新开始
     * summary: 重复－＞　hashSet,字符数组，滑动窗口
     * 字母-> 字符数组
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        String curStr = "";

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            int index = curStr.indexOf(ch);
            if (index >= 0){
                maxLength = Math.max(maxLength,curStr.length());
                curStr= curStr.substring(index+1);
            }

            curStr+= ch;

        }

        return Math.max(maxLength,curStr.length());
    }

    /**
     * method2 : 使用滑动窗口，remove的时候会一直remove到重复元素出现的位置，然后把新的重复元素添加进去
     * 要检查一个字符是否已经在子字符串中，我们可以检查整个子字符串，这将产生一个复杂度为 O(n^2)O(n
     * 2
     *  ) 的算法，但我们可以做得更好。
     *
     * 通过使用 HashSet 作为滑动窗口，我们可以用 O(1)O(1) 的时间来完成对字符是否在当前的子字符串中的检查。
     *
     * 滑动窗口是数组/字符串问题中常用的抽象概念。 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，即 [i, j)[i,j)（左闭，右开）。而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。例如，我们将 [i, j)[i,j) 向右滑动 11 个元素，则它将变为 [i+1, j+1)[i+1,j+1)（左闭，右开）。
     *
     * 回到我们的问题，我们使用 HashSet 将字符存储在当前窗口 [i, j)[i,j)（最初 j = ij=i）中。 然后我们向右侧滑动索引 jj，如果它不在 HashSet 中，我们会继续滑动 jj。直到 s[j] 已经存在于 HashSet 中。此时，我们找到的没有重复字符的最长子字符串将会以索引 ii 开头。如果我们对所有的 ii 这样做，就可以得到答案。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * 优化的滑动窗口
     * 如果 s[j]s[j] 在 [i, j)[i,j) 范围内有与 j'j
     * ′
     *   重复的字符，我们不需要逐渐增加 ii 。 我们可以直接跳过 [i，j'][i，j
     * ′
     *  ] 范围内的所有元素，并将 ii 变为 j' + 1j
     * ′
     *  +1。
     */

    /**
     public int lengthOfLongestSubstring(String s) {
     int max = 0;
     String curStr = "";
     int startIndex = 0;
     for (int i = 0; i < s.length(); i++) {
     char ch = s.charAt(i);
     int index = curStr.indexOf(ch);
     if (index < 0){
     curStr += ch;
     }else {
     max = Math.max(max,curStr.length());
     curStr = "";
     i = s.indexOf(ch,startIndex);
     startIndex = i+1;
     }
     }

     return Math.max(max,curStr.length());
     }
     */
}
