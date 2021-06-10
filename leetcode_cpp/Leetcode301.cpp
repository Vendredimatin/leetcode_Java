//
// Created by 刘汉乙 on 2020/9/26.
//
#include<string>
#include<vector>
#include<stack>
#include <unordered_set>

using namespace std;
void helper(vector<string>& ans, string s, int index, stack<char> st, string temp){
    if(index == s.size()){
        while(!st.empty()){
            if(st.top() == '(') return;
            st.pop();
        }
        if(ans.size() > 0){
            if(temp.size() > ans[0].size()){
                ans.clear();
                ans.push_back(temp);
            }else if(temp.size() == ans[0].size())
                ans.push_back(temp);
        }else ans.push_back(temp);

        return;
    }

    char ch = s[index];
    helper(ans, s, index+1, st ,temp);

    temp += ch;
    if(ch == ')'){
        while(!st.empty() && st.top() != '('){
            st.pop();
        }
        if(st.empty()) return;
        else st.pop();

    }else st.push(ch);

    helper(ans, s , index+1, st, temp);

}

vector<string> removeInvalidParentheses(string s) {
    vector<string> ans;
    stack<char> st;
    helper(ans, s, 0, st, "");

    if(ans.size() == 0)
        ans.push_back("");

    unordered_set<string> set(ans.begin(), ans.end());
    ans.assign(set.begin(), set.end());

    return ans;
}

int main301(){
    string s = "()())()";
    removeInvalidParentheses(s);
    return 0;
}