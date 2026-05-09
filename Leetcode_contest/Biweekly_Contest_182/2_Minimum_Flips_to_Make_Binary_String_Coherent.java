/**
 * Scroll below to see Java code as well
 * 
 * My youtube video of this question: https://youtu.be/0-btRk4xOyc
 * Leetcode Link: https://leetcode.com/problems/minimum-flips-to-make-binary-string-coherent/
 */

// C++ code 
/*
Approach: 
    Count the total number of '1's in the string. Then evaluate the minimum flips required for all valid target forms:

    1. All 0s
    2. All 1s
    3. Exactly one 1
    4. A string where only the first and last characters are 1 and all middle characters are 0 (100...001).

    Return the minimum cost among all these cases.
 */
// TC: O(n)
// SC: O(1)
class Solution {
public:
    int minFlips(string s) {
        int n = s.size();
        int ones = 0;

        for(char c : s){
            if(c == '1') ones++;
        }

        int ans = n;

        // Case 1: all 0s
        ans = min(ans, ones);

        // Case 2: all 1s
        ans = min(ans, n - ones);

        // Case 3: exactly one '1'
        ans = min(ans, abs(ones - 1));

        // Case 4: form = 1.....1
        if(n > 1){

            int cost = 0;

            if(s[0] == '0') cost++;
            if(s[n - 1] == '0') cost++;

            for(int i = 1; i < n - 1; i++){
                if(s[i] == '1') cost++;
            }

            ans = min(ans, cost);
        }

        return ans;
    }
};

// Java Code
// TC: O(n)
// SC: O(1)
class Solution {
    public int minFlips(String s) {
        int n = s.length();
        int ones = 0;

        // Count total number of '1's
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        int ans = n;

        // Case 1: All 0s
        ans = Math.min(ans, ones);

        // Case 2: All 1s
        ans = Math.min(ans, n - ones);

        // Case 3: Exactly one '1'
        ans = Math.min(ans, Math.abs(ones - 1));

        // Case 4: Form = 100...001
        if (n > 1) {
            int cost = 0;

            if (s.charAt(0) == '0') cost++;
            if (s.charAt(n - 1) == '0') cost++;

            for (int i = 1; i < n - 1; i++) {
                if (s.charAt(i) == '1') cost++;
            }

            ans = Math.min(ans, cost);
        }

        return ans;
    }
}