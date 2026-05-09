/**
 * Scroll below to see Java code as well
 * 
 * My youtube video of this question: https://youtu.be/IN03PFXp34k
 * Leetcode Link: https://leetcode.com/problems/score-validator/
 */

// C++ code 
// Approach: Simulation
// TC: O(n)
// SC: O(1)
class Solution {
public:
    vector<int> scoreValidator(vector<string>& e) {
        int sc = 0, cnt = 0;
        for(int i=0; i<e.size(); i++) {
            if(e[i]=="0" or e[i]=="1" or e[i]=="2" or e[i]=="3" or e[i]=="4" or e[i]=="6") {
                sc += stoi(e[i]);
            } else if(e[i]=="WD" or e[i]=="NB") {
                sc ++;
            } else {
                cnt++;
            }

            if(cnt==10) {
                break;
            }
        }

        return {sc, cnt};
    }
};

// Java Code
// TC: O(n)
// SC: O(1)
class Solution {
    public int[] scoreValidator(String[] e) {
        int sc = 0, cnt = 0;

        for (int i = 0; i < e.length; i++) {
            String curr = e[i];

            if (curr.equals("0") || curr.equals("1") || curr.equals("2") ||
                curr.equals("3") || curr.equals("4") || curr.equals("6")) {
                
                sc += Integer.parseInt(curr);

            } else if (curr.equals("WD") || curr.equals("NB")) {
                
                sc++;

            } else {
                
                cnt++;
            }

            if (cnt == 10) {
                break;
            }
        }

        return new int[]{sc, cnt};
    }
}