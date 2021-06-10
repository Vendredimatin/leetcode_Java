//
// Created by 刘汉乙 on 2020/9/29.
//
#include<vector>
using namespace std;

vector<long> tree;


void buildTree(int node,vector<int> nums, int l, int r){
    if(l == r){
        tree[node] = nums[l];
        return;
    }

    int mid = (l + r)/2;
    buildTree(node*2, nums, l, mid);
    buildTree(node*2+1, nums, mid+1, r);
    tree[node] = tree[node*2] + tree[node*2 + 1];
}
//L,R为目前结点表示的区间
//lower，upper表示查找的范围
long queryRange(int lower, int upper, int L, int R, int node){
    if(lower <= L && upper >= R) return tree[node];

    int mid = (L + R)/2;
    long sum = 0;
    if(mid >= lower) sum += queryRange(lower,upper, L, mid, node * 2);
    if(mid < upper) sum += queryRange(lower, upper, mid+1, R, node*2 + 1);
    return sum;
}

int countRangeSum1(vector<int>& nums, int lower, int upper) {
    int n = nums.size();
    int ans = 0;
    tree.resize(4 * n + 1);
    buildTree(1, nums, 0, nums.size()-1);

    for(int i = 0; i < n; i++){
        for(int j = i; j < n; j++){
            long sum = queryRange(i, j, 0, n-1, 1);
            if(sum >= lower && sum <= upper)
                ans++;
        }
    }

    return ans;
}

int main327(){
    vector<int> nums({-2147483647,0,-2147483647,2147483647});
    countRangeSum1(nums, -564, 3864);
    return 0;
}