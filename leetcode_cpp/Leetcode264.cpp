//
// Created by 刘汉乙 on 2020/9/24.
//
#include<vector>
#include<queue>
using namespace std;

int nthUglyNumber(int n) {
    vector<int> dp(n);
    dp[0] = 1;
    dp[1] = 2;
    dp[2] = 3;
    int factors[3] = {2, 3, 5};
    priority_queue<int, vector<int>, greater<int>> pq;
    for(int i = 3; i < n; i++){
        for(int j = i-3; j < i; j++){
            for(int factor : factors){
                int num = factor * dp[j];
                pq.push(num);
            }
        }

        while(pq.top() <= dp[i-1]) pq.pop();
        dp[i] = pq.top();   pq.pop();
    }

    return dp[n-1];
}

int main264(){
    nthUglyNumber(10);
    return 0;
}
