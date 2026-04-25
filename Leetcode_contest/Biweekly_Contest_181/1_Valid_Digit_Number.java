/**
 * Scroll below to see Java code as well
 * 
 * My youtube video of this question: https://youtu.be/TLbE2S8_ZnY
 * Leetcode Link: https://leetcode.com/problems/valid-digit-number/
 */

// C++ code 
// Approach: Basic math
// TC: O(digits)
// SC: O(1)
class Solution {
public:
    bool validDigit(int n, int x) {
        bool found = false;
        while(n>=10) {
            if(n%10==x) {
                found = true;
            }
            n = n/10;
        }

        if(n%10!=x && found) {
            return true;
        }

        return false;
    }
};

// Java Code
// TC: O(digits)
// SC: O(1)
class Solution {
    public boolean validDigit(int n, int x) {
        boolean found = false;
        while(n>=10) {
            if(n%10==x) {
                found = true;
            }
            n = n/10;
        }

        if(n%10!=x && found) {
            return true;
        }

        return false;
    }
}
