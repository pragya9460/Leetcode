/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/YOV3SqUWpsI
 * Leetcode Link: https://leetcode.com/problems/rotating-the-box/
 */

// C++ code (Optimal -> Single Iteration)
// Approach: Simulate rightward gravity row-wise in original matrix. Place elements directly into rotated positions.
// TC: O(n*m)
// SC: O(n*m)
class Solution {
public:
    vector<vector<char>> rotateTheBox(vector<vector<char>>& box) {
        int n = box.size(), m = box[0].size();
        vector<vector<char>> ans(m, vector<char>(n, '.'));

        for(int i = 0; i < n; i++) {
            int lastEmptyCol = m - 1;

            for(int j = m - 1; j >= 0; j--) {
                if(box[i][j] == '#') {
                    ans[lastEmptyCol][n - 1 - i] = '#';
                    lastEmptyCol--;
                } 
                else if(box[i][j] == '*') {
                    ans[j][n - 1 - i] = '*';
                    lastEmptyCol = j - 1;
                }
            }
        }

        return ans;
    }
};

// C++ code (Brute)
// Approach: First rotate the matrix (transpose + reverse). Then simulate downward gravity column-wise.
// TC: O(n*m)
// SC: O(n*m)
class Solution {
public:
    vector<vector<char>> rotateTheBox(vector<vector<char>>& box) {
        int n = box.size(), m = box[0].size();
        vector<vector<char>> ans(m, vector<char>(n, '.'));

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                ans[j][i] = box[i][j];
            }
        }

        for(int i=0; i<m; i++) {
            reverse(ans[i].begin(), ans[i].end());
        }

        for(int j=0; j<n; j++) {

            int lastRowWithEmptyCell = m-1;

            for(int i=m-1; i>=0; i--) {
                if(ans[i][j]=='#') {
                    ans[i][j] = '.';
                    ans[lastRowWithEmptyCell][j] = '#';
                    lastRowWithEmptyCell--;
                } else if(ans[i][j]=='*') {
                    lastRowWithEmptyCell = i-1;
                }
            }
        }

        return ans;
    }
};


// *************************************************************************** //


// Java Code (Optimal -> Single Iteration)
// TC: O(n*m)
// SC: O(n*m)
class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int n = box.length, m = box[0].length;
        char[][] ans = new char[m][n];

        // Fill with '.'
        for (int i = 0; i < m; i++) {
            Arrays.fill(ans[i], '.');
        }

        for (int i = 0; i < n; i++) {
            int empty = m - 1;

            for (int j = m - 1; j >= 0; j--) {
                if (box[i][j] == '#') {
                    ans[empty][n - 1 - i] = '#';
                    empty--;
                } else if (box[i][j] == '*') {
                    ans[j][n - 1 - i] = '*';
                    empty = j - 1;
                }
            }
        }

        return ans;
    }
}


// Java code (Brute)
// TC: O(n*m)
// SC: O(n*m)
class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int n = box.length, m = box[0].length;
        char[][] ans = new char[m][n];

        // Step 1: transpose
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[j][i] = box[i][j];
            }
        }

        // Step 2: reverse each row
        for (int i = 0; i < m; i++) {
            int l = 0, r = n - 1;
            while (l < r) {
                char temp = ans[i][l];
                ans[i][l] = ans[i][r];
                ans[i][r] = temp;
                l++;
                r--;
            }
        }

        // Step 3: apply gravity (downward)
        for (int j = 0; j < n; j++) {
            int empty = m - 1;
            for (int i = m - 1; i >= 0; i--) {
                if (ans[i][j] == '#') {
                    ans[i][j] = '.';
                    ans[empty][j] = '#';
                    empty--;
                } else if (ans[i][j] == '*') {
                    empty = i - 1;
                }
            }
        }

        return ans;
    }
}