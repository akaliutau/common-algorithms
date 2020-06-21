package org.algorithm;

public class ElementThatAppearsOnce {

	public static int findElementAppearsOnce(int[] a) {
		int[] counts = new int[32];
		for (int x : a) {
			for (int i = 0; i < 32; ++i) {
				if ((x & (1 << i)) != 0) {
					++counts[i];
				}
			}
		}
		int result = 0;
		for (int i = 0; i < 32; ++i) {
			result |= (counts[i] % 3) * (1 << i);
		}
		return result;
	}

	public static void main(String[] arg) {
		
		int[] a = {2, 4, 2, 5, 2, 5, 5};
		System.out.println(findElementAppearsOnce(a));

	}

}
