/**
 * Scroll below to see Java code as well, scroll further to see code for brute approach.
 * 
 * My youtube video of this question: https://youtu.be/eh0jOLBFt2Y
 * Leetcode Link: https://leetcode.com/problems/words-within-two-edits-of-dictionary/
 */

// C++ code (Optimal using trie)
// Approach: Insert dictionary into a Trie and DFS each query, allowing at most 2 mismatches while pruning invalid paths early using the Trie structure.
// TC: O(Q*L^2*26^2) worst-case (due to ≤2 edits branching) => Trie reduces practical runtime via pruning, though worst-case TC remains similar.
// SC: O(D⋅L⋅26) for Trie storage
class TrieNode {
public:
    vector<TrieNode*> children;
    bool isWordEnd;

    TrieNode() {
        children.resize(26, nullptr);
        isWordEnd = false;
    }
    
    bool hasChild(char ch) {
        return children[ch - 'a'] != nullptr;
    }

    void addChild(char ch, TrieNode* node) {
        children[ch - 'a'] = node;
    }

    TrieNode* getChild(char ch) {
        return children[ch - 'a'];
    }

    void markEnd() {
        isWordEnd = true;
    }
};

class Solution {
public:
    TrieNode* root;

    void insert(string word) {
        TrieNode* node = root;

        for(char ch : word) {
            if(!node->hasChild(ch)) {
                node->addChild(ch, new TrieNode());
            }
            node = node->getChild(ch);
        }
        node->markEnd();
    }

    bool canMatchWithTwoEdits(string& word, int index, int editsUsed, TrieNode* node) {

        if(editsUsed > 2 || node == nullptr) return false;

        if(index == word.size()) {
            return node->isWordEnd;
        }

        int charIndex = word[index] - 'a';

        // Case 1: No edit
        if(node->children[charIndex]) {
            if(canMatchWithTwoEdits(word, index + 1, editsUsed, node->children[charIndex])) {
                return true;
            }
        }

        // Case 2: Use an edit
        if(editsUsed < 2) {
            for(int c = 0; c < 26; c++) {
                if(c == charIndex) continue;

                if(node->children[c]) {
                    if(canMatchWithTwoEdits(word, index + 1, editsUsed + 1, node->children[c])) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    vector<string> twoEditWords(vector<string>& queries, vector<string>& dictionary) {
        vector<string> result;

        root = new TrieNode();

        for(string& word : dictionary) {
            insert(word);
        }

        for(string& query : queries) {
            if(canMatchWithTwoEdits(query, 0, 0, root)) {
                result.push_back(query);
            }
        }

        return result;
    }
};


//************************************************************************************************** //


// Java Code
// TC: O(Q*L^2*26^2) worst-case (due to ≤2 edits branching) => Trie reduces practical runtime via pruning, though worst-case TC remains similar.
// SC: O(D⋅L⋅26) for Trie storage
import java.util.*;

class TrieNode {
    TrieNode[] children;
    boolean isWordEnd;

    TrieNode() {
        children = new TrieNode[26];
        isWordEnd = false;
    }

    boolean hasChild(char ch) {
        return children[ch - 'a'] != null;
    }

    void addChild(char ch, TrieNode node) {
        children[ch - 'a'] = node;
    }

    TrieNode getChild(char ch) {
        return children[ch - 'a'];
    }

    void markEnd() {
        isWordEnd = true;
    }
}

class Solution {
    TrieNode root;

    void insert(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()) {
            if (!node.hasChild(ch)) {
                node.addChild(ch, new TrieNode());
            }
            node = node.getChild(ch);
        }
        node.markEnd();
    }

    boolean canMatchWithTwoEdits(String word, int index, int editsUsed, TrieNode node) {
        if (editsUsed > 2 || node == null) return false;

        if (index == word.length()) {
            return node.isWordEnd;
        }

        int charIndex = word.charAt(index) - 'a';

        // Case 1: No edit
        if (node.children[charIndex] != null) {
            if (canMatchWithTwoEdits(word, index + 1, editsUsed, node.children[charIndex])) {
                return true;
            }
        }

        // Case 2: Use an edit
        if (editsUsed < 2) {
            for (int c = 0; c < 26; c++) {
                if (c == charIndex) continue;

                if (node.children[c] != null) {
                    if (canMatchWithTwoEdits(word, index + 1, editsUsed + 1, node.children[c])) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();

        root = new TrieNode();

        for (String word : dictionary) {
            insert(word);
        }

        for (String query : queries) {
            if (canMatchWithTwoEdits(query, 0, 0, root)) {
                result.add(query);
            }
        }

        return result;
    }
}


// **************************************************************************************************** //


// C++ code (Brute force)
// Approach: For each query, try all possible 1 and 2 character modifications (26 choices each) and check if any transformed string exists in the dictionary using a hash set.
// TC: O(Q*L^2*26^2)
// SC: O(D*L) for storing dictionary in hash set
class Solution {
public:
    vector<string> twoEditWords(vector<string>& queries, vector<string>& dictionary) {
        vector<string> result;

        int dictSize = dictionary.size(), querySize = queries.size();

        unordered_set<string> dictSet;
        for(int i = 0; i < dictSize; i++) {
            dictSet.insert(dictionary[i]);
        }

        for(int i = 0; i < querySize; i++) {
            string currentWord = queries[i];

            if(dictSet.find(currentWord) != dictSet.end()) {
                result.push_back(currentWord);
                continue;
            }

            int wordLen = currentWord.size();

            for(int pos1 = 0; pos1 < wordLen; pos1++) {
                char originalChar1 = currentWord[pos1];
                bool matchFound = false;

                for(char newChar1 = 'a'; newChar1 <= 'z'; newChar1++) {
                    if(newChar1 == originalChar1) continue;

                    currentWord[pos1] = newChar1;

                    if(dictSet.find(currentWord) != dictSet.end()) {
                        result.push_back(queries[i]);
                        matchFound = true;
                        break;
                    }

                    for(int pos2 = pos1 + 1; pos2 < wordLen; pos2++) {
                        char originalChar2 = currentWord[pos2];

                        for(char newChar2 = 'a'; newChar2 <= 'z'; newChar2++) {
                            if(newChar2 == originalChar2) continue;

                            currentWord[pos2] = newChar2;

                            if(dictSet.find(currentWord) != dictSet.end()) {
                                result.push_back(queries[i]);
                                matchFound = true;
                                break;
                            }
                        }

                        if(matchFound) break;
                        currentWord[pos2] = originalChar2;
                    }

                    if(matchFound) break;
                    currentWord[pos1] = originalChar1;
                }

                if(matchFound) break;
            }
        }

        return result;
    }
};