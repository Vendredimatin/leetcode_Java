//
// Created by 刘汉乙 on 2020/9/15.
//
#include<vector>
#include<string>
using namespace std;

int helper(vector<vector<bool>>& dp,string s, int start){
    if(start == s.size())   return 0;

    int minCut = INT_MAX;
    for(int i = start; i < s.size(); i++){
        if(!dp[start][i]){
            if(start+1 == i && s[start] == s[i]) dp[start][i] = true;
            else if(s[start] == s[i] && dp[start+1][i-1]) dp[start][i] = true;
        }

        if(dp[start][i]){
            int rightCut = helper(dp, s, i+1);
            minCut = min(minCut, rightCut);
        }
    }

    return (dp[start][s.size()-1])?minCut:minCut + 1;

    /*if(start == end) return 0;
    if(s[start] == s[end] && start +1 == end){
        dp[start][end] = true;
        return 0;
    }
    if(s[start] == s[end] && dp[start+1][end-1] == true){
        dp[start][end] = true;
        return 0;
    }
    //接下来的情况只能内部进行进一步的分割
    //最差最差也是能切分的，比如把每一个都切出来。
    int minCut = INT_MAX;
    for(int i = start+1; i <= end; i++){
        int leftCut = helper(dp, s, start, i-1);
        int rightCut = helper(dp, s, i, end);
        minCut = min(minCut, leftCut+rightCut + 1);

    }
    return minCut;*/
}

int minCut(string s) {
    vector<vector<bool>> dp(s.size(), vector<bool>(s.size()));
    for(int i = 0; i < s.size(); i++){
        dp[i][i] = true;
    }

    int cut = helper(dp, s, 0);
    return cut;
}

int main132(){
    string s = "a";
    minCut(s);
    return 0;
}