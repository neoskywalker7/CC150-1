/*
1.8
*/
/*
 * int indexOf(String str): Returns the index within this string of the first occurrence of the specified substring. 
 * If it does not occur as a substring, -1 is returned.
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

