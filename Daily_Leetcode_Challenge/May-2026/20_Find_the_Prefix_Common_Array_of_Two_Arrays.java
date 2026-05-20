/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/-Mu9bc4L6Cs
 * Leetcode Link: https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/
 */

// C++ code (Most Optimal -> Bit Manipulation)
// Approach: Use two bitmasks to store the numbers seen so far in A and B, where each bit represents a number from 1 to n. At every index, the count of common elements is the number of set bits in (maskA & maskB).
// TC: O(n)
// SC: O(1)
class Solution {
public:
    vector<int> findThePrefixCommonArray(vector<int>& A, vector<int>& B) {
        vector<int> ans;
        unordered_set<int> s1, s2;
        int n = A.size();
        int cnt = 0;

        for(int i=0; i<n; i++) {
            s1.insert(A[i]);
            s2.insert(B[i]);

            if(A[i]==B[i]) {
                cnt++;
            } else {
                if(s1.find(B[i])!=s1.end()) {
                    cnt++;
                }
                if(s2.find(A[i])!=s2.end()) {
                    cnt++;
                }
            }
            ans.push_back(cnt);
        }

        return ans;
    }
};


// C++ code (Optimal -> Frequency Array)
// Approach: Maintain a frequency array and increment counts for A[i] and B[i]. Whenever any number's frequency becomes 2, it means that number has now appeared in both arrays, so increment the common count.
// TC: O(n)
// SC: O(n) 
class Solution {
public:
    vector<int> findThePrefixCommonArray(vector<int>& A, vector<int>& B) {
        vector<int> ans;
        int n = A.size();
        vector<int> freq(n+1, 0);
        
        int cnt = 0;

        for(int i=0; i<n; i++) {
            freq[A[i]]++;
            freq[B[i]]++;

            if(A[i]==B[i]) {
                cnt++;
            } else {
                if(freq[A[i]]==2){
                    cnt++;
                }
                if(freq[B[i]]==2) {
                    cnt++;
                }
            }

            ans.push_back(cnt);
        }

        return ans;
    }
};


// C++ code (Optimal -> 2 Set)
// Approach: Store the prefix elements of A and B in two hash sets to track numbers seen so far in each array. At every index, if the current element of one array already exists in the other set, it means a new common element is found, so increment the common count.
// TC: O(n) 
// SC: O(2*n)
class Solution {
public:
    vector<int> findThePrefixCommonArray(vector<int>& A, vector<int>& B) {
        vector<int> ans;
        unordered_set<int> s1, s2;
        int n = A.size();
        int cnt = 0;

        for(int i=0; i<n; i++) {
            s1.insert(A[i]);
            s2.insert(B[i]);

            if(A[i]==B[i]) {
                cnt++;
            } else {
                if(s1.find(B[i])!=s1.end()) {
                    cnt++;
                }
                if(s2.find(A[i])!=s2.end()) {
                    cnt++;
                }
            }
            ans.push_back(cnt);
        }

        return ans;
    }
};


// C++ code (Brute -> 3 Loops)
// Approach: For every index i, compare all elements in A[0...i] with all elements in B[0...i]. Count the numbers that match to build the prefix common array.
// TC: O(n^3) 
// SC: O(1)
class Solution {
public:
    vector<int> findThePrefixCommonArray(vector<int>& A, vector<int>& B) {
        vector<int> ans;
        int n = A.size();

        for(int i=0; i<n; i++) {
            int cnt = 0;
            for(int ai=0; ai<=i; ai++) {
                for(int bi=0; bi<=i; bi++) {
                    if(A[ai]==B[bi]) {
                        cnt++;
                        break;
                    }
                }
            }

            ans.push_back(cnt);
        }

        return ans;
    }
};


// *************************************************************************** //


// Java Code (Most Optimal -> Bit Manipulation))
// TC: O(n)
// SC: O(1)
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] ans = new int[n];

        long m1 = 0, m2 = 0;

        for (int i = 0; i < n; i++) {
            m1 |= (1L << A[i]);
            m2 |= (1L << B[i]);

            long commonBits = m1 & m2;

            int commonCnt = Long.bitCount(commonBits);
            ans[i] = commonCnt;
        }

        return ans;
    }
}

// Java Code (Optimal -> Frequency Array)
// TC: O(n)
// SC: O(n)
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] ans = new int[n];
        int[] freq = new int[n + 1];

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            freq[A[i]]++;
            freq[B[i]]++;

            if (A[i] == B[i]) {
                cnt++;
            } else {
                if (freq[A[i]] == 2) {
                    cnt++;
                }
                if (freq[B[i]] == 2) {
                    cnt++;
                }
            }

            ans[i] = cnt;
        }

        return ans;
    }
}


// Java Code (Optimal -> 2 Set)
// TC: O(n)
// SC: O(2*n)
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] ans = new int[n];

        HashSet<Integer> s1 = new HashSet<>();
        HashSet<Integer> s2 = new HashSet<>();

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            s1.add(A[i]);
            s2.add(B[i]);

            if (A[i] == B[i]) {
                cnt++;
            } else {
                if (s1.contains(B[i])) {
                    cnt++;
                }
                if (s2.contains(A[i])) {
                    cnt++;
                }
            }

            ans[i] = cnt;
        }

        return ans;
    }
}


// Java Code (Brute -> 3 Loops)
// TC: O(n^3)
// SC: O(1)
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int cnt = 0;

            for (int ai = 0; ai <= i; ai++) {
                for (int bi = 0; bi <= i; bi++) {
                    if (A[ai] == B[bi]) {
                        cnt++;
                        break;
                    }
                }
            }

            ans[i] = cnt;
        }

        return ans;
    }
}
