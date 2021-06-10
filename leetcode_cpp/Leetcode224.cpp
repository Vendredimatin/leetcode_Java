//
// Created by 刘汉乙 on 2020/9/20.
//
#include<string>
#include<vector>
#include<stack>
#include<stack>
using namespace std;
vector<string> preprocess(string s){
    vector<string> inputs;

    string str = "";
    for(char ch:s){
        if(ch == ' '){
            if(str.size() > 0)
                inputs.push_back(str);
            str = "";
        }else if(ch == '(' || ch == ')' || ch == '+' || ch == '-'){
            if(str.size() > 0){
                inputs.push_back(str);
            }
            string a(1, ch);
            inputs.push_back(a);
            str = "";
        }else
            str += ch;
    }
    if(str.size() > 0)
        inputs.push_back(str);

    return inputs;
}

void cal(stack<int>& nums, stack<string>& ops){
    int ans = 0;
    int num1 = nums.top(); nums.pop();
    int num2 = nums.top(); nums.pop();
    string op = ops.top(); ops.pop();
    if(op == "+")
        ans = num1 + num2;
    else if(op == "-")
        ans = num1 - num2;
    nums.push(ans);
}

int calculate(string s) {
    stack<int> nums;
    stack<string> ops;
    vector<string> inputs = preprocess(s);

    for(int i = inputs.size()-1; i >= 0; i--){
        string str = inputs[i];
        if(str == "("){
            while(ops.top() != ")") cal(nums, ops);
            ops.pop();
        }else if(str == ")" || str == "+" || str == "-"){
            ops.push(str);
        }else nums.push(stoi(str));
    }

    while(!ops.empty()) cal(nums, ops);

    return nums.top();
}

int main224(){
    string s = "(1+(4+5+2)-3)+(6+8)";
    calculate(s);
    return 0;
}