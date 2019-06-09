package test140;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class OccurrencesAfterBigram {
    public static void main(String[] args) {
        //注意这个测试用例问题
        //溢出
        String text = "we will we will rock you";
        new OccurrencesAfterBigram().findOcurrences2(text, "we", "will");
    }

    public String[] findOcurrences(String text, String first, String second) {
        String[] strs = text.split(" ");
        List<String> list = new ArrayList<>();

        for (int i = 0; i < strs.length - 2; i++) {
            if (strs[i].equals(first)) {
                int index = i;
                index++;
                if (strs[index].equals(second)) {
                    index++;
                    list.add(strs[index]);
                }
            }
        }

        String[] res = list.toArray(new String[0]);
        return res;
    }

    public String[] findOcurrences2(String text, String first, String second) {
        String regex = first + " " + second + " ";
        List<String> list = new ArrayList<>();

        int index = 0;
        int length = regex.length();
        int start = 0;
        while ((start = text.indexOf(regex, index)) > -1) {
            int nextSpacePos = text.indexOf(" ", start + length);
            String str = text.substring(start + length, nextSpacePos);
            index = start + first.length();
            list.add(str);
        }

        return list.toArray(new String[0]);
    }
}
