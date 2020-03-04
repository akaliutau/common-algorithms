package org.algorithm.utils;

import java.util.Arrays;
import java.util.stream.Stream;


/**
 * I/O util methods
 */
public class Utils {

	private static <T> void print(Stream<T> stream) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		stream.forEach(e -> {
			if (sb.length() > 1) {
				sb.append(",");
			}
			sb.append(e);
		});
		sb.append("]");
		System.out.println(sb.toString());
	}
	

	public static void print(int[] vector) {
		print(Arrays.stream(vector).boxed());
	}
	
	public static void print(int[][] vector) {
		for (int i = 0; i < vector.length; i++) {
			print(Arrays.stream(vector[i]).boxed());
		}
	}


	public static void print(String[] vector) {
		print(Arrays.stream(vector));
	}

	public static void print(char[] vector, int from) {
		int newSize = vector.length - from;
		Character[] newVector = new Character[newSize < 0 ? 0 : newSize];
		for (int i = from; i < vector.length; i++) {
			newVector[i - from] = vector[i];
		}
		print(Arrays.stream(newVector));

	}

}
