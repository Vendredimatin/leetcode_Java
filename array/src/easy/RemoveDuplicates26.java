package easy;

public class RemoveDuplicates26 {
    public static void main(String[] args) {
        int[] nums = {1,1,2};
        System.out.println(new RemoveDuplicates26().removeDuplicates(nums));
    }

    /**
     * tag: array, two pointers
     * 慢指针指向相同的元素，快指针一直往前探索，直到探索到与慢指针值不同的地方，然后nums[i+1] = nums[j]
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;

        int left = 0;
        int right = 0;
        while (nums[left] == nums[right]) right++;
        int res = 1;

        while (right < nums.length) {
            if (nums[left] != nums[right]){
                left++;
                nums[left] = nums[right];
                while (right < nums.length && nums[right] == nums[left]) right++;
                res++;
            }
        }
        return res;
    }

    public int removeDuplicates2(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
