package easy;

import java.util.*;

public class TwoSum1 {
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        System.out.println(Arrays.toString(new TwoSum1().twoSum(nums,9)));
    }

    /**
     * tag:Array, hashtable
     * 利用set/map检查容器内是否存在他的补数
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        int[] res = new int[2];
        int anotherNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(target-nums[i])){
                res[1] = i;
                anotherNum = target-nums[i];
                break;
            }
            set.add(nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == anotherNum){
                res[0] = i;
                break;
            }
        }

        return res;
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
