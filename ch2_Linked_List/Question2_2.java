/*
2.2
*/
/*
notes about the space complex for recursive
是程序运行所以需要的额外消耗存储空间, 一般的递归算法就要有o(n)的空间复杂度了, 简单说就是递归集算时通常是反复调用同一个方法, 因为每次递归都要存储返回信息, 递归n次, 就需要n个空间
*/
public class Question2_2 {
	// Recursive space O(n)
	public static int nthToLast1(ListNode head, int k) {
		if (head == null) {
			return 0;
		}
		
		int i = nthToLast1(head.next, k) + 1;
		if (i == k) {
			System.out.println(head.val);
		}
		return i;
	}
	// Recursive space O(n)
	public static ListNode nthToLast2(ListNode head, int k, IntWrapper i) {
		if (head == null) {
			return null;
		}
		
		ListNode node = nthToLast2(head.next, k, i);
		i.value = i.value + 1;
		if (i.value == k) {
			return head;
		}
		return node;
	}
	
	public static class IntWrapper {
		public int value = 0;
	}
	// Iterative time O(n) space O(1)
	public static ListNode nthToLast3(ListNode head, int k) {
		if (k <= 0) return null;
		
		ListNode p1 = head;
		ListNode p2 = head;
		
		for (int i = 0; i < k - 1; i++) {
			if (p2 == null) return null;
			p2 = p2.next;
		}
		
		if (p2 == null) return null;
		
		while (p2.next != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		
		return p1;
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
		/*
		while (test != null) {
			System.out.print(test.val);
			test = test.next;
			if (test != null) {
				System.out.print("->");
			}
		}
		*/
		System.out.print(test.printForward());
		System.out.println();
		int nth = 3;
		System.out.println();
		nthToLast1(test, nth);
		IntWrapper i = new IntWrapper();
        System.out.print(nthToLast2(test, nth, i).val);
        System.out.println();
		System.out.print(nthToLast3(test, nth).val);
	}
}
