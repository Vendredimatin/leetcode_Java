//
// Created by 刘汉乙 on 2020/9/18.
//
#include<string>
using namespace  std;

string add(string s){
    string ans = "#";
    for(char ch : s){
        ans += ch;
        ans += "#";
    }

    return ans;
}

string deleteSymbol(string s){
    string ans = "";
    for(char ch : s){
        if(ch != '#') ans += ch;
    }
    return ans;
}

string shortestPalindrome(string s) {
    if(s.size() == 0) return 0;

    s = add(s);
    int mid = (0 + s.size()-1)/2;
    int right = mid + 1;
    while(right != s.size()-1){
        //当mid变为0时，把mid后面的元素全部补充到前面
        if(mid == 0){
            while(right != s.size()-1){
                s = s[right] + s;
                right+=2;
            }
            break;
        }

        int left = mid - 1;
        if(s[left] != s[right]) {
            mid--;
            right = mid + 1;
        }else{
            while(left >= 0 && s[left] == s[right]){
                left--;
                right++;
            }

            //left == 0
            if(left == -1 && right != s.size()){
                while(right != s.size()){
                    s = s[right] + s;
                    right+=2;
                }
                break;
            }else if(left == -1 && right == s.size())
                break;

            //left != 0,说明以mid为中心的探索失败，mid往前移动
            mid--;
            right = mid + 1;
        }

    }

    return deleteSymbol(s);
}

int main214(){
    string s =  "abcd";
    shortestPalindrome(s);
}