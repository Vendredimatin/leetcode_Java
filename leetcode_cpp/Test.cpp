//
// Created by 刘汉乙 on 2020/12/12.
//
#include<vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

TreeNode* helper(vector<int>& preorder, vector<int>& inorder, int pstart, int pend, int istart, int iend){
    if(pstart > pend) return nullptr;
    if(pstart == pend) return new TreeNode(preorder[pstart]);

    int index = 0;
    for(int i = istart; i <= iend; i++){
        if (preorder[pstart] == inorder[i]){
            index = i;
            break;
        }
    }
    TreeNode* root = new TreeNode(preorder[pstart]);

    int count = index - istart;
    root->left = helper(preorder,inorder,pstart+1,pstart+count,istart,index-1);
    root->right = helper(preorder,inorder,pstart+count+1,pend,index+1,iend);
    return root;
}

TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
    if(preorder.size() == 0) return nullptr;
    int n = preorder.size();
    return helper(preorder,inorder,0, n-1,0,n-1);
}

int mainTest(){
    vector<int> preorder({3,9,20,15,7});
    vector<int> inorder({9,3,15,20,7});
    buildTree(preorder,inorder);
    return 0;
}