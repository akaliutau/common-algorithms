package org.algorithm;

/**
 * 	Solution for Amazon's code test (regarding event's propagation in 2d grid)
 *  using binary representation of grid of cells
 */
public class Cellular {
	
	public static int bin2int(int max, int[] array) {
		int res = 0;
		int p = max;
		for(int i = 0; i < array.length; i++) {
			res += p*array[i];
			p = p/2;
		}
		return res;
	}
	
	public static boolean isCompleted(int[] vector, int val) {
		for (int i = 0; i < vector.length; i++) {
			if (vector[i] != val) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Generates a new array consisting from left and right - shifted values
	 * @param vector
	 * @param val mask number = 2^n-1
	 * @return
	 */
	public static int[] hIterated(int[] vector, int val) {
		int[] res = new int[vector.length];
		for (int i = 0; i < vector.length; i++) {
			int shiftL = val & (vector[i] << 1);// take only last n bits
			int shiftR = vector[i] >> 1;
			res[i] = shiftL | shiftR;
		}
		return res;
	}

	public static int[] vIterated(int[] vector) {
		int[] res = new int[vector.length];
		for (int i = 0; i < vector.length; i++) {
			if (i - 1 >= 0) {
				res[i-1] = res[i-1] | vector[i];
			}
			if (i + 1 < vector.length) {
				res[i+1] = res[i+1] | vector[i];
			}
		}
		return res;
	}

	public static int getUpdateDays(int n, int m, int[][] grid) {
		// compose an array of integers representing each row of grid
		int[] rows = new int[n];
		int maxRowValue = (int) Math.pow(2, m-1);
		int mask = maxRowValue*2 - 1;
		for (int i = 0; i < n; i++) {
			rows[i] = bin2int(maxRowValue,grid[i]);
		}
		print(rows);
		// until completed:
		// -> calculate h-iterated values 
		// -> calculate v-iterated values
		// -> merge with main array
		int iteration = 1;
		while (!isCompleted(rows,mask)){
			int[] hLine = hIterated(rows,mask);
			print(hLine);
			int[] vLine = vIterated(rows);
			print(vLine);
			for (int i = 0; i < n; i++) {
				rows[i] = rows[i] | hLine[i] | vLine[i];
			}
			System.out.println("== merged ==");
			print(rows);
			iteration ++;
		}
		return iteration;
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

	static final int[][] array = 
			new int[][] { 
				{ 0, 0, 0 }, 
				{ 0, 1, 0 }, 
				{ 1, 0, 0 } 
			};
			


	public static void main(String[] arg) {

		String binary = Integer.toBinaryString(13);

		int n = 3;
		int m = 3;
		int maxV = (int) Math.pow(2, n);
		
		int days = getUpdateDays(n,m,array);
		System.out.println("days=" + days);
		
	}

}
