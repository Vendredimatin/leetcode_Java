//
// Created by 刘汉乙 on 2020/9/11.
//
#include<string>
#include<vector>
#include<algorithm>
#include<unordered_set>
using namespace std;

int binaryToDemical(string str){
    int num = 0;
    int k = 0;
    for(int i = str.size()-1; i >=0; i--,k++){
        if(str[i] == '1')
            num += pow(2,k);
    }
    return num;
}

void helper(vector<int>& ans, unordered_set<string>& strSet, string str, int index){
    if(strSet.size() == pow(2, str.size()))
        return;

    for(int i = index; i < str.size(); i++){
        string temp = str;
        if(temp[i] == '0')
            temp[i] = '1';
        else temp[i] = '0';

        if(strSet.find(temp) == strSet.end()){
            strSet.insert(temp);
            ans.push_back(binaryToDemical(temp));
            helper(ans, strSet, temp, 0);
            if(ans.size() == pow(2, str.size()))
                return;

            strSet.erase(temp);
            ans.pop_back();
        }
    }



}

vector<int> grayCode(int n) {
    vector<int> ans;
    unordered_set<string> strSet;
    string str = "";
    for(int i = 0; i < n; i++) str += "0";
    strSet.insert(str);
    ans.push_back(binaryToDemical(str));

    helper(ans, strSet, str, 0);

    return ans;
}

int main89(){
    grayCode(5);
    return 0;
}