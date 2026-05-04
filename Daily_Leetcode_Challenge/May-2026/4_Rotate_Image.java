/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/PZtAcX3PxCk
 * Leetcode Link: https://leetcode.com/problems/rotate-image/
 */

// C++ code (Optimal)
// Approach: First transpose the matrix, then reverse each row to achieve 90° rotation.
// TC: O(n*n)
// SC: O(1)
class Solution {
public:
    void rotate(vector<vector<int>>& mat) {
        int n = mat.size(), m = mat[0].size();
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                swap(mat[i][j], mat[j][i]);
            }
        }
        
        for(int i=0; i<n; i++) {
            reverse(mat[i].begin(), mat[i].end());
        }
    }
};

// C++ code (Brute)
// Approach: Create a new matrix and place each element using mapping (i, j) → (j, n−1−i), then copy it back.
// TC: O(n*n)
// SC: O(n*n)
class Solution {
public:
    void rotate(vector<vector<int>>& mat) {
        int n = mat.size();
        vector<vector<int>> res(n, vector<int>(n));

        // Map (i, j) -> (j, n-1-i)
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                res[j][n - 1 - i] = mat[i][j];
            }
        }

        // Copy back
        mat = res;
    }
};

// ******************************************************************************* //


// Java Code (Optimal)
// TC: O(n*n)
// SC: O(1)
class Solution {
    public void rotate(int[][] mat) {
        int n = mat.length;

        // Transpose
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        // Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = mat[i][left];
                mat[i][left] = mat[i][right];
                mat[i][right] = temp;
                left++;
                right--;
            }
        }
    }
}


// Java Code (Brute)
// TC: O(n*n)
// SC: O(n*n)
class Solution {
    public void rotate(int[][] mat) {
        int n = mat.length;
        int[][] res = new int[n][n];

        // Map (i, j) -> (j, n-1-i)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[j][n - 1 - i] = mat[i][j];
            }
        }

        // Copy back to original matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = res[i][j];
            }
        }
    }
}