package org.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 	Standard implementation of heap structure
 */
public class Heap {
	
	static class Elem {
		public int id;
		public int val;

		public Elem(int id, int val) {
			this.id = id;
			this.val = val;
		}

		@Override
		public String toString() {
			return "[id=" + id + ", val=" + val + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Elem other = (Elem) obj;
			if (id != other.id)
				return false;
			return true;
		}
	}
	
	
	public static void main(String[] arg) {
		
		// asc order - head always have the elem with Min(val)
		Comparator<Elem> byVal = (o,p) -> Integer.compare(o.val, p.val);
		
		PriorityQueue<Elem> maxHeap = new PriorityQueue<>(3,byVal);
		
		maxHeap.add(new Elem(1,23));
		System.out.println(maxHeap);

		maxHeap.add(new Elem(2,2));
		System.out.println(maxHeap);

		maxHeap.add(new Elem(3,12));
		System.out.println(maxHeap);
		
		System.out.println("poll "+maxHeap.poll());
		System.out.println(maxHeap);
		
		maxHeap.add(new Elem(4,5));
		System.out.println(maxHeap);
		

	}

}
