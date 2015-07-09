/*
2.3
*/
/*
常见错误:
"if lastNode.next == null; lastNode = null;" 注意lastNode = null代表的是用lastNode指代null, 注意指代和指向不同
n1.next是指向一个内存, 如果n2 ＝ null没有改变内存只是重赋值
e.g.
*/
/*
public static void main(String[] args) {
		ListNode n1 = new ListNode(0);
		ListNode n2 = new ListNode(1);
		n1.next = n2;
		n2.next = null;
		n2 = null;
		// n1.next = n2;
		if (n2 == null) {
			System.out.print("n2 is null" + "\n");
		}
		while (n1 != null) {
			System.out.print(n1.val);
			n1 = n1.next;
		}
	}
*/
/*
output:
n2 is null
01
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

