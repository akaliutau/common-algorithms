package org.algorithm;

import static org.algorithm.utils.Utils.print;

import java.util.ArrayList;
import java.util.List;

/**
 * 2D version of Kadane algorithm
 */
public class Kadane2d {
	
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



	public static List<List<Integer>> find(int[][] arr, int n, int m) {
		List<List<Integer>> result = new ArrayList<>();
		// best rectangle
		int bestLeft = 0;
		int bestRight = 0;
		int bestTop = 0;
		int bestBottom = 0;
		int sqMaxSum = arr[0][0];


		for (int left = 0; left < m; left++) {
			// this block is executed only when j-index has reached end of array
			// create a new vector
			int[] sum = new int[n];
			for (int i = 0; i < n; i++) {
				sum[i] = 0;
			}
			
			for (int right = left; right < m; right++) {
				
				// fill it with max sums for row
				for (int i = 0; i < n; i++) {
					sum[i] += arr[i][right];// get max (reached so far) sum
				}
				
				Subarray column = new Subarray(sum);
				print(sum);
				column.find();
				if (column.maxSum > sqMaxSum) {
					bestLeft = left;
					bestRight = right;
					bestTop = column.left;
					bestBottom = column.right;
					sqMaxSum = column.maxSum;
				}
			}
		}
		List<Integer> bestSq = new ArrayList<>();
		bestSq.add(bestLeft);
		bestSq.add(bestRight);
		bestSq.add(bestTop);
		bestSq.add(bestBottom);
		bestSq.add(sqMaxSum);
		result.add(bestSq);
		return result;
	}
	

	static final int[][] array = 
			new int[][] { 
				{  6, -5, -7,  4, -4 }, 
				{ -9,  3, -6,  5,  2 }, 
				{-10,  4,  7, -6,  3 },
				{ -8,  9, -3,  3, -7 } 
			};

	public static void main(String[] arg) {

		List<List<Integer>> result = find(array, 4, 5);

		System.out.println("found:"+result);

	}

}
