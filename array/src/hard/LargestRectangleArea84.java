package hard;

import java.util.Stack;

/**
 * @program: leetcode
 * @description: 84. Largest Rectangle in Histogram
 * array stack
 * @author: Liu Hanyi
 * @create: 2019-05-07 16:02
 **/

public class LargestRectangleArea84 {
    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(new LargestRectangleArea84().largestRectangleArea4(heights));
    }

    public int largestRectangleArea(int[] heights) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int area = heights[i];
            int minHeight = heights[i];
            for (int j = i + 1; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                area = Math.max(area, (j - i + 1) * minHeight);
            }

            max = Math.max(area, max);
        }
        return max;
    }

    public int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[heights.length]; // idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[heights.length]; // idx of the first bar the right that is lower than current
        lessFromRight[heights.length - 1] = heights.length;
        lessFromLeft[0] = -1;

        for (int i = 1; i < heights.length; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i])
                p = lessFromLeft[p];

            lessFromLeft[i] = p;
        }

        for (int i = heights.length - 2; i >= 0; i--) {
            int p = i + 1;
            while (p < heights.length && heights[p] >= heights[i])
                p = lessFromRight[p];

            lessFromRight[i] = p;
        }

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return max;
    }

    public int largestRectangleArea3(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : height[i]);
            if (s.isEmpty() || h >= height[s.peek()]) {
                s.push(i);
            } else {
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }

    public int largestRectangleArea4(int[] height) {
        if (height.length == 0)
            return 0;
        return largestRectangleArea(height, 0, height.length - 1);
    }

    private int largestRectangleArea(int[] height, int start, int end) {
        if (start == end)
            return height[start];

        int mid = start + (end - start) / 2;

        int area = largestRectangleArea(height, start, mid);

        area = Math.max(area, largestRectangleArea(height, mid + 1, end));

        area = Math.max(area, largestRectangleArea(height, start, mid, end));

        return area;
    }

    private int largestRectangleArea(int[] height, int start, int mid, int end) {
        int i = mid, j = mid + 1;

        int area = 0;
        int h = Math.min(height[i],height[j]);
        while (i >= start && j <= end){
            h = Math.min(h,Math.min(height[i],height[j]));
            area = Math.max(area,h * (j-i+1));
            if (i == start)
                j++;
            else if (j == end)
                i--;
            else {
                if (height[i-1] > height[j+1])
                    i--;
                else j++;
            }
        }

        return area;
    }
}
