import java.util.Stack;

/**
 * @program: leetcode
 * @description: 翻转字符串
 * @author: Liu Hanyi
 * @create: 2019-03-26 16:59
 **/

public class ReverseWords {
    public static void main(String[] args) {
        String s = "the sky is blue";
        System.out.println(new ReverseWords().reverseWords(s));
    }

    public String reverseWords(String s) {
        Stack<String> stack = new Stack<>();

        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' '){
                if (temp.equals(""))
                    continue;
                else {
                    stack.push(temp);
                    temp = "";
                }
            }else temp += ch;
        }

        if (!temp.isEmpty()) stack.push(temp);

        String res = "";
        while (!stack.empty()){
            res += stack.pop() + " ";
        }

        return res.length() == 0? "" : res.substring(0,res.length()-1);
    }

    /**
     * method2 : 使用两根指针法
     */

    /**
     * split() 方法
     */
}
