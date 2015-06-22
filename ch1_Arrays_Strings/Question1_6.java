/*
1.6
*/
/*
In other contexts there is a difference between ++i and i++, but not for loops.
*/
public class RotateSol {
	// O(N^2) time
	// matrix[i][j] i: rows, j: columns
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

		System.out.println();
		int[][] test = {{4, 2, 1, 3}, {1, 3, 2, 4}, {3, 1, 2, 4}, {2, 4, 1, 3}};
		for (int i = 0; i < test.length; i++) {
			for (int j = 0; j < test[i].length; j++) {
				if (test[i][j] < 10 && test[i][j] > -10) {
					System.out.print(" ");
				}
				if (test[i][j] < 100 && test[i][j] > -100) {
					System.out.print(" ");
				}
				if (test[i][j] >= 0) {
					System.out.print(" ");
				}
				System.out.print(" " + test[i][j]);
			}
		    System.out.println();
		}
		rotate(test, 4);
		System.out.println();
		for (int i = 0; i < test.length; i++) {
			for (int j = 0; j < test[i].length; j++) {
				if (test[i][j] < 10 && test[i][j] > -10) {
					System.out.print(" ");
				}
				if (test[i][j] < 100 && test[i][j] > -100) {
					System.out.print(" ");
				}
				if (test[i][j] >= 0) {
					System.out.print(" ");
				}
				System.out.print(" " + test[i][j]);
			}
			System.out.println();
		}
		
	}
}
