package medium;

/**
 * @program: leetcode
 * @description: leetcode 11
 * @author: Liu Hanyi
 * @create: 2019-02-12 21:16
 **/

public class MaxArea11 {
    public static void main(String[] args) {
        int[] height ={1,8,6,2,5,4,8,3,7};

        System.out.println(new MaxArea11().maxArea(height));
    }

    /**
     * tag:array,two pointers
     * 分别从两端开始，谁小移动谁，这样就能得到最大的水量，证明可见https://leetcode.com/problems/container-with-most-water/discuss/6091/Easy-Concise-Java-O(N)-Solution-with-Proof-and-Explanation
     * 因为如果移动大的，那么在另一端不变的情况下，水量绝对会变小，首先宽变小一位，而高度始终是以小的算的
     * 感觉还是不太理解原理
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right])
                    * (right - left));
            if (height[left] < height[right])
                left++;
            else
                right--;
        }

        return maxArea;
    }
}
