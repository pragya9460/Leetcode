/**
 * Scroll below to see Java code as well.
 * 
 * My youtube video of this question: https://youtu.be/3I0qoKoIXqk
 * Leetcode Link: https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/
 */


// C++ code (Union Find)
// Approach: Group indices using Union-Find (path compression + rank) → count source frequencies per component → greedily match target values → count remaining mismatches.
// TC: O((n+m)*α(n)) => Since α(n) ≤ 4 => O(n+m)
// SC: O(n)
class UnionFind {
public:
    vector<int> par;
    vector<int> rank;
    UnionFind(int n) {
        par.resize(n+1);
        rank.resize(n+1, 0);
        for(int i=0; i<=n; i++) {
            par[i] = i;
        }
    }

    int findPar(int node) {
        if(node==par[node]) {
            return node;
        }

        return par[node] = findPar(par[node]);
    }

    void unionByRank(int u, int v) {
        u = findPar(u);
        v = findPar(v);

        if(u==v) {
            return;
        }
        if(rank[u]>rank[v]) {
            par[v] = u;
        } else if(rank[v]>rank[u]) {
            par[u] = v;
        } else {
            par[v] = u;
            rank[u]++;
        }
    }
};

class Solution {
public:
    int minimumHammingDistance(vector<int>& src, vector<int>& tar, vector<vector<int>>& swaps) {
        int n = src.size(), m = swaps.size();

        UnionFind* unionFind = new UnionFind(n);

        for(int i=0; i<m; i++) {
            unionFind->unionByRank(swaps[i][0], swaps[i][1]);
        }

        unordered_map<int, unordered_map<int, int>> sets;

        for(int i=0; i<n; i++) {
            int par = unionFind->findPar(i);

            sets[par][src[i]]++;
        }

        int ans = 0;
        for(int i=0; i<n; i++) {
            int par = unionFind->findPar(i);

            if(sets[par][tar[i]] > 0) {
                sets[par][tar[i]]--;
            } else {
                ans++;
            }
        }

        return ans;
    }
};

// C++ code (BFS)
// Approach: Build graph from swaps → use BFS to get connected components → for each component match source and target via frequency map → count unmatched elements.
// TC: O(n+m)
// SC: O(n)
class Solution {
public:
    int minimumHammingDistance(vector<int>& source, vector<int>& target,
                               vector<vector<int>>& allowedSwaps) {
        int n = source.size();

        // 1. Build graph
        vector<vector<int>> adj(n);
        for (auto &e : allowedSwaps) {
            adj[e[0]].push_back(e[1]);
            adj[e[1]].push_back(e[0]);
        }

        vector<bool> visited(n, false);
        int ans = 0;

        // 2. Traverse components using BFS
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            queue<int> q;
            q.push(i);
            visited[i] = true;

            vector<int> indices;

            // BFS to get one component
            while (!q.empty()) {
                int node = q.front(); q.pop();
                indices.push_back(node);

                for (int nei : adj[node]) {
                    if (!visited[nei]) {
                        visited[nei] = true;
                        q.push(nei);
                    }
                }
            }

            // 3. Count frequencies for this component
            unordered_map<int, int> freq;
            for (int idx : indices) {
                freq[source[idx]]++;
            }

            // 4. Match with target
            for (int idx : indices) {
                if (freq[target[idx]] > 0) {
                    freq[target[idx]]--;
                } else {
                    ans++;
                }
            }
        }

        return ans;
    }
};

// ******************************************************************************************************** //

// Java Code
// TC: O((n+m)*α(n)) => Since α(n) ≤ 4 => O(n+m)
// SC: O(n)
class UnionFind {
    int[] par;
    int[] rank;

    UnionFind(int n) {
        par = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
        }
    }

    int findPar(int node) {
        if (node == par[node]) {
            return node;
        }
        return par[node] = findPar(par[node]); // path compression
    }

    void unionByRank(int u, int v) {
        u = findPar(u);
        v = findPar(v);

        if (u == v) return;

        if (rank[u] > rank[v]) {
            par[v] = u;
        } else if (rank[v] > rank[u]) {
            par[u] = v;
        } else {
            par[v] = u;
            rank[u]++;
        }
    }
}

class Solution {
    public int minimumHammingDistance(int[] src, int[] tar, int[][] swaps) {
        int n = src.length;

        UnionFind uf = new UnionFind(n);

        for (int[] s : swaps) {
            uf.unionByRank(s[0], s[1]);
        }

        Map<Integer, Map<Integer, Integer>> sets = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int par = uf.findPar(i);

            sets.putIfAbsent(par, new HashMap<>());
            Map<Integer, Integer> freq = sets.get(par);

            freq.put(src[i], freq.getOrDefault(src[i], 0) + 1);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int par = uf.findPar(i);
            Map<Integer, Integer> freq = sets.get(par);

            if (freq.getOrDefault(tar[i], 0) > 0) {
                freq.put(tar[i], freq.get(tar[i]) - 1);
            } else {
                ans++;
            }
        }

        return ans;
    }
}

