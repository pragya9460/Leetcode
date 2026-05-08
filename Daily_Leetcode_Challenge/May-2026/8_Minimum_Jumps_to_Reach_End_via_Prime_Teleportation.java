/**
 * Scroll below to see Java code.
 * 
 * My youtube video of this question: https://youtu.be/di-aQh4ApMg
 * Leetcode Link: https://leetcode.com/problems/minimum-jumps-to-reach-end-via-prime-teleportation/
 */

// C++ code (BFS)
// Approach: We model the array as a graph where every index is a node. From any index, we can either move to adjacent indices or teleport using prime numbers. To optimize teleportation, we precompute prime factors using Sieve and build: edges[p] -> all indices whose numbers are divisible by p. Then we use BFS since every move costs exactly 1 jump.
/*
TC: O(MX log log MX + n log MX)
    O(MX log log MX) for sieve preprocessing to compute distinct prime factors.
    O(n log MX) for building teleport groups and BFS traversal, since each index and prime-factor group is processed at most once.

SC: O(MX log log MX)
    Used mainly for storing prime factors for all numbers up to MX, along with BFS/graph structures.
*/
const int mx = 1000001;
vector<int> primeFactors[mx];
bool initialized = []() {
    for(int i=2; i<mx; i++) {
        if(primeFactors[i].empty()) {
            for(int j=i; j<mx; j+=i) {
                primeFactors[j].push_back(i);
            }
        }
    }
    return true;
}();
class Solution {
public:
    int minJumps(vector<int>& nums) {
        int n = nums.size();
        unordered_map<int, vector<int>> edges;

        for(int i=0; i<n; i++) {
            for(auto &factor: primeFactors[nums[i]]) {
                edges[factor].push_back(i);
            }
        }

        int res = 0;
        vector<int> vis(n, 0);
        vis[0] = 1;
        queue<int> q;
        q.push(0);

        while(!q.empty()) {
            int sz = q.size();

            for(int i=0; i<sz; i++) {
                int ind = q.front();
                q.pop();

                if(ind==n-1) {
                    return res;
                }

                if(ind>0 && !vis[ind-1]) {
                    vis[ind-1] = true;
                    q.push(ind-1);
                }

                if(ind<(n-1) && !vis[ind+1]) {
                    vis[ind+1] = true;
                    q.push(ind+1);
                }

                if(primeFactors[nums[ind]].size()==1) {
                    int prime = nums[ind];

                    if(edges.count(prime)) {
                        for(auto &j: edges[prime]) {
                            if(!vis[j]) {
                                vis[j] = 1;
                                q.push(j);
                            }
                        }
                        edges[prime].clear();
                    }
                }
            }
            res++;
        }

        return res;
    }
};

// *************************************************************************** //


// Java Code (BFS)
/*
TC: O(MX log log MX + n log MX)
    O(MX log log MX) for sieve preprocessing to compute distinct prime factors.
    O(n log MX) for building teleport groups and BFS traversal, since each index and prime-factor group is processed at most once.

SC: O(MX log log MX)
    Used mainly for storing prime factors for all numbers up to MX, along with BFS/graph structures.
*/
class Solution {

    static final int MX = 1000001;
    static List<Integer>[] primeFactors = new ArrayList[MX];

    static {
        for(int i = 0; i < MX; i++) {
            primeFactors[i] = new ArrayList<>();
        }

        for(int i = 2; i < MX; i++) {

            if(primeFactors[i].isEmpty()) {

                for(int j = i; j < MX; j += i) {
                    primeFactors[j].add(i);
                }
            }
        }
    }

    public int minJumps(int[] nums) {

        int n = nums.length;

        Map<Integer, List<Integer>> edges = new HashMap<>();

        for(int i = 0; i < n; i++) {

            for(int factor : primeFactors[nums[i]]) {

                edges.computeIfAbsent(factor, k -> new ArrayList<>()).add(i);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n];

        q.offer(0);
        vis[0] = true;

        int res = 0;

        while(!q.isEmpty()) {

            int sz = q.size();

            while(sz-- > 0) {

                int ind = q.poll();

                if(ind == n - 1) {
                    return res;
                }

                // left move
                if(ind > 0 && !vis[ind - 1]) {
                    vis[ind - 1] = true;
                    q.offer(ind - 1);
                }

                // right move
                if(ind < n - 1 && !vis[ind + 1]) {
                    vis[ind + 1] = true;
                    q.offer(ind + 1);
                }

                // prime teleportation
                if(primeFactors[nums[ind]].size() == 1 &&
                   primeFactors[nums[ind]].get(0) == nums[ind]) {

                    int prime = nums[ind];

                    if(edges.containsKey(prime)) {

                        for(int next : edges.get(prime)) {

                            if(!vis[next]) {
                                vis[next] = true;
                                q.offer(next);
                            }
                        }

                        edges.get(prime).clear();
                    }
                }
            }

            res++;
        }

        return res;
    }
}

