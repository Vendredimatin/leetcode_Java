package medium;

/**
 * @program: leetcode
 * @description: leetcode 162
 * @author: Liu Hanyi
 * @create: 2019-02-14 15:44
 **/

public class FindPeakElement162 {
    public static void main(String[] args) {
        int[] nums = {1,2,1,3,5,6,4};
        System.out.println(new FindPeakElement162().findPeakElement(nums));
    }

    public int findPeakElement(int[] nums) {
        for (int i = 1; i < nums.length-1; i++) {
            if (nums[i] > nums[i+1]){
                if (nums[i] > nums[i-1])
                    return i;
                else i++;
            }
        }

        return 0;
    }
}
