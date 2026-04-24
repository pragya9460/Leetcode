
/**
 * Scroll below to see Java code as well
 * 
 * My youtube video of this question: https://youtu.be/cfAaLObExSg
 * Leetcode Link: https://leetcode.com/problems/furthest-point-from-origin/
 */

// C++ code
// Approach: Count L, R, and '_' → maximize distance by assigning all '_' to the dominant side → result = abs(L - R) + underscores
// TC: O(n)
// SC: O(1)
class Solution {
public:
    int furthestDistanceFromOrigin(string moves) {
        int lCnt=0, rCnt=0, uCnt=0;
        int n = moves.size();

        for(int i=0; i<n; i++) {
            if(moves[i]=='L') {
                lCnt++;
            } else if(moves[i]=='R') {
                rCnt++;
            } else {
                uCnt++;
            }
        }

        return abs(lCnt-rCnt) + uCnt;
    }
};


// Java Code
// TC: O(n)
// SC: O(1)
class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int lCnt=0, rCnt=0, uCnt=0;
        int n = moves.length();

        for(int i=0; i<n; i++) {
            if(moves.charAt(i)=='L') {
                lCnt++;
            } else if(moves.charAt(i)=='R') {
                rCnt++;
            } else {
                uCnt++;
            }
        }

        return Math.abs(lCnt-rCnt) + uCnt;
    }
}