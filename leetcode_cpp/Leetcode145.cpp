//
// Created by 刘汉乙 on 2020/9/16.
//
#include<vector>
#include<stack>
using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

vector<int> postorderTraversal(TreeNode* root) {
    stack<TreeNode*> s;
    s.push(root);
    TreeNode* prev = nullptr;
    vector<int> ans;

    while(!s.empty()){
        TreeNode* node = s.top();

        if(node->right == nullptr || prev == node->right){
            ans.push_back(node->val);
            prev = node;
            s.pop();
        }else {
            s.push(node->right);
            while(s.top()->left != nullptr){
                s.push(s.top()->left);
            }
        }
    }

    return ans;
}

int main145(){
    TreeNode* node3 = new TreeNode(3);
    TreeNode* node2 = new TreeNode(2, node3, nullptr);
    TreeNode* node1 = new TreeNode(1, nullptr, node2);
    postorderTraversal(node1);
    return 0;
}