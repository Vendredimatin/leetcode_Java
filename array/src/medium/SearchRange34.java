package medium;

/**
 * @program: leetcode
 * @description: Find First and Last Position of Element in Sorted Array
 * @author: Liu Hanyi
 * @create: 2019-05-02 11:04
 **/

public class SearchRange34 {
    public static void main(String[] args) {
        int[] num = {5,7,7,8,8,10};
        System.out.println(new SearchRange34().searchRange(num,8));
    }

    public int[] searchRange(int[] nums, int target) {
        int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
        int[] ans = searchRange(nums, target, 0, nums.length - 1,first,last);

        if (ans[0] == Integer.MAX_VALUE)
            return new int[]{-1,-1};
        return ans;
    }

    public int[] searchRange(int[] num, int target, int l, int r, int first, int last) {
        if (l > r) return new int[]{first, last};

        int mid = (l + r) / 2;
        if (num[mid] == target){
          first = Math.min(first,mid);
          last = Math.max(last,mid);
          int[] ans = new int[2];
          if (mid > 0 && num[mid-1] == target){
              ans = searchRange(num,target,l,mid-1,first,last);
              first = Math.min(first,ans[0]);
          }
          if (mid < num.length-1 && num[mid+1] == target){
              ans = searchRange(num,target,mid+1,r,first,last);
              last = Math.max(last,ans[1]);
          }
          return new int[]{first,last};
        } else if (num[mid] > target)
            return searchRange(num, target, l, mid-1, first, last);
        else return searchRange(num, target, mid+1, r, first, last);
    }
}
