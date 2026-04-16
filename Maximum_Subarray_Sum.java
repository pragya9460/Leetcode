/**
 * Scroll below to see Java code as well
 * 
 * My youtube video of this question: https://youtu.be/1f7AKxWcpWM
 * Leetcode Link: https://leetcode.com/problems/maximum-subarray
 */

// C++ code
// Approach: Keep having sum & track of largest sum at each step and for negative sum, reset it to 0.
// TC: O(n) 
// SC: O(1)
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int largestSum = INT_MIN, curSum = 0;

        for(auto it: nums) {
            curSum += it;
            largestSum = max(largestSum, curSum);
            if(curSum < 0) {
                curSum = 0;
            }
        }

        return largestSum;
    }
};

// Java code
// TC: O(n)
// SC: O(1)
class Solution {
    public int maxSubArray(int[] nums) {
        int largestSum = Integer.MIN_VALUE, curSum = 0;

        for(int it: nums) {
            curSum += it;
            largestSum = Math.max(largestSum, curSum);
            if(curSum < 0) {
                curSum = 0;
            }
        }

        return largestSum;
    }
}
