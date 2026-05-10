/**
 * Scroll below to see Java code as well
 * 
 * My youtube video of this question: https://youtu.be/r-bgvH4sXuQ
 * Leetcode Link: https://leetcode.com/problems/minimum-threshold-path-with-limited-heavy-edges/
 */

// C++ code 
// Approach: Binary search on the answer (threshold). For each threshold, run BFS while tracking the maximum remaining k for each node, where traversing an edge with weight greater than the threshold consumes one unit of k
// TC: O(k × (n + e) × log(maxWeight))
// SC: O(n + e + n × k)
class Solution {
public:
    bool isPossible(int src, int target, vector<pair<int, int>> adj[], int k, int threshold, int n) {

        // best[node] = maximum remaining k seen so far at this node
        vector<int> best(n, -1);

        queue<pair<int, int>> q;   // {node, remaining_k}
        q.push({src, k});
        best[src] = k;

        while (!q.empty()) {
            auto [node, rem] = q.front();
            q.pop();

            if (node == target) {
                return true;
            }

            for (auto &[next, wt] : adj[node]) {
                int newRem = rem - (wt > threshold ? 1 : 0);

                // Cannot use more than k expensive edges
                if (newRem < 0) continue;

                // Visit only if we reach 'next' with more remaining k
                if (newRem > best[next]) {
                    best[next] = newRem;
                    q.push({next, newRem});
                }
            }
        }

        return false;
    }

    int minimumThreshold(int n, vector<vector<int>>& edges,
                         int source, int target, int k) {

        vector<pair<int, int>> adj[n];
        int low = 0, high = 0;

        for (int i = 0; i < edges.size(); i++) {
            int u  = edges[i][0];
            int v  = edges[i][1];
            int wt = edges[i][2];

            adj[u].push_back({v, wt});
            adj[v].push_back({u, wt});

            high = max(high, wt);
        }

        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isPossible(source, target, adj, k, mid, n)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
};


/* // *************************************************************************** //*/

// Java Code
// TC: O(k × (n + e) × log(maxWeight))
// SC: O(n + e + n × k)
class Solution {

    private boolean isPossible(int src, int target, List<int[]>[] adj, int k, int threshold, int n) {
        // best[node] = maximum remaining k seen so far at this node
        int[] best = new int[n];
        Arrays.fill(best, -1);

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, k});   // {node, remainingK}
        best[src] = k;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int rem = curr[1];

            if (node == target) {
                return true;
            }

            for (int[] edge : adj[node]) {
                int next = edge[0];
                int wt = edge[1];

                int newRem = rem - (wt > threshold ? 1 : 0);

                // Cannot use more than k expensive edges
                if (newRem < 0) continue;

                // Visit only if we reach 'next' with more remaining k
                if (newRem > best[next]) {
                    best[next] = newRem;
                    queue.offer(new int[]{next, newRem});
                }
            }
        }

        return false;
    }

    public int minimumThreshold(int n, int[][] edges,
                                int source, int target, int k) {

        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        int low = 0, high = 0;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            adj[u].add(new int[]{v, wt});
            adj[v].add(new int[]{u, wt});

            high = Math.max(high, wt);
        }

        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isPossible(source, target, adj, k, mid, n)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
}