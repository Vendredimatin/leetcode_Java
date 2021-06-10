//
// Created by 刘汉乙 on 2020/9/16.
//
struct ListNode {
    int val;
    ListNode* next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode* next) : val(x), next(next) {}
};

ListNode* insertionSortList(ListNode* head) {
    ListNode* it = new ListNode();
    it->next = head;
    ListNode* tail = head;
    ListNode* p = head->next;

    while(p != nullptr){
        ListNode* prev = it;
        bool flag = false;
        while(prev != tail){
            if(p->val < prev->next->val){
                tail->next = p->next;
                p->next = prev->next;
                prev->next = p;
                flag = true;

                break;
            }

            prev = prev->next;
        }

        if(!flag)
            tail = tail->next;
        p = tail->next;
    }

    return it->next;
}

int main147(){
    ListNode* node0 = new ListNode(0);
    ListNode* node4 = new ListNode(4, 0);
    ListNode* node3 = new ListNode(3, node4);
    ListNode* node5 = new ListNode(5, node3);
    ListNode* node1 = new ListNode(-1, node5);

    insertionSortList(node1);
    return 0;
}

