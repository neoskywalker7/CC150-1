/*
1.1
*/
/*
1. unicode or ascii?
*/
/*
ASCII码的取值范围是0~127, 可以用7个bit表示, char型变量的大小规定为一字节, 如果存放ASCII码则只用到低7位, 高位为0,
绝大多数计算机的一个字节是8位, 取值范围是0~255, 而ASCII码并没有规定编号为128~255的字符, 
为了能表示更多字符, 各厂商制定了很多种ASCII码的扩展规范. 注意, 虽然通常把这些规范称为扩展ASCII码(Extended ASCII), 但其实它们并不属于ASCII码标准. 
例如扩展ASCII码由IBM制定, 在字符终端下被广泛采用, 其中包含了很多表格边线字符用来画界面
Thus, there are in fact 256 possible unique characters for one char.

带符号的char类型取值范围是-128~127(01111111)
计算机里面所有数都是用补码表示, 负数补码是原码的反码加1
-127 (11111111) -> 10000000 -> 10000001
任何⼀一个原码都不可能在转成补码时变成10000000, 人为规定-128

Unicode defines (less than) 221 characters, which, similarly, map to numbers 0–221 (though not all numbers are currently assigned, and some are reserved).
Unicode is a superset of ASCII. Because Unicode characters don't generally fit into one 8-bit byte, there are numerous ways of storing Unicode characters in byte sequences, such as UTF-32 and UTF-8. 
*/
public class UniCharSol {
	// time O(n) space O(1)
	public static boolean isUniqueChars1(String str) {
		if (str.length() > 256) {
			return false;
		}
		
		boolean[] char_set = new boolean[256];
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i);
			if (char_set[val]) {
				return false;
			}
			char_set[val] = true;
		}
		return true;
	}
	// time O(n) space more reduced
	public static boolean isUniqueChars2(String str) {
		int checker = 0;
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0) {
				return false;
			}
			checker |= (1 << val);
		}
		return true;
    }
	
	public static void main(String[] args) {
		String test = "I love Wuhan City 我爱武汉";
		System.out.print(isUniqueChars2(test));
	}
}

