package org.algorithm;

/**
 *	Implements KMP algorithm 
 */
public class SubstringSearch1 {
	
	public static int[] genTable(String pattern) {
		int[] table = new int[pattern.length()];
		int i = 0;// an index of analyzing symbol in pattern
		int j = 1;// offset to search from
		while (j < pattern.length()){
			if (pattern.charAt(i) == pattern.charAt(j)) {
				table[j] = i+1;// search next symbol from i+1
				i++;
			}else {
				i = 0;
			}
			j++;
		}
		print(table);
		return table;
	}
	
	public static int find(String source, String pattern) {
		int[] table = genTable(pattern);
		// pattern searching
		int from = 0;
		for (int s = 0; s <= source.length() - pattern.length(); s++) {
			boolean match = true;
			int idx = s;
			for (int p = from; p < pattern.length(); p++) {
				if (source.charAt(idx) != pattern.charAt(p)) {
					match = false;
					from = from == 0 ? table[p] : 0;
					break;
				}
				idx++;
			}
			if (match) {
				return s;
			}
		}
		
		
		return -1;
	}
	
	public static void print(int[] vector) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < vector.length; i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(vector[i]);
		}
		sb.append("]");
		System.out.println(sb.toString());
	}

    
	public static void main(String[] arg) {
		//  0 1 2 3 4 5 6
		//  c o r d c o r
		// [0,0,0,0,1,2,3]
		int result = find("co", "coracor");
//		int result = find("acoracoryaacor", "coracor");
		System.out.println("result="+result);
		
	}
}
