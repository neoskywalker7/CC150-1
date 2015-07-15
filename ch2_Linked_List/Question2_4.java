/*
2.4
*/
public class Question2_4 {
	public static ListNode partition1(ListNode head, int x) {
		if (head == null) {
			return null;
		}
		
		ListNode dummyLeft = new ListNode(0);
		ListNode dummyRight = new ListNode(0);
		ListNode left = dummyLeft;
		ListNode right = dummyRight;
		while (head != null) {
			if (head.val < x) {
				left.next = head;
				left = head;
			} else {
				right.next = head;
				right = head;
			}
			head = head.next;
		}
		right.next = null;
		left.next = dummyRight.next;
		return dummyLeft.next;
	}
		
    public static ListNode partition2(ListNode head, int x) {
    	ListNode beforeStart = null;
    	ListNode beforeEnd = null;
    	ListNode afterStart = null;
    	ListNode afterEnd = null;
    	
    	while (head != null) {
    		ListNode next = head.next;
    		head.next = null;
    		if (head.val < x) {
    			if (beforeStart == null) {
    				beforeStart = head;
    				beforeEnd = beforeStart;
    			} else {
    				beforeEnd.next = head;
    				beforeEnd = head;
    			} 	
    		} else {
    			if (afterStart == null) {
    				afterStart = head;
    				afterEnd = afterStart;
    			} else {
    				afterEnd.next = head;
    				afterEnd = head;
    			}
    		}
    		head = next;
    	}
    	
    	if (beforeStart == null) {
    		return afterStart;
    	}
    	
    	beforeEnd.next = afterStart;
    	return beforeStart;
    }
    
    public static ListNode partition3(ListNode head, int x) {
    	ListNode beforeStart = null;
    	ListNode afterStart = null;
    	
    	while (head != null) {
    		ListNode next = head.next;
    		if (head.val < x) {
    			head.next = beforeStart;
    			beforeStart = head;
     		} else {
     			head.next = afterStart;
     			afterStart = head;
     		}
    		head = next;
    	}
    	
    	if (beforeStart == null) {
    		return afterStart;
    	}
    	
    	ListNode head1 = beforeStart;
    	while (beforeStart.next != null) {
    		beforeStart = beforeStart.next;
        }
    	beforeStart.next = afterStart;
    	
    	return head1;
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

		int[] vals = {1, 3, 7, 5, 2, 9, 4};
		ListNode head = new ListNode(vals[0]);
		ListNode current = head;
		
		for (int i = 1; i < vals.length; i++) {
			ListNode tmp = new ListNode(vals[i]);
			current.next = tmp;
			current = current.next;
		}
		
		System.out.println(head.printForward());
		
		ListNode rst = partition3(head, 5);
		System.out.println(rst.printForward());
		
		int len = 20;
		ListNode[] nodes = new ListNode[len];
		for (int i = 0; i < len; i++) {
			if (i >= len / 2) {
				nodes[i] = new ListNode(len - i - 1);
			} else {
				nodes[i] = new ListNode(i);
			}
		}
		
		ListNode head2 = nodes[0];
		ListNode cur = head2;
		for (int i = 1; i < len; i++) {
			ListNode tmp2 = nodes[i];
			cur.next = tmp2;
			cur = cur.next;
		}

		System.out.println(head2.printForward());
		
		ListNode rst2 = partition3(head2, 8);
		System.out.println(rst2.printForward());
		
	}
}
