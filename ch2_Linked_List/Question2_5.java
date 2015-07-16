/*
2.5
*/
public class Question2_5 {
	
	public static ListNode addList0(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return null;
		}
		
		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;
		int carry = 0;
		while (l1 != null || l2 != null || carry == 1) {
			int sum = carry;
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}
			
			carry = sum / 10;
			ListNode cur = new ListNode(sum % 10);
			tail.next = cur;
			tail = tail.next;
		}
		return dummy.next;
	}
	
	public static ListNode addList1(ListNode l1, ListNode l2, int carry) {
		if (l1 == null && l2 == null && carry == 0) {
			return null;
		}
		
		// ListNode rst = new ListNode();
		
		int value = carry;
		if (l1 != null) {
			value += l1.val;
		}
		if (l2 != null) {
			value += l2.val;
		}
		
		// rst.val = value % 10;
		ListNode rst = new ListNode(value % 10);
		ListNode cur = rst;
		
		if (l1 != null || l2 != null) {
			ListNode more = addList1(l1 == null ? null : l1.next, l2 == null ? null : l2.next, value >= 10 ? 1 : 0);
			// rst.setNext(more);
			cur.next = more;
			cur = cur.next;
		}
		return rst;
	}
	
	/*
	public static class ListNode {
		public int val;
		public ListNode next;
		public ListNode prev;
		public ListNode last;
		public ListNode(int x, ListNode n, ListNode p) {
			val = x;
			setNext(n);
			setPrevious(p);
		}
		
		public ListNode() { }
		
		public void setNext(ListNode n) {
			next = n;
			
			if (this == last) {
				last = n;
			}
			if (n != null && n.prev != this) {
				n.setPrevious(this);
			}
		}
		
		public void setPrevious(ListNode p) {
			prev = p;
			
			if (p != null && p.next != this) {
				p.setNext(this);
			}
		}
		
		public String printForward() {
			if (next != null) {
				return val + "->" + next.printForward();
			} else {
				return ((Integer) val).toString();
			}
		}
	}
	*/
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
		
		ListNode lA1 = new ListNode(9);
		ListNode lA2 = new ListNode(9);
		ListNode lA3 = new ListNode(9);
		lA1.next = lA2;
		lA2.next = lA3;
		lA3.next = null;
		ListNode lB1 = new ListNode(1);
		ListNode lB2 = new ListNode(0);
		ListNode lB3 = new ListNode(0);
		lB1.next = lB2;
		lB2.next = lB3;
		lB3.next = null;
		
		/*
		ListNode lA1 = new ListNode(9, null, null);
		ListNode lA2 = new ListNode(9, null, lA1);
		ListNode lA3 = new ListNode(9, null, lA2);
		
		ListNode lB1 = new ListNode(1, null, null);
		ListNode lB2 = new ListNode(0, null, lB1);
		ListNode lB3 = new ListNode(0, null, lB2);
		
		ListNode list3 = addList1(lA1, lB1, 0);
		*/
		ListNode list = addList0(lA1, lB1);
		ListNode list3 = addList1(lA1, lB1, 0);
		
		System.out.println("  " + lA1.printForward());
		System.out.println("+ " + lB1.printForward());
		System.out.println("= " + list3.printForward());
	}
}
/*
outputs:
  9->9->9
+ 1->0->0
= 0->0->0->1
*/
