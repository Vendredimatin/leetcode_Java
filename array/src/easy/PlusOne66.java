package easy;

import java.util.Arrays;

public class PlusOne66 {
    public static void main(String[] args) {
        int[] digits = {4,3,2,1};
        System.out.println(new PlusOne66().plusOne(digits));
    }

    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length-1; i >= 0; i--) {
            int num = digits[i]+carry;
            digits[i] = num%10;
            carry = num/10;
        }

        if (carry != 0){
            int[] res = new int[digits.length+1];
            res[0] = carry;
            for (int i = 0; i < digits.length; i++) {
                res[i+1] = digits[i];
            }
            return res;
        }

        System.out.println(Arrays.toString(digits));
        return digits;
    }
}
