package easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainsDuplicate217 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(new ContainsDuplicate217().containsDuplicate2(nums));
    }

    /**
     * tag:array hashtable
     * summary:可以直接使用hashset而不需要使用hashMap
     * @param nums
     * @return
     */
    public boolean containsDuplicate1(int[] nums) {
        Map<Integer,Boolean> map = new HashMap<>();
        boolean res = false;
        for (int num:nums) {
            if (map.containsKey(num)){
                return true;
            }else
                map.put(num,false);
        }

        return res;
    }

    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            if (!set.add(num)) return true;
        }

        return false;
    }
}
