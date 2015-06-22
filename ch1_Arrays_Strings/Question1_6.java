/*
1.6
*/
/*
In other contexts there is a difference between ++i and i++, but not for loops.
*/
public class RotateSol {
	// O(N^2) time
	public static void rotate(int[][] matrix, int n) {
		for (int layer = 0; layer < n / 2; layer++) {
			int first = layer;
			int last = n - 1 - layer;
			
			for (int i = first; i < last; i++) {
				int offset = i - first;
				
				int top = matrix[first][i];
				
				matrix[first][i] = matrix[last - offset][first];
				
				matrix[last - offset][first] = matrix[last][last - offset];
				
				matrix[last][last - offset] = matrix[i][last];
				
				matrix[i][last] = top;
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = AssortedMethods.randomMatrix(4, 4, 0, 3);
		AssortedMethods.printMatrix(matrix);
		rotate(matrix, 4);
		System.out.println();
		AssortedMethods.printMatrix(matrix);
	}
}
