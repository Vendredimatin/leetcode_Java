//
// Created by 刘汉乙 on 2021/5/23.
//
#include<vector>
#include<queue>
using namespace std;


/*
 * Example 1:

Input: nums = [1], k = 1
Output: 1
Example 2:

Input: nums = [1,2], k = 4
Output: -1
Example 3:

Input: nums = [2,-1,2], k = 3
Output: 3

 */
vector<int> tree862;
/**
 *
 * @param l
 * @param r
 * @param p 当前节点
 */
void build862(int l, int r, int p, vector<int> A){
    if(l == r){
        tree862[l] = A[l];
    }else {
        int mid = (l + r) / 2;
        build862(l, mid, 2 * p, A);
        build862(mid+1, r, 2 * p + 1, A);
        tree862[p] = tree862[2* p] + tree862[2* p + 1];
    }
}

/**
 *
 * @param l目标区间的左节点
 * @param r 目标区间的右节点
 * @param p
 * @param cl p节点的左区间
 * @param cr p节点的右区间
 * @return
 */
int query862(int l, int r, int p, int cl, int cr){
    //查询集合没有交叉匹配
    if(cl > r || cr < l){
        return 0;
    }else if(cl >= l && cr <= r){
        //要是当前查询区间被目标区间包含，那么直接返回tree862[p];
        return tree862[p];
    }else{
        //有交叉
        int mid = (l+r)/2;
        return query862(l,r, 2*p, l, mid) + query862(l,r, 2*p+1,mid+1,r);
    }
}

int shortestSubarray(vector<int>& A, int K) {
    deque<int> d;
    int res = INT_MAX;
    vector<int> preSum(A.size()+1);
    for(int i = 0; i < A.size(); i++){
        preSum[i+1] = preSum[i] + A[i];
    }

    for(int i = 0; i < preSum.size(); i++){
        while (!d.empty() && preSum[i] - preSum[d.front()] >= K) {
            res = min(res, i - d.front());
            d.pop_front();
        }

        while(!d.empty() && preSum[i] < preSum[d.back()])
            d.pop_back();
        d.push_back(i);
    }

    return (res == INT_MAX)?-1:res;
}

int main862(){
    vector<int> a;
    a.push_back(2);
    a.push_back(-1);
    a.push_back(2);
    shortestSubarray(a,3);
    return 0;
}



