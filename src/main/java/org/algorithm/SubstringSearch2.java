package org.algorithm;

/**
 *	Implements Substring Search algorithm  using hashes
 */
public class SubstringSearch2 {
	
	public static int BASE = 13;
	public static int MAXBASE = 1;
	
	public static int hash(String str) {
		int hash = 0;
		int base = 1;
		for (int i = 0; i < str.length(); i++) {
			hash += base*str.charAt(i);
			base = base*BASE;
		}
		return hash;
	}
	
	public static int find(String source, String pattern) {
		int pos = -1;
		
		int patternHash = hash(pattern);
		int sourceHash = hash(source.substring(0, pattern.length()));
		
		if (patternHash == sourceHash) {
			return 0;
		}
		
		int firstSymbol = 0;
		int offset = pattern.length();
		int MAXBASE = (int) Math.pow(BASE, pattern.length()-1);
		System.out.println("base="+MAXBASE);
		
		for (int i = offset; i <= source.length() - offset; i++) {
			// extract next symbol
			int next = source.charAt(i);
			sourceHash = sourceHash - source.charAt(firstSymbol);
			sourceHash = sourceHash/BASE + next*MAXBASE;
			firstSymbol++;
			if (patternHash == sourceHash) {
				return firstSymbol;
			}
		}
		
		return pos;
	}

	public static void main(String[] arg) {
		System.out.println("hash of a = "+hash("a"));
		int result = find("acoracordyaacor", "cordy");
		System.out.println("result="+result);
	}
}
