/*
2.5 follow up
*/
public class Question2_5_B {
	
	public static class PartialSum {
		public ListNode sum = null;
		public int carry = 0;
	}
	
	public static ListNode addLists(ListNode l1, ListNode l2) {
		int len1 = length(l1);
		int len2 = length(l2);
		
		if (len1 < len2) {
			l1 = padList(l1, len2 - len1);
		} else {
			l2 = padList(l2, len1 - len2);
		}
		
		PartialSum sum = addListsHelper(l1, l2);
		
		if (sum.carry == 0) {
			return sum.sum;
		} else {
			ListNode rst = insertBefore(sum.sum, sum.carry);
			return rst;
		}
	}
	
	public static PartialSum addListsHelper(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			PartialSum sum = new PartialSum();
			return sum;
		}
		
		PartialSum sum = addListsHelper(l1.next, l2.next);
		
		int val = sum.carry + l1.val + l2.val;
		
		ListNode full_result = insertBefore(sum.sum, val % 10);
		
		sum.sum = full_result;
		sum.carry = val / 10;
		return sum;
	}
	
	public static int length(ListNode l) {
		if (l == null) {
			return 0;
		} else {
			return 1 + length(l.next);
		}
	}
	
	public static ListNode padList(ListNode l, int padding) {
		ListNode head = l;
		ListNode pos = head;
		for (int i = 0; i < padding; i++) {
			ListNode n = new ListNode(0);
			n.next = pos;
			pos = n;
		}
		return head;
	}
	
	public static ListNode insertBefore(ListNode list, int data) {
		ListNode node = new ListNode(data);
		ListNode cur = node;
		if (list != null) {
			cur.next = list;
		}
		return node;
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
		ListNode lA1 = new ListNode(3);
		ListNode lA2 = new ListNode(1);
		ListNode lA3 = new ListNode(5);
		lA1.next = lA2;
		lA2.next = lA3;
		lA3.next = null;
		ListNode lB1 = new ListNode(5);
		ListNode lB2 = new ListNode(9);
		ListNode lB3 = new ListNode(1);
		lB1.next = lB2;
		lB2.next = lB3;
		lB3.next = null;
		ListNode list3 = addLists(lA1, lB1);
		
		System.out.println("  " + lA1.printForward());
		System.out.println("+ " + lB1.printForward());
		System.out.println("= " + list3.printForward());
	}
}
/*
outputs:
  3->1->5
+ 5->9->1
= 9->0->6
*/
