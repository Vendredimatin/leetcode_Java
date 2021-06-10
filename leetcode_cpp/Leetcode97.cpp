//
// Created by 刘汉乙 on 2020/9/12.
//
#include<string>
using namespace std;

bool helper(string s1, string s2, string s3, int idx1, int idx2, int idx3){
    if(idx3 == s3.size() && idx2 == s2.size() && idx1 == s1.size())
        return true;

    bool res = false;
    if(idx1 < s1.size() && idx2 < s2.size() && s1[idx1] == s2[idx2] && s1[idx1] == s3[idx3]){
        res |= helper(s1,s2,s3,idx1+1,idx2,idx3+1);
        res |= helper(s1,s2,s3,idx1,idx2+1,idx3+1);
    }else if(idx1 < s1.size() && s1[idx1] == s3[idx3])
        res = helper(s1,s2,s3,idx1+1, idx2, idx3+1);
    else if(idx2 < s2.size() && s2[idx2] == s3[idx3])
        res = helper(s1,s2,s3,idx1,idx2+1, idx3+1);

    return res;
}

bool isInterleave(string s1, string s2, string s3) {
    bool res = helper(s1,s2,s3,0,0,0);
    return res;
}

int main97(){
    string s1 = "a";//"aabcc";
    string s2 = "b";//"dbbca";
    string s3 = "a";//adbbbaccc";

    isInterleave(s1,s2,s3);
    return 0;
}