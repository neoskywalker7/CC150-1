public class BasicOperations {
	// Creating a Linked List
	public static class Node {
		Node next = null;
		int data;
		
		public Node(int d) {
			data = d;
		}
		
		public void appendToTail(int d) {
			Node end = new Node(d);
			Node n = this;
			while (n.next != null) {
				n = n.next;
			}
			n.next = end;
		}
	}
	// Deleting a Node from a Singly Linked List
	public static Node deleteNode1(Node head, int d) {
		Node n = head;
		
		if (n.data == d) {
			return head.next;
		}
		
		while (n.next != null) {
			if (n.next.data == d) {
				n.next = n.next.next;
				return head;
			}
			n = n.next;
		}
		return head;
	}
	
	public static Node deleteNode2(Node head, int d) {
		Node dummy = new Node(0);
		dummy.next = head;
		Node pre = dummy;
		
		while (pre.next != null) {
			if (pre.next.data == d) {
				pre.next = pre.next.next;
				return dummy.next;
			}
			pre = pre.next;
		}
		return dummy.next;
	}
	
	public static Node reorderList1(Node head) {
		if (head == null) {
			return head;
		} else if (head.next == null) {
			return head;
		}
		
		Node pre = findMidPre(head);
		Node right = pre.next;
		pre.next = null;
		
		merge(head, right);
		
		return head;
	}
	
	private static Node findMidPre(Node head) {
		if (head == null) {
			return null;
		}
		
		Node slow = head;
		Node fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		return slow;
	}
	
	private static void merge(Node head1, Node head2) {
		Node dummy = new Node(0);
		Node cur = dummy;
		while (head1 != null && head2 != null) {
			cur.next = head1;
			cur = cur.next;
			head1 = head1.next;
			cur.next = head2;
			cur = cur.next;
			head2 = head2.next;
		}
		
		if (head1 != null) {
			cur.next = head1;
		} else {
			cur.next = head2;
		}
	}
	
	public static Node reorderList2(Node head) {
		if (head == null) {
			return null;
		}
		
		Node slow = head;
		Node fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		Node slowNode = slow.next;
		slow.next = null;
		Node fastNode = head;
		
		Node newNode = new Node(0);
		Node tmp = newNode;
		
		while (fastNode != null && slowNode != null) {
			tmp.next = fastNode;
			tmp = tmp.next;
			fastNode = fastNode.next;
			tmp.next = slowNode;
			tmp = tmp.next;
			slowNode = slowNode.next;
		}
		
		if (fastNode != null) {
			tmp.next = fastNode;
		} else {
			tmp.next = slowNode;
		}
		
		return newNode.next;
	}
	
	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		n1.next = n2;
		n2.next = n3;
		n3.next = null;
		Node rst1 = deleteNode2(n1, 2);
		while (rst1 != null) {
			System.out.print(rst1.data + " ");
			rst1 = rst1.next;
		}
		System.out.println();
		
		Node a1 = new Node(1);
		Node a2 = new Node(2);
		Node a3 = new Node(3);
		Node b1 = new Node(1);
		Node b2 = new Node(2);
		Node b3 = new Node(3);
		a1.next = a2;
		a2.next = a3;
		a3.next = b1;
		b1.next = b2;
		b2.next = b3;
		b3.next = null;
		Node rst2 = reorderList2(a1);
		while (rst2 != null) {
			System.out.print(rst2.data + " ");
			rst2 = rst2.next;
		}
	}
}
