package org.algorithm;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
	
	private static boolean isValid(List<Integer> positions, int col, int row) {
		for (int r = 0; r < positions.size(); r++) {
			if (positions.get(r) == col) {
				return false;
			}
			if (Math.abs(positions.get(r) - col) == Math.abs(r-row)) {
				return false;
			}
		}
		return true;
	}
	
	
	public static void find(int n, int m, int row, List<Integer> positions, List<List<Integer>> results) {
		if (row == n) {
			List<Integer> found = new ArrayList<>(positions);
			results.add(found);
		}else {
			// check all possible positions on the row with index=row
			for (int col = 0; col < m; col++) {
				if (isValid(positions,col,row)) {
					positions.add(col);
					find(n,m,row+1,positions,results);
					positions.remove(row);
				}
			}
		}
		
	}


	public static void main(String[] arg) {
		
		int n = 4;
		int m = 4;
		List<Integer> positions = new ArrayList<>();
		List<List<Integer>> results = new ArrayList<>();
		

		find(n,m,0,positions,results);
		System.out.println("total:"+ results);

	}


}
