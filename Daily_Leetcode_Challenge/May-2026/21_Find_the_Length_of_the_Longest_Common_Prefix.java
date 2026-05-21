/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/e99tuyxvtS4
 * Leetcode Link: https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/
 */


// C++ code (Using Trie Data Structure)
// Approach: Insert all numbers from arr1 digit by digit into a Trie, where each path represents a numeric prefix. For each number in arr2, traverse the Trie until digits stop matching; the deepest traversal length gives the longest common prefix for that number.
// TC: O(n*d + m*d) where d is the number of digits in the numbers (log(max(A[i], B[i]))), n and m are the lengths of A and B respectively.
// SC: O(n*d)
/* code ->

class TrieNode {
public:
    TrieNode* child[10];
    TrieNode() {
        for(int i=0; i<10; i++) {
            child[i] = NULL;
        }
    }
};
class Trie {
public:
    TrieNode* root;

    Trie() {
        root = new TrieNode();
    }

    void insert(int num) {
        TrieNode* node = root;
        string numStr = to_string(num);

        for(char digit: numStr) {
            int idx = digit - '0';
            if(!node->child[idx]) {
                node->child[idx] = new TrieNode();
            }
            node = node->child[idx];
        }
    }

    int findLongestPrefix(int num) {
        TrieNode* node = root;
        string numStr = to_string(num);

        int len = 0;
        for(char digit: numStr) {
            int idx = digit - '0';

            if(node->child[idx]) {
                len++;
                node = node->child[idx];
            } else {
                break;
            }
        }

        return len;
    }
};
class Solution {
public:
    int longestCommonPrefix(vector<int>& arr1, vector<int>& arr2) {
        Trie* trie = new Trie();

        for(int num: arr1) {
            trie->insert(num);
        }

        int ans = 0;

        for(int num: arr2) {
            int len = trie->findLongestPrefix(num);
            ans = max(ans, len);
        }

        return ans;
    }
};

*/


// C++ code (Using Set Data Structure)
// Approach: Generate and store all prefixes of numbers in arr1 in a HashSet, then check every prefix of each number in arr2. Whenever a prefix exists in the set, update the maximum prefix length found so far.
// TC: O(n*d + m*d) 
// SC: O(n*d)
class Solution {
public:
    int longestCommonPrefix(vector<int>& arr1, vector<int>& arr2) {
        unordered_set<string> st1;
        int ans = 0;

        for (int num : arr1) {
            string s = to_string(num);
            for (int i = 1; i <= s.size(); i++) {
                st1.insert(s.substr(0, i));
            }
        }

        for (int num : arr2) {
            string s = to_string(num);
            for (int i = 1; i <= s.size(); i++) {
                if (st1.count(s.substr(0, i))) {
                    ans = max(ans, i);
                }
            }
        }
        
        return ans;
    }
};


// C++ code (Brute -> 2 Loops) -> Will Give TLE
// Approach: Compare every number in arr1 with every number in arr2, convert them to strings, and count matching digits from the beginning until a mismatch occurs. The maximum matched prefix length among all pairs is the answer.
// TC: O(n^2 * d) where d is the number of digits in the numbers (log(max(A[i], B[i])))
// SC: O(1)
class Solution {
public:
    int longestCommonPrefix(vector<int>& arr1, vector<int>& arr2) {
        int n = arr1.size(), m = arr2.size();
        int ans = 0;

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                string a = to_string(arr1[i]);
                string b = to_string(arr2[j]);

                int x = 0;
                while(x<a.size() && x<b.size()) {
                    if(a[x]!=b[x]) {
                        break;
                    }
                    x++;
                }

                ans = max(ans, x);
            }
        }

        return ans;
    }
};


// *************************************************************************** //


// Java Code (Using Trie Data Structure)
// TC: O(n*d + m*d) where d is the number of digits in the numbers (log(max(A[i], B[i]))), n and m are the lengths of A and B respectively.
// SC: O(n*d)
class TrieNode {
    TrieNode[] child;

    TrieNode() {
        child = new TrieNode[10];
    }
}

class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    public void insert(int num) {
        TrieNode node = root;
        String numStr = String.valueOf(num);

        for (char digit : numStr.toCharArray()) {
            int idx = digit - '0';

            if (node.child[idx] == null) {
                node.child[idx] = new TrieNode();
            }

            node = node.child[idx];
        }
    }

    public int findLongestPrefix(int num) {
        TrieNode node = root;
        String numStr = String.valueOf(num);

        int len = 0;

        for (char digit : numStr.toCharArray()) {
            int idx = digit - '0';

            if (node.child[idx] != null) {
                len++;
                node = node.child[idx];
            } else {
                break;
            }
        }

        return len;
    }
}

class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Trie trie = new Trie();

        for (int num : arr1) {
            trie.insert(num);
        }

        int ans = 0;

        for (int num : arr2) {
            int len = trie.findLongestPrefix(num);
            ans = Math.max(ans, len);
        }

        return ans;
    }
}


// Java Code (Using Set Data Structure)
// TC: O(n*d + m*d)
// SC: O(n*d)
class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<String> set = new HashSet<>();
        int ans = 0;

        // Store all prefixes of numbers in arr1
        for (int num : arr1) {
            String s = String.valueOf(num);
            for (int i = 1; i <= s.length(); i++) {
                set.add(s.substring(0, i));
            }
        }

        // Check all prefixes of numbers in arr2
        for (int num : arr2) {
            String s = String.valueOf(num);
            for (int i = 1; i <= s.length(); i++) {
                if (set.contains(s.substring(0, i))) {
                    ans = Math.max(ans, i);
                }
            }
        }

        return ans;
    }
}


// Java Code (Brute -> 2 Loops)
// TC: O(n^2 * d) where d is the number of digits in the numbers (log(max(A[i], B[i])))
// SC: O(1)
class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        int n = arr1.length, m = arr2.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                String a = String.valueOf(arr1[i]);
                String b = String.valueOf(arr2[j]);

                int x = 0;
                while (x < a.length() && x < b.length()) {
                    if (a.charAt(x) != b.charAt(x)) {
                        break;
                    }
                    x++;
                }

                ans = Math.max(ans, x);
            }
        }

        return ans;
    }
}