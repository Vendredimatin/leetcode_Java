package arrayAndsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: leetcode
 * @description: 三数之和
 * @author: Liu Hanyi
 * @create: 2019-03-26 17:29
 **/

public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(new ThreeSum().threeSum(nums));
    }

    /**
     * topic:array,two pointers
     * method:首先排序，排序一方面可以知道哪边指针该移动，而且避免了重复
     * 思路：找三个数的和可以转换为找两个数的和
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length-2; i++) {

            if (i == 0 || (i > 0 && nums[i] != nums[i-1])){
                int low = i+1, high = nums.length-1, sum = -nums[i];
                while (low < high){
                    if (nums[low] + nums[high] == sum){
                        res.add(Arrays.asList(nums[i],nums[low],nums[high]));
                        while (low < high && nums[low] == nums[low+1]) low++;
                        while (low < high && nums[high] == nums[high-1]) high--;
                        low++;high--;
                    }else if (nums[low] + nums[high] < sum) low++;
                    else high--;
                }
            }
        }

        return res;
    }
}
