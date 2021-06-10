//
// Created by 刘汉乙 on 2020/10/10.
//
#include<vector>
using namespace std;

int integerBreak(int n) {
    if (n <= 2)
        return 1;

    vector<int> maxArr(n+1, 0);

    /** For a number i: write i as a sum of integers, then take the product of those integers.
    maxArr[i] = maximum of all the possible products */

    maxArr[1] = 0;
    maxArr[2] = 1; // 2=1+1 so maxArr[2] = 1*1

    for (int i=3; i<=n; i++) {
        for (int j=1; j<i; j++) {

            maxArr[i] = max(maxArr[i],max(j * (i-j) ,j*maxArr[i-j]));
        }
    }
    return maxArr[n];
}

int main343(){
    integerBreak(10);
    return 0;
}