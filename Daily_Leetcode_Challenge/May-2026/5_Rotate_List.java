/**
 * Scroll below to see Java code as well as optimal & brute force approaches.
 * 
 * My youtube video of this question: https://youtu.be/FnA2QsDtsAs
 * Leetcode Link: https://leetcode.com/problems/rotate-list/
 */

// C++ code (Optimal)
// Approach: Get the length of LL & convert list into a circular linked list by attaching last node to 1st node, then break it at the new head position (len - k % len) to achieve 'k' rotation.
// TC: O(n)
// SC: O(1)
class Solution {
public:
    ListNode* rotateRight(ListNode* head, int k) {
        if(head==NULL or head->next==NULL or k==0) {
            return head;
        }

        ListNode* cur = head;
        int len = 1;
        while(cur->next) {
            cur = cur->next;
            len++;
        }

        cur->next = head;
        k = k%len;
        k = len-k;

        while(k>0) {
            cur = cur->next;
            k--;
        }

        head = cur->next;
        cur->next = NULL;

        return head;
    }
};


// *************************************************************************** //


// Java Code (Optimal)
// TC: O(n)
// SC: O(1)
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode cur = head;
        int len = 1;

        while (cur.next != null) {
            cur = cur.next;
            len++;
        }

        // make it circular
        cur.next = head;

        k = k % len;
        k = len - k;

        while (k > 0) {
            cur = cur.next;
            k--;
        }

        head = cur.next;
        cur.next = null;

        return head;
    }
}