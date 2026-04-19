/**
 * Scroll below to see Java code as well
 * 
 * My youtube video of this question: https://youtu.be/w-QSPr6vBb4
 * Leetcode Link: https://leetcode.com/problems/maximum-distance-between-a-pair-of-values/
 */

// C++ code
// Approach: 
// TC: O(m+n)
// SC: O(1)
class Solution {
public:
    int maxDistance(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size(), m = nums2.size();
        int ans = 0;
        int i = 0, j = 0;

        while(i<n && j<m) {
            if(nums1[i]<=nums2[j]) {
                ans = max(ans, j-i);
                j++;
            } else {
                i++;
            }
        }

        return ans;
    }
};

// Java Code
// TC: O(m+n)
// SC: O(1)
class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int ans = 0;
        int i = 0, j = 0;

        while(i<n && j<m) {
            if(nums1[i]<=nums2[j]) {
                ans = Math.max(ans, j-i);
                j++;
            } else {
                i++;
            }
        }

        return ans;
    }
}
