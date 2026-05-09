/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/ERG5-TH9p4A
 * Leetcode Link: https://leetcode.com/problems/cyclically-rotating-a-grid/
 */

// C++ code (Optimal)
// Approach: Store all elements of a layer in an array, compute their rotated positions using modulo, and write them back in one pass.
// TC: O(m × n)
// SC: O(m + n)
class Solution {
public:
    vector<vector<int>> rotateGrid(vector<vector<int>>& grid, int k) {
        int n = grid.size(), m = grid[0].size();

        int top = 0, left = 0, bottom = n - 1, right = m - 1;

        while (top < bottom && left < right) {
            int total = 2 * (bottom - top + 1) + 2 * (right - left + 1) - 4;

            vector<int> r, c, val;

            // Top row: left -> right (excluding last corner)
            for (int j = left; j < right; j++) {
                r.push_back(top);
                c.push_back(j);
                val.push_back(grid[top][j]);
            }

            // Right column: top -> bottom (excluding last corner)
            for (int i = top; i < bottom; i++) {
                r.push_back(i);
                c.push_back(right);
                val.push_back(grid[i][right]);
            }

            // Bottom row: right -> left (excluding last corner)
            for (int j = right; j > left; j--) {
                r.push_back(bottom);
                c.push_back(j);
                val.push_back(grid[bottom][j]);
            }

            // Left column: bottom -> top (excluding last corner)
            for (int i = bottom; i > top; i--) {
                r.push_back(i);
                c.push_back(left);
                val.push_back(grid[i][left]);
            }

            int kk = k % total;

            // Anti-clockwise rotation by kk positions
            for (int i = 0; i < total; i++) {
                int idx = (i + kk) % total;
                grid[r[i]][c[i]] = val[idx];
            }

            top++;
            left++;
            bottom--;
            right--;
        }

        return grid;
    }
};


// C++ code (Brute)
// Approach: Rotate each layer one step anti-clockwise, and repeat this process k % layerSize times for every layer.
// TC: O(m × n × min(m, n)) (worst case)
// SC: O(1)
class Solution {
public:
    vector<vector<int>> rotateGrid(vector<vector<int>>& grid, int k) {
        int n = grid.size(), m = grid[0].size();

        int top = 0, left = 0, bottom = n-1, right = m-1;

        while(top<bottom && left<right) {
            int totalEle = 2 * (bottom - top + 1) + 2 * (right - left + 1) - 4;

            int rotations = k%totalEle;

            while(rotations--) {
                int temp = grid[top][left];

                // Left -> right
                for(int j=left; j<right; j++) { 
                    grid[top][j] = grid[top][j+1];
                }

                // top -> bottom
                for(int i=top; i<bottom; i++) {
                    grid[i][right] = grid[i+1][right];
                }

                // right -> left
                for(int j=right; j>left; j--) {
                    grid[bottom][j] = grid[bottom][j-1];
                }

                // bottom -> top
                for(int i=bottom; i>top; i--) {
                    grid[i][left] = grid[i-1][left];
                }

                grid[top+1][left] = temp;
            }

            top++, left++, bottom--, right--;
        }

        return grid;
    }
};


// *************************************************************************** //


// Java Code (Optimal -> Single Iteration)
// TC: O(m × n)
// SC: O(m + n)
class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;

        int top = 0, left = 0, bottom = n - 1, right = m - 1;

        while (top < bottom && left < right) {
            int total = 2 * (bottom - top + 1) + 2 * (right - left + 1) - 4;

            List<Integer> rows = new ArrayList<>();
            List<Integer> cols = new ArrayList<>();
            List<Integer> vals = new ArrayList<>();

            // Top row
            for (int j = left; j < right; j++) {
                rows.add(top);
                cols.add(j);
                vals.add(grid[top][j]);
            }

            // Right column
            for (int i = top; i < bottom; i++) {
                rows.add(i);
                cols.add(right);
                vals.add(grid[i][right]);
            }

            // Bottom row
            for (int j = right; j > left; j--) {
                rows.add(bottom);
                cols.add(j);
                vals.add(grid[bottom][j]);
            }

            // Left column
            for (int i = bottom; i > top; i--) {
                rows.add(i);
                cols.add(left);
                vals.add(grid[i][left]);
            }

            int kk = k % total;

            // Left rotation by kk positions
            for (int i = 0; i < total; i++) {
                int idx = (i + kk) % total;
                grid[rows.get(i)][cols.get(i)] = vals.get(idx);
            }

            top++;
            left++;
            bottom--;
            right--;
        }

        return grid;
    }
}


// Java code (Brute)
// TC: O(m × n × min(m, n)) (worst case)
// SC: O(1)
class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;

        int top = 0, left = 0, bottom = n - 1, right = m - 1;

        while (top < bottom && left < right) {
            int total = 2 * (bottom - top + 1) + 2 * (right - left + 1) - 4;
            int rotations = k % total;

            while (rotations-- > 0) {
                int temp = grid[top][left];

                // Top row
                for (int j = left; j < right; j++) {
                    grid[top][j] = grid[top][j + 1];
                }

                // Right column
                for (int i = top; i < bottom; i++) {
                    grid[i][right] = grid[i + 1][right];
                }

                // Bottom row
                for (int j = right; j > left; j--) {
                    grid[bottom][j] = grid[bottom][j - 1];
                }

                // Left column
                for (int i = bottom; i > top + 1; i--) {
                    grid[i][left] = grid[i - 1][left];
                }

                grid[top + 1][left] = temp;
            }

            top++;
            left++;
            bottom--;
            right--;
        }

        return grid;
    }
}