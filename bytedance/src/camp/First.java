package camp;

import java.util.Scanner;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class First {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*int n = sc.nextInt();
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            System.out.println(str);
        }*/
       /* while (true) {
            String nextLine = sc.nextLine();
            if (nextLine == null || nextLine.trim().length() == 0) {
                System.out.println("empty, break.");
                break;
            }
            System.out.println(nextLine);
        }*/

       int w = sc.nextInt();
       int b = sc.nextInt();
       int sum = w+b;
       int maxHeight = 0;
       int maxMethods = 0;
       int n = 1;
       while (true){
           int curMethods = 0;
           curMethods += cal(w,b,n);
           if (curMethods == 0)
               break;
           maxHeight = n;
           maxMethods = curMethods;
           n++;
       }
        System.out.println(maxHeight+" "+maxMethods);
    }

    public static int cal(int w, int b, int n){
        if (w < n && b < n)
            return 0;
        if (n == 0)
            return 1;

        int res = 0;
        if (w >= n)
            res += cal(w-n, b, n-1);
        if (b >= n)
            res += cal(w,b-n,n-1);
        return res;
    }
}

/**
 * public class Main{
 * public static void main(String[] args) {
 *        Scanner sc = new Scanner(System.in);
 *
 *        int w = sc.nextInt();
 *        int b = sc.nextInt();
 *        int sum = w+b;
 *        int n = 0;
 *        while (n*n + n <= sum*2){
 *            n++;
 *            if (n*n + n == sum*2)
 *                break;
 *        }
 *
 *        int height = n;
 *        int res = 0;
 *        while (n > 0){
 *            if (w >= n)
 *                res += cal(w-n, b, n-1);
 *            if (b >= n)
 *                res += cal(w, b-n, n-1);
 *            if (res <= 0)
 *                n--;
 *            else break;
 *        }
 *
 *         res = res % 1000000007;
 *         System.out.println(n+" "+res);
 *     }
 *
 *     public static int cal(int w, int b, int n){
 *         int res = 0;
 *         if (n == 0)
 *             return 1;
 *         if (w >= n)
 *             return res + cal(w-n, b, n-1);
 *         if (b >= n)
 *             return res + cal(w, b-n, n-1);
 *         return res;
 *     }
 * }
 */