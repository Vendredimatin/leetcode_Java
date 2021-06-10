//
// Created by 刘汉乙 on 2020/10/7.
//
#include<unordered_set>
#include<vector>
using namespace std;
int minPatches(vector<int>& nums, int n) {
    int originSize = nums.size();
    if(nums.size() == 0 || nums[0] != 1){
        nums.insert(nums.begin(), 1);
    }
    int end = 0;

    for(int i = 0; i < nums.size()-1; i++){
        end = end + nums[i];
        if(end >= n) return nums.size() - originSize;
        if(end < nums[i+1] && end + 1 != nums[i+1])  nums.insert(nums.begin() + i+1, end + 1);
    }

    while(end + nums.back() < n){
        end += nums.back();
        nums.push_back(end  + 1);
    }

    return nums.size() - originSize;
}

int main330(){
    vector<int> nums({1,7,21,31,34,37,40,43,49,87,90,92,93,98,99});
    minPatches(nums, 12);
    return 0;
}