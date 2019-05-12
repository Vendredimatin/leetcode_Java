package easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MissingNumber268 {
    public static void main(String[] args) {
        int[] nums = {9,6,4,2,3,5,7,0,1};
        System.out.println(new MissingNumber268().missingNumber(nums));
    }

    /**
     * tag: array,math,bit manipulation
     * 因为是由连续数字构成，所以很容易想到求和
     * summary：连续->sum
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int n = nums.length + 1;
        int sum = n*(n-1)/2;

        for (int num:nums) {
            sum -= num;
        }

        return sum;
    }

    /**
     * 方法二：hashset
     */
    public int missingNumber2(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) numSet.add(num);

        int expectedNumCount = nums.length + 1;
        for (int number = 0; number < expectedNumCount; number++) {
            if (!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
    }

    /**
     * 方法三：排序
     * @param nums
     * @return
     */
    public int missingNumber3(int[] nums) {
        Arrays.sort(nums);

        // Ensure that n is at the last index
        if (nums[nums.length-1] != nums.length) {
            return nums.length;
        }
        // Ensure that 0 is at the first index
        else if (nums[0] != 0) {
            return 0;
        }

        // If we get here, then the missing number is on the range (0, n)
        for (int i = 1; i < nums.length; i++) {
            int expectedNum = nums[i-1] + 1;
            if (nums[i] != expectedNum) {
                return expectedNum;
            }
        }

        // Array was not missing any numbers
        return -1;
    }

    /**
     * 方法四：位操作
     * 利用一个数的抑或是它本身
     * 因为连续的，所以可以知道如果排序后，那么i==nums[i]，那么i^nums[i]=0，所以可以使用位操作
     * summary：涉及到两个数相同，使用位操作
     */
    public int missingNumber4(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i^nums[i];
        }

        return missing;
    }
}
