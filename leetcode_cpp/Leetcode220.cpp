//
// Created by 刘汉乙 on 2020/9/18.
//
#include<vector>
using namespace std;

bool containsNearbyAlmostDuplicate(vector<int>& nums, int k, int t) {
    for(int i = 1; i < nums.size(); i++){
        for(int j = k; j > 0; j--){
            if(i-j >= 0 && abs(nums[i]-nums[i-j]) <= t)
                return true;
        }
    }

    return false;
}

int main220(){
    vector<int> nums({1,5,9,1,5,9});
    containsNearbyAlmostDuplicate(nums,2,3);
    return 0;
}

