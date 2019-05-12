package arrayAndsort;

import java.util.PriorityQueue;

/**
 * @program: leetcode
 * @description: 数组中第ｋ个最大元素
 * @author: Liu Hanyi
 * @create: 2019-04-10 09:30
 **/

public class FindKthLargest {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        System.out.println(new FindKthLargest().findKthLargest(nums, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : nums) {
            pq.add(num);
        }

        int res = 0;
        for (int i = 0; i < k; i++) {
            res = pq.poll();
        }

        return res;
    }

    /**
     * 快排： 根据一次快排(Partition)的想法，我们知道一次随机快速排序可以确定一个有序的位置，这个位置的左边都小于这个数，右边都大于这个数，
     * 我们如果能找到随机快速排序确定的位置＝＝ｋ那么这个就是我们要找的数
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        //第ｋ大，所以应该是nums.length-k;如果是第Ｋ小，那么就应该是ｋ-1
        k = nums.length - k;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int j = partition(nums, l, h);
            if (j == k) {
                break;
            } else if (j < k) {
                l = j + 1;
            } else {
                h = j - 1;
            }
        }
        return nums[k];
    }

    private int partition(int[] a, int l, int h) {
        int i = l, j = h + 1;
        while (true) {
            while (a[++i] < a[l] && i < h) ;
            while (a[--j] > a[l] && j > l) ;
            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, l, j);
        return j;
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
