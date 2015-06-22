/*
1.7
*/
public class SetZeroSol {
	// O(N) space
	public static void setZeros1(int[][] matrix) {
		boolean[] row = new boolean[matrix.length];
		boolean[] column = new boolean[matrix[0].length];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					row[i] = true;
					column[j] = true;
				}
			}
		}
		
		for (int i = 0; i < row.length; i++) {
			if (row[i]) nullifyRow(matrix, i);
		}
		
		for (int j = 0; j < column.length; j++) {
			if (column[j]) nullifyColumn(matrix, j);
		}
	}
	
	public static void nullifyRow(int[][] matrix, int row) {
		for (int j = 0; j < matrix[0].length; j++) {
			matrix[row][j] = 0;
		}
	}
	
	public static void nullifyColumn(int[][] matrix, int col) {
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][col] = 0;
		}
	}
	// O(1) space
	public static void setZeros2(int[][] matrix) {
		boolean rowHasZero = false;
		boolean colHasZero = false;
		// Check if first row has a zero
		for (int j = 0; j < matrix[0].length; j++) {
			if (matrix[0][j] == 0) {
				rowHasZero = true;
				break;
			}
		}
		// Check if first column has a zero
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				colHasZero = true;
				break;
			}
		}
		// Check for zeros in the rest of the array
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		
		for (int i = 1; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				nullifyRow(matrix, i);
			}
		}
		
		for (int j = 1; j < matrix[0].length; j++) {
			if (matrix[0][j] == 0) {
				nullifyColumn(matrix, j);
			}
		}
		
		if (rowHasZero) {
			nullifyRow(matrix, 0);
		}
		
		if (colHasZero) {
			nullifyColumn(matrix, 0);
		}
	}
	
	public static void main(String[] args) {
		int M = 5;
		int N = 7;
		int[][] test = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {				
				test[i][j] = (int) (Math.random() * (6 + 1 - 0)) + 0;
			}
		}
		for (int i = 0; i < test.length; i++) {
			for (int j = 0; j < test[i].length; j++) {
				System.out.print(" " + test[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		setZeros1(test);
		for (int i = 0; i < test.length; i++) {
			for (int j = 0; j < test[i].length; j++) {
				System.out.print(" " + test[i][j]);
			}
			System.out.println();
		}
	}
}
