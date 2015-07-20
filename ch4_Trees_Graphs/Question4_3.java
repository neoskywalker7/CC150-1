/*
4.3
*/
public class Question4_3 {
	// DFS time O(N)
	public static void createLevelLinkedList1(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level) {
		if (root == null) {
			return;
		}
		
		LinkedList<TreeNode> list = null;
		if (lists.size() == level) {
			list = new LinkedList<TreeNode>();
			lists.add(list);
		} else {
			list = lists.get(level);
		}
		list.add(root);
		createLevelLinkedList1(root.left, lists, level + 1);
		createLevelLinkedList1(root.right, lists, level + 1);
	}
	
	public static ArrayList<LinkedList<TreeNode>> createLevelLinkedList1(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
		createLevelLinkedList1(root, lists, 0);
		return lists;
	}
	// BFS time O(N)
	public static ArrayList<LinkedList<TreeNode>> createLevelLinkedList2(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> rst = new ArrayList<LinkedList<TreeNode>>();
		LinkedList<TreeNode> current = new LinkedList<TreeNode>();
		if (root != null) {
			current.add(root);
		}
		
		while (current.size() > 0) {
			rst.add(current);
			LinkedList<TreeNode> parents = current;
			current = new LinkedList<TreeNode>();
			for (TreeNode parent : parents) {
				if (parent.left != null) {
					current.add(parent.left);
				}
				if (parent.right != null) {
					current.add(parent.right);
				}
			}
		}
		return rst;
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
	
	public static void printResult(ArrayList<LinkedList<TreeNode>> rst) {
		int depth = 0;
		for (LinkedList<TreeNode> entry : rst) {
			Iterator<TreeNode> i = entry.listIterator();
			System.out.print("Linked List at depth " + depth + ":");
			while(i.hasNext()) {
				System.out.print(" " + ((TreeNode)i.next()).val);
			}
			System.out.println();
			depth++;
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
	
	public static void main(String[] args) {
		int[] nodes_flattened = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = createTreeFromArray(nodes_flattened);
		ArrayList<LinkedList<TreeNode>> list = createLevelLinkedList2(root);
		printResult(list);
	}
}
/*
 * outputs:
 * Linked List at depth 0: 1
 * Linked List at depth 1: 2 3
 * Linked List at depth 2: 4 5 6 7
 * Linked List at depth 3: 8 9 10
 * 
 */
