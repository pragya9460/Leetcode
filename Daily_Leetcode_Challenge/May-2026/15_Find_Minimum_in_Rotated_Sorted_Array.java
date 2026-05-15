/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/wX6nsG4Ldj8
 * Leetcode Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */

// C++ code (Optimal -> Using Binary Search)
// Approach: Use binary search to locate the rotation point, which is the position of the minimum element. By comparing nums[mid] with nums[high], we determine whether mid lies in the left or right sorted portion and discard half of the search space in each step.
// TC: O(log(n))
// SC: O(1)
class Solution {
    public:
        int findMin(vector<int>& nums) {
            int n = nums.size();
            int low = 0, high = n - 1;

            while (low < high) {
                if (nums[low] < nums[high]) {
                    return nums[low];
                }
                int mid = low + (high - low) / 2;

                if (nums[mid] > nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            return nums[low];
        }
};


// C++ code (Brute -> Linear Search)
// Approach: Traverse the entire array and keep track of the smallest element seen so far. Since the minimum element could be at any position after rotation, checking all elements guarantees the correct answer.
// TC: O(n)
// SC: O(1)
class Solution {
    public:
        int findMin(vector<int>& nums) {
            int n = nums.size();
            int mn = INT_MAX;
            for (int i = 0; i < n; i++) {
                mn = min(mn, nums[i]);
            }   
            return mn;
        }   
};


// *************************************************************************** //


// Java Code (Optimal -> Using Binary Search)
// TC: O(log(n))
// SC: O(1)
class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int low = 0, high = n - 1;

        while (low < high) {
            if (nums[low] < nums[high]) {
                return nums[low];
            }
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return nums[low];
    }
}


// Java code (Brute -> Linear Search)
// TC: O(n)
// SC: O(1)
class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int mn = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            mn = Math.min(mn, nums[i]);
        }   
        return mn;
    }   
}

