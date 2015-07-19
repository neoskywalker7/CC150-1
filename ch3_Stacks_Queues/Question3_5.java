/*
3.5
*/
public class Question3_5 {
	// time O(N^2) space O(N)
	public static Stack<Integer> sort(Stack<Integer> s) {
		Stack<Integer> r = new Stack<Integer>();
		while (!s.isEmpty()) {
			int tmp = s.pop();
			while (!r.isEmpty() && r.peek() > tmp) {
				s.push(r.pop());
			}
			r.push(tmp);
		}
		return r;
	}
	// Merge Sort
	static int c = 0;
	public static Stack<Integer> mergeSort(Stack<Integer> s2) {
		if (s2.size() <= 1) {
			return s2;
		}
		
		Stack<Integer> left = new Stack<Integer>();
		Stack<Integer> right = new Stack<Integer>();
		int count = 0;
		while (s2.size() != 0) {
			count++;
			c++;
			if (count % 2 == 0) {
				left.push(s2.pop());
			} else {
				right.push(s2.pop());
			}
		}
		
		left = mergeSort(left);
		right = mergeSort(right);
		
		while (left.size() > 0 || right.size() > 0) {
			if (left.size() == 0) {
				s2.push(right.pop());
			} else if (right.size() == 0) {
				s2.push(left.pop());
			} else if (right.peek().compareTo(left.peek()) <= 0) {
				s2.push(left.pop());
			} else {
				s2.push(right.pop());
			}
		}
		
		Stack<Integer> reverseStack = new Stack<Integer>();
		while (s2.size() > 0) {
			c++;
			reverseStack.push(s2.pop());
		}
		
		return reverseStack;
	}
	// Quick Sort 
	// time O(nlogn) to sort n items, time O(n^2) in worst case
	public static Stack<Integer> quickSort(Stack<Integer> s3) {
		if (s3.isEmpty()) {
			return s3;
		}
		
		int pivot = s3.pop();
		
		Stack<Integer> left = new Stack<Integer>();
		Stack<Integer> right = new Stack<Integer>();
		while (!s3.isEmpty()) {
			int y = s3.pop();
			if (y < pivot) {
				left.push(y);
			} else {
				right.push(y);
			}
		}
		quickSort(left);
		quickSort(right);
		
		Stack<Integer> tmp = new Stack<Integer>();
		while (!right.isEmpty()) {
			tmp.push(right.pop());
		}
		tmp.push(pivot);
		while (!left.isEmpty()) {
			tmp.push(left.pop());
		}
		while (!tmp.isEmpty()) {
			s3.push(tmp.pop());
		}
		return s3;
	}
	
	
	public static int randomInt(int n) {
		return (int) (Math.random() * n);
	}
	
	public static int randomIntInRange(int min, int max) {
		return randomInt(max + 1 - min) + min;
	}
	
	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		for (int i = 0; i < 10; i++) {
			int r = randomIntInRange(0, 1000);
			test.push(r);
		}
		test = quickSort(test);
		while(!test.isEmpty()) {
			System.out.println(test.pop());
		}
	}
}
