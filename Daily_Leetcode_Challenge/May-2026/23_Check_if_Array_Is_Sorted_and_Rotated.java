/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/Ed2PGM-R15c
 * Leetcode Link: https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
 */

// C++ code (Optimal)
// Approach: Count the number of positions where the sorted order breaks (nums[i-1] > nums[i]). A sorted-and-rotated array can have at most one such violation, including the circular check between the last and first element.
// TC: O(n)
// SC: O(1)
class Solution {
public:
    bool check(vector<int>& nums) {
        int voilation = 0, n = nums.size();
        if(n <= 1) {
            return true;
        }

        for(int i = 1; i < n; i++) {
            if(nums[i-1] > nums[i]) {
                voilation++;
                if(voilation > 1) {
                    return false;
                }
            }
        }

        if(nums[0]<nums[n-1]) {
            voilation++;
        }

        return voilation<=1;
    }
};

// C++ code (Brute)
// Approach: Try every possible rotation as the starting point and check whether the next n-1 elements are in non-decreasing order. If any rotation is sorted, return true.
// TC: O(n^2)
// SC: O(1)
class Solution {
public:
    bool check(vector<int>& nums) {
        int n = nums.size();

        for(int start = 0; start < n; start++) {
            bool sorted = true;

            for(int k = 0; k < n - 1; k++) {
                int curr = (start + k) % n;
                int next = (start + k + 1) % n;

                if(nums[curr] > nums[next]) {
                    sorted = false;
                    break;
                }
            }

            if(sorted) return true;
        }

        return false;
    }
};

// *************************************************************************** //


// Java Code (Optimal)
// TC: O(n)
// SC: O(1)
class Solution {
    public boolean check(int[] nums) {
        int violations = 0;
        int n = nums.length;

        if (n <= 1) {
            return true;
        }

        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > nums[i]) {
                violations++;
                if (violations > 1) {
                    return false;
                }
            }
        }

        if (nums[0] < nums[n - 1]) {
            violations++;
        }

        return violations <= 1;
    }
}

// Java Code (Brute)    
// TC: O(n^2)
// SC: O(1)
class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;

        for (int start = 0; start < n; start++) {
            boolean sorted = true;

            for (int k = 0; k < n - 1; k++) {
                int curr = (start + k) % n;
                int next = (start + k + 1) % n;

                if (nums[curr] > nums[next]) {
                    sorted = false;
                    break;
                }
            }

            if (sorted) {
                return true;
            }
        }

        return false;
    }
}