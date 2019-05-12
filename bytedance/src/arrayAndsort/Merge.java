package arrayAndsort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: leetcode
 * @description: 合并区间
 * @author: Liu Hanyi
 * @create: 2019-04-11 16:01
 **/

public class Merge {
    public static class Interval {
        int start;
        int end;

        public Interval() {
            start = 0;
            end = 0;
        }

        public Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(15, 18));
        System.out.println(new Merge().merge1(intervals));
    }

    /**
     * tag:array,sort
     * 根据ｓｔａｒｔ进行排序，然后比较ｅｎｄ，比较简单易懂
     * 但是在原数组进行操作，始终不是个好习惯
     *
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);

        int cur = 0;
        for (int i = 1; i < intervals.size(); i++) {
            Interval i_interval = intervals.get(i);
            Interval cur_interval = intervals.get(cur);

            if (i_interval.start <= cur_interval.end) {
                if (i_interval.end > cur_interval.end) {
                    cur_interval.end = i_interval.end;
                    intervals.set(cur, cur_interval);
                }
                intervals.remove(i--);
            } else cur++;

        }

        return intervals;
    }

    /**
     * improve
     *
     * @param intervals
     * @return
     */
    public List<Interval> merge1(List<Interval> intervals) {
        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);

        List<Interval> res = new ArrayList<>();
        res.add(intervals.get(0));

        for (int i = 1; i < intervals.size(); i++) {
            Interval last = res.get(res.size()-1);
            Interval cur = intervals.get(i);

            if (cur.start <= last.end){
                if (cur.end > last.end){
                    last.end = cur.end;
                    intervals.set(res.size()-1,last);
                }
            }else res.add(cur);
        }

        return res;
    }
}
