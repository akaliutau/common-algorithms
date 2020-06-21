package org.algorithm;

public class FirstMissingPositiveEntry {

	static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static int findFirstMissingPositive(int[] a) {
		int i = 0;
		int n = a.length;

		// using a for holding found elems
		while (i < n) {
			if (a[i] > 0 && a[i] <= n && a[a[i] - 1] != a[i]) {
				swap(a, i, a[i] - 1);
			} else {
				i++;
			}
		}

		for (i = 0; i < n; i++) {
			if (a[i] != i + 1) {
				return i + 1;
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
