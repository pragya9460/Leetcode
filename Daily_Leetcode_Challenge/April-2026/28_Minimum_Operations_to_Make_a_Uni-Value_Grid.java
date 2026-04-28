/**
 * Scroll below to see Java code as well.
 * 
 * My youtube video of this question: https://youtu.be/EPztTf5aUaU
 * Leetcode Link: https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/
 */

// C++ code 
// Approach: Flatten the grid and check all elements have the same remainder modulo x; otherwise return -1. Sort the array and compute the minimum operations by converting all elements to the median (for even size, try both medians and take the minimum).
// TC: O(n*m*log(n*m))
// SC: O(n*m)
class Solution {
public:
    int minOperations(vector<vector<int>>& grid, int x) {
        int n = grid.size(), m = grid[0].size();

        int mod = grid[0][0]%x;
        vector<int> arr;

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(grid[i][j]%x!=mod) {
                    return -1;
                }
                arr.push_back(grid[i][j]);
            }
        }

        sort(arr.begin(), arr.end());
        int sz = arr.size();

        if(sz%2==0) {
            int op1 = 0, op2 = 0;
            int m1 = arr[sz/2], m2 = arr[sz/2 - 1];
            for(int i=0; i<sz; i++) {
                op1 += abs(arr[i]-m1)/x;
                op2 += abs(arr[i]-m2)/x;
            }
            return min(op1, op2);
        } else {
            int op = 0;
            int m = arr[sz/2];
            for(int i=0; i<sz; i++) {
                op += abs(arr[i]-m)/x;
            }
            return op;
        }

        return -1;
    }
};


//************************************************************************************************** //


// Java Code
// TC: O(n*m*log(n*m))
// SC: O(n*m)
class Solution {
    public int minOperations(int[][] grid, int x) {
        int n = grid.length, m = grid[0].length;

        int mod = grid[0][0] % x;
        List<Integer> arr = new ArrayList<>();

        // Flatten + check feasibility
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] % x != mod) {
                    return -1;
                }
                arr.add(grid[i][j]);
            }
        }

        Collections.sort(arr);
        int sz = arr.size();

        if (sz % 2 == 0) {
            int m1 = arr.get(sz / 2);
            int m2 = arr.get(sz / 2 - 1);

            int op1 = 0, op2 = 0;

            for (int val : arr) {
                op1 += Math.abs(val - m1) / x;
                op2 += Math.abs(val - m2) / x;
            }

            return Math.min(op1, op2);
        } else {
            int median = arr.get(sz / 2);
            int op = 0;

            for (int val : arr) {
                op += Math.abs(val - median) / x;
            }

            return op;
        }
    }
}
