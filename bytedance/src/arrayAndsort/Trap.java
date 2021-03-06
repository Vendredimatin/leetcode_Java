package arrayAndsort;

/**
 * @program: leetcode
 * @description: 接雨水
 * @author: Liu Hanyi
 * @create: 2019-04-12 10:58
 **/

public class Trap {
    public static void main(String[] args) {
        int[] A = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new Trap().trap(A));
    }

    /**
     * https://leetcode.com/articles/trapping-rain-water/ 各种解答
     * tag: array, stack, two pointers
     * method1:two pointers
     * 这个方法与我之前的误区就是，我之前想的是找到一个区间，然后遍历这个区间获得这个区间的积水量，但有个问题在于，方向和突然出现的更高峰会使判断复杂
     * 这个方法精妙在，不是着眼于区间，而是着眼于ｉ上的位置能积多少水，将每个ｉ上位置积的水加起来就行
     * 因此要求ｉ左边或右边的最大值，这里要求根据两边最大值中的小一个进行求解，因为如果根据大的一个进行求解，那么小的一边水就会溢出
     * 虽然微观上只求ｉ位置上的积水，但宏观来看，还是求区间的积水
     * 将上面暴力解的思想进行优化，就得到两根指针的解法
     * 对两边中小的一边进行计算，如果左边最大ｂａｒ高度大于Ａ［ｌｅｆｔ］，那么就相当于在left可以存水，然后不断更新ｌｅｆｔＭａｘ
     * 右边也同样道理
     * ｓｕｍｍａｒｙ：计算每一个ｉ位置上的积水，而不要直接确定区间！
     * @param A
     * @return
     */
    public int trap(int[] A) {
        int leftMax = 0,rightMax = 0;
        int left = 0;
        int right = A.length-1;

        int res = 0;
        while (left < right){
            if (A[left] < A[right]){
                leftMax = Math.max(A[left],leftMax);
                if (leftMax > A[left]) res += leftMax - A[left];
                left++;
            }else {
                rightMax = Math.max(A[right],rightMax);
                if (rightMax > A[rightMax]) res += rightMax - A[right];
                right--;
            }
        }

        return res;
    }
}
