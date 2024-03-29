/*
1.4
*/
public class ReplaceSpaceSol {
	public static String replacesSpaces(char[] str, int length) {
		int spaceCount = 0;
		int newLength, i;
		
		for (i = 0; i < length; i++) {
			if (str[i] == ' ') {
				spaceCount++;
			}
		}
		
		newLength = length + spaceCount * 2;
		str[newLength] = '\0';
		for (i = length - 1; i >= 0; i--) {
			if (str[i] == ' ') {
				str[newLength - 1] = '0';
				str[newLength - 2] = '2';
				str[newLength - 3] = '%';
				newLength = newLength - 3;
			} else {
				str[newLength - 1] = str[i];
				newLength--;
			}
		}
		return Arrays.toString(str);
	}
	
	public static void main(String[] args) {
		String str = "um a s s";
		char[] arr = new char[str.length() + 3 * 2 + 1];
		for (int i = 0; i < str.length(); i++) {
			arr[i] = str.charAt(i);
		}
		System.out.print(replacesSpaces(arr, str.length()));
	}
}

/*
 * outputs:
 * [u, m, %, 2, 0, a, %, 2, 0, s, %, 2, 0, s,  ]
 */
