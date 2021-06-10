//
// Created by 刘汉乙 on 2020/9/30.
//
#include<vector>
#include <unordered_set>

using namespace std;

struct SegmentTree{
    SegmentTree* left;
    SegmentTree* right;
    int count;
    long min;
    long max;

    SegmentTree(vector<int>& nums, int l, int r):left(NULL),right(NULL){
        if(l > r)   return;
        min = nums[l];
        max = nums[r];
        count = 0;
        if(l == r) return;

        int mid = (l+r) >> 1;
        if(mid >= l)
            left = new SegmentTree(nums, l, mid);
        else left = NULL;

        if(mid < r)
            right = new SegmentTree(nums, mid+1, r);
        else right = NULL;
    }

    void addVal(long val){
        if(val < min || val > max)  return;

        //在区间内
        count++;
        if(left && val <= left->max)
            left->addVal(val);
        if(right && val >= right->min)
            right->addVal(val);
    }

    int getCount(int l, int r){
        //把该节点表示的区间都包含在内了
        if(l <= min && r >= max) return count;
        if(l > max || r < min) return 0;

        int res = 0;
        if (left)   res += left->getCount(l, r);
        if (right)  res += right->getCount(l,r);
        return res;
    }
};

int countRangeSum(vector<int>& nums, int lower, int upper){
    if(nums.size() == 0)    return 0;
    unordered_set<long> set;
    int sum = 0;
    for(int i = 0; i < nums.size(); i++){
        sum += nums[i];
        set.insert(sum);
    }

    vector<int> sums(set.begin(), set.end());
    sort(sums.begin(), sums.end());

    SegmentTree* root = new SegmentTree(sums, 0, nums.size()-1);
    int count = 0;
    for(int i = nums.size()-1; i >= 0; i--){
        root->addVal(sum);
        sum -= nums[i];
        count += root->getCount(sum+lower, sum + upper);
    }

    return count;
}

int mainS(){
    vector<int> nums({0,0});
    countRangeSum(nums, 0, 0);
    return 0;
}