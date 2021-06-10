//
// Created by 刘汉乙 on 2020/9/12.
//
#include<vector>
using namespace std;

int maximalRectangle(vector<vector<char>>& matrix) {
    if(matrix.size() == 0 || matrix[0].size() == 0) return 0;
    int r = matrix.size();
    int c = matrix[0].size();
    vector<vector<int>> board(r, vector<int>(c, 0));

    for(int i = 0; i < r; i++){
        int cnt = 0;
        for(int j = 0; j < c; j++){
            if(matrix[i][j] == '1'){
                cnt++;
                board[i][j] = cnt;
            }else cnt = 0;
        }
    }

    int area = 0;
    for(int i = 0; i < r; i++){
        for(int j = 0; j < c; j++){
            if(matrix[i][j] == '1')
                area = max(area, board[i][j]);
            else continue;

            int width = board[i][j];
            for(int k = i-1; k >= 0 && matrix[k][j] == '1'; k--){
                int height = i - k + 1;
                width = min(board[k][j], width);
                area = max(area, height * width);
            }
        }

    }

    return area;

}

int main85(){
    vector<vector<char>> matrix;
    vector<char> row1;//"1","0","1","0","0"
    row1.push_back('1');
    row1.push_back('0');
    row1.push_back('1');
    row1.push_back('0');
    row1.push_back('0');

    vector<char> row2;//"1","0","1","1","1"
    row2.push_back('1');
    row2.push_back('0');
    row2.push_back('1');
    row2.push_back('1');
    row2.push_back('1');

    vector<char> row3;//"1","0","1","1","1"
    row3.push_back('1');
    row3.push_back('1');
    row3.push_back('1');
    row3.push_back('1');
    row3.push_back('1');

    vector<char> row4;//"1","0","1","1","1"
    row4.push_back('1');
    row4.push_back('0');
    row4.push_back('0');
    row4.push_back('1');
    row4.push_back('0');

    matrix.push_back(row1);
    matrix.push_back(row2);
    matrix.push_back(row3);
    matrix.push_back(row4);

    maximalRectangle(matrix);
    return 0;
}