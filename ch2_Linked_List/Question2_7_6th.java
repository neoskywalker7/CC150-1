/*
2.7 (6th edition)
*/
// time O(A + B) space O(1)
public class Question2_7_6th {
	public static ListNode findIntersection(ListNode list1, ListNode list2) {
		if (list1 == null || list2 == null) {
			return null;
		}
		
		Result result1 = getTailAndSize(list1);
		Result result2 = getTailAndSize(list2);
		
		if (result1.tail != result2.tail) {
			return null;
		}
		
		ListNode shorter = result1.size < result2.size ? list1 : list2;
		ListNode longer = result1.size > result2.size ? list1 : list2;
		
		longer = getKthNode(longer, Math.abs(result1.size - result2.size));
		
		while (shorter != longer) {
			shorter = shorter.next;
			longer = longer.next;
		}
		
		return longer;
	}
	
	public static class Result {
		public int size;
		public ListNode tail;
		public Result(ListNode tail, int size) {
			this.tail = tail;
			this.size = size;
		}
	}
	
	public static Result getTailAndSize(ListNode list) {
		if (list == null) {
			return null;
		}
		
		int size = 1;
		ListNode cur = list;
		while (cur.next != null) {
			size++;
			cur = cur.next;
		}
		
		return new Result(cur, size);
	}
	
	public static ListNode getKthNode(ListNode head, int k) {
		ListNode cur = head;
		while (k > 0 && cur != null) {
			cur = cur.next;
			k--;
		}
		return cur;
	}
	
	public static ListNode findIntersection2(ListNode list1, ListNode  list2) {
		if (list1 == null || list2 == null) {
			return null;
		}
		
		int len1 = getLen(list1);
		int len2 = getLen(list2);
		int cnt = Math.abs(len1 - len2);
		
		if (len1 > len2) {
			while (cnt > 0) {
				list1 = list1.next;
				cnt--;
			}
		} else {
			while (cnt > 0) {
				list2 = list2.next;
				cnt--;
			}
		}
		
		while (list1 != null) {
			if (list1 == list2) {
				return list1;
			}
			list1 = list1.next;
			list2 = list2.next;
		}
		return null;
	}
	
	public static int getLen(ListNode list) {
		int cnt = 0;
		while (list != null) {
			cnt++;
			list = list.next;
		}
		return cnt;
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
		int[] set1 = {3, 1, 5, 9};
		int[] set2 = {4, 6};
		int[] set3 = {7, 2, 1};
		ListNode[] nodes1 = new ListNode[set1.length];
		ListNode[] nodes2 = new ListNode[set2.length];
		ListNode[] nodes3 = new ListNode[set3.length];

		for (int i = 0; i < set1.length; i++) {
			nodes1[i] = new ListNode(set1[i]);
		}
		
		for (int i = 0; i < set2.length; i++) {
			nodes2[i] = new ListNode(set2[i]);
		}
		
		for (int i = 0; i < set3.length; i++) {
			nodes3[i] = new ListNode(set3[i]);
		}
		
		ListNode list1 = nodes1[0];
		ListNode cur1 = list1;
		for (int i = 1; i < set1.length; i++) {
			ListNode tmp = nodes1[i];
			cur1.next = tmp;
			cur1 = cur1.next;
		}
		for (int i = 0; i < set3.length; i++) {
			ListNode tmp = nodes3[i];
			cur1.next = tmp;
			cur1 = cur1.next;
		}
		
		ListNode list2 = nodes2[0];
		ListNode cur2 = list2;
		for (int i = 1; i < set2.length; i++) {
			ListNode tmp = nodes2[i];
			cur2.next = tmp;
			cur2 = cur2.next;
		}
		for (int i = 0; i < set3.length; i++) {
			ListNode tmp = nodes3[i];
			cur2.next = tmp;
			cur2 = cur2.next;
		}
		System.out.println(list1.printForward());
		System.out.println(list2.printForward());
		
		System.out.print(findIntersection2(list1, list2).val);
	}
}
