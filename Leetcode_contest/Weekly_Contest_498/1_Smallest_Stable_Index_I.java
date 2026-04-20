/**
 * Scroll below to see Java code as well
 * 
 * My youtube video of this question: https://youtu.be/Buo05ZkrPgs
 * Leetcode Link: https://leetcode.com/problems/smallest-stable-index-i/
 */

// C++ code (Brute Force)
// Approach: For each index, track max on the left and recompute min on the right every time to check if their difference ≤ k.
// TC: O(n^2)
// SC: O(1)
class Solution {
public:
    int firstStableIndex(vector<int>& nums, int k) {
        int n = nums.size();

        int mx = INT_MIN;

        for(int i=0; i<n; i++) {
            mx = max(mx, nums[i]);
            
            int mn = INT_MAX;
            for(int j=i; j<n; j++) {
                mn = min(mn, nums[j]);
            }

            if(mx - mn <= k) {
                return i;
            }
        }

        return -1;
    }
};


// C++ code (Optimal)
// Approach: Precompute prefix max and maintain suffix min on the fly to check in O(1) per index whether max(left) − min(right) ≤ k.
// TC: O(n)
// SC: O(n)
class Solution {
public:
    int firstStableIndex(vector<int>& nums, int k) {
        int n = nums.size();

        vector<int> preMin(n);
        int mn = INT_MAX;

        for(int i = n-1; i>=0; i--) {
            mn = min(mn, nums[i]);
            preMin[i] = mn;
        }

        int mx = INT_MIN;

        for(int i=0; i<n; i++) {
            mx = max(mx, nums[i]);
            
            if(mx - preMin[i] <= k) {
                return i;
            }
        }

        return -1;
    }
};


// Java Code (Optimal)
// TC: O(n)
// SC: O(n)
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        int[] preMin = new int[n];
        int mn = Integer.MAX_VALUE;

        for(int i = n-1; i>=0; i--) {
            mn = Math.min(mn, nums[i]);
            preMin[i] = mn;
        }

        int mx = Integer.MIN_VALUE;

        for(int i=0; i<n; i++) {
            mx = Math.max(mx, nums[i]);
            
            if(mx - preMin[i] <= k) {
                return i;
            }
        }

        return -1;
    }
}