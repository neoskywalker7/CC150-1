/*
2.1
*/
public class RemoveDupSol {
	// time O(N) space O(N)
	public static ListNode removeDup1(ListNode head) {
		Hashtable<Integer, Boolean> table = new Hashtable<Integer, Boolean>();
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		
		while (head != null) {
			if (table.containsKey(head.val)) {
				pre.next = head.next;
			} else {
				table.put(head.val, true);
				pre = head;
			}
			head = head.next;
		}
		
		return dummy.next;
	}
	// time O(N^2) space O(1)
	public static ListNode removeDup2(ListNode head) {
		if (head == null) {
			return head;
		}
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode cur = dummy;
		
		while (cur.next != null) {
			ListNode runner = cur.next;
			while (runner.next != null) {
				if (runner.next.val == cur.next.val) {
					runner.next = runner.next.next;
				} else {
					runner = runner.next;
				}
			}
			cur = cur.next;
		}
		
		return dummy.next;
	}
	
	public static class ListNode {
		int val;
		ListNode next;
		
		ListNode(int x) {
			val = x;
		}
	}
	
	public static void main(String[] args) {
		ListNode n1 = new ListNode(6);
		ListNode n2 = new ListNode(1);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(6);
		ListNode n5 = new ListNode(7);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = null;
		
		ListNode rst = removeDup2(n1);
		
		while (rst != null) {
			System.out.print(rst.val);
			rst = rst.next;
		}
	}
}
