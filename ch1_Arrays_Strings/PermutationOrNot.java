/*
1.3
*/
/*
1. case sensitive or not? God vs dog?
2. whitespace is significant or not?
assume yes
*/
public class PermutationJudgeSol {
	public static boolean permutation1(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		
		return sort(s).equals(sort(t));
	}
	
	private static String sort(String s) {
		char[] content = s.toCharArray();
		Arrays.sort(content);
		return new String(content);
	}
	
	public static boolean permutation2(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		
		int[] letters = new int[256];
		char[] s_array = s.toCharArray();
		for (char c : s_array) {
			letters[c]++;
		}
		
		for (int i = 0; i < t.length(); i++) {
			int c = (int) t.charAt(i);
			if (--letters[c] < 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.print(permutation2("God ", "dog"));
	}
}
