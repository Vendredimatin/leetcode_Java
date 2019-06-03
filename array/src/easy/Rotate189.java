package easy;

import java.util.Arrays;

public class Rotate189 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6};
        new Rotate189().rotate(nums,2);
    }

    public void rotate(int[] nums, int k) {
        int curIndex = 0;
        int nextIndex;
        int current;
        for (int i = 0; i < nums.length; curIndex++) {
            int start = curIndex;
            current = nums[curIndex];
            do {
                nextIndex = (curIndex + k) % nums.length;
                int next = nums[nextIndex];
                nums[nextIndex] = current;
                current = next;
                curIndex = nextIndex;
                i++;
            }while (start != curIndex);

        }

        System.out.println(Arrays.toString(nums));
    }

    /**
     * tag: Array
     * summary:精妙神奇的解法！先反转整个数组，然后再翻转前k个元素，再翻转后n-k个元素
     *
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
