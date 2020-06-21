package org.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Let A be an array of integers. Call the pair of indices (i, j) inverted if i
 * < j and A[i] > A[j].
 * 
 * For example, if A - (4,1,2,3), then the pair of indices (0,3) is inverted.
 * 
 * (The number of inverted pairs in an array is a measure of how unsorted it is)
 * 
 */
public class CountInversions {

	// Merge two sorted sublists
	private static int merge(int[] a, int start, int mid, int end) {
		List<Integer> sorted = new ArrayList<>();
		int left = start;
		int right = mid;
		int inversionCount = 0;

		while (left < mid && right < end) {
			if (Integer.compare(a[left], a[right]) <= 0) {
				sorted.add(a[left++]);
			} else {
				inversionCount += mid - left;
				sorted.add(a[right++]);
			}
		}
		for (int i = left; i < mid; i++) {
			sorted.add(a[i]);
		}
		for (int i = right; i < mid; i++) {
			sorted.add(a[i]);
		}
		// Updates A with sorted.
		for (Integer t : sorted) {
			a[start++] = t;
		}
		return inversionCount;
	}

	// Return the number of inversions in A.subList(start , end).
	private static int countlnversions(int[] a, int start, int end) {
		if (end - start <= 1) {
			return 0;
		}
		int mid = start + ((end - start) / 2);
		return countlnversions(a, start, mid) + countlnversions(a, mid, end) + merge(a, start, mid, end);
	}

	public static int countlnversions(int[] a) {
		int n = a.length;
		return countlnversions(a, 0, n);
	}

	public static void main(String[] arg) {

		int[] a = {4,1,2,3};
		System.out.println(countlnversions(a));

	}

}
