package medium;

/**
 * @program: leetcode
 * @description: leetcode 162
 * @author: Liu Hanyi
 * @create: 2019-02-14 15:44
 **/

public class FindPeakElement162 {
    public static void main(String[] args) {
        int[] nums = {1,2,1,3,5,6,7};
        System.out.println(new FindPeakElement162().findPeakElement2(nums));
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

    public int findPeakElement2(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
