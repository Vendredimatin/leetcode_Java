package easy;

import java.util.Arrays;

public class Merge88 {
    public static void main(String[] args) {
        int[] nums1 = {0,0,3,0,0,0,0,0,0};
        int[] nums2 = {-1,1,1,1,2,3};
        new Merge88().merge(nums1,3,nums2,6);
    }

    /**
     * tag:Array,two pointers
     * 只是用O（1）空间的解法：从末尾用两根指针法排序
     * summary：从开头进行排序非常困难，要将后面的点依次往后排，所以不如换一种思维，从后往前排
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m-1;
        int right = n-1;

        for (int i = m+n-1; i >= 0 && left>= 0 && right >= 0; i--) {
            if (nums1[left] > nums2[right]){
                nums1[i] = nums1[left];
                left--;
            }else {
                nums1[i] = nums2[right];
                right--;
            }
        }

        if (right >= 0){
            for (int i = right; i >= 0; i--) {
                nums1[i] = nums2[i];
            }
        }

        System.out.println(Arrays.toString(nums1));
    }
}
