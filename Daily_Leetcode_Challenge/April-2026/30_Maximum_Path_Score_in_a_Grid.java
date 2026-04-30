/**
 * Scroll below to see Java code & other approaches code as well.
 * 
 * My youtube video of this question: https://youtu.be/XrmLI-wtJlk
 * Leetcode Link: https://leetcode.com/problems/maximum-path-score-in-a-grid/
 */

// C++ code (Bottom up DP)
// Approach: Build a 3D DP table where dp[i][j][c] stores max score reaching (i,j) using c cost, transitioning from top/left states.
// TC: O(n*m*k)
// SC: O(n*m*k)
class Solution {
public:
    int n, m;
    int maxPathScore(vector<vector<int>>& grid, int k) {
        n = grid.size();
        m = grid[0].size();

        vector<vector<vector<int>>> dp(n, vector<vector<int>>(m, vector<int>(k+1, -2)));

        for(int i=n-1; i>=0; i--) {
            for(int j=m-1; j>=0; j--) {
                for(int c=k; c>=0; c--) {
                    int new_c = c;
                    if(grid[i][j]!=0) {
                        new_c++;
                    }

                    if(new_c>k) {
                        dp[i][j][c] = -1;
                        continue;
                    }

                    if(i==n-1 && j==m-1) {
                        dp[i][j][c] = grid[i][j];
                        continue;
                    }

                    int right = -1;
                    if(j+1<m) {
                        right = dp[i][j+1][new_c];
                    }
                    int down = -1;
                    if(i+1<n) {
                        down = dp[i+1][j][new_c];
                    }

                    int mx = max(right, down);

                    if(mx==-1) {
                        dp[i][j][c] = -1;
                    } else {
                        dp[i][j][c] = mx + grid[i][j];
                    }
                }
            }
        }

        return dp[0][0][0];
    }
};

// C++ code (Top down DP -> Memoization)
// Approach: Cache results for state (i, j, cost) to avoid recomputation, ensuring cost includes current cell before lookup.
// TC: O(n*m*k)
// SC: O(n*m*k + n*m*k)
class Solution {
public:
    int n, m;
    int rec(vector<vector<int>>& grid, int k, int i, int j, int cost, vector<vector<vector<int>>> &dp) {
        if(i>=n or j>=m) {
            return -1;
        }

        if(grid[i][j]!=0) {
            cost++;
        }

        if(cost>k) {
            return -1;
        }

        if(i==n-1 && j==m-1) {
            return grid[i][j];
        }

        if(dp[i][j][cost]!=-2) {
            return dp[i][j][cost];
        }

        int right = rec(grid, k, i, j+1, cost, dp);
        int down = rec(grid, k, i+1, j, cost, dp);

        int mx = max(right, down);

        if(mx==-1) {
            return dp[i][j][cost] = -1;
        }

        return dp[i][j][cost] = mx + grid[i][j];
    }
    int maxPathScore(vector<vector<int>>& grid, int k) {
        n = grid.size();
        m = grid[0].size();

        vector<vector<vector<int>>> dp(n, vector<vector<int>>(m, vector<int>(k+1, -2)));

        return rec(grid, k, 0, 0, 0, dp);
    }
};

// C++ code (Recursion)
// Approach: Try all paths (up/left) while tracking cost, and return max score only if cost ≤ k; otherwise discard path.
// TC: O(2^(m+n))
// SC: O(m+n)
class Solution {
public:
    int n, m;
    int rec(vector<vector<int>>& grid, int k, int i, int j, int cost) {
        if(i>=n or j>=m) {
            return -1;
        }

        if(grid[i][j]!=0) {
            cost++;
        }

        if(cost>k) {
            return -1;
        }

        if(i==n-1 && j==m-1) {
            return grid[i][j];
        }

        int right = rec(grid, k, i, j+1, cost);
        int down = rec(grid, k, i+1, j, cost);

        int mx = max(right, down);

        if(mx==-1) {
            return -1;
        }

        return mx + grid[i][j];
    }
    int maxPathScore(vector<vector<int>>& grid, int k) {
        n = grid.size();
        m = grid[0].size();

        return rec(grid, k, 0, 0, 0);
    }
};



//************************************************************************************************** //


// Java Code
// TC: O(n*m*k)
// SC: O(n*m*k)
class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;

        int[][][] dp = new int[n][m][k + 1];

        // initialize with -2
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int c = 0; c <= k; c++) {
                    dp[i][j][c] = -2;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                for (int c = k; c >= 0; c--) {

                    int newC = c;
                    if (grid[i][j] != 0) {
                        newC++;
                    }

                    if (newC > k) {
                        dp[i][j][c] = -1;
                        continue;
                    }

                    if (i == n - 1 && j == m - 1) {
                        dp[i][j][c] = grid[i][j];
                        continue;
                    }

                    int right = -1;
                    if (j + 1 < m) {
                        right = dp[i][j + 1][newC];
                    }

                    int down = -1;
                    if (i + 1 < n) {
                        down = dp[i + 1][j][newC];
                    }

                    int mx = Math.max(right, down);

                    if (mx == -1) {
                        dp[i][j][c] = -1;
                    } else {
                        dp[i][j][c] = mx + grid[i][j];
                    }
                }
            }
        }

        return dp[0][0][0];
    }
}