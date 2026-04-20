/**
 * Scroll below to see Java code as well
 * 
 * My youtube video of this question: https://youtu.be/kKswOr7jWpc
 * Leetcode Link: https://leetcode.com/problems/two-furthest-houses-with-different-colors/
 */

// C++ code
// Approach: Thinking greedily, maximize distance by pairing each boundary element (first and last) with the farthest index having a different color, since the optimal pair must include at least one endpoint.
// TC: O(n)
// SC: O(1)
class Solution {
public:
    int maxDistance(vector<int>& colors) {
        int n = colors.size();
        int mx = 0;

        for(int i=n-1; i>=0; i--) {
            if(colors[i]!=colors[0]) {
                mx = max(mx, i);
            }
        }

        for(int i=0; i<n; i++) {
            if(colors[i]!=colors[n-1]) {
                mx = max(mx, n-1-i);
            }
        }
        

        return mx;
    }
};


// Java Code
// TC: O(n)
// SC: O(1)
class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int mx = 0;

        for(int i=n-1; i>=0; i--) {
            if(colors[i]!=colors[0]) {
                mx = Math.max(mx, i);
            }
        }

        for(int i=0; i<n; i++) {
            if(colors[i]!=colors[n-1]) {
                mx = Math.max(mx, n-1-i);
            }
        }
        

        return mx;
    }
}

