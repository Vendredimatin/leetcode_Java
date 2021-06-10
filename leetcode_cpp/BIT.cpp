//
// Created by 刘汉乙 on 2020/9/27.
//
#include<vector>
using namespace std;

class BIT{
private:
    vector<int> tree;
    vector<int> lazy;
    vector<int> origin;
public:
    BIT(vector<int> nums){
        this->origin = nums;
        tree.resize(nums.size()*2);
        lazy.resize(nums.size()*2);
        build_tree(0, 0, nums.size()*2-1);
    }

    void build_tree(int node, int l, int r){
        if(l == r){
            tree[node] = origin[node];
            return;
        }

        int mid = (l+r) >> 1;
        //left
        build_tree(node*2, l, mid);
        build_tree(node*2+1, mid+1, r);
        tree[node] = tree[node*2] + tree[node*2+1];
    }

    //单点更新
    void update(int node, int newVal, int index, int l, int r){
        if(l == r){
            tree[index] += newVal;
            return;
        }

        //
        int mid = l + (r - l)/2;
        if(index <= mid)
            update(node*2, newVal, index, l, mid);
        else update(node*2 + 1, newVal, index, mid+1, r);

        tree[node] = tree[node*2] + tree[node*2 + 1];
    }

    //懒惰标记更新
    void push_down(int node, int l, int r){
        if(lazy[node]){
            int mid = (l+r)/2;
            lazy[node*2] += lazy[node];
            lazy[node*2+1] += lazy[node];
            //对线段树上的节点进行更细
            tree[node*2] += (mid - l + 1) * lazy[node];
            tree[node*2 + 1] += (r - mid) * lazy[node];
        }
    }

    //l,r为更新范围,addVal为更新值,L/R为目前结点表示的区间
    void update_range(int node, int l, int r, int L, int R, int addVal){
        if(l <= L && r >= R){
            lazy[node] += addVal;
            tree[node] += (r-l + 1) * addVal;
        }
        push_down(node, L, R);
        int mid = (L+R)/2;
        if(mid >= l) update_range(node*2, l, r, L, mid, addVal);
        if(mid < r) update_range(node*2 + 1, l, r, mid+1, R ,addVal);
        tree[node] = tree[node*2] + tree[node*2 + 1];
    }

    // 区间查找
    int query_range(int node,int L,int R,int l,int r){
        if(l <= L && r >= R) return tree[node];
        push_down(node,L,R);
        int mid = (L+R) / 2;
        int sum = 0;
        if(mid >= l) sum += query_range(node*2,L,mid,l,r);
        if(mid < r) sum += query_range(node*2 + 1,mid+1,R,l,r);
        return sum;
    }
};

