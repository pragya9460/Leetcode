/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/KG7X-6f9PXo
 * Leetcode Link: https://leetcode.com/problems/minimum-element-after-replacement-with-digit-sum/
 */

// C++ code (Digit Extraction)
// Approach: Repeatedly extract the last digit using % 10 and remove it using / 10 to calculate digit sum.
// TC: O(n*d) ~ O(n)
// SC: O(1)
class Solution {
public:
    int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    int minElement(vector<int>& nums) {
        int ans = INT_MAX;

        for (int num : nums) {
            ans = min(ans, digitSum(num));
        }

        return ans;
    }
};


// C++ code (Constant time digit sum, arthmetic operations)
// Approach: Since numbers contain at most 5 digits, directly extract every digit using fixed arithmetic operations without a loop.
// TC: O(n)
// SC: O(1)
class Solution {
public:
    int digitSum(int num) {
        return
            (num / 10000) +
            (num % 10000) / 1000 +
            (num % 1000) / 100 +
            (num % 100) / 10 +
            (num % 10);
    }

    int minElement(vector<int>& nums) {
        int ans = INT_MAX;

        for (int num : nums) {
            ans = min(ans, digitSum(num));
        }

        return ans;
    }
};



// ************************************************************************** //

// Java code (Digit Extraction)
// TC: O(n*d) ~ O(n)
// SC: O(1)
class Solution {

    private int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    public int minElement(int[] nums) {
        int ans = Integer.MAX_VALUE;

        for (int num : nums) {
            ans = Math.min(ans, digitSum(num));
        }

        return ans;
    }
}


// Java code (Constant time digit sum, arthmetic operations)
// TC: O()
// SC: O(1)
class Solution {

    private int digitSum(int num) {
        return
            (num / 10000) +
            (num % 10000) / 1000 +
            (num % 1000) / 100 +
            (num % 100) / 10 +
            (num % 10);
    }

    public int minElement(int[] nums) {
        int ans = Integer.MAX_VALUE;

        for (int num : nums) {
            ans = Math.min(ans, digitSum(num));
        }

        return ans;
    }
}