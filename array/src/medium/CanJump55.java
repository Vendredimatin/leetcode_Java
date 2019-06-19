package medium;

/**
 * @program: leetcode
 * @description: Jump Game
 * @author: Liu Hanyi
 * @create: 2019-05-05 16:22
 **/

public class CanJump55 {
    public static void main(String[] args) {
        //int[] nums = {2,3,1,1,4};//{3,2,1,0,4};
        int[] nums = {3,0,8,2,0,0,1};
        //int[] nums = {3,2,1,0,4};

        System.out.println(new CanJump55().canJump(nums));
    }

    public boolean canJump(int[] nums) {
        index = new Index[nums.length];
        for (int i = 0; i < nums.length-1; i++) {
            index[i] = Index.UNKNOWN;
        }

        index[nums.length-1] = Index.GOOD;
        return canJump2(nums,0);
    }

    enum Index {
        GOOD, BAD, UNKNOWN
    }
    /**
     * method1:不断回溯，但会超时
     * @param nums
     * @param start
     * @param max
     * @return
     */
    private boolean canJump(int[] nums, int start, int max) {
        if (nums[start] == 0 && start != nums.length-1)
            return false;
        if (start + max >= nums.length-1)
            return true;

        for (int i = max; i >= 1; i--) {
           if (canJump(nums,start+i,nums[start+i]))
                return true;
        }

        return false;
    }

    Index[] index;

    /**
     * method2:dp 自顶向下
     * 通过使用ｄｐ降低了复杂度，存储了index i是否能到达终点的结果，
     * 当再经过这些ｉｎｄｅｘ的时候不再需要计算
     * @param nums
     * @param start
     * @return
     */
    private boolean canJump2(int[] nums, int start) {
        start = Math.min(start,nums.length-1);

        if (index[start] != Index.UNKNOWN)
            return (index[start] == Index.GOOD)?true:false;

        int max = nums[start];
        for (int i = max; i >= 1; i--) {
            if (canJump2(nums,start+i)){
                index[start] = Index.GOOD ;
                return true;
            }
        }

        index[start] = Index.BAD;
        return false;
    }

    /**
     * method3: 自底向上，从右边开始跳，递归转循环
     * @param nums
     * @return
     */
    public boolean canJump３(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;
    }


    /**
     * method4:greedy
     * 从右开始遍历，只要当前ｐｏｓ+nums[pos]大于等于最右边的ｇｏｏｄ　ｉｎｄｅｘ，
     * 那么代表ｐｏｓ也是ｇｏｏｄ　ｉｎｄｅｘ．遍历完后如果ｐｏｓ　＝　０　则代表可以跳到
     * @param nums
     * @return
     */
    public boolean canJump４(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
}
