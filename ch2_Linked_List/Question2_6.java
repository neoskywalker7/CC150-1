/*
2.6 LeetCode Linked List Cycle II
*/
public class Question2_6 {
	public static ListNode FindBeginning(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			
			if (slow == fast) {
				break;
			}
		}
		
		if (fast == null || fast.next == null) {
			return null;
		}
		
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		
		return fast;
	}
	
	public static class ListNode {
		public int val;
		public ListNode next;
		public ListNode(int x) {
			val = x;
		}
		
		public String printForward() {
			if (next != null) {
				return val + "->" + next.printForward();
			} else {
				return ((Integer) val).toString();
			}
		}
	}
	
	public static void main(String[] args) {
		int list_len = 100;
		int k = 10;
		
		ListNode[] nodes = new ListNode[list_len];
		for (int i = 0; i < list_len; i++) {
			nodes[i] = new ListNode(i);
		}
		
		ListNode head = nodes[0];
		ListNode cur = head;
		for (int i = 1; i < list_len; i++) {
			ListNode tmp = nodes[i];
			cur.next = tmp;
			cur = cur.next;
		}
		
		// System.out.println(nodes[0].printForward());
		
		nodes[list_len - 1].next = nodes[k];
		ListNode loop = FindBeginning(nodes[0]);
		if (loop == null) {
			System.out.println("No Cycle.");
		} else {
			System.out.println(loop.val);
		}
	}
}
