/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/CnukxKjW9zY
 * Leetcode Link: https://leetcode.com/problems/count-indices-with-opposite-parity/
 */

// C++ code (Most optimal)
// Approach: Traverse from right, maintain running count of even & odd.
// TC: O(n)
// SC: O(1)
class Solution {
public:
    vector<int> countOppositeParity(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n, 0);

        int evenCnt = 0, oddCnt = 0;
        for(int i=n-1; i>=0; i--) {
            if(nums[i]%2==0) {
                ans[i] = oddCnt;
                evenCnt++;
            } else {
                ans[i] = evenCnt;
                oddCnt++;
            }
        }

        return ans;
    }
};

// C++ code (Better using prefix sum)
// Approach: Precompute count of even numbers to the right, then derive opposite parity count.
// TC: O(n)
// SC: O(n)
class Solution {
public:
    vector<int> countOppositeParity(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n, 0);

        vector<int> evenCnt(n, 0);
        int cnt = 0;
        for(int i=n-1; i>=0; i--) {
            if(nums[i]%2==0) {
                cnt++;
            }
            evenCnt[i] = cnt;
        }

        for(int i=n-2; i>=0; i--) {
            if(nums[i]%2==0) {
                ans[i] = (n-1-i)-evenCnt[i+1];
            } else {
                ans[i] = evenCnt[i+1];
            }
        }

        return ans;
    }
};

// C++ code (Brute Force)
// Approach: For each index, check all elements to its right and count opposite parity. 
// TC: O(n^2)
// SC: O(1)
class Solution {
public:
    vector<int> countOppositeParity(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n, 0);

        for(int i=0; i<n; i++) {
            int cnt = 0;
            for(int j=i+1; j<n; j++) {
                if(nums[i]%2 != nums[j]%2) {
                    cnt++;
                }
            }

            ans[i] = cnt;
        }

        return ans;
    }
};


// ******************************************************************************* //

// Java code (Most optimal)
// TC: O(n)
// SC: O(1)
class Solution {
    public int[] countOppositeParity(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        int evenCnt = 0, oddCnt = 0;

        for(int i = n - 1; i >= 0; i--) {
            if(nums[i] % 2 == 0) {
                ans[i] = oddCnt;
                evenCnt++;
            } else {
                ans[i] = evenCnt;
                oddCnt++;
            }
        }

        return ans;
    }
}


// Java code (Better using prefix sum)
// TC: O(n)
// SC: O(n)
class Solution {
    public int[] countOppositeParity(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        int[] evenCnt = new int[n];
        int cnt = 0;

        for(int i = n - 1; i >= 0; i--) {
            if(nums[i] % 2 == 0) {
                cnt++;
            }
            evenCnt[i] = cnt;
        }

        for(int i = n - 2; i >= 0; i--) {
            if(nums[i] % 2 == 0) {
                ans[i] = (n - 1 - i) - evenCnt[i + 1];
            } else {
                ans[i] = evenCnt[i + 1];
            }
        }

        return ans;
    }
}


// Java code (Brute Force)
// TC: O(n^2)
// SC: O(1)
class Solution {
    public int[] countOppositeParity(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        for(int i = 0; i < n; i++) {
            int cnt = 0;
            for(int j = i + 1; j < n; j++) {
                if(nums[i] % 2 != nums[j] % 2) {
                    cnt++;
                }
            }
            ans[i] = cnt;
        }

        return ans;
    }
}

