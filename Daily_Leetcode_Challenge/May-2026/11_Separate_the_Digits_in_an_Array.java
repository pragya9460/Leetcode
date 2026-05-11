/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/ODj1fD6ov4U
 * Leetcode Link: https://leetcode.com/problems/separate-the-digits-in-an-array/
 */

// C++ code (Optimal)
// Approach: Extract digits of each number in reverse order and store them in a temporary list. Then traverse the temporary list backwards to append digits to the final answer in correct order.
// TC: O(n*log(x))
// SC: O(1)
class Solution {
public:
    vector<int> separateDigits(vector<int>& nums) {
        vector<int> ans;
        int n = nums.size();

        for(int i=n-1; i>=0; i--) {
            int num = nums[i];

            while(num>0) {
                ans.push_back(num%10);
                num = num/10;
            }
        }

        reverse(ans.begin(), ans.end());

        return ans;
    }
};


// C++ code (Brute)
// Approach: Traverse the input array from right to left and directly append extracted digits (which come in reverse order). Finally, reverse the complete answer to restore the correct order.
// TC: O(n*log(x)
// SC: O(log(x))
class Solution {
public:
    vector<int> separateDigits(vector<int>& nums) {
        vector<int> ans;
        int n = nums.size();

        for(int i=0; i<n; i++) {
            int num = nums[i];
            vector<int> temp;

            while(num>0) {
                temp.push_back(num%10);
                num = num/10;
            }

            for(int j=temp.size() - 1; j>=0; j--) {
                ans.push_back(temp[j]);
            }
        }

        return ans;
    }
};


// *************************************************************************** //


// Java Code (Optimal)
// TC: O(n*log(x))
// SC: O(1)
class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];

            while (num > 0) {
                ans.add(num % 10);
                num /= 10;
            }
        }

        Collections.reverse(ans);

        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }

        return result;
    }
}

// Java code (Brute)
// TC: O(n*log(x))
// SC: O(log(x))
class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        for (int num : nums) {
            List<Integer> temp = new ArrayList<>();

            while (num > 0) {
                temp.add(num % 10);
                num /= 10;
            }

            for (int i = temp.size() - 1; i >= 0; i--) {
                ans.add(temp.get(i));
            }
        }

        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }

        return result;
    }
}