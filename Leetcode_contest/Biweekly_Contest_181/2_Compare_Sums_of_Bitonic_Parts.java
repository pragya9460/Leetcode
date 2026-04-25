/**
 * Scroll below to see Java code as well
 * 
 * My youtube video of this question: https://youtu.be/yCQmpC6h6Gk
 * Leetcode Link: https://leetcode.com/problems/compare-sums-of-bitonic-parts/
 */

// C++ code 
// Approach: Simple Iteration over array
// TC: O(n)
// SC: O(1)
class Solution {
public:
    int compareBitonicSums(vector<int>& nums) {
        int n = nums.size();

        int i = 1;

        long long inc = nums[0];

        while(nums[i-1]<nums[i]) {
            inc+=nums[i];
            i++;
        }

        long long dsc = nums[i-1];

        while(i<n) {
            dsc += nums[i];
            i++;
        }

        if(inc>dsc) {
            return 0;
        } else if(dsc>inc) {
            return 1;
        }

        return -1;
    }
};


// Java Code
// TC: O(n)
// SC: O(1)
class Solution {
    public int compareBitonicSums(int[] nums) {
        int n = nums.length;
        int i = 1;

        long inc = nums[0];

        while (nums[i - 1] < nums[i]) {
            inc += nums[i];
            i++;
        }

        long dsc = nums[i - 1]; // peak

        while (i < n) {
            dsc += nums[i];
            i++;
        }

        if (inc > dsc) return 0;
        if (dsc > inc) return 1;
        return -1;
    }
}