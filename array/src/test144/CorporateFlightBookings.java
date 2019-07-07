package test144;

import java.util.Arrays;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class CorporateFlightBookings {
    //https://www.cnblogs.com/TheRoadToTheGold/p/6254255.html 线段树
    public static void main(String[] args) {
        int[][] bookings = {{1,2,10},{2,3,20},{2,5,25}};
        new CorporateFlightBookings().corpFlightBookings(bookings, 5);
    }
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        for(int i = 0; i < bookings.length; i++){
            int[] booking = bookings[i];
            int start = booking[0];
            int end = booking[1];
            int k = booking[2];

            int total = (end >= start)?(end-start):(end + n - start);
            int j = 0;
            while (j <= total){
                int index = (j+start-1)%n;
                res[index] += k;
                j++;
            }
        }

        System.out.println(Arrays.toString(res));

        return res;
    }
}
