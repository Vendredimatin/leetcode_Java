package medium;

/**
 * @program: leetcode
 * @description: leetcode 287
 * @author: Liu Hanyi
 * @create: 2019-02-09 09:03
 **/

public class FindDuplicate287 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        System.out.println(new FindDuplicate287().findDuplicate2(nums));
    }

    /**
     * tag: array, binarySerach, circle detection(two pointers)
     * explain:
     * 1. why,因为problem中提到顶多一个重复的数字，我们构造键值对，index i和value-i，下一个值value-j的index是value-i，那我们我们很容易构造出一个有环的结构
     * 我们的目标就是找出入口点，所以使用环检测算法（Floyd's Tortoise and Hare）
     * https://blog.csdn.net/xyzxiaoxiong/article/details/78761940作了详细的解释
     * phase1 tortoise即第一次相遇点
     * phase2 因为在h点第一次相遇，而为什么b=F在链接中也解释的很清楚，虽然这个公式不是太准确，但可以由小及大
     * <p>
     * 第一个循环就是hare一次走两步，tortoise一次走一步，那么在有环的情况下，他们迟早相遇，tortoise即为相遇点
     * 第二个循环，令ptr1为头指针，令ptr2为相遇的几点，因为b=F那么，那么他们每次走一步，那么将在入口节点相遇
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }

    /**
     * binary search 使用鸽笼原理，当数组内的数<= mid的个数多余mid时，将search范围放在mid～high，
     * 当个数小于等于mid时，将search范围放在low～mid，最后返回low/high（指的是索引）
     * 巧妙的运用二分法
     * 如n=10,mid=5,如果数组内小于等于5的个数多余5个，那么根据鸽笼原理，重复的数必在1～5内
     * https://leetcode.com/problems/find-the-duplicate-number/discuss/72844/Two-Solutions-(with-explanation)%3A-O(nlog(n))-and-O(n)-time-O(1)-space-without-changing-the-input-array
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            int count = 0;

            for (int num : nums) {
                if (num <= mid) count++;
            }

            if (count <= mid) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return high;
    }

    /**
     * 方法三sort
     * 方法四set
     */
}
