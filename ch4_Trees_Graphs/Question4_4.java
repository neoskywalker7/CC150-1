/*
4.4
*/
/*
In the average case, for a balanced binary tree
T(n) = 2T(n/2) + Θ(1);
Every recursive call gives you two problems of half the size. By master theorem, this would evaluate to T(n) = Θ(n)
In the worst case, where each node has only one child.
T(n) = T(n-1) + Θ(1)
Which evaluates to T(n) = Θ(n)
*/
/*
T(n) = 2T(n / 2) + O(1)
     = 2(2T(n / 4) + O(1)) + O(1) = 4T(n / 4) + 2O(1)
     …
     = nT(1) + nO(1) = O(n)
*/
/*
首先如果一个root的树有n个点，求height是O(n) ->每个点走一遍
然后看第7行主程序，设复杂度的是f(n)，那么有：
f(n) = 2 * n/2 + 2*f(n/2)  --> 第一个2 * n/2是第10行求两个子树的高度, f(n/2) 是14行的recursion
      = n + 2 * (2 * n / 4 + 2 * f(n/4))
      = n + n + 4 * f(n/4) = ... = O(nlogn)
*/
public class Question4_4 {
	// SOL 1 time O(N log N) ? O(N^2)
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
