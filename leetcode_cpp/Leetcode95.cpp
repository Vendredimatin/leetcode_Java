//
// Created by 刘汉乙 on 2020/9/11.
//
#include<vector>
using namespace std;

struct TreeNode {
      int val;
      TreeNode *left;
      TreeNode *right;
      TreeNode() : val(0), left(nullptr), right(nullptr) {}
      TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
      TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};


vector<TreeNode*> helper(int start, int end){
    if(start > end)
        return {NULL};


    vector<TreeNode*> trees;
    for(int i = start; i <= end; i++){
        vector<TreeNode*> leftTrees = helper(start, i-1);
        vector<TreeNode*> rightTrees = helper(i+1, end);


        for(int j = 0; j < leftTrees.size(); j++){
            TreeNode* leftRoot = leftTrees[j];
            for(int k = 0; k < rightTrees.size(); k++){
                TreeNode* root = new TreeNode(i);
                TreeNode* rightRoot = rightTrees[k];

                root->left = leftRoot;
                root->right = rightRoot;
                trees.push_back(root);
            }
        }
    }

    return trees;
}

vector<TreeNode*> generateTrees(int n) {
    if(n == 0) return {};
    return helper(1, n);
}

int main95(){
    generateTrees(3);
    return 0;
}