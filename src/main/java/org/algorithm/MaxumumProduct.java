package org.algorithm;


/**
 *	Find the biggest product of (n-1) elems in array of size n 
 */
public class MaxumumProduct {

	public static int findBiggestProduct(int[] a) {
		// Builds suffix products.
		int product = 1;
		int n = a.length;
		int[] suffix = a.clone();
		for (int i = n - 1; i >= 0; --i) {
			product *= a[i];
			suffix[i] = product;
		}
		// Finds the biggest product of (n - 1) numbers.
		int prefixProduct = 1;
		int maxProduct = Integer.MIN_VALUE;
		for (int i = 0; i < n; ++i) {
			
			int suffixProduct = i + 1 < n ? suffix[i + 1] : 1;
			// prefixProduct * suffixProduct = candidate for biggest product
			maxProduct = Math.max(maxProduct, prefixProduct * suffixProduct);
			prefixProduct *= a[i];
		}
		return maxProduct;
	}

	public static void main(String[] arg) {
		
		int[] a = {3, 2, 5, 4};
		System.out.println(findBiggestProduct(a));

		int[] a1 = {3, 2, -5, 4};

		System.out.println(findBiggestProduct(a1));

		int[] a2 = {3, 0, 5, -4};

		System.out.println(findBiggestProduct(a2));

	}

}
