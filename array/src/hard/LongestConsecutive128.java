package hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class LongestConsecutive128 {
    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(new LongestConsecutive128().longestConsecutive(nums));
    }

    public int longestConsecutive(int[] nums) {

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int res = 1;
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()){
            int start = it.next();
            int left = start;
            int right = start;
            set.remove(start);
            while (set.remove(--left));
            while (set.remove(++right));
            res = Math.max(res, right-left-1);

            it = set.iterator();
        }

        return res;
    }
}
