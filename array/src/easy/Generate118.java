package easy;

import java.util.ArrayList;
import java.util.List;

public class Generate118 {
    public static void main(String[] args) {
        System.out.println(new Generate118().generate(5));
    }

    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0)
            return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);

        for (int i = 1; i < numRows; i++) {
            List<Integer> list = new ArrayList<>(i+1);
            List<Integer> lastRow = res.get(i-1);
            list.add(1);
            for (int j = 1; j <= i-1; j++) {
                list.add(lastRow.get(j-1)+lastRow.get(j));
            }
            list.add(1);
            res.add(list);
        }

        return res;
    }
}
