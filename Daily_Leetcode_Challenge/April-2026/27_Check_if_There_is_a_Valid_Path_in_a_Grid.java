/**
 * Scroll below to see Java code as well.
 * 
 * My youtube video of this question: https://youtu.be/zDLBZeyNTjc
 * Leetcode Link: https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/
 */

// C++ code (DFS)
// Approach: DFS traversal on the grid, where from each cell you only move in allowed directions based on street type and ensure the next cell has a valid reverse connection back. If you can reach (n-1, m-1) following only these mutually compatible connections, a valid path exists.
// TC: O(n*m)
// SC: O(n*m)
class Solution {
public:
    unordered_map<int, vector<vector<int>>> directions = {
        {1, {{0, -1}, {0, 1}}},
        {2, {{-1, 0}, {1, 0}}},
        {3, {{0, -1}, {1, 0}}},
        {4, {{1, 0}, {0, 1}}},
        {5, {{0, -1}, {-1, 0}}},
        {6, {{-1, 0}, {0, 1}}}
    };
    int n, m;
    bool dfs(int i, int j, vector<vector<int>> &vis, vector<vector<int>> &grid) {
        if(i==n-1 && j==m-1) {
            return true;
        }
        vis[i][j] = true;

        for(auto &dir: directions[grid[i][j]]) {
            int ni = i + dir[0];
            int nj = j + dir[1];

            if(ni<0 or nj<0 or ni>=n or nj>=m or vis[ni][nj]) {
                continue;
            }

            for(auto &revDir: directions[grid[ni][nj]]) {
                if(ni+revDir[0]==i && nj+revDir[1]==j) {
                    if(dfs(ni, nj, vis, grid)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    bool hasValidPath(vector<vector<int>>& grid) {
        n = grid.size();
        m = grid[0].size();

        vector<vector<int>> vis(n, vector<int>(m, 0));

        return dfs(0, 0, vis, grid);
    }
};

//************************************************************************************************** //


// Java Code
// TC: O(n*m)
// SC: O(n*m)

class Solution {
    int[][][] directions = {
        {}, // dummy (1-based indexing)
        {{0, -1}, {0, 1}},   // 1
        {{-1, 0}, {1, 0}},   // 2
        {{0, -1}, {1, 0}},   // 3
        {{1, 0}, {0, 1}},    // 4
        {{0, -1}, {-1, 0}},  // 5
        {{-1, 0}, {0, 1}}    // 6
    };

    int n, m;

    public boolean dfs(int i, int j, boolean[][] vis, int[][] grid) {
        if (i == n - 1 && j == m - 1) return true;

        vis[i][j] = true;

        for (int[] dir : directions[grid[i][j]]) {
            int ni = i + dir[0];
            int nj = j + dir[1];

            if (ni < 0 || nj < 0 || ni >= n || nj >= m || vis[ni][nj]) continue;

            for (int[] revDir : directions[grid[ni][nj]]) {
                if (ni + revDir[0] == i && nj + revDir[1] == j) {
                    if (dfs(ni, nj, vis, grid)) return true;
                }
            }
        }
        return false;
    }

    public boolean hasValidPath(int[][] grid) {
        n = grid.length;
        m = grid[0].length;

        boolean[][] vis = new boolean[n][m];
        return dfs(0, 0, vis, grid);
    }
}