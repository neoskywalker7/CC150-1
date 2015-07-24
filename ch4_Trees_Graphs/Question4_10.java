/*
4.10
*/
// time O(nm) space O(log(n) + log(m))
public class Question4_10 {
	public static boolean containsTree(TreeNode t1, TreeNode t2) {
		if (t2 == null) {
			return true;
		}
		return subTree(t1, t2);
	}
	
	public static boolean subTree(TreeNode r1, TreeNode r2) {
		if (r1 == null) {
			return false;
		} else if (r1.val == r2.val && matchTree(r1, r2)) {
			return true;
		}
		return (subTree(r1.left, r2) || subTree(r1.right, r2));
	}
	
	public static boolean matchTree(TreeNode r1, TreeNode r2) {
		if (r2 == null && r1 == null) {
			return true;
		} else if (r1 == null || r2 == null) {
			return false;
		} else if (r1.val != r2.val) {
			return false;
		} else {
			return (matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right));
		}
	}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int x) {
			val = x;
		}
	}
	
	public static TreeNode createTreeFromArray(int[] array) {
		if (array.length > 0) {
			TreeNode root = new TreeNode(array[0]);
			Queue<TreeNode> queue = new LinkedList<TreeNode>();
			queue.add(root);
			boolean done = false;
			int i = 1;
			while (!done) {
				TreeNode r = (TreeNode) queue.element();
				if (r.left == null) {
					r.left = new TreeNode(array[i]);
					i++;
					queue.add(r.left);
				} else if (r.right == null) {
					r.right = new TreeNode(array[i]);
					i++;
					queue.add(r.right);
				} else {
					queue.remove();
				}
				if (i == array.length) {
					done = true;
				}
			}
			return root;
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) {
		int[] array1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		int[] array2 = {2, 4, 5, 8, 9, 10, 11};
		TreeNode t1 = createTreeFromArray(array1);
		TreeNode t2 = createTreeFromArray(array2);
		
		if (containsTree(t1, t2)) {
			System.out.println("t2 is a subtree of t1");
		} else {
			System.out.println("t2 is not a subtree of t1");
		}
		
		int[] array3 = {1, 2, 3};
		TreeNode t3 = createTreeFromArray(array1);
		TreeNode t4 = createTreeFromArray(array3);
		
		if (containsTree(t3, t4)) {
			System.out.println("t4 is a subtree of t3");
		} else {
			System.out.println("t4 is not a subtree of t3");
		}
	}
}
