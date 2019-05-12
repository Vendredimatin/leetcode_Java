package medium;

/**
 * @program: leetcode
 * @description: leetcode 75
 * @author: Liu Hanyi
 * @create: 2019-02-13 11:14
 **/

public class SortColors75 {
    public static void main(String[] args) {
        int[] nums =  {2,0,2,1,1,0};
        //int[] nums =  {2,0,1};
        new SortColors75().sortColors(nums);
    }

    /**
     * tag: array,two pointers
     * summary:在考虑的时候思考过于复杂，总是想着怎么排布三个数，其实只要拍好0和2，那么1就可以自动拍好，从反面思考w
     * @param nums
     */
    public void sortColors(int[] nums) {
        int left=0,right = nums.length-1;

        for (int i = 0; i <= right; i++) {
            if (nums[i] == 0 && left < right) swap(nums,i,left++);
            else if (nums[i] == 2 && left < right) swap(nums,i--,right--);
        }
    }

    public void swap(int[] nums,int desc,int source){
        int tmp = nums[desc];
        nums[desc] = nums[source];
        nums[source] = tmp;
    }
}
