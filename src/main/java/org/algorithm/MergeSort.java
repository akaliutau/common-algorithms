package org.algorithm;

import static org.algorithm.utils.Utils.print;

import java.util.Arrays;
import java.util.List;

public class MergeSort {
	
	public static class Partition{
		int left;
		int right;
		
		public Partition(int left,int right) {
			this.left = left;
			this.right = right;
		}
	}
	
	public static int[] merge(int[] a,int[] b) {
		int[] merged = new int[a.length + b.length];
		int idxA = 0;
		int idxB = 0;
		int idx = 0;
		while (idxA < a.length && idxB < b.length) {
			if (a[idxA] < b[idxB]) {
				merged[idx++] = a[idxA];
				idxA++;
			}else {
				merged[idx++] = b[idxB];
				idxB++;
			}
		}
		// add the tail if any
		if (idxA < a.length) {
			for (int i = idxA; i < a.length; i++) {
				merged[idx++] = a[i];
			}
		}
		if (idxB < b.length) {
			for (int i = idxB; i < b.length; i++) {
				merged[idx++] = b[i];
			}
		}
		return merged;
	}
	
	
	private static int[] sort(Integer[] a) {
		int length = a.length;
		
		return sort(a,0,length-1);
	}
	
	/*
	 *  Main recursive method implementing algorithm
	 *  Returns: sorted part of array a from [left,right] inclusive
	 */
	private static int[] sort(Integer[] a, int left, int right) {
		int[] sorted;
		if (left == right) {
			sorted = new int[1];
			sorted[0] = a[left];
			return sorted;
		}
		if (left+1 == right) {
			sorted = new int[2];
			if (a[left] > a[right]) {
				sorted[0] = a[right];
				sorted[1] = a[left];
			}else {
				sorted[0] = a[left];
				sorted[1] = a[right];
			}
			return sorted;
		}

		// Divide range [left,right] in half
		int center = (left+right)/2;
		// sort each part separately
		int[] l = sort(a,left,center);
		int[] r = sort(a,center+1,right);
		//merge parts together
		return merge(l,r);
	}
	
	
	public static void main(String[] args) { 
		List<Integer> input = Arrays.asList(1,5,0,3,7,13,2,1,1,3,9,8,9,2);
		Integer[] a = input.toArray(new Integer[14]);

		int[] results = sort(a);
        print(results);

	}




}
