/**
 * Scroll below to see Java code as well
 * 
 * My youtube video of this question: https://youtu.be/qjJWJiJ1wT8
 * Leetcode Link: https://leetcode.com/problems/maximize-the-distance-between-points-on-a-square/
 */

// C++ code
// Approach: Binary search the maximum minimum distance (limit). For each start point, treat it as the first pick and define a valid window [start, start + (perimeter − limit)]. Greedily pick remaining points using lower_bound (next ≥ cur + limit) and ensure all chosen points stay within this window to satisfy the circular constraint.
// TC: O(n*k*log(side)*log(n))
// SC: O(n)
class Solution {
public:
    bool canPickKPointsWithLimit(long long limit, vector<long long> arr, int side, int k) {
        int n = arr.size();
        for(int ind=0; ind<n; ind++) {
            long long start = arr[ind];
            long long end = start + 4LL*side - limit;
            long long cur = start;

            for(int i=0; i<(k-1); i++) {
                auto it = lower_bound(arr.begin(), arr.end(), cur+limit);

                if(it == arr.end() || *it > end) {
                    cur = -1;
                    break;
                }

                cur = *it;
            }

            if(cur>=0) {
                return true;
            }
        }

        return false;
    }
    int maxDistance(int side, vector<vector<int>>& points, int k) {
        vector<long long> arr;
        int ans = 0;

        for(auto& p: points) {
            int x = p[0];
            int y = p[1];

            if(x==0) {
                arr.push_back(y);
            } else if(y==side) {
                arr.push_back(side+x);
            } else if(x==side) {
                arr.push_back(3LL*side - y);
            } else {
                arr.push_back(4LL*side - x);
            }
        }

        sort(arr.begin(), arr.end());

        long long low = 1, high = side;
        while(low<=high) {
            long long mid = low + (high-low)/2;

            if(canPickKPointsWithLimit(mid, arr, side, k)) {
                ans = mid;
                low = mid+1;
            } else {
                high = mid-1;
            }
        }

        return ans;
    }
};


// Java Code
// TC: O(n*k*log(side)*log(n))
// SC: O(n)
class Solution {

    private boolean canPickKPointsWithLimit(long limit, List<Long> arr, int side, int k) {
        int n = arr.size();

        for (int ind = 0; ind < n; ind++) {
            long start = arr.get(ind);
            long end = start + 4L * side - limit;
            long cur = start;

            for (int i = 0; i < k - 1; i++) {
                int idx = lowerBound(arr, cur + limit);

                if (idx == n || arr.get(idx) > end) {
                    cur = -1;
                    break;
                }

                cur = arr.get(idx);
            }

            if (cur >= 0) return true;
        }

        return false;
    }

    private int lowerBound(List<Long> arr, long target) {
        int left = 0, right = arr.size();

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public int maxDistance(int side, int[][] points, int k) {
        List<Long> arr = new ArrayList<>();

        for (int[] p : points) {
            int x = p[0], y = p[1];

            if (x == 0) {
                arr.add((long) y);
            } else if (y == side) {
                arr.add((long) side + x);
            } else if (x == side) {
                arr.add(3L * side - y);
            } else {
                arr.add(4L * side - x);
            }
        }

        Collections.sort(arr);

        long low = 1, high = side;
        int ans = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (canPickKPointsWithLimit(mid, arr, side, k)) {
                ans = (int) mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }
}