/*
4.6
*/
public class Question4_6 {
	
	public static TreeNode inorderSucc(TreeNode n) {
		if (n == null) return null;
		
		if (n.parent == null || n.right != null) {
			return leftMostChild(n.right);
		} else {
			TreeNode q = n;
			TreeNode x = q.parent;
			while (x != null && x.left != q) {
				q = x;
				x = x.parent;
			}
			return x;
		}
	}
	
	public static TreeNode leftMostChild(TreeNode n) {
		if (n == null) {
			return null;
		}
		
		while (n.left != null) {
			n = n.left;
		}
		return n;
	}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode parent;
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
	
	public static TreeNode find(int d, TreeNode root) {
		if (d == root.val) {
			return root;
		} else if (d <= root.val) {
			return root.left != null ? find(d, root.left) : null;
		} else if (d > root.val) {
			return root.right != null ? find(d, root.right) : null;
		}
		return null;
	}
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		TreeNode root = createMinimalBST(array);
		
		for (int i = 0; i < array.length; i++) {
			TreeNode node = find(array[i], root);
			TreeNode next = inorderSucc(node);
			if (next != null) {
				System.out.println(node.val + "->" + next.val);
			} else {
				System.out.println(node.val + "->" + null);
			}
		}
	}
}
