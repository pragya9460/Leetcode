/**
 * Scroll below to see Java code.
 * 
 * My youtube video of this question: https://youtu.be/RnMeCr1KTiE
 * Leetcode Link: https://leetcode.com/problems/jump-game-ix/
 */

// C++ code (DP)
// Approach: Build preMax[i] = maximum till index i and sufMin[i] = minimum from index i to end. Traverse from right: if current prefix max is ≤ next suffix min, current answer becomes preMax[i], otherwise inherit answer from right side.
// TC: O(n)
// SC: O(n)
class Solution {
public:
    vector<int> maxValue(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n, 0);

        vector<int> preMax(n, INT_MIN);
        vector<int> sufMin(n, INT_MAX);

        preMax[0] = nums[0];
        for(int i=1; i<n; i++) {
            preMax[i] = max(preMax[i-1], nums[i]);
        }

        sufMin[n-1] = nums[n-1];
        for(int i=n-2; i>=0; i--) {
            sufMin[i] = min(sufMin[i+1], nums[i]);
        }

        ans[n-1] = preMax[n-1];
        for(int i=n-2; i>=0; i--) {
            if(preMax[i]<=sufMin[i+1]) {
                ans[i] = preMax[i];
            } else {
                ans[i] = ans[i+1];
            }
        }

        return ans;
    }
};

// *************************************************************************** //


// Java Code (DP)
// TC: O(n)
// SC: O(n)
class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;

        int[] ans = new int[n];
        int[] preMax = new int[n];
        int[] sufMin = new int[n];

        preMax[0] = nums[0];
        for(int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], nums[i]);
        }

        sufMin[n - 1] = nums[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            sufMin[i] = Math.min(sufMin[i + 1], nums[i]);
        }

        ans[n - 1] = preMax[n - 1];

        for(int i = n - 2; i >= 0; i--) {
            if(preMax[i] <= sufMin[i + 1]) {
                ans[i] = preMax[i];
            } else {
                ans[i] = ans[i + 1];
            }
        }

        return ans;
    }
}