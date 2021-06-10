//
// Created by 刘汉乙 on 2020/9/13.
//
struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};


ListNode* reverseKGroup(ListNode* head, int k) {
    ListNode* cur = new ListNode(0, head);
    ListNode* pre = cur;
    ListNode* m = pre->next;
    ListNode* p = m;

    ListNode* curTail;
    ListNode* curHead = pre;
    while(m != nullptr && m->next != nullptr){
        int n = k-1;
        while(n-- > 0){
            m = m ->next;
            if(m == nullptr)
                return cur->next;
        }
        curTail = m->next;
        m = m->next;

        n = k;
        ListNode* tempTail = p;
        ListNode* tempPre = nullptr;
        while(n-- > 0){
            ListNode* q = p->next;
            p->next = tempPre;
            tempPre = p;
            p = q;
        }

        curHead->next = tempPre;
        tempTail->next = curTail;

        curHead = tempTail;
    }



    return cur->next;
}

ListNode* reverseKGroup2(ListNode* head, int k) {
    ListNode* curr = head;
    int count = 0;
    while (curr != nullptr && count != k) { // find the k+1 node
        curr = curr->next;
        count++;
    }
    if (count == k) { // if k+1 node is found
        curr = reverseKGroup2(curr, k); // reverse list with k+1 node as head
        // head - head-pointer to direct part,
        // curr - head-pointer to reversed part;
        while (count-- > 0) { // reverse current k-group:
            ListNode* tmp = head->next; // tmp - next head in direct part
            head->next = curr; // preappending "direct" head to the reversed list
            curr = head; // move head of reversed part to a new node
            head = tmp; // move "direct" head to the next node in direct part
        }
        head = curr;
    }
    return head;
}

int main25(){
    ListNode* node5 = new ListNode(5);
    ListNode* node4 = new ListNode(4, node5);
    ListNode* node3 = new ListNode(3, node4);
    ListNode* node2 = new ListNode(2, node3);
    ListNode* node1 = new ListNode(1, node2);
    reverseKGroup2(node1, 2);
    return 0;
}