/**
 * Scroll below to see Java code.
 * 
 * My youtube video of this question: https://youtu.be/xmSyEL64zyU
 * Leetcode Link: https://leetcode.com/problems/minimum-moves-to-make-array-complementary/
 */

// C++ code (Optimal)
// Approach: For each symmetric pair (nums[i], nums[n-1-i]), the number of moves required for every possible target sum follows a fixed pattern: mostly 2 moves, a range of 1 move, and exactly one sum (a + b) requiring 0 moves. Instead of updating all target sums individually, we use a difference array to mark where the move count changes. After processing all pairs, a prefix sum reconstructs the total moves needed for each target sum, and the minimum of these values is the answer.
// TC: O(n + limit)
// SC: O(limit)
class Solution {
public:
    int minMoves(vector<int>& nums, int limit) {
        int n = nums.size();

        vector<int> delta(2*limit+2, 0);

        for(int i=0; i<n/2; i++) {
            int mn = nums[i];
            int mx = nums[n-1-i];

            if(mn>mx) {
                swap(mn, mx);
            }

            delta[2] += 2;
            delta[mn+1]--;
            delta[mn+mx]--;
            delta[mn+mx+1]++;
            delta[mx+limit+1]++;
        }

        int ans = n, moves = 0;

        for(int t = 2; t<=limit*2; t++) {
            moves += delta[t];

            ans = min(ans, moves);
        }

        return ans;
    }
};


// *************************************************************************** //


// Java Code Optimal
// TC: O(n + limit)
// SC: O(limit)
class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] delta = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {
            int mn = nums[i];
            int mx = nums[n - 1 - i];

            if (mn > mx) {
                int temp = mn;
                mn = mx;
                mx = temp;
            }

            delta[2] += 2;
            delta[mn + 1]--;
            delta[mn + mx]--;
            delta[mn + mx + 1]++;
            delta[mx + limit + 1]++;
        }

        int ans = n;
        int moves = 0;

        for (int target = 2; target <= 2 * limit; target++) {
            moves += delta[target];
            ans = Math.min(ans, moves);
        }

        return ans;
    }
}
