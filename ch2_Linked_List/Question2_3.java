/*
2.3
*/
public class Question2_3 {
	public static boolean deleteNode1(ListNode n) { 
		if (n == null || n.next == null) {
			return false;
		}
		
		ListNode next = n.next;
		n.val = next.val;
		n.next = next.next;
		return true;
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
	
	public static ListNode randomLinkedList(int N, int min, int max) {
		ListNode root = new ListNode(randomIntInRange(min, max));
		ListNode pre = root;
		for (int i = 1; i < N; i++) {
			int val = randomIntInRange(min, max);
			ListNode next = new ListNode(val);
			pre.next = next;
			pre = next;
		}
		return root;
	}
	
	public static int randomInt(int n) {
		return (int) (Math.random() * n);
	}
	
	public static int randomIntInRange(int min, int max) {
		return randomInt(max + 1 - min) + min;
	}
	
	public static void main(String[] args) {
		ListNode test = randomLinkedList(9, 0, 10);
		System.out.print(test.printForward() + "\n");
		deleteNode1(test.next.next.next.next);
		System.out.println(test.printForward());
	}
}

