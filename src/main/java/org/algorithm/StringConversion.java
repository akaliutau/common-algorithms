package org.algorithm;

/**
 * how many minimum number of operations (insert, remove or replace) can be
 * performed on the string 1 that it gets converted to string 2
 *
 */
public class StringConversion {

	// using dynamic programming to calculate differences
	public static int getDistance(String str1, String str2) {
		int n = str1.length();
		int m = str2.length();

		int dist[][] = new int[n + 1][m + 1];

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (i == 0) {
					dist[i][j] = j;
				} else if (j == 0) {
					dist[i][j] = i;
				} else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dist[i][j] = dist[i - 1][j - 1];
				} else {
					dist[i][j] = 1 + Math.min(dist[i - 1][j], // previous line
							Math.min(dist[i][j - 1], dist[i - 1][j - 1])// previous column
					);
				}
			}
		}
		return dist[n][m];

	}

	public static void main(String[] arg) {
		int dist = getDistance("march", "april");// del m, add p, c->i, h->l
		System.out.println("dist=" + dist);
	}

}
