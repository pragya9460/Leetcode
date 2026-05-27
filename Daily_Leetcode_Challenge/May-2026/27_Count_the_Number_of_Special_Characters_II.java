/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/yLj51gLzkOE
 * Leetcode Link: https://leetcode.com/problems/count-the-number-of-special-characters-ii/
 */

// C++ code (Optimal)
// Approach: Store the last occurrence of every lowercase letter and the first occurrence of every uppercase letter. A character is special if both exist and the last lowercase index is smaller than the first uppercase index.
// TC: O(n)
// SC: 52 bits = O(1)
class Solution {
public:
    int numberOfSpecialChars(string word) {
        vector<int> firstUpper(26, -1);
        vector<int> lastLower(26, -1);

        for(int i = 0; i < word.size(); i++) {
            char c = word[i];

            if(islower(c)) {
                lastLower[c - 'a'] = i;
            } else {
                if(firstUpper[c - 'A'] == -1) {
                    firstUpper[c - 'A'] = i;
                }
            }
        }

        int cnt = 0;

        for(int i = 0; i < 26; i++) {
            if(lastLower[i] != -1 &&
               firstUpper[i] != -1 &&
               lastLower[i] < firstUpper[i]) {
                cnt++;
            }
        }

        return cnt;
    }
};



// C++ code (Brute)
// Approach: For every letter from 'a' to 'z', find its last lowercase occurrence and first uppercase occurrence by scanning the entire string. The character is special if both exist and the last lowercase appears before the first uppercase.
// TC: O(26 × n) ≈ O(n)
// SC: O(1)
class Solution {
public:
    int numberOfSpecialChars(string word) {
        int cnt = 0;
        int n = word.size();

        for(int ch = 0; ch < 26; ch++) {
            int lastLower = -1;
            int firstUpper = -1;

            for(int i = 0; i < n; i++) {
                if(word[i] == ('a' + ch)) {
                    lastLower = i;
                }

                if(word[i] == ('A' + ch) && firstUpper == -1) {
                    firstUpper = i;
                }
            }

            if(lastLower != -1 &&
               firstUpper != -1 &&
               lastLower < firstUpper) {
                cnt++;
            }
        }

        return cnt;
    }
};

// C++ code (State Tracking Approach)
// Approach: Maintain a state for each character indicating whether we've seen a lowercase letter, an uppercase letter, a valid special character, or an invalid sequence. Update the state while traversing the string once and count the characters that end in the valid special state.
// TC: O(n)
// SC: O(26) = O(1)
class Solution {
public:
    int numberOfSpecialChars(string word) {
        vector<int> state(26, 0);

        for(char c : word) {
            int idx;

            if(islower(c)) {
                idx = c - 'a';

                if(state[idx] == 0) {
                    state[idx] = 1;
                }
                else if(state[idx] == 3) {
                    state[idx] = 2;
                }
            }
            else {
                idx = c - 'A';

                if(state[idx] == 0) {
                    state[idx] = 2;
                }
                else if(state[idx] == 1) {
                    state[idx] = 3;
                }
            }
        }

        int ans = 0;

        for(int s : state) {
            if(s == 3) {
                ans++;
            }
        }

        return ans;
    }
};



// *************************************************************************** //


// Java Code (Optimal)
// TC: O(n)
// SC: 52 bits = O(1)
class Solution {
    public int numberOfSpecialChars(String word) {
        int[] firstUpper = new int[26];
        int[] lastLower = new int[26];

        Arrays.fill(firstUpper, -1);
        Arrays.fill(lastLower, -1);

        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if(Character.isLowerCase(c)) {
                lastLower[c - 'a'] = i;
            } else {
                if(firstUpper[c - 'A'] == -1) {
                    firstUpper[c - 'A'] = i;
                }
            }
        }

        int cnt = 0;

        for(int i = 0; i < 26; i++) {
            if(lastLower[i] != -1 &&
               firstUpper[i] != -1 &&
               lastLower[i] < firstUpper[i]) {
                cnt++;
            }
        }

        return cnt;
    }
}


// Java Code (Brute)
// TC: O(26 × n) ≈ O(n)
// SC: O(1)
class Solution {
    public int numberOfSpecialChars(String word) {
        int cnt = 0;
        int n = word.length();

        for(int ch = 0; ch < 26; ch++) {
            int lastLower = -1;
            int firstUpper = -1;

            for(int i = 0; i < n; i++) {
                if(word.charAt(i) == ('a' + ch)) {
                    lastLower = i;
                }

                if(word.charAt(i) == ('A' + ch) && firstUpper == -1) {
                    firstUpper = i;
                }
            }

            if(lastLower != -1 &&
               firstUpper != -1 &&
               lastLower < firstUpper) {
                cnt++;
            }
        }

        return cnt;
    }
}

// Java Code (State Tracking Approach)
// TC: O(n)
// SC: O(26) = O(1)
class Solution {
public:
    int numberOfSpecialChars(string word) {
        vector<int> state(26, 0);

        for(char c : word) {
            int idx;

            if(islower(c)) {
                idx = c - 'a';

                if(state[idx] == 0) {
                    state[idx] = 1;
                }
                else if(state[idx] == 3) {
                    state[idx] = 2;
                }
            }
            else {
                idx = c - 'A';

                if(state[idx] == 0) {
                    state[idx] = 2;
                }
                else if(state[idx] == 1) {
                    state[idx] = 3;
                }
            }
        }

        int ans = 0;

        for(int s : state) {
            if(s == 3) {
                ans++;
            }
        }

        return ans;
    }
};