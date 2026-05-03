/**
 * Scroll below to see Java code as well.
 *
 * My youtube video of this question: https://www.youtube.com/watch?v=G6qQ8vjW8rQ
 * Leetcode Link: https://leetcode.com/problems/rotate-string/
 */

// C++ code (Brute Force)
// TC: O(n^2)
// SC: O(1)
class Solution {
    public:
    bool rotateString(string s, string goal) {
        if (s.length() != goal.length()) return false;
        int length = s.length();

        // Try all possible rotations of the string
        for (int rotationCount = 0; rotationCount < length; ++rotationCount) {
            // Perform one rotation
            rotate(s.begin(), s.begin() + 1, s.end());
            if (s == goal) return true;
        }
        return false;
    }
};

/*Approach 2: Mannually Check
 TC: O(n^2)
 SC: O(n) */
class Solution {
    public:
    bool rotateString(string s, string goal) {
        if(s.size()!=goal.size())   return false;
        if(s==goal)     return true;
        int n = goal.size();
        for(int i=1; i<n; i++){
            string temp="";
            for(int j=i; j<i+n; j++){
                temp+= s[j%n];
            }
            if(temp==goal)  return true;
        }
        return false;
    }
};


/*Approach 3: Concatenation Check
 TC: O(n)
 SC: O(n) */
class Solution {
    public:
    bool rotateString(string s, string goal) {
        // Check if the lengths are different
        if (s.length() != goal.length()) return false;

        // Create a new string by concatenating 's' with itself
        string doubledString = s + s;
        return doubledString.find(goal) < doubledString.length();
    }
};



//************************************************************************************************** //


/* Java Code (Brute force)
 TC: O(n^2)
 SC: O(n) */
class Solution {

    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) return false;
        int length = s.length();
        char[] sChars = s.toCharArray();

        // Try all possible rotations of the string
        for (int rotationCount = 0; rotationCount < length; ++rotationCount) {
            // Perform one rotation
            sChars = rotateOnce(sChars);
            if (new String(sChars).equals(goal)) return true;
        }
        return false;
    }

    private char[] rotateOnce(char[] arr) {
        char firstChar = arr[0];
        System.arraycopy(arr, 1, arr, 0, arr.length - 1);
        arr[arr.length - 1] = firstChar;
        return arr;
    }
}


/*Approach 2: Mannually Check
 TC: O(n^2)
 SC: O(n) */
class Solution {

    public boolean rotateString(String s, String goal) {
        // Check if the lengths are different
        if (s.length() != goal.length()) return false;

        // Create a new string by concatenating 's' with itself
        String doubledString = s + s;

        // Use contains to search for 'goal' in 'doubledString'
        // If contains return true, 'goal' is a substring
        return doubledString.contains(goal);
    }
}


/*Approach 3: Concatenation Check
 TC: O(n)
 SC: O(n) */
class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) return false;
        if (s.equals(goal)) return true;

        int n = s.length();
        for (int i = 1; i < n; i++) {
            String temp = "";
            for (int j = i; j < i + n; j++) {
                temp += s.charAt(j % n);
            }
            if (temp.equals(goal)) return true;
        }
        return false;
    }
}