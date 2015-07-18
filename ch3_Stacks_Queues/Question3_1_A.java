/*
3.1 A
*/
/*
throw和throws的区别:
1. throw代表动作, 表示抛出一个异常的动作; throws代表一种状态, 代表方法可能有异常抛出
2. throw用在方法实现中, 而throws用在方法声明中
3. throw只能用于抛出一种异常, 而throws可以抛出多个异常
throws是用来声明一个方法可能抛出的所有异常信息
throw则是指抛出的一个具体的异常类型
通常在一个方法(类)的声明处通过throws声明方法(类)可能抛出的异常信息, 而在方法(类)内部通过throw声明一个具体的异常信息,
throws通常不用显示的捕获异常, 可由系统自动将所有捕获的异常信息抛给上级方法,
throw则需要用户自己捕获相关的异常, 而后在对其进行相关包装, 最后在将包装后的异常信息抛.
*/
public class Question3_1_A {
	public static class FixedMultiStack1 {
		private int numberOfStacks = 3;
		private int stackCapacity;
		private int[] values;
		private int[] sizes;
		
		public FixedMultiStack1(int stackSize) {
			stackCapacity = stackSize;
			values = new int[stackSize * numberOfStacks];
			sizes = new int[numberOfStacks];
		}
		
		public void push(int stackNum, int value) throws FullStackException {
			if (isFull(stackNum)) {
				throw new FullStackException();
			}
			
			sizes[stackNum]++;
			values[indexOfTop(stackNum)] = value;
		}
		
		public int pop(int stackNum) throws Exception {
			if (isEmpty(stackNum)) {
				throw new EmptyStackException();
			}
			
			int topIndex = indexOfTop(stackNum);
			int value = values[topIndex];
			values[topIndex] = 0;
			sizes[stackNum]--;
			return value;
		}
		
		public int peek(int stackNum) {
			if (isEmpty(stackNum)) {
				throw new EmptyStackException();
			}
			return values[indexOfTop(stackNum)];
		}
		
		public boolean isFull(int stackNum) {
			return sizes[stackNum] == stackCapacity;
		}
		
		public boolean isEmpty(int stackNum) {
			return sizes[stackNum] == 0;
		}
		
		private int indexOfTop(int stackNum) {
			int offset = stackNum * stackCapacity;
			int size = sizes[stackNum];
			return offset + size - 1;
		}
	}
	
	public static class FullStackException extends Exception {
		
		private static final long serialVersionUID = 1L;
		
		public FullStackException() {
			super();
		}
		
		public FullStackException(String message) {
			super(message);
		}
	}
	
	static int stackSize = 10;
	// static int[] buffer = new int[stackSize * 3];
	// static int[] stackPointer = {-1, -1, -1};
	
	public static void main(String[] args) throws Exception {
		FixedMultiStack1 test = new FixedMultiStack1(stackSize);
		test.push(2, 4);
		System.out.println("Peek 2: " + test.peek(2));
		test.push(0, 3);
		test.push(0, 7);
		test.push(0, 5);
		System.out.println("Peek 0: " + test.peek(0));
		test.pop(0);
		System.out.println("Peek 0: " + test.peek(0));
		test.pop(0);
		System.out.println("Peek 0: " + test.peek(0));
	}
}
