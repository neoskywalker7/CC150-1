/*
4.11
*/
public class Question4_11 {
	// SOL 1 time O(logN)
	public static class TreeNode1 {
		private int data;
		public TreeNode1 left;
		public TreeNode1 right;
		private int size = 0;
		
		public TreeNode1(int d) {
			data = d;
			size = 1;
		}
		
		public TreeNode1 getRandomNode() {
			int leftSize = left == null ? 0 : left.size();
			Random random = new Random();
			int index = random.nextInt(size);
			if (index < leftSize) {
				return left.getRandomNode();
			} else if (index == leftSize) {
				return this;
			} else {
				return right.getRandomNode();
			}
		}
		
		public void insertInOrder(int d) {
			if (d <= data) {
				if (left == null) {
					left = new TreeNode1(d);
				} else {
					left.insertInOrder(d);
				}
			} else {
				if (right == null) {
					right = new TreeNode1(d);
				} else {
					right.insertInOrder(d);
				}
			}
			size++;
		}
		
		public int size() {
			return size;
		}
		
		public int data() {
			return data;
		}
		
		public TreeNode1 find(int d) {
			if (d == data) {
				return this;
			} else if (d <= data) {
				return left != null ? left.find(d) : null;
			} else if (d > data) {
				return right != null ? right.find(d) : null;
			}
			return null;
		}
	}
	// SOL 2 time O(D)
	public static class Tree2 {
		TreeNode2 root = null;
		
		public int size() {
			return root == null ? 0 : root.size();
		}
		
		public TreeNode2 getRandomNode() {
			if (root == null) return null;
			
			Random random = new Random();
			int i = random.nextInt(size());
			return root.getIthNode(i);
		}
		
		public void insertInOrder(int value) {
			if (root == null) {
				root = new TreeNode2(value);
			} else {
				root.insertInOrder(value);
			}
		}
	}
	
	public static class TreeNode2 {
		private int data;
		public TreeNode2 left;
		public TreeNode2 right;
		private int size = 0;
		
		public TreeNode2(int d) {
			data = d;
			size = 1;
		}
		
		public TreeNode2 getIthNode(int i) {
			int leftSize = left == null ? 0 : left.size();
			if (i < leftSize) {
				return left.getIthNode(i);
			} else if (i == leftSize) {
				return this;
			} else {
				return right.getIthNode(i - (leftSize + 1));
			}
		}
		
		public void insertInOrder(int d) {
			if (d <= data) {
				if (left == null) {
					left = new TreeNode2(d);
				} else {
					left.insertInOrder(d);
				}
			} else {
				if (right == null) {
					right = new TreeNode2(d);
				} else {
					right.insertInOrder(d);
				}
			}
			size++;
		}
		
		public int size() {
			return size;
		}
		
		public TreeNode2 find(int d) {
			if (d == data) {
				return this;
			} else if (d <= data) {
				return left != null ? left.find(d) : null;
			} else if (d > data) {
				return right != null ? right.find(d) : null;
			}
			return null;
		}
	}
}
