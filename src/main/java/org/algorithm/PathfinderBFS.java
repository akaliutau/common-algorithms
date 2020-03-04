package org.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *	BFS path finder - always finds the optimal path 
 */
public class PathfinderBFS {

	public static final int OPEN = 0;
	public static final int VISITED = 1;
	public static final int CLOSED = 2;
	public static final int END = 3;
	public static final int MAXG = 10000;
	public static final boolean considerDiag = false;
	
	public static final Comparator<Cell> byF = new Comparator<Cell>() {
		@Override
		public int compare(Cell n1, Cell n2) {
			// n1 > n2 if f1> f2
			// sort in desc order - on the top will be cells with lowest sum (h+g)
			return n1.getWeight() - n2.getWeight();
		}
	};

	public static class Cell {
		int status;
		int h;// global strategy
		int g;// g - cost = distance from the start node
		int f;// h+g, used to make decisions
		int i, j;

		public Cell() {
			this.status = CLOSED;
			this.g = MAXG;
		}

		public Cell(int i, int j, int h, int g) {
			status = OPEN;
			this.h = h;
			this.g = g;
			this.i = i;
			this.j = j;
		}

		public int getWeight() {
			return h + g;
		}
	}

	public static class Grid {

		int rows;
		int columns;
		Cell[][] grid;

		Queue<Cell> queue;
		List<String> path;
		int iStart;
		int jStart;
		int iEnd;
		int jEnd;

		public Grid(int[][] adjacentmatrix, int rows, int columns, int iStart, int jStart, int iEnd, int jEnd) {
			this.rows = rows;
			this.columns = columns;
			grid = new Cell[rows][columns];
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (adjacentmatrix[i][j] != 0) {
						grid[i][j] = new Cell();
					}
				}
			}
			Cell start = new Cell(0, 0, 0, 0);
			start.status = VISITED;
			grid[iStart][jStart] = start;
			queue = new LinkedList<Cell>();
			queue.add(start);
			path = new ArrayList<>();
			this.iStart = iStart;
			this.jStart = jStart;
			this.iEnd = iEnd;
			this.jEnd = jEnd;
		}

		public boolean find() {
			return getPath(iStart, jStart);
		}

		/**
		 * returns true if and only if correct path has been found 
		 * (i,j) - coordinates of current cell 
		 * g - cost = distance from the start node
		 * 
		 * @param iEnd
		 * @param jEnd
		 */
		private boolean getPath(int i, int j) {

			// if stack is empty return false - nowhere to go
			while (!queue.isEmpty()) {
				Cell curCell = queue.remove();
				if (curCell.i == iEnd && curCell.j == jEnd) {
					return true;
				}
				// mark as visited
				curCell.status = VISITED;
				List<Cell> neighbors = getNeighbors(curCell.i, curCell.j);
				queue.addAll(neighbors);
			}

			return false;
		}

		/* util methods */
		public List<Cell> getNeighbors(int i, int j) {
			System.out.println(String.format("(%s,%s)", i, j));
			Cell curNode = grid[i][j];
			List<Cell> next = new ArrayList<>();
			if (i - 1 >= 0) {
				if (grid[i - 1][j] == null) {
					grid[i - 1][j] = new Cell(i - 1, j, h(i - 1, j, iEnd, jEnd), curNode.g + 10);
				}
				Cell node = grid[i - 1][j];
				if (node.status == OPEN) {
					next.add(node);
				}
			}
			if (i + 1 < rows) {
				if (grid[i + 1][j] == null) {
					grid[i + 1][j] = new Cell(i + 1, j, h(i + 1, j, iEnd, jEnd), curNode.g + 10);
				}
				Cell node = grid[i + 1][j];
				if (node.status == OPEN) {
					next.add(node);
				}
			}
			if (j - 1 >= 0) {
				if (grid[i][j - 1] == null) {
					grid[i][j - 1] = new Cell(i, j - 1, h(i, j - 1, iEnd, jEnd), curNode.g + 10);
				}
				Cell node = grid[i][j - 1];
				if (node.status == OPEN) {
					next.add(node);
				}
			}
			if (j + 1 < columns) {
				if (grid[i][j + 1] == null) {
					grid[i][j + 1] = new Cell(i, j + 1, h(i, j + 1, iEnd, jEnd), curNode.g + 10);
				}
				Cell node = grid[i][j + 1];
				if (node.status == OPEN) {
					next.add(node);
				}
			}
			next.sort(byF);
			return next;
		}

		public Cell getClosestNeighbor(int i, int j) {
			Cell curNode = grid[i][j];
			Cell next = null;
			int maxWeight = curNode.getWeight();
			if (i - 1 >= 0) {
				if (grid[i - 1][j] != null) {
					Cell node = grid[i - 1][j];
					if (node.status == VISITED && maxWeight >= node.getWeight()) {
						next = node;
						maxWeight = node.getWeight();
					}
				}
			}
			if (i + 1 < rows) {
				if (grid[i + 1][j] != null) {
					Cell node = grid[i + 1][j];
					if (node.status == VISITED && maxWeight >= node.getWeight()) {
						next = node;
						maxWeight = node.getWeight();
					}
				}
			}
			if (j - 1 >= 0) {
				if (grid[i][j - 1] != null) {
					Cell node = grid[i][j - 1];
					if (node.status == VISITED && maxWeight >= node.getWeight()) {
						next = node;
						maxWeight = node.getWeight();
					}
				}
			}
			if (j + 1 < columns) {
				if (grid[i][j + 1] != null) {
					Cell node = grid[i][j + 1];
					if (node.status == VISITED && maxWeight >= node.getWeight()) {
						next = node;
						maxWeight = node.getWeight();
					}
				}
			}
			return next;
		}

		public void trace(int i, int j) {
			if (i == iStart && j == jStart) {
				path.add(String.format("(%s,%s)", i, j));
				return;
			}
			Cell cell = getClosestNeighbor(i,j);
			path.add(String.format("(%s,%s)", i, j));
			trace(cell.i,cell.j);
		}

		public String getPath() {
			// print all nodes
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (grid[i][j] != null) {
						Cell c = grid[i][j];
						System.out.printf("%6d",c.getWeight());
					} else {
						System.out.printf("%6d",-1);
					}
				}
				System.out.println();
			}
			trace(this.iEnd,this.jEnd);
			List<String> reversed = new ArrayList<>();
			for (int i = path.size() - 1; i > -1 ; i--) {
				reversed.add(path.get(i));
			}
			
			return String.join("->", reversed);
		}

		public static int h(int i, int j, int iEnd, int jEnd) {
			return Math.abs(i - iEnd) + Math.abs(j - jEnd);
		}


	}

	/**
	 * Test data input
	 */
	static final int[][] adjacentMatrix = 
			new int[][] { 
			{ 0, 1, 0, 1, 0 }, 
			{ 0, 1, 0, 1, 0 }, 
			{ 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 1, 0 }, 
			{ 0, 0, 1, 1, 0 }, 
			{ 0, 0, 0, 0, 0 } 
		};

	public static void main(String[] arg) {
		int n = 6;
		int m = 5;
		Grid grid = new Grid(adjacentMatrix, n, m, 0, 0, 4, 4);
		boolean isFound = grid.find();
		if (isFound) {// path has been found, trace back
			System.out.print("path:"+grid.getPath());
		}else {
			System.out.print("no path found");
		}
	}

}
