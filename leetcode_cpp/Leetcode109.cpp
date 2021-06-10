//
// Created by 刘汉乙 on 2020/9/13.
//

#include <cstdlib>
using namespace std;
struct ListNode {
       int val;
         ListNode* next;
         ListNode() : val(0), next(nullptr) {}
         ListNode(int x) : val(x), next(nullptr) {}
         ListNode(int x, ListNode* next) : val(x), next(next) {}
     };


  struct TreeNode {
      int val;
      TreeNode* left;
      TreeNode* right;
      TreeNode() : val(0), left(nullptr), right(nullptr) {}
      TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
      TreeNode(int x, TreeNode* left, TreeNode* right) : val(x), left(left), right(right) {}
  };

ListNode*  current;
TreeNode*  build_tree(int low,int up)
{
    if(low >= up ) return nullptr;
    TreeNode*  tmp = new TreeNode();
    int mid = low + ((up - low)>>1);
    tmp -> left = build_tree(low,mid);
    tmp -> val = current -> val;
    current = current -> next;
    tmp -> right = build_tree(mid+1,up);
    return tmp;
}

TreeNode* sortedListToBST(struct ListNode* head) {
    current = head;
    int len = 0;
    while(head)
        len++, head = head->next;
    return build_tree(0,len);
}

int main109(){
    ListNode* node5 = new ListNode(9);
    ListNode* node4 = new ListNode(5, node5);
    ListNode* node3 = new ListNode(0, node4);
    ListNode* node2 = new ListNode(-3, node3);
    ListNode* node1 = new ListNode(-10, node2);
    sortedListToBST(node1);
    return 0;
}