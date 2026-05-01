/**
 * Scroll below to see Java code as well.
 * 
 * My youtube video of this question: https://youtu.be/kEi-bDZLZW8
 * Leetcode Link: https://leetcode.com/problems/rotate-function/
 */

// C++ code (Math + DP)
// Approach: Compute initial rotation value F(0) and total sum of array, then use recurrence => F(k)=F(k−1)+totalSum−n*nums[n−k], to get each rotation in O(1), tracking the maximum over all rotations (overall O(n)).
// TC: O(n)
// SC: O(1)
class Solution {
public:
    int maxRotateFunction(vector<int>& nums) {
        int n = nums.size();

        int tSum = 0, prev=0, cur = 0;

        for(int i=0; i<n; i++) {
            prev += nums[i]*i;
            tSum += nums[i];
        }

        int mx = prev;

        for(int k=1; k<n; k++) {
            cur = prev + tSum - n*nums[n-k];
            mx = max(mx, cur);
            prev = cur;
        }

        return mx;
    }
};

//************************************************************************************************** //


// Java Code
// TC: O(n)
// SC: O(1)
class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;

        int totalSum = 0;
        int prev = 0;

        // F(0) and total sum
        for (int i = 0; i < n; i++) {
            totalSum += nums[i];
            prev += nums[i] * i;
        }

        int max = prev;

        // Compute F(k) using recurrence
        for (int k = 1; k < n; k++) {
            int cur = prev + totalSum - n * nums[n - k];
            max = Math.max(max, cur);
            prev = cur;
        }

        return max;
    }
}

