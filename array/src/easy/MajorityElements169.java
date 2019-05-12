package easy;

import java.util.Random;

public class MajorityElements169 {
    /**
     * Boyer-Moore Voting Algorithm 多数投票算法
     * 算法思路：一旦两个相邻的元素不同，就把这两个元素对冲抵消掉。
     * 由于众数的出现频次大于数据其他元素出现频率次之和，所以这种抵消最后剩下的一定是众数
     * <p>
     * tag：array，divide and conquer
     * summary：与众数相关的投票算法
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int answer = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                answer = num;
                count++;
            } else if (answer == num) {
                count++;
            } else if (answer != num)
                count--;
        }

        return answer;
    }

    /**
     * 方法一：检查每个元素的个数是否大于1/2
     * 二：使用hashmap记录个数
     * 三：排序
     */

    /**
     * 方法四： 因为主元素更可能被选中，就随机挑选一个元素，检查他的个数是否大于二分之一
     *
     * @param
     * @param
     * @param
     * @return
     */
    public int majorityElement1(int[] nums) {
        Random rand = new Random();

        int majorityCount = nums.length / 2;

        while (true) {
            int candidate = nums[randRange(rand, 0, nums.length)];
            if (countOccurences(nums, candidate) > majorityCount) {
                return candidate;
            }
        }
    }


    private int randRange(Random rand, int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    /**
     * 采用分治法，分别得到数组左边的主元素和右边的主元素，如果两边主元素是同一个数，那直接return
     * 如果不一样，就需要计算搜索范围看哪一个出现的次数更多就返回哪一个
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    private int countOccurences(int[] nums, int num) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }


    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    /**
     * 因为一个整数在32为机器上只有32位，那么可以使用一个长度为32的数组来记录输入数组中每1位中1的个数和0的个数，由于存在一个出现次数超过一半的元素，那么取第i位中出现次数多的（0或者1）即可以构成超过数组一半元素。
     */
    public int majorityElement5(int[] nums) {
        int ones = 0;
        int zeros = 0;

        int res = 0;
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < nums.length; j++) {
                int num = nums[i];
                if ((num & (1 << i)) != 0)
                    ones++;
                else zeros++;
            }

            if (ones > zeros)
                res |= (1 << i);
        }

        return res;
    }

}
