//
// Created by 刘汉乙 on 2021/6/6.
//

#include<iostream>
#include<vector>
using namespace std;
//https://www.luogu.com.cn/problem/solution/P2392
//深搜思路，把作业分别放进左脑和右脑，最后去左脑和右脑的最大值就是该课的最终结果，一开始想得太复杂了，想把左脑和右脑一起处理
//summary：搜索简单化
//01背包问题
//背包容量相当于t/2,最好的结果当然是t/2，意味着左脑和右脑之间没有时间差，即求时间差尽量小，单脑时间尽可能接近t/2，所以背包容量定为t/2，使得背包内价值尽可能高，价值就是时间，体积也是时间。
//每个作业有放入和不放入的情况，所以就是01背包问题。dp[i] = max(dp[i], dp[i-w[i]]+v[i]);

int left_; int right_; int min_;
void search1(int k, vector<int> v1){
    if(k >= v1.size()){
        min_ = min(min_, max(left_, right_));
        return;
    }

    left_ += v1[k];
    search1(k+1, v1);
    left_ -= v1[k];
    right_ += v1[k];
    search1(k+1, v1);
    right_ -= v1[k];
}

int search(vector<int> v){
    int sum = 0;
    for(int num:v) sum+=num;
    vector<int> dp(v.size());

    for (int i = 0; i < v.size(); i++){
        for (int j = sum/2; j >= v[i]; j--){
            dp[j] = max(dp[j], dp[j - v[i]] + v[i]);
        }
    }

    return max(dp[sum/2], sum - dp[sum/2]);
}

int main2392(){
    int n1,n2,n3,n4;
    cin >> n1;
    cin >> n2;
    cin >> n3;
    cin >> n4;

    vector<int> v1(n1);
    vector<int> v2(n2);
    vector<int> v3(n3);
    vector<int> v4(n4);
    int ans = 0;
    left_ = 0; right_ = 0; min_=INT_MAX;
    for(int i = 0; i < n1; i++){
        cin >> v1[i];
    }
    ans += search(v1);
    //ans += min_;
    left_ = 0; right_ = 0; min_=INT_MAX;
    for(int i = 0; i < n2; i++){
        cin >> v2[i];
    }
    ans += search(v2);
    //ans += min_;
    left_ = 0; right_ = 0; min_=INT_MAX;
    for(int i = 0; i < n3; i++){
        cin >> v3[i];
    }
    ans += search(v3);
    //ans += min_;
    left_ = 0; right_ = 0; min_=INT_MAX;
    for(int i = 0; i < n4; i++){
        cin >> v4[i];
    }
    ans += search(v4);
    //ans += min_;

    cout << ans;


    return ans;
}