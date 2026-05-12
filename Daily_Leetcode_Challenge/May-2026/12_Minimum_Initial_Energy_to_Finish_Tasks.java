/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/2vi-zUsm4eo
 * Leetcode Link: https://leetcode.com/problems/minimum-initial-energy-to-finish-tasks/
 */

// C++ code (Greedy + sorting)
// Approach: Sort the tasks in descending order of (minimumRequired - actualCost), so tasks that need the largest extra buffer are performed first. Maintain remain as the energy left after completing processed tasks and ans as the minimum initial energy needed so far. If current energy is less than the task's minimum required energy, increase ans just enough to meet it, then update the remaining energy after completing the task.
// TC: O(n*log(n))
// SC: O(log(n)) -> Used internally for sorting
class Solution {
public:
    static bool comp(vector<int> &a, vector<int> &b) {
        return (a[1]-a[0]) > (b[1]-b[0]);
    }
    int minimumEffort(vector<vector<int>>& tasks) {
        sort(tasks.begin(), tasks.end(), comp);

        int ans = 0, remain = 0;

        for(auto &task: tasks) {
            ans += remain>task[1] ? 0 : task[1]-remain;
            remain = max(task[1]-task[0], remain-task[0]);
        }

        return ans;
    }
};


// *************************************************************************** //


// Java Code (Greedy + sorting)
// TC: O(n*log(n))
// SC: O(log(n)) -> Used internally for sorting
class Solution {
    public int minimumEffort(int[][] tasks) {
        // Sort by descending order of (minimumRequired - actualCost)
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));

        int ans = 0;      // Minimum initial energy required
        int remain = 0;   // Energy remaining after completing tasks

        for (int[] task : tasks) {
            int actual = task[0];
            int minimum = task[1];

            // If remaining energy is less than required minimum,
            // add the extra energy needed to ans
            if (remain < minimum) {
                ans += (minimum - remain);
            }

            // Update remaining energy after completing this task
            remain = Math.max(minimum - actual, remain - actual);
        }

        return ans;
    }
}