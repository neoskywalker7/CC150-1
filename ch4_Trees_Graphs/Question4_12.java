/*
4.12
*/
public class Question4_12 {
	// SOL 1 Brute Force balanced time O(N log N), unbalanced time O(N^2)
	public static int countPathsWithSum1(TreeNode root, int targetSum) {
		if (root == null) return 0;
		
		int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);
		
		int pathsOnLeft = countPathsWithSum1(root.left, targetSum);
		int pathsOnRight = countPathsWithSum1(root.right, targetSum);
		
		return pathsFromRoot + pathsOnLeft + pathsOnRight;
	}
	
	public static int countPathsWithSumFromNode(TreeNode node, int targetSum, int currentSum) {
		if (node == null) return 0;
		
		currentSum += node.val;
		
		int totalPaths = 0;
		if (currentSum == targetSum) {
			totalPaths++;
		}
		
		totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum);
		totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum);
		return totalPaths;
	}
	// SOL 2 DFS + HashMap time O(N)
	public static int countPathsWithSum2(TreeNode root, int targetSum) {
		if (root == null) return 0;
		HashMap<Integer, Integer> pathCount = new HashMap<Integer, Integer>();
		incrementHashTable(pathCount, 0, 1);
		return countPathsWithSum2(root, targetSum, 0, pathCount);
	}
	
	public static int countPathsWithSum2(TreeNode node, int targetSum, int runningSum, HashMap<Integer, Integer> pathCount) {
		if (node == null) return 0;
		
		runningSum += node.val;
		incrementHashTable(pathCount, runningSum, 1);
		
		int sum = runningSum - targetSum;
		int totalPaths = pathCount.containsKey(sum) ? pathCount.get(sum) : 0;
		
		totalPaths += countPathsWithSum2(node.left, targetSum, runningSum, pathCount);
		totalPaths += countPathsWithSum2(node.right, targetSum, runningSum, pathCount);
		
		incrementHashTable(pathCount, runningSum, -1);
		return totalPaths;
	}
	
	public static void incrementHashTable(HashMap<Integer, Integer> hashTable, int key, int delta) {
		if (!hashTable.containsKey(key)) {
			hashTable.put(key, 0);
		}
		hashTable.put(key, hashTable.get(key) + delta);
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
		TreeNode n1 = new TreeNode(5);
		TreeNode n2 = new TreeNode(3);
		TreeNode n3 = new TreeNode(1);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(8);
		TreeNode n6 = new TreeNode(2);
		TreeNode n7 = new TreeNode(6);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		n3.right = n7;
		System.out.print(countPathsWithSum2(n1, 8));
	}
}
