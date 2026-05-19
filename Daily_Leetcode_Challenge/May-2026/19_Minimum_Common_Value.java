/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/Lj5nxia6y48
 * Leetcode Link: https://leetcode.com/problems/minimum-common-value/
 */

// C++ code
// Approach: 2 Pointer approach. Since both arrays are sorted, we can use two pointers to traverse them simultaneously. If the elements at both pointers are equal, we have found the minimum common value. If the element in arr1 is smaller, we move the pointer in arr1 forward; if the element in arr2 is smaller, we move the pointer in arr2 forward. This way, we efficiently find the minimum common value without needing extra space.
// TC: O(m+n)
// SC: O(1)
class Solution {
public:
    int getCommon(vector<int>& a1, vector<int>& a2) {
        int n = a1.size(), m = a2.size();

        int i=0, j=0;

        while(i<n && j<m) {
            if(a1[i]==a2[j]) {
                return a1[i];
            } else if(a1[i]<a2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return -1;
    }
};


// C++ code
// Approach: Use a hash set to store the elements of the first array, then iterate through the second array and check if any element is present in the set. Keep track of the minimum common value found. This approach is straightforward but uses extra space for the hash set.
// TC: O(m+n)
// SC: O(m) where m is the size of the first array
class Solution {
public:
    int getCommon(vector<int>& a1, vector<int>& a2) {
        int n = a1.size(), m = a2.size();
        unordered_set<int> st;

        for(int i=0; i<m; i++) {
            st.insert(a2[i]);
        }
        
        for(int i=0; i<n; i++) {
            if(st.find(a1[i])!=st.end()) {
                return a1[i];
            }
        }

        return -1;
    }
};


// C++ code
// Approach: Using Binary Search. For each element in the smaller array, perform a binary search in the larger array to check for its presence. Keep track of the minimum common value found. This approach is efficient when one array is significantly smaller than the other.
// TC: O(m log n) where m is the size of the smaller array and n is the size of the larger array
// SC: O(1)
class Solution {
public:
    int getCommon(vector<int>& a1, vector<int>& a2) {
        int n = a1.size(), m = a2.size();
        
        for(int i=0; i<n; i++) {
            int val = a1[i];
            int l = 0, h = m-1;

            while(l<=h) {
                int m = l + (h-l)/2;

                if(a2[m]==val) {
                    return a2[m];
                } else if(a2[m]<val) {
                    l = m+1;
                } else {
                    h = m-1;
                }
            }
        }

        return -1;
    }
};



// *************************************************************************** //


// Java Code
// TC: O(m+n)
// SC: O(1)
class Solution {
    public int getCommon(int[] a1, int[] a2) {
        int n = a1.length, m = a2.length;
        int i=0, j=0;
        while(i<n && j<m) {
            if(a1[i]==a2[j]) {
                return a1[i];
            } else if(a1[i]<a2[j]) {
                i++;
            } else {
                j++;
            }
        }   
        return -1;  
    }
}

// Java Code
// TC: O(m+n)
// SC: O(m)
class Solution {
    public int getCommon(int[] a1, int[] a2) {
        int n = a1.length, m = a2.length;
        HashSet<Integer> st = new HashSet<>();

        for(int i=0; i<m; i++) {
            st.add(a2[i]);
        }   
        for(int i=0; i<n; i++) {
            if(st.contains(a1[i])) {
                return a1[i];
            }
        }   
        return -1;
    }   
}

// Java Code
// TC: O(m log n) where m is the size of the smaller array and n is the size of the larger array
// SC: O(1)
class Solution {
    public int getCommon(int[] a1, int[] a2) {
        int n = a1.length, m = a2.length;
        
        for(int i=0; i<n; i++) {
            int val = a1[i];
            int l = 0, h = m-1;

            while(l<=h) {
                int mid = l + (h-l)/2;

                if(a2[mid]==val) {
                    return a2[mid];
                } else if(a2[mid]<val) {
                    l = mid+1;
                } else {
                    h = mid-1;
                }
            }
        }

        return -1;
    }
}
