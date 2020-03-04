package org.algorithm;

public class DescreteOptimisation {
	
	public static int objectiveFunction(int x, int y) {
		return 4*x + 3*y;
	}
	
	public static boolean boundary1(int x, int y) {
		return 3*x + y <= 9;
	}

	public static boolean boundary2(int x, int y) {
		return 4*x + 3.2*y <= 16;
	}
	
	public static boolean validPoint(int x, int y) {
		return boundary1(x, y) && boundary2(x, y);
	}
	
	private static int[] optimize(int xRange, int yRange) {
		int optimum = Integer.MIN_VALUE;
		int xOptimum = 0;
		int yOptimum = 0;
		int y = 0;
		while (y < yRange) {
			int x = 0;
			while (x < xRange && validPoint(x,y)) {
				int objectValue = objectiveFunction(x,y);
				if (objectValue > optimum) {// search for global max
					optimum = objectValue;
					xOptimum = x;
					yOptimum = y;
				}
				x++;
			}
			y++;
		}
		int[] result = new int[3]; 
		result[0] = optimum;
		result[1] = xOptimum;
		result[2] = yOptimum;
		return result;
	}
	
	
	static final int[] array = new int[] { 1, 0, 3, 5 };

	public static void main(String[] arg) {
		
		int xRange = 4;
		int yRange = 9;

		int[] optimum = optimize(xRange, yRange);
		System.out.printf("Optimum: (%d,%d)=%d",  optimum[1],  optimum[2],  optimum[0]);
	}


}
