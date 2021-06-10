//
// Created by 刘汉乙 on 2021/6/7.
//

#include <iostream>
#include <vector>
#include<queue>
#include "limits.h"
using namespace std;
//很自然的BFS
pair<int,int> op[8] = {make_pair(-1,-2),make_pair(-2,-1),make_pair(-2,1),make_pair(-1,2),make_pair(1,-2),make_pair(1,2),make_pair(2,-1),make_pair(2,1)};
int main(){
    int n,m,x,y;
    cin >> n >> m >> x >> y;
    vector<vector<int>> board(n, vector<int>(m,INT_MAX));
    x -=1; y-=1;
    queue<pair<int,int>> q;
    q.push(make_pair(x,y));
    board[x][y] = 0;
    while(!q.empty()){
        pair<int,int> pos = q.front(); q.pop();
        for (int i = 0; i < 8; ++i) {
            int newX = pos.first + op[i].first;
            int newY = pos.second + op[i].second;
            if(newX >= 0 && newX <= n-1 && newY >= 0 && newY <= m-1){
                int actions = board[pos.first][pos.second] + 1;
                if(actions < board[newX][newY]){
                    board[newX][newY] = actions;
                    q.push(make_pair(newX, newY));
                }
            }
        }
    }
    cout.setf(std::ios::left);

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; j++){
            if (board[i][j] == INT_MAX) board[i][j] = -1;
            cout.width(5);
            cout << board[i][j];
        }
        cout << endl;
    }
    return 0;
}