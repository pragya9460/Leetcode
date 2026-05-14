/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/a2aIBAixqi4
 * Leetcode Link: https://leetcode.com/problems/check-if-array-is-good/
 */

// C++ code (Optimal -> Using frequency array)
// Approach: Use a frequency array of size n (n = nums.length). Every number must be in the range [1, n-1], numbers 1 to n-2 should appear exactly once, and n-1 should appear exactly twice.
// TC: O(n)
// SC: O(n)
class Solution {
public:
    bool isGood(vector<int>& nums) {
        int n = nums.size();
        
        vector<int> count(n, 0);

        for(int num: nums) {
            if(num >= n) {
                return false;
            }

            count[num]++;

            if(count[num]>1) {
                if((num==(n-1) && count[num]>2) or (num!=(n-1))) {
                    return false;
                } 
            }
        }

        return true;
    }
};


// C++ code (Brute -> Sorting + checking)
// Approach: Sort the array. For an array of length n + 1, a good array must become [1, 2, ..., n, n] after sorting, so verify the first n elements are 1 to n and the last element is also n.
// TC: O(n log n)
// SC: O(1)
class Solution {
public:
    bool isGood(vector<int>& nums) {
        int n = nums.size()-1;
        int sum = 0;

        sort(nums.begin(), nums.end());

        for(int i=0; i<n; i++) {
            if(nums[i]!=(i+1)) {
                return false;
            }
        }

        return nums[n] == n;
    }
};


// *************************************************************************** //


// Java Code (Optimal -> Using frequency array)
// TC: O(n)
// SC: O(n)
class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];

        for (int num : nums) {
            if (num >= n || num < 1) {
                return false;
            }

            count[num]++;

            if (count[num] > 1) {
                if ((num == n - 1 && count[num] > 2) || (num != n - 1)) {
                    return false;
                }
            }
        }

        return true;
    }
}


// Java code (Brute -> Sorting + checking)
// TC: O(n log n)
// SC: O(1)
class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length - 1;

        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return false;
            }
        }

        return nums[n] == n;
    }
}