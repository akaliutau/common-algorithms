package org.algorithm;

import java.util.ArrayList;
import java.util.List;

public class DrawTheSkyline {

	static class Rectangle {
		public int left, right, height;

		public Rectangle(int left, int right, int height) {
			this.left = left;
			this.right = right;
			this.height = height;
		}

		@Override
		public String toString() {
			return "Rectangle [left=" + left + ", right=" + right + ", height=" + height + "]";
		}
	}

	public static List<Rectangle> drawingSkylines(int[][] buildings) {
		int minLeft = Integer.MAX_VALUE;
		int maxRight = Integer.MIN_VALUE;
		List<Rectangle> rectangles = new ArrayList<>();
		for (int[] building : buildings) {
			rectangles.add(new Rectangle(building[0], building[1], building[2]));
		}

		for (Rectangle rect : rectangles) {
			minLeft = Math.min(minLeft, rect.left);
			maxRight = Math.max(maxRight, rect.right);
		}
		int[] heights = new int[maxRight - minLeft + 1];

		for (Rectangle rect : rectangles) {
			for (int i = rect.left; i < rect.right; i++) {
				heights[i - minLeft] = Math.max(heights[i - minLeft], rect.height);
			}
		}
		List<Rectangle> result = new ArrayList<>();
		int left = 0;
		for (int i = 1; i < heights.length; i++) {
			if (heights[i] != heights[i - 1]) {
				result.add(new Rectangle(left + minLeft, i - 1 + minLeft, heights[i - 1]));
				left = i;
			}
		}
		result.add(new Rectangle(left + minLeft, maxRight, heights[heights.length - 1]));
		return result;
	}

	public static void main(String[] arg) {

		int[][] buildings = {
				{0, 3, 1},
				{1, 6, 3},
				{4, 8, 4},
				{5, 9, 2},
				{7, 14, 3},
				{10, 12, 6},
				{11, 17, 1},
				{13, 16, 2}
		};
		System.out.println(drawingSkylines(buildings));

	}

}
