//
// Created by 刘汉乙 on 2021/5/22.
//

#include<vector>
#include<unordered_map>
#include<algorithm>
#include <iostream>

using namespace std;

vector<vector<int> > state;
int total;


/*
x full
y full
x empty
y empty
x poor
y poor
*/

bool helper(int x, int y, int curX, int curY, int z){
    if(curX + curY == z) return true;
    else if(total == 0) return false;
    bool ans = false;

    for(int i = 0; i < 6; i++){
        int tmpX = curX;
        int tmpY = curY;
        if(ans) break;
        switch (i)
        {
            case 0: {
                //x full
                if (tmpX != x) {
                    // x full
                    if (!state[x][tmpY]) {
                        tmpX = x;
                        state[tmpX][tmpY] = true;
                        total--;
                        ans = ans || helper(x, y, tmpX, tmpY, z);
                    }
                }
                //break;
                continue;
            }
            case 1: {
                //y full
                if (tmpY != y) {
                    if (!state[tmpX][y]) {
                        tmpY = y;
                        state[tmpX][tmpY] = true;
                        total--;
                        ans = ans || helper(x, y, tmpX, tmpY, z);
                    }
                }
                //break;
                continue;
            }
            case 2: {
                //x empty
                if (!state[0][tmpY]) {
                    tmpX = 0;
                    state[tmpX][tmpY] = true;
                    total--;
                    ans = ans || helper(x, y, tmpX, tmpY, z);
                }
                //break;
                continue;
            }
            case 3:{
                //y empty
                if(!state[tmpX][0]){
                    tmpY = 0;
                    state[tmpX][tmpY] = true;
                    total--;
                    ans = ans || helper(x,y,tmpX,tmpY,z);
                }
                //break;
                continue;
            }
            case 4: {
                //x pour to y
                int remainderY = y - tmpY;
                //如果x仍有剩余，y倒满
                if (tmpX > remainderY) {
                    tmpX = tmpX - remainderY;
                    tmpY = y;
                    if (!state[tmpX][tmpY]) {
                        state[tmpX][tmpY] = true;
                        total--;
                        ans = ans || helper(x, y, tmpX, tmpY, z);
                    }
                } else {
                    //如果x倒空
                    tmpY = tmpY + tmpX;
                    tmpX = 0;
                    if (!state[tmpX][tmpY]) {
                        state[tmpX][tmpY] = true;
                        total--;
                        ans = ans || helper(x, y, tmpX, tmpY, z);
                    }
                }
                //break;
                continue;
            }
            case 5: {
                //y pour to x
                int remainderX = x - tmpX;
                //如果y仍有剩余，x倒满
                if (tmpY > remainderX) {
                    tmpY = tmpY - remainderX;
                    tmpX = x;
                    if (!state[tmpX][tmpY]) {
                        state[tmpX][tmpY] = true;
                        total--;
                        ans = ans || helper(x, y, tmpX, tmpY, z);
                    }
                } else {
                    //如果y倒空
                    tmpX = tmpX + tmpY;
                    tmpY = 0;
                    if (!state[tmpX][tmpY]) {
                        state[tmpX][tmpY] = true;
                        total--;
                        ans = ans || helper(x, y, tmpX, tmpY, z);
                    }
                }
                //break;
                continue;
            }
        }

        return ans;
    }

    return ans;
}


bool canContainWater(int x, int y, int z){
    if(z == 0) return true;
    state.resize(x+1, vector<int>(y+1, false));
    total = (x+1)*(y+1)-1;
    state[0][0] = true;
    bool ans = helper(x,y,0,0,z);

    return ans;
}

int mai365(){
    bool res = canContainWater(1,2,3);
    cout << res;
    return 0;
}