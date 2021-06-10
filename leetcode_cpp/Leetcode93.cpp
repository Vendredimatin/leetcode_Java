//
// Created by 刘汉乙 on 2020/9/11.
//
#include<string>
#include<vector>
using namespace std;

string convert(vector<string> ip){
    string s = ip[0];
    for(int i = 1; i < 4; i++)
        s = s + "." + ip[i];
    return s;
}

void helper(vector<string>& ans, vector<string> ip, string s, int index){
    if(ip.size() == 4 && index < s.size())
        return;
    if(ip.size() == 4 && index == s.size()){
        ans.push_back(convert(ip));
        return;
    }

    string temp = "";
    int limit = (s[index] == '0') ? 1:3;
    for(int k = 0; k < limit && index + k < s.size(); k++){
        int i = index + k;
        temp += s[i];
        if(temp.size() == 3 && temp > "255") break;

        ip.push_back(temp);
        helper(ans, ip, s, i + 1);
        ip.pop_back();
    }



}

vector<string> restoreIpAddresses(string s) {
    vector<string> ans;
    vector<string> ip;
    helper(ans, ip, s, 0);
    return ans;
}

int main93(){
    string s = "1111";
    restoreIpAddresses(s);
    return 0;
}