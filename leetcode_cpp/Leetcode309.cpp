//
// Created by 刘汉乙 on 2020/9/28.
//
#include<vector>
using namespace std;

int maxProfit(vector<int>& prices) {
    int n = prices.size();
    vector<int> dp(n);
    dp[0] = 0;

    for(int i = 1; i < n; i++){

    }
    
    return dp[n-1];
}

int main309(){
    vector<int> prices({1,2,3,0,2});
    maxProfit(prices);
    return 0;
}