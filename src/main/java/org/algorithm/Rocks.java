package org.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class Rocks {
	
	static class Cell {
		public int row;
		public int col;
		public int rocks;
		
		public Cell(int row, int col, int rocks) {
			this.row = row;
			this.col = col;
			this.rocks = rocks;
		}
		
		public String toString() {
			return String.format("[%d,%d]=%d", row,col,rocks);
		}

	}
	
	public static int getMaxRocks(int[][] map) {
		
		int rows = map.length;
		int cols = 0;
		if (rows > 0) {
			cols = map[0].length;
		}
		
		int[][] rocks = new int[rows][cols];
		Queue<Cell> queue = new LinkedList<>();
		queue.add(new Cell(rows-1,0,0));
		while (!queue.isEmpty()) {
			Cell curCell = queue.poll();
			int nRocks = curCell.rocks + map[curCell.row][curCell.col];
			rocks[curCell.row][curCell.col] = Math.max(nRocks, rocks[curCell.row][curCell.col]);
			
			if (curCell.row-1 >= 0) {
				queue.add(new Cell (curCell.row-1,curCell.col,nRocks));
			}

			if (curCell.col+1 < cols) {
				queue.add(new Cell (curCell.row,curCell.col+1,nRocks));
			}
		}
		
		
		return rocks[0][cols-1];
		
	}
	
	public static void main(String[] arg) {
		
		int[][] map = {
				{0,0,0,0,5},
				{0,1,1,1,0},
				{2,0,0,0,0},
		};
		
		System.out.println(getMaxRocks(map));
	}

}
