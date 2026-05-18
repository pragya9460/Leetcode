/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/MeD7TAmL5Dg
 * Leetcode Link: https://leetcode.com/problems/jump-game-iv/
 */

// C++ code (BFS)
// Approach: Treat each index as a node in an unweighted graph connected to i-1, i+1, and all indices having the same value. Use BFS to find the shortest path, and clear the list of same-value indices after processing once to avoid repeatedly traversing the same group and achieve O(n) time complexity.
// TC: O(n)
// SC: O(n)
class Solution {
public:
    int minJumps(vector<int>& arr) {
        int n = arr.size();

        unordered_map<int, vector<int>> mp;
        for(int i=0; i<n; i++) {
            mp[arr[i]].push_back(i);
        }

        queue<pair<int, int>> q;
        q.push({0, 0});
        vector<int> vis(n, 0);
        vis[0] = 1;

        while(!q.empty()) {
            int node = q.front().first;
            int step = q.front().second;
            q.pop();

            if(node==(n-1)) {
                return step;
            }

            int n1 = node+1;
            if(n1 < n && !vis[n1]) {
                vis[n1] = 1;
                q.push({n1, step+1});
            }

            int n2 = node-1;
            if(n2 >=0 && !vis[n2]) {
                vis[n2] = 1;
                q.push({n2, step+1});
            }

            for(auto &it: mp[arr[node]]) {
                if(!vis[it]) {
                    vis[it] = 1;
                    q.push({it, step+1});
                }
            }
            mp[arr[node]].clear();
        }

        return n-1;
    }
};


// *************************************************************************** //


// Java Code (BFS)
// TC: O(n)
// SC: O(n)
class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0}); // {index, steps}

        boolean[] visited = new boolean[n];
        visited[0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int steps = curr[1];

            if (node == n - 1) {
                return steps;
            }

            int n1 = node + 1;
            if (n1 < n && !visited[n1]) {
                visited[n1] = true;
                queue.offer(new int[]{n1, steps + 1});
            }

            int n2 = node - 1;
            if (n2 >= 0 && !visited[n2]) {
                visited[n2] = true;
                queue.offer(new int[]{n2, steps + 1});
            }

            for (int next : map.get(arr[node])) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, steps + 1});
                }
            }

            map.get(arr[node]).clear();
        }

        return n - 1;
    }
}