/*
3.1 B
*/
public class Question3_1_B {
	
	public static class StackData {
		public int start;
		public int pointer;
		public int size;
		public int capacity;
		public StackData(int _start, int _capacity) {
			start = _start;
			pointer = _start - 1;
			capacity = _capacity;
		}
		
		public boolean isWithinStackCapacity(int index, int total_size) {
			if (start <= index && index < start + capacity) {
				return true;
			} else  if (start + capacity > total_size && index < (start + capacity) % total_size) {
				return true;
			}
			return false;
		}
	}
	
	static int number_of_stacks = 3;
	static int default_size = 4;
	static int total_size = default_size * number_of_stacks;
    static StackData[] stacks = {new StackData(0, default_size), 
    	new StackData(default_size, default_size), 
    	new StackData(default_size * 2, default_size)};
	static int[] buffer = new int[total_size];
	
	public static int numberOfElements() {
		return stacks[0].size + stacks[1].size + stacks[2].size;
	} 
	
	public static int nextElement(int index) {
		if (index + 1 == total_size) {
			return 0;
		} else {
			return index + 1;
		}
	}
	
	public static int previousElement(int index) {
		if (index == 0) {
			return total_size - 1;
		} else {
			return index - 1;
		}
	}
	
	public static void shift(int stackNum) {
		System.out.println("/// Shifting " + stackNum);
		StackData stack = stacks[stackNum];
		
		if (stack.size >= stack.capacity) {
			int nextStack = (stackNum + 1) % number_of_stacks;
			shift(nextStack);
			stack.capacity++;
		}
		
		for (int i = (stack.start + stack.capacity - 1) % total_size;
				stack.isWithinStackCapacity(i, total_size);
				i = previousElement(i)) {
			buffer[i] = buffer[previousElement(i)];
		}

		buffer[stack.start] = 0;
		stack.start = nextElement(stack.start);
		stack.pointer = nextElement(stack.pointer);
		stack.capacity--;
	}
	
	public static void expand(int stackNum) {
		shift((stackNum + 1) % number_of_stacks);
		stacks[stackNum].capacity++;
	}
	
	public static void push(int stackNum, int value) throws Exception {
		
		StackData stack = stacks[stackNum];
		
		if (stack.size >= stack.capacity) {
			if (numberOfElements() >= total_size) {
				throw new Exception("Out of space.");
			} else {
				expand(stackNum);
			}
		}
		
		stack.size++;
		stack.pointer = nextElement(stack.pointer);
		buffer[stack.pointer] = value;
	}
	
	public static int pop(int stackNum) throws Exception {
		StackData stack = stacks[stackNum];
		if (stack.size == 0) {
			throw new Exception("Trying to pop an empty stack.");
		}
		
		int value = buffer[stack.pointer];
		buffer[stack.pointer] = 0;
		stack.pointer = previousElement(stack.pointer);
		stack.size--;
		return value;
	}
	
	public static int peek(int stackNum) {
		StackData stack = stacks[stackNum];
		return buffer[stack.pointer];
	}
	
	public static boolean isEmpty(int stackNum) {
		StackData stack = stacks[stackNum];
		return stack.size == 0;
	}
	
	public static String arrayToString(int[] array) {
		StringBuilder sb = new StringBuilder();
		for (int v : array) {
			sb.append(v + ",");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		
		push(0, 10);
		push(1, 20);
		push(2, 30);
		push(1, 21);
		push(0, 11);
		push(0, 12);
		pop(0);
		push(2, 31);
		push(0, 13);
		push(1, 22);
		push(2, 31);
		push(2, 32);
		push(2, 33);
		push(2, 34);
		System.out.println("Final Stack: " + arrayToString(buffer));
		pop(1);
		push(2, 35);
		System.out.println("Final Stack: " + arrayToString(buffer));
	}
}
/*
output:
/// Shifting 0
/// Shifting 0
/// Shifting 1
Final Stack: 33, 34, 10, 11, 13, 20, 21, 22, 30, 31, 31, 32, 
/// Shifting 0
/// Shifting 1
Final Stack: 33, 34, 35, 10, 11, 13, 20, 21, 30, 31, 31, 32, 
*/
