/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/bBeXMkcj160
 * Leetcode Link: https://leetcode.com/problems/jump-game-vii/
 */

// C++ code (BFS)
// Approach: Perform BFS from index 0, but maintain the farthest position already scanned. Since jump ranges of different nodes overlap heavily, only process the newly uncovered portion of each range, ensuring every index is scanned at most once.
// TC: O(n)
// SC: O(n)
class Solution {
public:
    bool canReach(string s, int minJump, int maxJump) {
        int n = s.size();

        if (s[n - 1] == '1')
            return false;

        queue<int> q;
        q.push(0);

        vector<bool> vis(n, false);
        vis[0] = true;

        int farthestProcessed = 0;

        while (!q.empty()) {
            int curr = q.front();
            q.pop();

            if (curr == n - 1)
                return true;

            int start = max(curr + minJump, farthestProcessed + 1);
            int end = min(curr + maxJump, n - 1);

            for (int next = start; next <= end; next++) {
                if (s[next] == '0' && !vis[next]) {
                    vis[next] = true;
                    q.push(next);
                }
            }

            farthestProcessed = max(farthestProcessed, end);
        }

        return false;
    }
};

// C++ code (DP + Prefix Sum)
// Approach: Let dp[i] denote whether index i is reachable from index 0. To compute dp[i], check whether any reachable index exists in the predecessor range [i-maxJump, i-minJump]; a prefix-sum array stores the count of reachable indices and allows this range query in O(1) time.
// TC: O(n)
// SC: O(n)
class Solution {
public:
    int n;
    bool canReach(string s, int minJump, int maxJump) {
        n = s.size();
        if(s[n-1]=='1') {
            return false;
        }
        vector<int> dp(n, 0), pre(n, 0);

        dp[0] = 1;
        pre[0] = 1;

        for(int i=1; i<n; i++) {
            if(s[i]=='0') {
                int left = max(0, i-maxJump);
                int right = i-minJump;

                if(right>=0) {
                    int reachable = pre[right] - (left>0?pre[left-1]:0);
                    
                    dp[i] = (reachable>0);
                }
            }

            pre[i] = pre[i-1] + dp[i];
        }

        return dp[n-1];
    }
};


// *************************************************************************** //


// Java Code (BFS)
// TC: O(n)
// SC: O(n)
class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();

        if (s.charAt(n - 1) == '1') {
            return false;
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);

        boolean[] vis = new boolean[n];
        vis[0] = true;

        int farthestProcessed = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (curr == n - 1) {
                return true;
            }

            int start = Math.max(curr + minJump, farthestProcessed + 1);
            int end = Math.min(curr + maxJump, n - 1);

            for (int next = start; next <= end; next++) {
                if (s.charAt(next) == '0' && !vis[next]) {
                    vis[next] = true;
                    q.offer(next);
                }
            }

            farthestProcessed = Math.max(farthestProcessed, end);
        }

        return false;
    }
}

// Java code (DP + Prefix Sum)
// TC: O(n)
// SC: O(n)
class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();

        if (s.charAt(n - 1) == '1') {
            return false;
        }

        int[] dp = new int[n];
        int[] pre = new int[n];

        dp[0] = 1;
        pre[0] = 1;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '0') {
                int left = Math.max(0, i - maxJump);
                int right = i - minJump;

                if (right >= 0) {
                    int reachable = pre[right]
                            - (left > 0 ? pre[left - 1] : 0);

                    dp[i] = (reachable > 0) ? 1 : 0;
                }
            }

            pre[i] = pre[i - 1] + dp[i];
        }

        return dp[n - 1] == 1;
    }
}