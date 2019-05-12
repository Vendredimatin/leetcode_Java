/**
 * @program: leetcode
 * @description: 最长公共前缀
 * @author: Liu Hanyi
 * @create: 2019-03-25 17:11
 **/

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strings = {"flower", "flow", "flight"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix4(strings));
    }

    /**
     * method1: 水平扫描
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        String longest = "";
        for (int i = 0; i < strs[0].length(); i++) {
            char ch = strs[0].charAt(i);
            for (int j = 1; j < n; j++) {
                String str = strs[j];
                if (i >= str.length() || str.charAt(i) != ch)
                    return longest;
            }

            longest += ch;
        }

        return longest;
    }

    /**
     * 神奇的解法，思路值得借鉴！
     * 两个字符串先找到公共字符串，之后再和第三个字符串比较
     * method2: 水平扫描２
     */
    public String longestCommonPrefix２(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    /**
     * 分治法：搜索的先后顺序不会影响最终结果的时候就可以考虑分治法
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    public String longestCommonPrefix(String[] strs, int left, int right) {
        if (left == right)
            return strs[left];

        int mid = (left + right) / 2;

        String leftCommonPrefix = longestCommonPrefix(strs, left, mid);
        String rightCommonPrefix = longestCommonPrefix(strs, mid + 1, right);

        return longestCommonePrefix(leftCommonPrefix, rightCommonPrefix);
    }

    public String longestCommonePrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());

        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i))
                return left.substring(0, i);
        }

        return left.substring(0, min);
    }

    /**
     * method4: 二分查找
     * 没想到可以这样二分查找
     * 这个想法是应用二分查找法找到所有字符串的公共前缀的最大长度 L。 算法的查找区间是 (0 \ldots minLen)(0…minLen)，其中 minLen 是输入数据中最短的字符串的长度，同时也是答案的最长可能长度。 每一次将查找区间一分为二，然后丢弃一定不包含最终答案的那一个。算法进行的过程中一共会出现两种可能情况：
     *
     * S[1...mid] 不是所有串的公共前缀。 这表明对于所有的 j > i S[1..j] 也不是公共前缀，于是我们就可以丢弃后半个查找区间。
     *
     * S[1...mid] 是所有串的公共前缀。 这表示对于所有的 i < j S[1..i] 都是可行的公共前缀，因为我们要找最长的公共前缀，所以我们可以把前半个查找区间丢弃。
     */
    public String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        int minLen = strs[0].length();
        for (String s : strs) {
            minLen = Math.min(s.length(), minLen);
        }

        int low = 0;
        int high = minLen;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (hasCommonPrefix(strs, mid)){
                low = mid+1;
            }else
                high = mid-1;
        }

        return strs[0].substring(0,(low+high)/2);
    }

    private boolean hasCommonPrefix(String[] strs, int mid) {
        String str = strs[0].substring(0,mid);
        for (String s:strs) {
            if (!s.startsWith(str))
                return false;
        }
        return true;
    }
}
