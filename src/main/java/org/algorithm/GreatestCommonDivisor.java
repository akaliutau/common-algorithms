package org.algorithm;

public class GreatestCommonDivisor {

	public static long gcd(long x, long y) {
		if (x == y) {
			return x;
		} else if ((x & 1) == 0 && (y & 1) == 0) {// x and y are even.
			return gcd(x >>> 1, y >>> 1) << 1;
		} else if ((x & 1) == 0 && (y & 1) != 0) {// x is even, y is odd.
			return gcd(x >>> 1, y);
		} else if ((x & 1) != 0 && (y & 1) == 0) {// x is odd, y is even.
			return gcd(x, y >>> 1);
		} else if (x > y) {// Both x and y are odd, and x > y.
			return gcd(x - y, y);
		}
		return gcd(x, y - x); // Both x and y are odd, and x <= y.
	}

	public static void main(String[] arg) {

		System.out.println(gcd(24,16));

	}

}
