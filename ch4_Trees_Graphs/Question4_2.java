/*
4.2
*/
public class Question4_2 {
	
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
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int val) {
			this.val = val;
			left = null;
			right = null;
		}
		
		public boolean isBST() {
			if (left != null) {
				if (val < left.val || !left.isBST()) {
					return false;
				}
			}
			
			if (right != null) {
				if (val >= right.val || !right.isBST()) {
					return false;
				}
			}
			
			return true;
		}
		
		public int height() {
			int leftHeight = left != null ? left.height() : 0;
			int rightHeight = right != null ? right.height() : 0;
			return 1 + Math.max(leftHeight, rightHeight);
		}
	}
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		TreeNode root = createMinimalBST(array);
		System.out.println("Root? " + root.val);
		System.out.println("Created BST? " + root.isBST());
		System.out.println("Height: " + root.height());
	}
}
