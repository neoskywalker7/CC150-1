/*
4.5
*/
public class Question4_5 {
	// SOL 1
	public static Integer last_printed = null;
	
	public static boolean checkBST1(TreeNode n) {
		if (n == null) {
			return true;
		}
		
		if (!checkBST1(n.left)) {
			return false;
		}
		
		if (last_printed != null && n.val <= last_printed) {
			return false;
		}
		last_printed = n.val;
		
		if (!checkBST1(n.right)) {
			return false;
		}
		return true;
	}
	// SOL 2 time O(N) space O(log N)
	public static boolean checkBST2(TreeNode n) {
		return checkBST2(n, null, null);
	}
	
	public static boolean checkBST2(TreeNode n, Integer min, Integer max) {
		if (n == null) {
			return true;
		}
		if ((min != null && n.val <= min) || (max != null && n.val > max)) {
			return false;
		}
		if ((!checkBST2(n.left, min, n.val)) || (!checkBST2(n.right, n.val, max))) {
			return false;
		}
		return true;
	}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int x) {
			val = x;
		}
	}
	
	public static TreeNode createMinimalBST(int array[]) {
		return createMinimalBST(array, 0, array.length - 1);
	}
	
	public static TreeNode createMinimalBST(int arr[], int start, int end) {
		if (end < start) {
			return null;
		}
		
		int mid = (start + end) / 2;
		TreeNode n = new TreeNode(arr[mid]);
		n.left = createMinimalBST(arr, start, mid - 1);
		n.right = createMinimalBST(arr, mid + 1, end);
		return n;
	}
	
	public static void main(String[] args) {
		int[] array = {Integer.MIN_VALUE, Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE};
		TreeNode node = createMinimalBST(array);
		System.out.println(checkBST2(node));
	}
}
