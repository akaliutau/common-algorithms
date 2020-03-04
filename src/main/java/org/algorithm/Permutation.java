package org.algorithm;

public class Permutation {
	
    // print N! permutations of the elements of array
    public static long perm2(int[] array) {
       int N = array.length;
       int[] a = new int[N];
       for (int i = 0; i < N; i++) {
           a[i] = array[i];
       }
       return perm2(a, N);
    }

    private static long perm2(int[] array, int n) {
        if (n == 1) {
        	print(array);
            return 1;
        }
        long counter = 0;
        for (int i = 0; i < n; i++) {
            swap(array, i, n-1);
            counter += perm2(array, n-1);
            swap(array, i, n-1);
        }
        return counter;
    }  

    // swap the characters at indices i and j
    private static void swap(int[] a, int i, int j) {
    	int c;
        c = a[i]; a[i] = a[j]; a[j] = c;
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
	static final int[] array = new int[] { 1, 0, 3, 5 };

	public static void main(String[] arg) {

		long numberOfPermutations = perm2(array);
		System.out.println("total:"+numberOfPermutations );

	}


}
