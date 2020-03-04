package org.algorithm;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Problem statement: from a given list of students find the best one who has a
 * max median of her scores
 * 
 * This solution does not holds all results, instead it collects only median of
 * top K results for list of objects of type Student
 */
public class TopK {

	public static int TOPSIZE = 4;

	private static class Compare implements Comparator<Student> {
		@Override
		public int compare(Student ol, Student o2) {
			return Integer.compare(o2.median, ol.median);
		}

		public static final Compare COMPARE_HEAP_ENTRIES = new Compare();
	}

	public static class Student {
		String name;
		int median = -1;
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(TOPSIZE); // head always contains the min element from the
																		// list
//		PriorityQueue<Integer> minHeap = new PriorityQueue<>(TOPSIZE, Collections.reverseOrder()); //  head always contains the max element from the list 
//		PriorityQueue<Integer> minHeap = new PriorityQueue<>(TOPSIZE, Compare.COMPARE_HEAP_ENTRIES); // custom comparator 

		public Student(String name) {
			this.name = name;
		}

		public void addScore(int score) {
			System.out.println(name + ":" + score);
			// find and
			maxHeap.add(score);
			if (maxHeap.size() == TOPSIZE + 1) {
				maxHeap.remove();// remove the min elem, so the queue will always contain the biggest elements
			}
			System.out.println(maxHeap.toString());
		}

		public int getMedian() {
			if (median > -1) {
				return median;
			}
			int sum = 0;
			int size = maxHeap.size();
			while (!maxHeap.isEmpty()) {
				sum += maxHeap.remove();
			}
			median = sum / size;
			return median;
		}

		// use this method to qualify students
		public boolean isQualified() {
			return maxHeap.size() == TOPSIZE;
		}

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

	static final String[] names = new String[] { "Alice", "Elley", "Sarah", "Nikky", "Door", "Alice", "Rebecca",
			"Sarah", "Alice", "Elley", "Alina", "Haruki", "Rebecca", "Rebecca", "Alice", "Alice" };
	static final int[] scores = new int[] { 5, 0, 10, 91, 12, 98, 27, 19, 3, 18, 13, 18, 43, 67, 91, 4 };

	public static void main(String[] arg) {
		Map<String, Student> students = new HashMap<String, Student>();
		for (int i = 0; i < names.length; i++) {
			if (!students.containsKey(names[i])) {
				students.put(names[i], new Student(names[i]));
			}
			students.get(names[i]).addScore(scores[i]);
		}

		Comparator<Student> sortByMeanScore = (p, o) -> Integer.compare(o.getMedian(), p.getMedian());// desc

		List<Student> collected = students.values().stream().sorted(sortByMeanScore).collect(Collectors.toList());
		System.out.printf("name=%s, avg score=%d", collected.get(0).name, collected.get(0).median);

	}

}
