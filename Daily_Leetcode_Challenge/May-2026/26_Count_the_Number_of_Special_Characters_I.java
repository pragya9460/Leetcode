/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: 
 * Leetcode Link: 
 */

// C++ code (Bitset)
// Approach: Store lowercase and uppercase character presence in two bitsets and return the count of common set bits after taking their intersection.
// TC: O(n)
// SC: 52 bits = O(1)
class Solution {
public:
    int numberOfSpecialChars(string word) {
        bitset<26> lower;
        bitset<26> upper;

        for(char c : word) {
            if(islower(c)) {
                lower.set(c - 'a');
            } else {
                upper.set(c - 'A');
            }
        }

        return (lower & upper).count();
    }
};

// C++ code (Boolean Array / Frequency Array)
// Approach: Use two boolean arrays to mark the presence of lowercase and uppercase letters separately, then count the alphabets present in both arrays.
// TC: O(n)
// SC: O(O(52) = O(1))
class Solution {
public:
    int numberOfSpecialChars(string word) {
        bool lower[26] = {false};
        bool upper[26] = {false};

        for(char c : word) {
            if(islower(c)) {
                lower[c - 'a'] = true;
            } else {
                upper[c - 'A'] = true;
            }
        }

        int cnt = 0;

        for(int i = 0; i < 26; i++) {
            if(lower[i] && upper[i]) {
                cnt++;
            }
        }

        return cnt;
    }
};


// C++ code (Hashset)
// Approach: Insert all characters into a hash set and check for every alphabet whether both its lowercase and uppercase forms are present.
// TC: O(n)
// SC: O(52) = O(1)
class Solution {
public:
    int numberOfSpecialChars(string word) {
        unordered_set<char> st(word.begin(), word.end());

        int cnt = 0;

        for(char c = 'a'; c <= 'z'; c++) {
            if(st.count(c) && st.count(c - 'a' + 'A')) {
                cnt++;
            }
        }

        return cnt;
    }
};


// C++ code (Brute)
// Approach: For every alphabet from 'a' to 'z', scan the entire string and check whether both its lowercase and uppercase forms are present. Count the letters for which both forms exist.
// TC: O(26 × n) ≈ O(n)
// SC: O(1)
class Solution {
public:
    int numberOfSpecialChars(string word) {
        int cnt = 0;
        int n = word.size();

        for(int i = 0; i < 26; i++) {
            bool hasLower = false;
            bool hasUpper = false;

            for(int j = 0; j < n; j++) {
                if(word[j] == ('a' + i)) {
                    hasLower = true;
                } else if(word[j] == ('A' + i)) {
                    hasUpper = true;
                }

                if(hasLower && hasUpper) {
                    cnt++;
                    break;
                }
            }
        }

        return cnt;
    }
};


// *************************************************************************** //


// Java Code (Bitset)
// TC: O(n)
// SC: 52 bits = O(1)
import java.util.BitSet;

class Solution {
    public int numberOfSpecialChars(String word) {
        BitSet lower = new BitSet(26);
        BitSet upper = new BitSet(26);

        for(char c : word.toCharArray()) {
            if(Character.isLowerCase(c)) {
                lower.set(c - 'a');
            } else {
                upper.set(c - 'A');
            }
        }

        lower.and(upper);
        return lower.cardinality();
    }
}

// Java Code (Boolean Array / Frequency Array)
// TC: O(n)
// SC: O(52) = O(1)
class Solution {
    public int numberOfSpecialChars(String word) {
        boolean[] lower = new boolean[26];
        boolean[] upper = new boolean[26];

        for(char c : word.toCharArray()) {
            if(Character.isLowerCase(c)) {
                lower[c - 'a'] = true;
            } else {
                upper[c - 'A'] = true;
            }
        }

        int cnt = 0;

        for(int i = 0; i < 26; i++) {
            if(lower[i] && upper[i]) {
                cnt++;
            }
        }

        return cnt;
    }
}


// Java Code (Hashset)
// TC: O(n)
// SC: O(52) = O(1)
import java.util.*;

class Solution {
    public int numberOfSpecialChars(String word) {
        Set<Character> set = new HashSet<>();

        for(char c : word.toCharArray()) {
            set.add(c);
        }

        int cnt = 0;

        for(char c = 'a'; c <= 'z'; c++) {
            if(set.contains(c) &&
               set.contains((char)(c - 'a' + 'A'))) {
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

        for(int i = 0; i < 26; i++) {
            boolean hasLower = false;
            boolean hasUpper = false;

            for(int j = 0; j < word.length(); j++) {
                if(word.charAt(j) == ('a' + i)) {
                    hasLower = true;
                } else if(word.charAt(j) == ('A' + i)) {
                    hasUpper = true;
                }

                if(hasLower && hasUpper) {
                    cnt++;
                    break;
                }
            }
        }

        return cnt;
    }
}