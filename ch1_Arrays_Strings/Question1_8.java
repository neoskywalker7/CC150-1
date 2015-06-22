/*
1.8
*/
/*
int indexOf(int ch): Returns the index within this string of the first occurrence of the specified character or -1 if the character does not occur.
int indexOf(int ch, int fromIndex): Returns the index within this string of the first occurrence of the specified character, starting the search at the specified index or -1 if the character does not occur.
int indexOf(String str): Returns the index within this string of the first occurrence of the specified substring. If it does not occur as a substring, -1 is returned.
int indexOf(String str, int fromIndex): Returns the index within this string of the first occurrence of the specified substring, starting at the specified index. If it does not occur, -1 is returned.
*/
public class IsRotationSol {
	public static boolean isRotation(String s1, String s2) {
		int len = s1.length();
		
		if (len == s2.length() && len > 0) {
			String s1s1 = s1 + s1;
			return isSubstring(s1s1, s2);
		}
		return false;
	}
	
	private static boolean isSubstring(String big, String small) {
		if (big.indexOf(small) >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.print(isRotation("xy", "yx"));
	}
}

