/*
4.8 Leetcode: lowest common ancestor
*/
public class Question4_8 {
	// SOL 1 balanced tree time O((log N)^2), unbalanced tree time O(N^2)
	public static TreeNode commonAncestor1(TreeNode p, TreeNode q) {
		if (p == q) {
			return null;
		}
		
		TreeNode ancestor = p;
		while (ancestor != null) {
			if (isOnPath(ancestor, q)) {
				return ancestor;
			}
			ancestor = ancestor.parent;
		}
		return null;
	}
	
	public static boolean isOnPath(TreeNode ancestor, TreeNode q) {
		while (q != ancestor && q != null) {
			q = q.parent;
		}
		return q == ancestor;
	}
	// SOL 1 B
	public static TreeNode commonAncestor1B(TreeNode root, TreeNode p, TreeNode q) {
		ArrayList<TreeNode> list1 = getPath2Root(p);
		ArrayList<TreeNode> list2 = getPath2Root(q);
		int i, j;
		for (i = list1.size() - 1, j = list2.size() - 1; i >= 0 && j >= 0; i--, j--) {
			if (list1.get(i) != list2.get(j)) {
				return list1.get(i).parent;
			}
		}
		return list1.get(i + 1);
	}
	
	public static ArrayList<TreeNode> getPath2Root(TreeNode node) {
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		while (node != null) {
			list.add(node);
			node = node.parent;
		}
		return list;
	}
	// SOL 2 time O(t), t is the size of the subtree for the first common ancestor
	public static TreeNode commonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		if (!covers(root, p) || !covers(root, q)) {
			return null;
		} else if (covers(p, q)) {
			return p;
		} else if (covers(q, p)) {
			return q;
		}
		
		TreeNode sibling = getSibling(p);
		TreeNode parent = p.parent;
		while (!covers(sibling, q)) {
			sibling = getSibling(parent);
			parent = parent.parent;
		}
		return parent;
	}
	
	public static boolean covers(TreeNode root, TreeNode node) {
		if (root == null) return false;
		if (root == node) return true;
		return covers(root.left, node) || covers(root.right, node);
	}
	
	public static TreeNode getSibling(TreeNode node) {
		if (node == null || node.parent == null) {
			return null;
		}
		TreeNode parent = node.parent;
		return parent.left == node ? parent.right : parent.left;
	}
    // SOL 3 time O(n) 2n / 2 + 2n / 4 + ... -> n		
	public static TreeNode commonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		boolean is_p_on_left = covers(root.left, p);
		boolean is_q_on_left = covers(root.left, q);
		if (is_p_on_left != is_q_on_left) { // Nodes are on different side
			return root;
		}
		TreeNode child_side = is_p_on_left ? root.left : root.right;
		return commonAncestorHelper(child_side, p, q);
	}
	
	public static TreeNode commonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
		if (!covers(root, p) || !covers(root, q)) { 
			return null;
		}
		return commonAncestorHelper(root, p, q);
	}	
	// SOL 4
	public static TreeNode commonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
		Result r = commonAncHelper(root, p, q);
		if (r.isAncestor) {
			return r.node;
		}
		return null;
	}
	
	public static Result commonAncHelper(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return new Result(null, false);
		}
		if (root == p && root == q) {
			return new Result(root, true);
		}
		
		Result rx = commonAncHelper(root.left, p, q);
		if (rx.isAncestor) {
			return rx;
		}
		
		Result ry = commonAncHelper(root.right, p, q);
		if (ry.isAncestor) {
			return ry;
		}
		
		if (rx.node != null && ry.node != null) {
			return new Result(root, true);
		} else if (root == p || root == q) {
			boolean isAncestor = rx.node != null || ry.node != null;
			return new Result(root, isAncestor);
		} else {
			return new Result(rx.node != null ? rx.node : ry.node, false);
		}
	}
	
	public static class Result {
		public TreeNode node;
		public boolean isAncestor;
		public Result(TreeNode n, boolean isAnc) {
			node = n;
			isAncestor = isAnc;
		}
	}
	// SOL 5 
	public static TreeNode commonAncestor5(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) {
			return root;
		}
		
		TreeNode left = commonAncestor5(root.left, p, q);
		TreeNode right = commonAncestor5(root.right, p, q);
		if (left != null && right != null) {
			return root;
		}
		if (left != null) {
			return left;
		}
		if (right != null) {
			return right;
		}
		return null;
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
		TreeNode tmp1 = createMinimalBST(arr, start, mid - 1);
		n.left = tmp1;
		if (tmp1 != null) {
			tmp1.parent = n;
		}
		TreeNode tmp2 = createMinimalBST(arr, mid + 1, end);
		n.right = tmp2;
		if (tmp2 != null) {
			tmp2.parent = n;
		}
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
		TreeNode n3 = find(10, root);
		TreeNode n7 = find(6, root);
		TreeNode n0 = new TreeNode(100);
		TreeNode ancestor = commonAncestor4(root, n3, n0);
		if (ancestor != null) {
			System.out.println(ancestor.val);
		} else {
			System.out.println("null");
		}
	}
}
