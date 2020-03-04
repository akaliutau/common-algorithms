package org.algorithm;

import static org.algorithm.utils.Utils.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LCS1 {
	
	public static class Pair {
		int i;
		int j;
		public Pair (int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	public static List<String> lcs(String s1, String s2){
		s1 = " "+s1;
		s2 = " "+s2;
		int n = s1.length();
		int m = s2.length();
		int maxlen = 0;
		int[][] table = new int[n][m];
		String[] str1 = new String[n];
		String[] str2 = new String[m];
		Map<String,String> found = new HashMap<>();
		
		// init table and string arrays
		for (int i = 0; i < n; i++) {
			table[i][0] = 0;
			str1[i] = String.valueOf(s1.charAt(i));
		}
		for (int j = 0; j < m; j++) {
			table[0][j] = 0;
			str2[j] = String.valueOf(s2.charAt(j));
		}
		
		// fill table with data
		for (int i = 1; i < n; i++) {//line, also index in s1
			for (int j = 1; j < m; j++) {//column, also index in s2
				if (str1[i].equals(str2[j])) {
					int len = 1 + table[i-1][j-1];
				    maxlen = len > maxlen ? len : maxlen;
					table[i][j] = len;
					// save also the value itself 
					found.put(i + "#" + j, str1[i]);
				}else {
					table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
				}
			}
		}
		System.out.println("maxlen="+maxlen);
		print(str1);
		print(str2);
		System.out.println(found);
		
		// extract all found sequences, starting from the lower right corner (2 directions)
		List<String> results = new ArrayList<>();
		if (maxlen == 0) {
			return results;
		}
		//scan all boundary cells with value=maxlen
		String res1 = "";
		String res2 = "";
		int i = n-1;
		int j = m-1;
		while (table[i][j] != 0) {
			if (table[i-1][j] != table[i][j] && table[i][j-1] != table[i][j]) {
				String key = i + "#" +j;
				if (found.containsKey(key)) {
					res2 = found.get(key)+res2;
				}
				i--;
				j--;
			}
			if (i == 0 || j == 0) continue;
			if (table[i-1][j] == table[i][j]) {
				i--;
			}else if (table[i][j-1] == table[i][j]){
				j--;
			}
		}
		i = n-1;
		j = m-1;
		while (table[i][j] != 0) {
			if (table[i-1][j] != table[i][j] && table[i][j-1] != table[i][j]) {
				String key = i + "#" +j;
				if (found.containsKey(key)) {
					res1 = found.get(key)+res1;
				}
				i--;
				j--;
			}
			if (i == 0 || j == 0) continue;
			if (table[i][j-1] == table[i][j]){
				j--;
			}else if (table[i-1][j] == table[i][j]) {
				i--;
			}
		}
		results.add(res2);

		if (!res1.equals(res2)) {
			results.add(res1);
		}
		return results;
		
	}
	
	
	
	/* tests */ 
	public static final List<String> sequences1 = Arrays.asList("ABSDF","ACEDB","ACEUYIDB","ACEOPFUYIDB");
	public static final List<String> sequences2 = Arrays.asList("BDDS", "DBKAC","DBKACWUY","DBKACWUY");
	
	
	public static void main(String[] args) { 
		List<String> results = lcs("ACEDB", "DBKAC");
        System.out.println(String.format("found sequences %d", results.size()));
	    for(String result : results) {
	        System.out.println(result);
	    }
	}


}
