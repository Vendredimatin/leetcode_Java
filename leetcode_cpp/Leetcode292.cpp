//
// Created by 刘汉乙 on 2020/9/24.
//

bool canWinNim(int n) {
    if(n <= 3) return true;
    bool flag1 = !canWinNim(n-1);
    bool flag2 = !canWinNim(n-2);
    bool flag3 = !canWinNim(n-3);
    return flag1 || flag2 || flag3;
}

int main292(){
    canWinNim(4);
    return 0;
}