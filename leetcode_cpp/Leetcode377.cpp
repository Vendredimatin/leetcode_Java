//
// Created by 刘汉乙 on 2021/6/4.
//
#include<vector>
#include<unordered_map>
using namespace std;

unordered_map<int, int> dp377;

void helper_tle(vector<int>& nums, int target, int cur, int *ans){
    for(int i = 0; i < nums.size(); i++){
        int num = nums[i];
        int tmpSum = num + cur;
        if(tmpSum > target) return;
        else if(tmpSum == target){
            (*ans)++;
            return;
        }else{
            helper_tle(nums,target,tmpSum,ans);
        }
    }
}

int helper377(vector<int>& nums, int target){
    int ans = 0;
    for(int i = 0; i < nums.size(); i++){
        int num = nums[i];
        int newTarget = target - num;
        if(newTarget < 0) break;
        else if(newTarget == 0) {
            ans+=1;
            break;
        }

        if(dp377.find(newTarget) != dp377.end()){
            ans += dp377[newTarget];

        }else{
            int tmp = helper377(nums, newTarget);
            ans += tmp;
        }
    }

    dp377[target] = ans;
    return ans;
}


int combinationSum4(vector<int>& nums, int target) {
    int ans = 0;
    sort(nums.begin(), nums.end());

    dp377[nums[0]] = 1;
    ans = helper377(nums, target);
    return ans;
}

int main377(){
    vector<int> nums({1,2,3});
    combinationSum4(nums, 35);
}
