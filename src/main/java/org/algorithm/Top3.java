package org.algorithm;

import static org.algorithm.utils.Utils.print;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; 

/**
 * Problem statement: from a given list of students find the best one who has a
 * max median of theirs scores
 * 
 * This solution does not holds all results, instead it collects only median of
 * top 3 results for list of objects of type Student
 */
public class Top3 {

	public static int TOPSIZE = 3;

	public static class Student {
		String name;
		int[] scores = new int[TOPSIZE];
		int median = -1;
		
		public Student(String name) {
			this.name = name;
			// prepare the array
			for (int i = 0; i < TOPSIZE; i++) {
				scores[i] = Integer.MIN_VALUE;
			}
		}

		public void addScore(int score) {
			System.out.println("Student " + name + ":" + score);
			int min = Integer.MAX_VALUE;
			int minIndex = 0;
			// find minimal element in scores array
			for (int i = 0; i < TOPSIZE; i++) {
				if (scores[i] < min) {
					min = scores[i];
					minIndex = i;
				}
			}
			// replace minimal element in the array with score
			// as a result only latest TOPSIZE maximum elements will be collected
			if (score > min) {
				scores[minIndex] = score;
			}
		}

		public int getMedian() {
			// calculate only once
			if (median > -1) {
				return median;
			}
			int sum = 0;
			int nScores = 0;
			for (int i = 0; i < TOPSIZE; i++) {
				if (scores[i] != Integer.MIN_VALUE) {
					sum += scores[i];
					nScores ++;
				}
			}
			median = nScores == 0 ? 0 : sum / nScores;
			return median;
		}

	}


	static final String[] names = new String[] { "Alice", "Elley", "Sarah", "Nikky", "Door", "Alice", "Rebecca",
			"Sarah", "Alice", "Elley", "Alina", "Haruki", "Rebecca", "Rebecca", "Alice" };
	static final int[] scores = new int[] { 5, 0, 10, 91, 12, 98, 27, 19, 3, 18, 13, 18, 43, 67, 91 };

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
		System.out.printf("The best student's name is %s, avg score=%d", collected.get(0).name, collected.get(0).median);
		print(collected.get(0).scores);

	}

}
