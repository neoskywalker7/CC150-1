/*
2.7
*/
public class Question2_7 {
	// Iteration
	public static boolean isPalindrome1(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		
		Stack<Integer> stack = new Stack<Integer>();
		
		while (fast != null && fast.next != null) {
			stack.push(slow.val);
			slow = slow.next;
			fast = fast.next.next;
		}
		/* Has odd number of elements, so skip the middle element */
		if (fast != null) {
			slow = slow.next;
		}
		
		while (slow != null) {
			int top = stack.pop().intValue();
			if (top != slow.val) {
				return false;
			}
			slow = slow.next;
		}
		return true;
	}
	// Recursive
	public static boolean isPalindrome2(ListNode head) {
		int size = 0;
		ListNode n = head;
		while (n != null) {
			size++;
			n = n.next;
		}
		Result p = isPalindromeRecurse(head, size);
		return p.result;
	}
	
	public static Result isPalindromeRecurse(ListNode head, int length) {
		if (head == null || length == 0) {
			return new Result(null, true);
		} else if (length == 1) {
			return new Result(head.next, true);
		} else if (length == 2) {
			return new Result(head.next.next, head.val == head.next.val);
		}
		
		Result res = isPalindromeRecurse(head.next, length - 2);
		
		if (!res.result || res.node == null) {
			return res; // Only "result" member is actually used in the call stack.
		} else {
			res.result = head.val == res.node.val;
			res.node = res.node.next;
			return res;
		}
	}
	
	public static class Result {
		public ListNode node;
		public boolean result;
		public Result(ListNode n, boolean res) {
			node = n;
			result = res;
		}
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
		
		int len = 10;
		ListNode[] nodes = new ListNode[len];
		for (int i = 0; i < len; i++) {
			if (i >= len / 2) {
				nodes[i] = new ListNode(len - i - 1);
			} else {
				nodes[i] = new ListNode(i);
			}
		}
		
		ListNode head = nodes[0];
		ListNode cur = head;
		for (int i = 1; i < len; i++) {
			ListNode tmp = nodes[i];
			cur.next = tmp;
			cur = cur.next;
		}

		System.out.println(head.printForward());
		
		Question2_7 q = new Question2_7();
		System.out.println(q.isPalindrome1(head));
	}
}
