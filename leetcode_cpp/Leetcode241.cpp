//
// Created by 刘汉乙 on 2020/9/20.
//
#include<string>
#include<vector>
using namespace std;

int cal(int left, int right, string symbol){
    if(symbol == "*")
        return left * right;
    else if(symbol == "+")
        return left + right;
    else return left - right;
}

vector<int> helper(vector<int>& ans, vector<string> input, int start, int end){
    vector<int> temp;
    if(start == end) {
        temp.push_back(stoi(input[start]));
        return temp;
    }
    for(int i = start; i <= end; i++){
        if(input[i] == "+" || input[i] == "-" || input[i] == "*"){
            vector<int> left = helper(ans, input, start, i-1);
            vector<int> right = helper(ans, input, i+1, end);
            for(int num1 : left){
                for(int num2 : right){
                    temp.push_back(cal(num1,num2, input[i]));
                }
            }
        }
    }

    return temp;
}

vector<int> diffWaysToCompute(string input) {
    vector<string> inputs;
    vector<int> ans;
    if(input.size() == 0) return ans;
    string str = "";
    for(char ch : input){
        if(ch == '+' || ch == '-' || ch == '*'){
            inputs.push_back(str);
            string symbol(1, ch);
            inputs.push_back(symbol);
            str = "";
        }else
            str += ch;
    }

    inputs.push_back(str);

    ans = helper(ans, inputs, 0, inputs.size()-1);

    return ans;
}

int main241(){
    string input = "11";
    diffWaysToCompute(input);
    return 0;
}