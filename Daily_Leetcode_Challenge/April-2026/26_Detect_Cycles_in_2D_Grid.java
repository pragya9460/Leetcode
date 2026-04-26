/**
 * Scroll below to see Java code as well, scroll further to see code for brute approach.
 * 
 * My youtube video of this question: https://youtu.be/DuZCHZVR7bI
 * Leetcode Link: https://leetcode.com/problems/detect-cycles-in-2d-grid/
 */

// C++ code (DFS)
// Approach: Use DFS/BFS on each unvisited cell, exploring only same-character neighbors, while tracking the parent cell. If you encounter a visited neighbor that is not the parent, a cycle exists.
// TC: O(n*m)
// SC: O(n*m)
class Solution {
public:
    int dx[4] = {-1, 0, 1, 0};
    int dy[4] = {0, 1, 0, -1};
    int n, m;
    bool isCycle(int i, int j, vector<vector<int>> &vis, vector<vector<char>> &grid, char c, int pi, int pj) {
        vis[i][j] = 1;
        cout<<i<<" "<<j<<endl;

        for(int ind=0; ind<4; ind++) {
            int ni = i + dx[ind];
            int nj = j + dy[ind];

            if(ni<0 or nj<0 or ni>=n or nj>=m or grid[ni][nj]!=c) {
                continue;
            }
            if(!vis[ni][nj]) {
                if(isCycle(ni, nj, vis, grid, c, i, j)){
                    return true;
                } 
            } else if(!(ni == pi && nj == pj)) {
                return true;
            }
        }

        return false;
    }
    bool containsCycle(vector<vector<char>>& grid) {
        n = grid.size();
        m = grid[0].size();

        vector<vector<int>> vis(n, vector<int>(m, 0));

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(!vis[i][j]) {
                    if(isCycle(i, j, vis, grid, grid[i][j], i, j)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
};

//************************************************************************************************** //


// Java Code
// TC: O(n*m)
// SC: O(n*m)
class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    int n, m;

    public boolean isCycle(int i, int j, int pi, int pj, int[][] vis, char[][] grid, char c) {

        vis[i][j] = 1;

        for (int ind = 0; ind < 4; ind++) {
            int ni = i + dx[ind];
            int nj = j + dy[ind];

            if (ni < 0 || nj < 0 || ni >= n || nj >= m || grid[ni][nj] != c) {
                continue;
            }

            if (vis[ni][nj] == 0) {
                if (isCycle(ni, nj, i, j, vis, grid, c)) {
                    return true;
                }
            } else if (!(ni == pi && nj == pj)) {
                return true;
            }
        }

        return false;
    }

    public boolean containsCycle(char[][] grid) {
        n = grid.length;
        m = grid[0].length;

        int[][] vis = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == 0) {
                    if (isCycle(i, j, -1, -1, vis, grid, grid[i][j])) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}