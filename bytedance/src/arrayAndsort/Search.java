package arrayAndsort;

/**
 * @program: leetcode
 * @description: 搜索排序数组
 * @author: Liu Hanyi
 * @create: 2019-04-09 20:30
 **/

public class Search {
    public static void main(String[] args) {
        int[] nums = {3,4,5,6,1,2};//{4,5,6,7,0,1,2};
        System.out.println(new Search().search(nums,2));
    }


    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;

        return search(l,r,nums,target);
    }

    /**
     * recusive
     * tag: array,binary search
     * @param l
     * @param r
     * @param nums
     * @param target
     * @return
     */
    private int search(int l, int r, int[] nums, int target) {
        if (l > r || l == -1 || r == nums.length)
            return -1;

        int mid = (l + r)/2;
        int res = 0;

        if (l == mid){
            if (target == nums[l]) return l;
            else if (target == nums[r]) return r;
            else return -1;
        }

        if (nums[mid-1] < nums[mid] && nums[mid+1] > nums[mid]){
            if (target == nums[mid]) res = mid;
            else res = Math.max(search(l,mid-1,nums,target),search(mid+1,r,nums,target));
        }else if (nums[mid-1] > nums[mid]){
            if (target > nums[l]) res = search(l+1,mid-1,nums,target);
            else if (target < nums[l]) res = search(mid, r,nums, target);
            else if (target == nums[l]) res = l;
        }else if (nums[mid+1] < nums[mid]){
            if (target < nums[r]) res = search(mid+1, r-1,nums,target);
            else if (target > nums[r]) res = search(l, mid, nums,target);
            else if (target == nums[r]) res =  r;
        }

        return res;
    }

    /**
     * 迭代
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[start] <= nums[mid]){
                if (target < nums[mid] && target >= nums[start])
                    end = mid - 1;
                else
                    start = mid + 1;
            }

            if (nums[mid] <= nums[end]){
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }
}
