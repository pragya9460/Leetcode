/**
 * Scroll below to see Java code as well
 * 
 * My youtube video of this question: https://youtu.be/5KkG49Jkd40
 * Leetcode Link: https://leetcode.com/problems/mirror-distance-of-an-integer/
 */

// C++ code
// Approach: Reverse the digits of the given number using digit-by-digit extraction. Compute the absolute difference between the original number and its reversed value to get the mirror distance.
// TC: O(log(n))
// SC: O(1)
class Solution {
public:
    int reverseNum(int n) {
        int rev = 0;

        while(n>0) {
            rev = rev*10 + n%10;
            n = n/10;
        }

        return rev;
    }
    int mirrorDistance(int n) {
        int revNum = reverseNum(n);
        return abs(n-revNum);
    }
};

// Java Code
// TC: O(log(n))
// SC: O(1)
class Solution {
    int reverseNum(int n) {
        int rev = 0;

        while(n>0) {
            rev = rev*10 + n%10;
            n = n/10;
        }

        return rev;
    }
    public int mirrorDistance(int n) {
        int revNum = reverseNum(n);
        return Math.abs(n-revNum);
    }
}