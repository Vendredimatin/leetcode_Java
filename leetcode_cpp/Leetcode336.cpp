//
// Created by 刘汉乙 on 2020/10/20.
//
#include<vector>
#include<string>
#include<unordered_set>
using namespace std;

struct TrieNode{
    bool isWord;
    int index = -1;
    TrieNode* next[26];
    vector<int> ids;
    TrieNode() :isWord(false){ memset(next, 0, sizeof(next)); }
};

class Solution {
public:
    TrieNode* root;

    void insert(string word, int index){
        TrieNode* cur = root;
        int n = word.size();
        for(int i = 0; i < n; i++){
            char ch = word[i];
            TrieNode* next = cur->next[ch - 'a'];
            if(next == nullptr)
                cur->next[ch - 'a'] = new TrieNode();
            cur = cur->next[ch - 'a'];

            if(i+1 < n && isPalindrome(word.substr(i+1)))
                cur->ids.push_back(index);

        }

        if(!cur->isWord) cur->isWord = true;
        cur->index = index;
    }

    //
    vector<int> search(string prefix, int index){
        TrieNode* cur = root;
        vector<int> ans;

        for(int i = 0; i < prefix.size(); i++){
            char ch = prefix[i];
            TrieNode* next = cur->next[ch-'a'];
            cur = next;
            if(next != nullptr) {
                if(next->isWord && next->index != index && isPalindrome(prefix.substr(i+1)))
                    ans.push_back(next->index);
            }else break;
        }

        //假如prefix已经结束，且这条分支上的词的剩余部分是对称的
        if(cur != nullptr){
            for(int id:cur->ids)
                ans.push_back(id);
        }
        return ans;
    }

    unordered_set<string> set;
    bool isPalindrome(string temp){
        if(set.find(temp) != set.end()) return true;
        int i = 0, j = temp.size()-1;
        while(i <= j){
            if(temp[i] != temp[j]) return false;
            i++; j--;
        }

        set.insert(temp);
        return true;
    }

    vector<vector<int>> palindromePairs(vector<string>& words) {
        vector<vector<int>> ans;
        root = new TrieNode();
        if(words.size() == 0 || words.size() == 1) return ans;
        int emptyIndex = -1;
        for(int i = 0; i < words.size(); i++){
            if(words[i].size() == 0) emptyIndex = i;
            else{
                string word = words[i];
                reverse(word.begin(), word.end());
                insert(word, i);
            }
        }

        for(int i = 0; i < words.size(); i++){
            string word = words[i];

            if(emptyIndex != -1 && i != emptyIndex && isPalindrome(word)) {
                ans.push_back({emptyIndex, i});
                ans.push_back({i,emptyIndex});
            }

            vector<int> temp = search(word, i);
            if(!temp.empty()){
                for(int id:temp)
                    ans.push_back({i, id});
            }
        }


        return ans;

    }
};

int main336(){
    vector<string> words({"a","b","c","ab","ac","aa"});
    Solution solution;
    solution.palindromePairs(words);
    return 0;
}