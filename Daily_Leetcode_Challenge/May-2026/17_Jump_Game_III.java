/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/Miy_JE8FDJo
 * Leetcode Link: https://leetcode.com/problems/jump-game-iii/
 */

// C++ code (BFS)
// Approach: Treat each index as a graph node and use a queue to explore reachable indices level by level. A visited array ensures that each index is processed only once and prevents revisiting cycles.
// TC: O(BFS)
// SC: O(n)
class Solution {
public:
    bool canReach(vector<int>& arr, int start) {
        int n = arr.size();

        queue<int> q;
        q.push(start);
        vector<int> vis(n, 0);
        vis[start] = 1;

        while(!q.empty()) {
            int ind = q.front();
            q.pop();

            if(arr[ind]==0) {
                return true;
            }
            
            int nextInd1 = ind+arr[ind];
            if(nextInd1<n && !vis[nextInd1]) {
                q.push(nextInd1);
                vis[nextInd1] = 1;
            }
            int nextInd2 = ind-arr[ind];
            if(nextInd2>=0 && !vis[nextInd2]) {
                q.push(nextInd2);
                vis[nextInd2] = 1;
            }
        }

        return false;
    }
};

// C++ code (DFS)
// Approach: DFS + Memoization (DP) with Visited Intuition: Recursively explore both possible jumps from each index and store whether that index can eventually reach a 0. The same dp array acts as both a memoization cache and a visited marker by marking a node before exploring its neighbors to avoid infinite recursion in cyclic paths.
// TC: O(n)
// SC: O(n)
class Solution {
public:
    int n;
    vector<int> dp;
    bool rec(vector<int> &arr, int i) {
        if(i<0 or i>=n) {
            return false;
        }
        if(arr[i]==0) {
            return true;
        }

        if(dp[i]!=-1) {
            return dp[i];
        }

        dp[i] = 0;

        bool plus = rec(arr, i+arr[i]);
        bool minus = rec(arr, i-arr[i]);

        return dp[i] = plus or minus;
    }
    bool canReach(vector<int>& arr, int start) {
        n = arr.size();
        dp.resize(n, -1);
        return rec(arr, start);
    }
};


// *************************************************************************** //


// Java Code (BFS)
// TC: O(n)
// SC: O(n)
class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;

        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        queue.offer(start);

        boolean[] visited = new boolean[n];
        visited[start] = true;

        while (!queue.isEmpty()) {
            int index = queue.poll();

            if (arr[index] == 0) {
                return true;
            }

            int nextIndex1 = index + arr[index];
            if (nextIndex1 < n && !visited[nextIndex1]) {
                queue.offer(nextIndex1);
                visited[nextIndex1] = true;
            }

            int nextIndex2 = index - arr[index];
            if (nextIndex2 >= 0 && !visited[nextIndex2]) {
                queue.offer(nextIndex2);
                visited[nextIndex2] = true;
            }
        }

        return false;
    }
}

// Java code (DFS)
// TC: O(n)
// SC: O(n)
class Solution {
    int n;
    int[] dp;   // -1 = unvisited, 0 = cannot reach 0 (also used as visited), 1 = can reach 0

    private boolean dfs(int[] arr, int i) {
        if (i < 0 || i >= n) {
            return false;
        }

        if (arr[i] == 0) {
            return true;
        }

        if (dp[i] != -1) {
            return dp[i] == 1;
        }

        // Mark as visited before exploring to avoid cycles
        dp[i] = 0;

        boolean forward = dfs(arr, i + arr[i]);
        boolean backward = dfs(arr, i - arr[i]);

        dp[i] = (forward || backward) ? 1 : 0;
        return dp[i] == 1;
    }

    public boolean canReach(int[] arr, int start) {
        n = arr.length;
        dp = new int[n];
        java.util.Arrays.fill(dp, -1);

        return dfs(arr, start);
    }
}