//
// Created by 刘汉乙 on 2020/9/13.
//
struct TreeNode {
         int val;
         TreeNode *left;
         TreeNode *right;
         TreeNode() : val(0), left(nullptr), right(nullptr) {}
         TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
         TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

TreeNode* temp;
void preOrder(TreeNode* root){
    if(root == nullptr) return;

    temp->right = new TreeNode(root->val);
    temp = temp->right;
    preOrder(root->left);
    preOrder(root->right);
}

void flatten(TreeNode* root) {
    TreeNode* ans = new TreeNode(0);
    temp = ans;
    preOrder(root);
    root = ans->right;
}

int main114(){
    TreeNode* node3 = new TreeNode(3);
    TreeNode* node4 = new TreeNode(4);
    TreeNode* node6 = new TreeNode(6);
    TreeNode* node2 = new TreeNode(2, node3,node4);
    TreeNode* node5 = new TreeNode(5, nullptr,node6);
    //TreeNode* node2 = new TreeNode(-3, node3);
    TreeNode* node1 = new TreeNode(1, node2, node5);
    flatten(node1);
}