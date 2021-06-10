//
// Created by 刘汉乙 on 2020/9/27.
//
#include<vector>
using namespace std;

class NumArray {
public:
    //https://leetcode.com/problems/range-sum-query-mutable/discuss/75711/C%2B%2B-Segment-Treeupdate-and-sum-are-both-O(logn) 线段树node的实现形式
    //线段树array的实现形式
    //https://www.cnblogs.com/jason2003/p/9676729.html
    //https://blog.csdn.net/iwts_24/article/details/81484561
    vector<int> tree;
    vector<int> origin;
    NumArray(vector<int>& nums) {
        this->origin = nums;
        tree.resize(nums.size()*4);
        build_tree(1, 0, nums.size()-1);
    }

    void update(int i, int val) {
        update(1, val, i, 0, origin.size()-1);
    }

    int sumRange(int i, int j) {
        int sum = query_range(1, 0, origin.size()-1, i, j);
        return sum;
    }

    void build_tree(int node, int l, int r){
        if(l == r){
            tree[node] = origin[l];
            return;
        }
        int mid = (l+r)/2;
        build_tree(node*2,l,mid);
        build_tree(node*2+1,mid+1,r);
        tree[node] = tree[node*2] + tree[node*2 + 1];
    }

    //单点更新
    void update(int node, int newVal, int index, int l, int r){
        if(l == r){
            tree[node] = newVal;
            return;
        }

        //
        int mid = l + (r - l)/2;
        if(index <= mid)
            update(node*2, newVal, index, l, mid);
        else update(node*2 + 1, newVal, index, mid+1, r);

        tree[node] = tree[node*2] + tree[node*2 + 1];
    }

    // 区间查找
    int query_range(int node,int L,int R,int l,int r){
        if(l <= L && r >= R) return tree[node];
        int mid = (L+R) / 2;
        int sum = 0;
        if(mid >= l) sum += query_range(node*2,L,mid,l,r);
        if(mid < r) sum += query_range(node*2 + 1,mid+1,R,l,r);
        return sum;
    }
};

int main307(){
    vector<int> nums({-28,-39,53,65,11,-56,-65,-39,-43,97});
    NumArray* obj = new NumArray(nums);
    int param_1 = obj->sumRange(5,6);
    obj->update(9,27);
    int param_2 = obj->sumRange(2,3);
    int param_3 = obj->sumRange(6,7);
    obj->update(1,-82);
    obj->update(3,-72);
    int param_4 = obj->sumRange(3,7);
    int param_5 = obj->sumRange(1,8);
    obj->update(5,13);
    obj->update(4,-67);
    return 0;
}