package org.algorithm;

import static org.algorithm.utils.Utils.print;

import java.util.ArrayList;
import java.util.List;


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
		
		public void find(){
			int cumulativeSum = arr[0];// cumulative cumulativeSum

			for (int i = 1; i < arr.length; i++) {
				// update a cumulative cumulativeSum for subarray [bestLeft,i]
				cumulativeSum = Math.max(cumulativeSum,0) + arr[i];
				// choose max of 3 values:
				// arr[i], maxSum and cumulativeSum
				if (arr[i] > cumulativeSum){
					if (cumulativeSum > maxSum) {
						maxSum = arr[i];
						left = i;
						right = i;
					}
					cumulativeSum = arr[i];
				}else if(cumulativeSum >= maxSum) {
					right = i;// leave the left boundary the same, update the right one
					maxSum = cumulativeSum;
				}else {
					// leave all as is
				}
			}

		}
	}
	

	//static final int[] array = new int[] { 1, -3, 4, 6, -2 };
	static final int[] array = new int[] { 1, -2, 3, 5, -6, 4 };
	//static final int[] array = new int[] { -1, -2 };

	public static void main(String[] arg) {
		List<Integer> result = new ArrayList<>();
		Subarray subarray = new Subarray(array);
		subarray.find();
		for (int i = subarray.left; i <= subarray.right; i++) {
			result.add(array[i]);
		}
		System.out.println("found:"+result.toString());

	}

}
