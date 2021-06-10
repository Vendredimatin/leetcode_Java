//
// Created by 刘汉乙 on 2020/9/19.
//
#include<queue>
using namespace std;

class MyStack {
public:
    /** Initialize your data structure here. */
    queue<int> q1;
    int topVal;
    MyStack() {

    }

    /** Push element x onto stack. */
    void push(int x) {
        q1.push(x);
        topVal = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    int pop() {
        queue<int> q2;
        int res = topVal;
        while(q1.size() > 1){
            if(q1.size() == 2)
                topVal = q1.front();
            q2.push(q1.front());
            q1.pop();

        }

        q1 = q2;
        return res;

    }

    /** Get the top element. */
    int top() {
        return topVal;
    }

    /** Returns whether the stack is empty. */
    bool empty() {
        return q1.empty();
    }
};

int main225(){
    MyStack* stack = new MyStack();

    stack->push(1);
    stack->push(2);
    //stack->push(3);
    //stack->top();   // returns 2
    stack->pop();   // returns 2
    stack->top(); // returns false
    //stack->pop();
    //stack->top();
    //stack->empty();
    return 0;
}