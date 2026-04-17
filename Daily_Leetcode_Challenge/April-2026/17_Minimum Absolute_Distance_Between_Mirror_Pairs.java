/**
 * Scroll below to see Java code as well
 * 
 * My youtube video of this question: https://youtu.be/uaEcuEioi0o
 * Leetcode Link: https://leetcode.com/problems/minimum-absolute-distance-between-mirror-pairs/
 */

// C++ code
// Approach: Store the reversed value of each number in a map; for every element in array, check if its original value already exists to compute the minimum index distance.
// TC: O(nLog(c)) => where C is max value in array, which can be at max 10^9, so digits = log(x) + 1 = log(10^9) + 1 = 10. Total iterations = number of digits = O(log C)
// SC: O(n)
class Solution {
public:
    int reverseNum(int val) {
        int rev = 0;

        while(val>0) {
            rev = rev*10 + val%10;
            val = val/10; 
        }

        return rev;
    }
    int minMirrorPairDistance(vector<int>& nums) {
        int n = nums.size();
        unordered_map<int, int> mp;
        int minDist = INT_MAX;

        for(int i=0; i<n; i++) {
            int revVal = reverseNum(nums[i]);

            if(mp.find(nums[i])!=mp.end()) {
                minDist = min(minDist, i-mp[nums[i]]);
            }
            mp[revVal] = i;
        }

        return minDist==INT_MAX ? -1 : minDist;
    }
};


// Java Code
// TC: O(nLog(c)) => where C is max value in array, which can be at max 10^9, so digits = log(x) + 1 = log(10^9) + 1 = 10. Total iterations = number of digits = O(log C)
// SC: O(n)
class Solution {
    int reverseNum(int val) {
        int rev = 0;

        while(val>0) {
            rev = rev*10 + val%10;
            val = val/10; 
        }

        return rev;
    }
    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>();
        int minDist = Integer.MAX_VALUE;

        for(int i=0; i<n; i++) {
            int revVal = reverseNum(nums[i]);

            if(mp.containsKey(nums[i])) {
                minDist = Math.min(minDist, i-mp.get(nums[i]));
            }
            mp.put(revVal, i);
        }

        return minDist==Integer.MAX_VALUE ? -1 : minDist;
    }
}
