package org.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.algorithm.utils.Utils;


public class Kadane {
	
	public static class Subarray {
		int left = 0;
		int right = 0;
		int maxSum;
		int[] arr;
		
		public Subarray(int[] arr) {
			this.arr = arr;
			this.maxSum = arr[0];
		}
		
		public int[] findMax(){
			int cumulativeSum = arr[0];// cumulative cumulativeSum

			for (int i = 1; i < arr.length; i++) {
				// update a cumulative cumulativeSum for subarray [bestLeft,i]
				cumulativeSum = Math.max(cumulativeSum,0) + arr[i];
				// choose max of 3 values:
				// arr[i], maxSum and cumulativeSum
				if (arr[i] >= cumulativeSum){
					if (cumulativeSum > maxSum) {
						maxSum = arr[i];
						left = i;
						right = i;
					}
					cumulativeSum = arr[i];
				}else if(cumulativeSum >= maxSum) {
					right = i;// leave the left boundary the same, update the right one
					maxSum = cumulativeSum;
				}
			}
			return Arrays.copyOfRange(this.arr, left, right+1);
			
		}

		@Override
		public String toString() {
			return "Subarray [left=" + left + ", right=" + right + ", maxSum=" + maxSum + "]";
		}
		
		
	}
	

	static final int[] array1 = new int[] { 1, -3, 4, 6, -2 };
	static final int[] array2 = new int[] { 1, -2, 3, 5, -6, 4 };
	static final int[] array3 = new int[] { -1, -2 };

	public static void main(String[] arg) {
		Subarray subarray1 = new Subarray(array1);
		Utils.print(subarray1.findMax());
		System.out.println(subarray1);

		Subarray subarray2 = new Subarray(array2);
		Utils.print(subarray2.findMax());
		System.out.println(subarray2);

		Subarray subarray3 = new Subarray(array3);
		Utils.print(subarray3.findMax());
		System.out.println(subarray3);
}

}
