package hard;

import java.util.HashSet;

/**
 * @program: leetcode
 * @description: 41. First Missing Positive
 * @author: Liu Hanyi
 * @create: 2019-05-10 10:49
 **/

public class FirstMissingPositive41 {
    public static void main(String[] args) {
        int[] nums = {3,4,-1,1};
        System.out.println(new FirstMissingPositive41().firstMissingPositive2(nums));
    }

    //https://leetcode.com/problems/first-missing-positive/discuss/17073/Share-my-O(n)-time-O(1)-space-solution
    //https://leetcode.com/problems/first-missing-positive/discuss/17083/O(1)-space-Java-Solution
    //https://leetcode.com/problems/first-missing-positive/discuss/17214/Java-simple-solution-with-documentation
    public int firstMissingPositive(int[] nums) {
        //或者ｓｅｔ　ｆｉｎｄ　ｒｅｍｏｖｅ
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int i = 1;
        while (set.contains(i))
            i++;
        return i;
    }

    public int firstMissingPositive2(int[] nums){
        int i = 0;
        while (i < nums.length){
            if (nums[i] == i+1 || nums[i] <= 0 || nums[i] > nums.length) i++;
            else if (nums[nums[i] - 1] != nums[i]) swap(nums,i,nums[i]-1);
            else i++;
        }

        int res = 0;
        while (res < nums.length && nums[res] == res+1){
            res++;
        }

        return res+1;

      /*  int i = 0;
        while(i < A.length){
            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) i++;
            else if(A[A[i]-1] != A[i]) swap(A, i, A[i]-1);
            else i++;
        }
        i = 0;
        while(i < A.length && A[i] == i+1) i++;
        return i+1;*/
    }

    private void swap(int[] nums, int i, int i1) {
        int tmp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = tmp;
    }
}
