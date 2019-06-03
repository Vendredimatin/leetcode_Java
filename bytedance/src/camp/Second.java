package camp;

import java.util.*;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class Second {
    public static List<String> canSplit = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String unSplitVar = sc.nextLine();
        String splitVar = sc.nextLine();
        List<String> vars = Arrays.asList(splitVar.split(" "));

        boolean res = false;
        for (int i = 0; i < vars.size(); i++) {
            String var = vars.get(i);
            int preIndex = unSplitVar.indexOf(var);
            while (preIndex > -1) {
                String pre = unSplitVar.substring(0, preIndex);
                String post = unSplitVar.substring(preIndex + var.length());
                res |= isFound(pre, vars) && isFound(post, vars);

                if (!res)
                    preIndex = unSplitVar.indexOf(var, preIndex+var.length());
                else break;
            }
            if (!res) break;
        }
        if (res) System.out.println("True");
        else System.out.println("False");
    }

    public static boolean isFound(String unSplitVar, List<String> vars) {
        if (unSplitVar.equals("") || canSplit.indexOf(unSplitVar) > -1) return true;

        if (vars.indexOf(unSplitVar) > -1) {
            canSplit.add(unSplitVar);
            return true;
        }

        boolean res = false;
        for (int i = 0; i < vars.size(); i++) {
            String var = vars.get(i);
            int preIndex = unSplitVar.indexOf(var);
            if (preIndex > -1) {
                String pre = unSplitVar.substring(0, preIndex);
                String post = unSplitVar.substring(preIndex + var.length());
                res = isFound(pre, vars) && isFound(post, vars);
                if (res) break;
            }
        }

        return res;
    }
}
