/*
1.2
Java strings are not terminated with a null characters as in C or C++. Although java strings uses internally the char array but there is no terminating null in that. String class provides a method called length to know the number of characters in the string.
*/
public class ReverseStrSol {
	public static String reverse1(String str) {
		int len = str.length();
		char[] list = new char[len];
		int start = 0;
		int end = len - 1;
		
		for (int i = 0; i < len; i++) {
			list[i] = str.charAt(i);
		}
		
		while (start < end) {
			char tmp = list[start];
			list[start] = list[end];
			list[end] = tmp;
			start++;
			end--;
		}
		
		return Arrays.toString(list);
	}
	
	public static String reverse2(String str) {
		int len = str.length();
		ArrayList<Character> list = new ArrayList<Character>();
		
		for (int i = 0; i < len; i++) {
			list.add(str.charAt(i));
		}
		
		Collections.reverse(list);
		return list.toString();
	}
	
	public static void main(String[] args) {
		String test = "Jun";
		System.out.println(reverse2(test));
	}
}
/*
 * output:
 * [n, u, J]
 */
