//
// Created by 刘汉乙 on 2020/9/22.
//
#include<vector>
using namespace std;
int hIndex(vector<int>& citations) {
    int n = citations.size();
    int lo = 0, hi = n-1;
    int ans = 0;
    while(lo <= hi){
        int mid = lo + (hi - lo)/2;
        int h = citations[mid];
        int papers = n - mid;
        if(h >= papers){
            ans = max(ans, papers);
            lo = mid + 1;
        }else{
            hi = mid-1;
        }
    }

    return ans;
}

int main274(){
    vector<int> v({0,1});
    hIndex(v);
    return 0;
}