package org.algorithm;

import java.util.HashSet;
import java.util.Set;

public class FirstMissingPositiveEntryII {


	public static int findFirstMissingPositive(int[] a) {
		int i = 0;
		int n = a.length;

		Set<Integer> present = new HashSet<>();
		// using a for holding found elems
		for (i = 0; i < n; i++) {
			if (a[i] > 0) {
				present.add(a[i]);
			}
		}
		for (i = 1; i < n; i++) {
			if (!present.contains(i)) {
				return i;
			}
		}
		return n + 1;
	}

	public static void main(String[] arg) {

		int[] a = { 3, 5, 4, -1, 5, 1, -1 };
		System.out.println(findFirstMissingPositive(a));

		int[] a1 = { 1, 2, 3, -1, 0, 5, 4};
		System.out.println(findFirstMissingPositive(a1));

	}

}
