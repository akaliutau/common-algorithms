package org.algorithm;

import java.util.Arrays;

/**
 * 
 * Takes a final score and scores for individual plays, and returns
 * the number of combinations of plays that result in the final score
 *
 */
public class CombinationsForSum {

	public static int numCombinationsForFinalScore(int finalScore, int[] scores) {
		int n = scores.length;
		int[][] combinations = new int[scores.length][finalScore + 1];
		
		Arrays.parallelSort(scores);
		for (int i = 0; i < n; i++) {
			combinations[i][0] = 1;
			for (int j = 1; j <= finalScore; j++) {
				int without = i - 1 >= 0 ? combinations[i - 1][j] : 0;
				int with = j >= scores[i] ? combinations[i][j - scores[i]] : 0;
				combinations[i][j] = without + with;
			}
		}
		
		return combinations[n - 1][finalScore];
		
	}

	public static void main(String[] arg) {
		int[] scores = {2, 3, 7};
		
		System.out.println(numCombinationsForFinalScore(12, scores));
		
	}
}
