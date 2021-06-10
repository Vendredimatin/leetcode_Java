#include <iostream>
using namespace std;
struct ListNode {
         int val;
         ListNode *next;
         ListNode() : val(0), next(nullptr) {}
         ListNode(int x) : val(x), next(nullptr) {}
         ListNode(int x, ListNode *next) : val(x), next(next) {}
};

ListNode* reverseBetween(ListNode* head, int m, int n) {
    int k = 0;
    ListNode* cur = new ListNode();
    cur->next = head;
    ListNode* p = cur;

    while(k < m-1){
        p = p->next;
        k++;
    }

    ListNode* curHead = p;
    p = p->next;
    k++;
    ListNode* reverseTail = p;

    ListNode* prev;
    ListNode* curTail;
    ListNode* reverseHead;
    while(k < n){
        ListNode* mid = p->next;
        p->next = prev;
        prev = p;
        p = mid;

        k++;
    }

    curTail = p->next;
    p->next = prev;
    reverseHead = p;

    curHead->next = reverseHead;
    reverseTail->next = curTail;



    return cur->next;
}



int main1() {
    std::cout << "Hello, World!" << std::endl;
    ListNode* five = new ListNode();
    five->val = 5;
    ListNode four(4, five);
    ListNode third(3, &four);
    ListNode second(2, &third);
    ListNode first(1, &second);
    reverseBetween(&first,2,4);
    return 0;
}
