/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/hiHqr1uPMLI
 * Leetcode Link: https://leetcode.com/problems/search-in-rotated-sorted-array/
 */

// C++ code (Binary Search)
// Approach: At every binary search step, one half of the rotated array is always sorted. Determine the sorted half, check if the target lies within its range, and discard the other half.
// TC: O(log(n))
// SC: O(1)
class Solution {
public:
    int search(vector<int>& nums, int target) {
        int n = nums.size();
        int low = 0, high = n-1;

        while(low<=high) {
            int mid = low + (high-low)/2;

            if(nums[mid]==target) {
                return mid;
            }

            if(nums[low]<=nums[mid]) {
                if(target>=nums[low] && target<nums[mid]) {
                    high = mid-1;
                } else {
                    low = mid+1;
                }
            } else {
                if(target>nums[mid] && target<=nums[high]) {
                    low = mid+1;
                } else {
                    high = mid-1;
                }
            }
        }

        return -1;
    }
};


// *************************************************************************** //


// Java Code (Binary Search)
// TC: O(log(n))
// SC: O(1)
class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Left half is sorted
            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // Right half is sorted
            else {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }
}