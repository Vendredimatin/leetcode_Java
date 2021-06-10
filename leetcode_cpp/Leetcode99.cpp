//
// Created by 刘汉乙 on 2020/9/12.
//
#include<vector>
#include<queue>
using namespace std;

struct TreeNode99 {
    int val;
    TreeNode99 *left;
    TreeNode99 *right;
    TreeNode99() : val(0), left(nullptr), right(nullptr) {}
    TreeNode99(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode99(int x, TreeNode99 *left, TreeNode99 *right) : val(x), left(left), right(right) {}
};


void inOrder(TreeNode99* root, vector<int>& sequence){
    if(root == nullptr) return;

    inOrder(root->left,sequence);
    sequence.push_back(root->val);
    inOrder(root->right, sequence);
}

TreeNode99* findNode(TreeNode99* root, int val){
    queue<TreeNode99*> q;
    q.push(root);

    while(!q.empty()){
        int sz = q.size();
        while(sz-- > 0){
            TreeNode99* p = q.front(); q.pop();
            if(p->val == val) return p;
            if(p->left != nullptr) q.push(p->left);
            if(p->right != nullptr) q.push(p->right);
        }
    }

    return nullptr;
}

void recoverTree(TreeNode99* root) {
    if(root == nullptr) return;
    vector<int> sequence;
    inOrder(root, sequence);
    int val1 = INT_MAX;
    int val2 = INT_MAX;


    for(int i = 0; i < sequence.size()-1; i++){
        if(sequence[i] > sequence[i+1]) {
            val1 = sequence[i];
            break;
        }
    }

    for(int i = sequence.size()-1; i > 0; i--){
        if(sequence[i] < sequence[i-1]){
            val2 = sequence[i];
            break;
        }
    }

    TreeNode99* node1 = findNode(root, val1);
    TreeNode99* node2 = findNode(root, val2);

    node1->val = val2;
    node2->val = val1;

    //return root;
}

int main99(){
    TreeNode99* head = new TreeNode99(2);
    TreeNode99* head4 = new TreeNode99(1);
    TreeNode99* head2 = new TreeNode99(4,head, NULL);
    TreeNode99* head3 = new TreeNode99(3, head4, head2);
    recoverTree(head3);
    return 0;
}