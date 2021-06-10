//
// Created by 刘汉乙 on 2021/6/5.
//
#include<vector>
#include<iostream>
using namespace std;

vector<int> nums;

int pick(int target) {
    int count = 0;
    int idx = 0;
    for(int i = 0; i < nums.size(); i++){
        if(nums[i] == target){
            if(count == 0)  idx = i;
            count++;
            //这个没法达到概率平均,越到后面概率越低
            //选中第二个的概率是1/2；选中第三个的概率是（1-1/2）*1/3；选中第四个的概率是（1-1/2）*（1-1/3）*1/4 = 1/12
            //正确pick的概率，重点是不应该break，要将其保留，保留之后概率才会平等
            if(count > 1 && rand()%count == 0){
                idx = i;
                break;
            }
        }


    }

    return idx;
}

int main398(){
    nums.push_back(1);
    nums.push_back(2);
    nums.push_back(3);
    nums.push_back(3);
    nums.push_back(3);
    nums.push_back(3);
    int record[4] = {0,0,0,0};

    for(int i = 0; i < 1000; i++){
        int ans = pick(3);
        record[ans-2]++;
    }

    cout << " 2 count:" << record[0] << endl;
    cout << " 3 count:" << record[1] << endl;
    cout << " 4 count:" << record[2] << endl;
    cout << " 5 count:" << record[3] << endl;
}

