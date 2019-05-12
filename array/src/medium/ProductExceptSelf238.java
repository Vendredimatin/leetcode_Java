package medium;

/**
 * @program: leetcode
 * @description: leetcode 238
 * @author: Liu Hanyi
 * @create: 2019-02-08 16:46
 **/

public class ProductExceptSelf238 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(new ProductExceptSelf238().productExceptSelf2(nums));
    }

    /**
     * tag: array
     * 动态规划方法
     * @param nums
     * @return
     */
    public int[] productExceptSelf1(int[] nums) {
        int i, j;

        int[] leftDP = new int[nums.length];
        int[] rightDP = new int[nums.length];

        int leftProduct = 1;
        int rightProduct = 1;
        leftDP[0] = 1;
        rightDP[nums.length-1] = 1;

        for (i = 1, j = nums.length-2; i < nums.length; i++,j--) {
            leftProduct *= nums[i-1];
            rightProduct *= nums[j+1];
            leftDP[i] = leftProduct;
            rightDP[j] = rightProduct;
        }

        int[] answer = new int[nums.length];
        for (int k = 0; k < nums.length; k++) {
            answer[k] = leftDP[k]*rightDP[k];
        }

        return answer;
    }

    /**
     * 对动态规划的改进，将两个dp数组浓缩为两个变量，边乘边进行赋值，也可以理解为两根指针法
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int i, j;
        int leftProduct = 1;
        int rightProduct = 1;

        int[] answer = new int[nums.length];

        for (int k = 0; k < answer.length; k++) {
            answer[k] = 1;
        }

        for (i = 1, j = nums.length-2; i < nums.length; i++,j--) {
            leftProduct *=nums[i-1];
            rightProduct *= nums[j+1];

            answer[i] *= leftProduct;
            answer[j] *= rightProduct;
        }

        return answer;
    }

    /**
     * 另一种形式，两根指针变为一根指针，先正序乘一遍，再逆序乘一遍
     * @param nums
     * @return
     */
    public int[] productExceptSelf3(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0, tmp = 1; i < nums.length; i++) {
            result[i] = tmp;
            tmp *= nums[i];
        }
        for (int i = nums.length - 1, tmp = 1; i >= 0; i--) {
            result[i] *= tmp;
            tmp *= nums[i];
        }
        return result;
    }
}
