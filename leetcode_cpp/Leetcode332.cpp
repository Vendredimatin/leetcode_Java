//
// Created by 刘汉乙 on 2020/10/7.
//
#include<string>
#include<vector>
#include<set>
#include<unordered_map>
using namespace std;

vector<string> dfs(vector<string> temp, unordered_map<string, vector<string>> map, string from, int n){
    if(map.find(from) == map.end()){
        return temp;
    }

    vector<string> tos = map[from];
    int i = 0;
    for (;i < tos.size(); i++){
        string to = tos[i];
        temp.push_back(to);
        map[from].erase(map[from].begin() + i);
        vector<string> ans = dfs(temp, map, to, n);
        if(ans.size() == n + 1) return ans;
        map[from].insert(map[from].begin() + i, to);
        temp.pop_back();
    }

    return temp;
}

vector<string> findItinerary(vector<vector<string>>& tickets) {
    unordered_map<string, vector<string>> map;
    for(vector<string> ticket: tickets){
        if(map.find(ticket[0]) == map.end()){
            vector<string> s;
            s.push_back(ticket[1]);
            map[ticket[0]] = s;
        }else map[ticket[0]].push_back(ticket[1]);
    }

    unordered_map<string, vector<string>>::iterator it = map.begin();
    while (it != map.end()){
        string from = it->first;
        sort(map[from].begin(), map[from].end());
        it++;
    }

    string from = "JFK";
    vector<string> ans;
    ans.push_back(from);
    ans = dfs(ans, map, from, tickets.size());

    return ans;
}

int main332(){
    vector<vector<string>> tickets;
    tickets.push_back({"EZE","AXA"});//
    tickets.push_back({"TIA","ANU"});//
    tickets.push_back({"ANU","JFK"});//
    tickets.push_back({"JFK","ANU"});//
    tickets.push_back({"ANU","EZE"});//
    tickets.push_back({"TIA","ANU"});
    tickets.push_back({"AXA","TIA"});//
    tickets.push_back({"TIA","JFK"});
    tickets.push_back({"ANU","TIA"});
    tickets.push_back({"JFK","TIA"});//
    findItinerary(tickets);
    return 0;
}