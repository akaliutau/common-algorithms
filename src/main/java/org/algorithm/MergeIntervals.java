package org.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeIntervals {
	
	public static class Interval  implements Comparable<Interval> {
		int point;
		int change;
		
		public Interval(int point, int change) {
			this.point = point;
			this.change = change;
		}

		@Override
		public int compareTo(Interval interval) {
			return Integer.compare(point, interval.point);// in desc order
		}
		
		@Override
		public String toString() {
			return String.format("[%d,%d]", point, change);
		}
		
	}
	
	static final int[][] intervals = 
			new int[][] { 
				{ 0, 2 }, 
				{ 1, 4 },
				{ 3, 4 },
				{ 3, 7 },
				{ 5, 6 }, 
				{ 8, 9 } 
			};

	public static void main(String[] arg) {
		
		List<Interval> list = new ArrayList<>(); 
		for (int i = 0; i < intervals.length; i++) {
			int[] interval = intervals[i];
			list.add(new Interval(interval[0],1));
			list.add(new Interval(interval[1],-1));
		}
		Collections.sort(list);
		System.out.println("sorted list:"+list.toString());
		int maxOverlapses = 0;
		int overlaps = 0;
		for (Interval interval : list) {
			overlaps += interval.change;
			maxOverlapses = overlaps > maxOverlapses ? overlaps : maxOverlapses;
		}
		System.out.println("max number of overlapses:"+maxOverlapses);
	}
}
