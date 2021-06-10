#include<iostream>
#include<vector>
#include<string>
using namespace std;

vector<vector<int> > ans;

bool concludeInLines(int x, int y, vector<vector<int> > lines){
    for(vector<int> pr:lines){
        //if(pr[1]*y == pr[0]*x+pr[1]*pr[3]-pr[0]*pr[2]) return true;
        if(y == pr[0] * x + pr[1]) return true;
    }

    return false;
}

vector<int> getLine(vector<vector<int> > board, int x1, int y1, bool positive){
    int x2 = 0, y2 = 0;
    if(positive){
        x2 = x1-1, y2 = y1+1;
    }else{
        x2 = x1-1, y2 = y1-1;
    }

    vector<int> ans(2);
    ans[0] = (y2-y1)/(x2-x1);
    //ans[1] = x2-x1;
    ans[1] = y2 - ans[0]*x2;
    //ans[2] = x2;
    //ans[3] = y2;
    return ans;
}

void helper1219(vector<vector<int> > board, vector<vector<int> > lines, vector<int> tmp, int i){
    if(tmp.size() == board.size()){
        vector<int> v;
        for (int col:tmp){
            v.push_back(col);
        }
        ans.push_back(v);
        return;
    }


    for(int j = 0; j < board[i].size(); j++){
        if(board[0][j] == -1 || concludeInLines(i,j,lines)) continue;
        tmp.push_back(j+1);
        //这道题之所以纠结这么多，md，全部因为这傻逼boar[i][0] = -1,使得天然多了一列为-1，所以总是五个数
        board[0][j] = -1;

        vector<int> line1 = getLine(board, i, j, true);
        vector<int> line2 = getLine(board, i, j, false);
        lines.push_back(line1);
        lines.push_back(line2);

        helper1219(board,lines,tmp,i+1);

        tmp.pop_back();
        board[0][j] = 0;

        lines.pop_back();
        lines.pop_back();
    }


}

int main1219(){
    int n;
    cin >> n;
    vector<vector<int> > board(n,vector<int>(n, 0));
    vector<vector<int> > lines;
    vector<int> tmp;
    helper1219(board,lines,tmp,0);

    for(int i = 0; i < 4; i++){
        vector<int> a = ans[i];
        for(int num:a){
            cout << num << " ";
        }
        cout << endl;
    }

    cout << ans.size() << endl;
}