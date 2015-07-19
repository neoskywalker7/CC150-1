/*
3.4 LeetCode: Implement Queue by Two Stacks
*/
public class Question3_4 {
	public static class MyQueue<T> {
		Stack<T> stackNewest, stackOldest;
		
		public MyQueue() {
			stackNewest = new Stack<T>();
			stackOldest = new Stack<T>();
		}
		
		public int size() {
			return stackNewest.size() + stackOldest.size();
		}
		
		public void add(T value) {
			stackNewest.push(value);
		}
		
		private void shiftStacks() {
			if (stackOldest.isEmpty()) {
				while (!stackNewest.isEmpty()) {
					stackOldest.push(stackNewest.pop());
				}
			}
		}
		
		public T peek() {
			shiftStacks();
			return stackOldest.peek();
		}
		
		public T remove() {
			shiftStacks();
			return stackOldest.pop();
		}
	} 
	
	public static int randomInt(int n) {
		return (int) (Math.random() * n);
	}
	
	public static int randomIntInRange(int min, int max) {
		return randomInt(max + 1 - min) + min;
	}
	
	public static void main(String[] args) {
		MyQueue<Integer> my_queue = new MyQueue<Integer>();
		Queue<Integer> test_queue = new LinkedList<Integer>();
		
		for (int i = 0; i < 100; i++) {
			int choice = randomIntInRange(0, 10);
			if (choice <= 5) {
				int element = randomIntInRange(1, 10);
				test_queue.add(element);
				my_queue.add(element);
				System.out.println("Enqueued " + element);
			} else if (test_queue.size() > 0) {
				int top1 = test_queue.remove();
				int top2 = my_queue.remove();
				if (top1 != top2) {
					System.out.println("FAILURE - DIFFERENT TOPS: " + top1 + ", " + top2);	
				}
				System.out.println("Dequeued " + top1);
			}
			
			if (test_queue.size() == my_queue.size()) {
				if (test_queue.size() > 0 && test_queue.peek() != my_queue.peek()) {
					System.out.println("FAILURE - DIFFERENT TOPS: " + test_queue.peek() + ", " + my_queue.peek());
				}
			} else {
				System.out.println("FAILURE - DIFFERENT SIZES");
			}
		}
	}
}
