/*
3.2
*/
public class Question3_2 {
	public static class StackWithMin1 extends Stack<NodeWithMin> {
		public void push(int value) {
			int newMin = Math.min(value, min());
			super.push(new NodeWithMin(value, newMin));
		}
		
		public int min() {
			if (this.isEmpty()) {
				return Integer.MAX_VALUE;
			} else {
				return peek().min;
			}
		}
	}
	
	public static class NodeWithMin {
		public int value;
		public int min;
		public NodeWithMin(int v, int min) {
			value = v;
			this.min = min;
		}
	}
	
	public static class StackWithMin2 extends Stack<Integer> {
		Stack<Integer> s2;
		public StackWithMin2() {
			s2 = new Stack<Integer>();
		}
		
		public void push(int value) {
			if (value <= min()) {
				s2.push(value);
			}
			super.push(value);
		}
		
		public Integer pop() {
			int value = super.pop();
			if (value == min()) {
				s2.pop();
			}
			return value;
		}
		
		public int min() {
			if (s2.isEmpty()) {
				return Integer.MAX_VALUE;
			} else {
				return s2.peek();
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
		StackWithMin1 test1 = new StackWithMin1();
		StackWithMin2 test2 = new StackWithMin2();
		for (int i = 0; i < 15; i++) {
			int value = randomIntInRange(0, 100);
			test1.push(value);
			test2.push(value);
			System.out.println(value + ", ");
		}
		System.out.println('\n');
		for (int i = 0; i < 15; i ++) {
			System.out.println("Popped " + test1.pop().value + ", " + test2.pop());
			System.out.println("New min is " + test1.min() + ", " + test2.min());
		}
	}
}
/*
outputs:
40, 
52, 
76, 
15, 
10, 
25, 
27, 
54, 
42, 
54, 
37, 
16, 
47, 
56, 
87, 

Popped 87, 87
New min is 10, 10
Popped 56, 56
New min is 10, 10
Popped 47, 47
New min is 10, 10
Popped 16, 16
New min is 10, 10
Popped 37, 37
New min is 10, 10
Popped 54, 54
New min is 10, 10
Popped 42, 42
New min is 10, 10
Popped 54, 54
New min is 10, 10
Popped 27, 27
New min is 10, 10
Popped 25, 25
New min is 10, 10
Popped 10, 10
New min is 15, 15
Popped 15, 15
New min is 40, 40
Popped 76, 76
New min is 40, 40
Popped 52, 52
New min is 40, 40
Popped 40, 40
New min is 2147483647, 2147483647
*/
