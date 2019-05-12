package easy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MoveZeros283 {
    public static void main(String[] args) {
        int[] nums = {4,2,4,0,0,3,0,5,1,0};
        new MoveZeros283().moveZeroes1(nums);
    }
    /**category：Array Transformation
     *  summary：题目中有两种状态，一个为0,一个非0,那么用两根指针分别表示
     *  array: two points, two points
     *  left标识着为0的元素，right标识着非0元素，每当right指针标识非0时，就和left交换，这样保证了在原数组上交换，同时只遍历一次
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;

        while (left < nums.length && nums[left] != 0) {
            left++;
            right++;
        }

        while (right < nums.length && nums[right] == 0) right++;

        while (right < nums.length && left < nums.length) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                while (left < nums.length && nums[left] != 0) left++;
            }
            right++;
        }

        System.out.println(Arrays.toString(nums));
    }

    /**
     * optimal
     * 跟我的一样是最优解法，简化了我很多不必要的操作
     * @param nums
     */
    public void moveZeroes1(int[] nums){
        for (int lastNonZeroFoundAt = 0,cur = 0; cur < nums.length ; cur++) {
            if (nums[cur] != 0){
                swap(nums,lastNonZeroFoundAt,cur);
                lastNonZeroFoundAt++;
            }
        }
    }

    /**
     * 时间和空间都是O（n）的解法，先遍历一次记录0的个数，再遍历一次将非0的按原来的顺序放到新数组中，然后在往新数组的尾部添加0
     * @param nums
     */
    public void moveZroes2(int[] nums){
        int n = nums.length;
        int numsOfZero = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) numsOfZero++;
        }

        Queue<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) stack.offer(nums[i]);
        }

        while (numsOfZero-- > 0)
            stack.offer(0);

        for (int i = 0; i < n; i++) {
            nums[i] = stack.poll();
        }
    }

    /**
     * 空间O（1），时间O（n）但是对数组的总操作数要多于optimal解法
     * 还是相同的，lastNonZeroFoundAt表示为0元素，当i指针标识的元素为非0时，就直接赋值
     * 然后再从lastNonZeroFoundAt开始，将后面所有的元素标识为0
     * @param nums
     */
    public void moveZroes3(int[] nums){
        int lastNonZeroFoundAt = 0;
        // If the current element is not 0, then we need to
        // append it just in front of last non 0 element we found.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroFoundAt++] = nums[i];
            }
        }
        // After we have finished processing new elements,
        // all the non-zero elements are already at beginning of array.
        // We just need to fill remaining array with 0's.
        for (int i = lastNonZeroFoundAt; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    private void swap(int[] nums,int left,int right){
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
