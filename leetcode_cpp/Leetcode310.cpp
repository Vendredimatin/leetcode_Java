//
// Created by 刘汉乙 on 2020/9/29.
//
#include<vector>
#include<unordered_set>
using namespace std;

vector<int> findMinHeightTrees(int n, vector<vector<int>>& edges) {
    vector<unordered_set<int>> adj(n);
    vector<int> current;
    if(n == 1){
        current.push_back(0);
        return current;
    } else if(n == 0) return current;

    for(vector<int> edge : edges){
        adj[edge[0]].insert(edge[1]);
        adj[edge[1]].insert(edge[0]);
    }

    for(int i = 0; i < n; i++){
        if(adj[i].size() == 1)  current.push_back(i);
    }

    while(true){
        vector<int> next;

        for(int leaf : current){
            for(int i = 0; i < adj.size(); i++){
                if(i != leaf && adj[i].find(leaf) != adj[i].end()) {
                    adj[i].erase(leaf);
                    if (adj[i].size() == 1) next.push_back(i);
                }
            }

        }
        if(next.empty())
            return current;
        current = next;
    }
}

int main310(){
    vector<int> edge1({3,0});
    vector<int> edge2({3,1});
    vector<int> edge3({3,2});
    vector<int> edge4({3,4});
    vector<int> edge5({5,4});


    vector<vector<int>> edges({edge1,edge2,edge3,edge4,edge5});
    findMinHeightTrees(6, edges);
    return 0;
}