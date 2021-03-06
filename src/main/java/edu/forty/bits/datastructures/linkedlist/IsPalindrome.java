package edu.forty.bits.datastructures.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Implement a function to check if a linked list is a palindrome.
 */
public class IsPalindrome {

    // one approach is to reverse the linked list and match if all the elements are same in both the list
    boolean checkPalindrome(LinkedListNode linkedListNode) {
        LinkedListNode reverse = ReverseLinkedList.reverseLinkedListAndClone(linkedListNode);
        return isEqual(linkedListNode, reverse);
    }

    private boolean isEqual(LinkedListNode first, LinkedListNode second) {
        while (first != null && second != null) {
            if (first.data != second.data) {
                return false;
            }
            first = first.next;
            second = second.next;
        }
        return first == null && second == null;
    }

    // One needs to traverse only until the middle element and comparing the fold with the existing elements
    // so a Stack could be used to push the first half of elements and then pop the elements to
    // compare them with the other half. This would require being aware fof the size or
    // using the runner approach to decide the middle element)
    boolean isPalindrome(LinkedListNode head) {
        LinkedListNode fast = head;
        LinkedListNode slow = head;
        Deque<Integer> stack = new ArrayDeque<>(); // Java implementation for synchronised stack
        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        // odd number of elements (already taken care for even since they could be double folded)
        if (fast != null) {
            slow = slow.next;
        }

        // continue with the slow pointer for the rest of the half while cross checking the stack
        while (slow != null) {
            int top = stack.pop();
            if (top != slow.data) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }
}