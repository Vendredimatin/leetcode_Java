//
// Created by 刘汉乙 on 2020/10/12.
//

#include<vector>
using namespace std;
vector<int> countBits(int num) {
    vector<int> dp(num+1);
    dp[0] = 0;
    dp[1] = 1;
    int k = 0;
    for(int i = 2; i <= num; i++){
        if(pow(2, k+1) == i){
            k = k+1;
            dp[i] = 1;
        }else{
            dp[i] = 1 + dp[i - pow(2,k)];
        }
    }
    return dp;
}

int main338(){
    countBits(16);
    return 0;
}