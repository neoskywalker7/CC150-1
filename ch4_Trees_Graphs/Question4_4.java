/*
4.4
*/
public class Question4_4 {
	// SOL 1 time O(N log N)
	public static int getHeight(TreeNode root) {
		if (root == null) return 0;
		return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
	}
	
	public static boolean isBalanced1(TreeNode root) {
		if (root == null) return true;
		
		int heightDiff = getHeight(root.left) - getHeight(root.right);
		if (Math.abs(heightDiff) > 1) {
			return false;
		} else {
			return isBalanced1(root.left) && isBalanced1(root.right);
		}
	}
	// SOL 2 time O(N) time O(H)
	public static int checkHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		int leftHeight = checkHeight(root.left);
		if (leftHeight == - 1) {
			return -1;
		}
		int rightHeight = checkHeight(root.right);
		if (rightHeight == - 1) {
			return -1;
		}
		
		int heightDiff = leftHeight - rightHeight;
		if (Math.abs(heightDiff) > 1) {
			return -1;
		} else {
			return Math.max(leftHeight, rightHeight) + 1;
		}
	}
	
	public static boolean isBalanced2(TreeNode root) {
		if (checkHeight(root) == - 1) {
			return false;
		} else {
			return true;
		}
	}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int x) {
			val = x;
		}
		
		public void insertInOrder(int d) {
			if (d <= val) {
				if (left == null) {
					TreeNode tmp = new TreeNode(d);
					this.left = tmp;
				} else {
					left.insertInOrder(d);
				}
			} else {
				if (right == null) {
					TreeNode tmp = new TreeNode(d);
					this.right = tmp;
				} else {
					right.insertInOrder(d);
				}
			}
		}
	}
	
	public static int randomInt(int n) {
		return (int) (Math.random() * n);
	}
	
	public static int randomIntInRange(int min, int max) {
		return randomInt(max + 1 - min) + min;
	}
	
	public static void main(String[] args) {
		TreeNode unbalanced = new TreeNode(10);
		for (int i = 0; i < 10; i++) {
			unbalanced.insertInOrder(randomIntInRange(0, 100));
		}
		System.out.println("Root? " + unbalanced.val);
		System.out.println("Is balanced? " + isBalanced1(unbalanced));
	}
}
