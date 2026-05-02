/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/G6qQ8vjW8rQ
 * Leetcode Link: https://leetcode.com/problems/rotated-digits/
 */

// C++ code (Digit DP)
// Approach: Build numbers using DP by classifying each as invalid / same / different, and derive dp[i] from dp[i/10] and last digit to count valid-different numbers.
// TC: O(n)
// SC: O(1)
class Solution {
public:
    int rotatedDigits(int n) {
        vector<int> dp(n+1, 0);
        int cnt = 0;

        for(int i = 0; i <= n; i++) {
            if(i < 10) {
                if(i==0 || i==1 || i==8) dp[i] = 1;
                else if(i==2 || i==5 || i==6 || i==9) {
                    dp[i] = 2;
                    cnt++;
                }
            } else {
                int a = dp[i/10];
                int b = dp[i%10];

                if(a==1 && b==1) dp[i] = 1;
                else if(a>=1 && b>=1) {
                    dp[i] = 2;
                    cnt++;
                }
            }
        }
        return cnt;
    }
};

// C++ code (Brute)
// Approach: Iterate from 1 → n, check each number’s digits → reject if {3,4,7}, ensure at least one from {2,5,6,9}.
// TC: O(n*log(n))
// SC: O(1)
class Solution {
public:
    bool isValidAndDiff(int x) {
        bool only018 = true;
        while(x>0) {
            int dig = x%10;
            if(dig==3 or dig==4 or dig==7) {
                return false; // invalid case;
            } else if(dig==2 or dig==5 or dig==6 or dig==9) {
                only018 = false;
            }

            x = x/10;
        }

        if(only018) {
            return false;
        }

        return true;
    }
    int rotatedDigits(int n) {
        int cnt = 0;

        for(int x=0; x<=n; x++) {
            cnt += isValidAndDiff(x);
        }

        return cnt;
    }
};


//************************************************************************************************** //


// Java Code (Digit DP)
// TC: O(n)
// SC: O(1)
class Solution {
    public int rotatedDigits(int n) {
        int[] dp = new int[n + 1];
        int cnt = 0;

        for (int i = 0; i <= n; i++) {
            if (i < 10) {
                if (i == 0 || i == 1 || i == 8) {
                    dp[i] = 1;
                } else if (i == 2 || i == 5 || i == 6 || i == 9) {
                    dp[i] = 2;
                    cnt++;
                }
            } else {
                int a = dp[i / 10];
                int b = dp[i % 10];

                if (a == 1 && b == 1) {
                    dp[i] = 1;
                } else if (a >= 1 && b >= 1) {
                    dp[i] = 2;
                    cnt++;
                }
            }
        }

        return cnt;
    }
}


// Java Code (Brute)
// TC: O(n*log(n))
// SC: O(1)
class Solution {
    public boolean isValidAndDiff(int x) {
        boolean only018 = true;

        while (x > 0) {
            int dig = x % 10;

            if (dig == 3 || dig == 4 || dig == 7) {
                return false; // invalid
            } else if (dig == 2 || dig == 5 || dig == 6 || dig == 9) {
                only018 = false;
            }

            x /= 10;
        }

        return !only018;
    }

    public int rotatedDigits(int n) {
        int cnt = 0;

        for (int x = 1; x <= n; x++) { // start from 1
            if (isValidAndDiff(x)) {
                cnt++;
            }
        }

        return cnt;
    }
}