package org.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SubsetSum {

	static int isSubsetSum(int set[], int n, int sum) {
		// The value of subset[i][j] will be
		// true if there is a subset of
		// set[0..j-1] with sum equal to i
		int subset[][] = new int[n + 1][sum + 1];

		// If sum is 0, then answer is true (empty set)
		for (int i = 0; i <= n; i++) {
			subset[i][0] = 1;
		}

		// If sum is not 0 and set is empty (the line with index=0),
		for (int i = 1; i <= sum; i++) {
			subset[0][i] = 0;
		}

		// Filling the subset table
		for (int i = 1; i <= n; i++) {
			for (int s = 1; s <= sum; s++) {
				subset[i][s] = subset[i - 1][s];
				int num = set[i - 1];
				if (s >= num) {
					subset[i][s] = subset[i][s] | subset[i - 1][s - num];
				}
			}
		}

		// uncomment this code to print table
		for (int i = 0; i <= sum; i++) {
			for (int j = 0; j <= n; j++)
				System.out.println(subset[j][i]);
		}

		return subset[n][sum];
	}

	public static List<Integer> find(int[] array, int minSum, int sum) {
		return  Arrays.stream(array).boxed().collect(Collectors.toList());
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

	// static final int[] array = new int[] { 1, -3, 4, 6, -2 };
	static final int[] array = new int[] { 1, 0, 3, 5, 4, 4 };

	public static void main(String[] arg) {

		int result = isSubsetSum(array, 6, 16);
		System.out.println("found:" + result);

	}

}
