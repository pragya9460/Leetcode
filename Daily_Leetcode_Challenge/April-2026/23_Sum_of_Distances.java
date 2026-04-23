/**
 * Scroll below to see Java code as well, scroll further to see code for brute approach.
 * 
 * My youtube video of this question: https://youtu.be/HFOacXt9Qzo
 * Leetcode Link: https://leetcode.com/problems/sum-of-distances/
 */

// C++ code (Optimal using Math & Map)
// Approach: For each group, use prefix sum to compute contribution of left + right indices in O(1) per index.
// TC: O(n)
// SC: O(n)
class Solution {
public:
    vector<long long> distance(vector<int>& nums) {
        int n = nums.size();
        vector<long long> ans(n, 0);

        unordered_map<int, vector<int>> groups;

        for(int i=0; i<n; i++) {
            groups[nums[i]].push_back(i);
        }

        for(auto &it: groups) {
            vector<int> group = it.second;
            int m = group.size();

            long long sum = 0;
            for(int i=0; i<m; i++) {
                sum += group[i];
            }

            long long preSum = 0;
            for(int i=0; i<m; i++) {
                ans[group[i]] = sum - 2*preSum + group[i]*(2*i - group.size());

                preSum += group[i];
            }
        }

        return ans;
    }
};


//************************************************************************************************** //


// Java Code
// TC: O(n)
// SC: O(n)
class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];

        Map<Integer, List<Integer>> groups = new HashMap<>();

        for (int i = 0; i < n; i++) {
            groups.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> group : groups.values()) {
            int m = group.size();

            long sum = 0;
            for (int idx : group) {
                sum += idx;
            }

            long preSum = 0;
            for (int i = 0; i < m; i++) {
                int idx = group.get(i);
                ans[idx] = sum - 2 * preSum + (long) idx * (2 * i - m);
                preSum += idx;
            }
        }

        return ans;
    }
}


// **************************************************************************************************** //


// C++ code (Brute force)
// Approach: For each index, iterate over all indices having the same value and sum their absolute distances (brute force using hashmap grouping).
// TC: O(n)
// SC: O(n)
class Solution {
public:
    vector<long long> distance(vector<int>& nums) {
        vector<long long> ans;
        int n = nums.size();

        unordered_map<int, vector<int>> mp;

        for(int i=0; i<n; i++) {
            mp[nums[i]].push_back(i);
        }

        for(int i=0; i<n; i++) {
            long long sum = 0;
            for(auto it: mp[nums[i]]) {
                if(it!=i) {
                    sum += abs(it-i);
                }
            }

            ans.push_back(sum);
        }

        return ans;
    }
};