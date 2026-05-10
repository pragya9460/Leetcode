/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/KvZHjj04TqE
 * Leetcode Link: https://leetcode.com/problems/maximum-number-of-jumps-to-reach-the-last-index/
 */

// C++ code (Bottom Up DP)
// Approach:  Iteratively builds a DP table from left to right, where each entry represents the max jumps to reach that index. It eliminates recursion overhead and ensures every possible jumping path is accounted for systematically.
// TC: O(n*n)
// SC: O(n)
class Solution {
public:
    int maximumJumps(vector<int>& nums, int target) {
        int n = nums.size();

        vector<int> dp(n, -1);
        dp[n - 1] = 0;

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (abs(nums[i] - nums[j]) <= target && dp[j] != -1) {
                    dp[i] = max(dp[i], 1 + dp[j]);
                }
            }
        }

        return dp[0];
    }
};


// C++ code (Memoization (Top Down DP))
// Approach: Optimizes recursion by storing the results of each index in a cache. This prevents re-calculating the maximum jumps for an index we've already visited, bringing the complexity down to O(n^2).
// TC: O(n*n)
// SC: O(n)
class Solution {
public:
    int n;

    int rec(vector<int>& nums, int target, int ind, vector<int>& dp) {
        if (ind == n - 1) {
            return 0;
        }

        if (dp[ind] != -2) {
            return dp[ind];
        }

        int ans = -1;

        for (int i = ind + 1; i < n; i++) {
            if (abs(nums[ind] - nums[i]) <= target) {
                int next = rec(nums, target, i, dp);

                if (next != -1) {
                    ans = max(ans, 1 + next);
                }
            }
        }

        return dp[ind] = ans;
    }

    int maximumJumps(vector<int>& nums, int target) {
        n = nums.size();
        vector<int> dp(n, -2);   // -2 = not computed
        return rec(nums, target, 0, dp);
    }
};


// C++ code (Recursion)
// Approach: Explores every valid jump sequence using a decision tree to find the maximum path. It has exponential time complexity due to redundant calculations of the same indices.
// TC: O(2^n) in worst case
// SC: O(n) due to recursion stack
class Solution {
public:
    int n;

    int rec(vector<int>& nums, int target, int ind) {
        if (ind == n - 1) {
            return 0;
        }

        int ans = -1;

        for (int i = ind + 1; i < n; i++) {
            if (abs(nums[ind] - nums[i]) <= target) {
                int next = rec(nums, target, i);

                if (next != -1) {
                    ans = max(ans, 1 + next);
                }
            }
        }

        return ans;
    }

    int maximumJumps(vector<int>& nums, int target) {
        n = nums.size();
        return rec(nums, target, 0);
    }
};


// *************************************************************************** //


// Java Code (Bottom Up DP)
// TC: O(n*n)
// SC: O(n)
class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;

        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[n - 1] = 0;

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) <= target && dp[j] != -1) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }

        return dp[0];
    }
}


// Java Code (Memoization (Top Down DP))
// TC: O(n*n)
// SC: O(n)
class Solution {
    int n;

    private int rec(int[] nums, int target, int ind, int[] dp) {
        if (ind == n - 1) {
            return 0;
        }

        if (dp[ind] != -2) {
            return dp[ind];
        }

        int ans = -1;

        for (int i = ind + 1; i < n; i++) {
            if (Math.abs(nums[ind] - nums[i]) <= target) {
                int next = rec(nums, target, i, dp);

                if (next != -1) {
                    ans = Math.max(ans, 1 + next);
                }
            }
        }

        return dp[ind] = ans;
    }

    public int maximumJumps(int[] nums, int target) {
        n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -2);   // -2 = not computed
        return rec(nums, target, 0, dp);
    }
}


// Java code (Recursion)
// TC: O(2^n) in worst case
// SC: O(n) due to recursion stack
class Solution {
    int n;

    private int rec(int[] nums, int target, int ind) {
        if (ind == n - 1) {
            return 0;
        }

        int ans = -1;

        for (int i = ind + 1; i < n; i++) {
            if (Math.abs(nums[ind] - nums[i]) <= target) {
                int next = rec(nums, target, i);

                if (next != -1) {
                    ans = Math.max(ans, 1 + next);
                }
            }
        }

        return ans;
    }

    public int maximumJumps(int[] nums, int target) {
        n = nums.length;
        return rec(nums, target, 0);
    }
}