package org.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 	Implementation of heap structure from the scratch
 */
public class HeapSort {
	
	
	public static class Node{
		boolean removed = false;
		int value;
		Node parent;// a ref to the node on higher level
		List<Node> branches = new ArrayList<>();
		
		public boolean remove(Node node) {
			return branches.remove(node);
		}
		
		@Override
		public String toString() {
			return String.format("%d", value);
		}
	}
	public static List<Node> heap = new ArrayList<>();
	
	public static int buildHeap(Node parent, Integer[] a, int index) {
		Node left,right;
		if (index < a.length) {
			left = new Node();
			left.value = a[index++];
			left.parent = parent;
			heap.add(left);
			parent.branches.add(left);
		}
		if (index < a.length) {
			right = new Node();
			right.value = a[index++];
			right.parent = parent;
			heap.add(right);
			parent.branches.add(right);
		}
		return index;
	}
	
	/**
	 * Builds max heap 
	 */
	public static int buildMaxHeap(int max) {
		int swaps = 0;
		for (int i = max; i > 0; i--) {
			Node curNode = heap.get(i);
			// compare with parent and swap if needed
			if (curNode.value > curNode.parent.value) {
				swap(curNode,curNode.parent);
				swaps++;
			}
		}
		return swaps;
	}
	
	public static void swap(Node curNode, Node otherNode) {
		int swap = curNode.value;
		curNode.value = otherNode.value;
		otherNode.value = swap;
	}
	
	/**
	 * rebuild of partially ordered tree
	 * @param curNode - always the highest node in hierarchy
	 */
	public static void rebuildHeap(Node curNode) {
		Node maxNode = null;
		int maxValue = -1;
		for (Node node : curNode.branches) {
			if (node.value > maxValue ) {
				maxValue = node.value;
				maxNode = node;
			}
		}
		if (maxNode != null && curNode.value < maxNode.value) {
			swap(curNode,maxNode);
			rebuildHeap(maxNode);
		}
	}

	public  static int[] sort(Integer[] a) {
		// build heap
		int index = 0;
		int nodeIndex = 0;
		
		Node root = new Node();
		root.value = a[0];
		heap.add(root);
		index++;
		while (index < a.length) {
			index = buildHeap(heap.get(nodeIndex++),a,index);
		}
		
		// normalize heap
        int lastNodeIndex = heap.size() - 1;
        int updated = 0;
        
        while (buildMaxHeap(lastNodeIndex)>0) {
        	System.out.println("pass:"+updated++);
        };
        
        int[] sorted = new int[a.length];
        
        while (lastNodeIndex > 0) {
        	// swap 0 and lastNodeIndex
        	swap(heap.get(0),heap.get(lastNodeIndex));
        	
        	// remove last node and put value into sorted array
        	Node last = heap.get(lastNodeIndex);
        	sorted[lastNodeIndex] = last.value;
        	last.parent.remove(last);
        	lastNodeIndex--;
        	rebuildHeap(heap.get(0));
        	// rebuild heap
        	
        }
		
		return sorted;
	}
	
	
	public static void main(String[] args) { 
		List<Integer> input = Arrays.asList(1,5,0,3,7,13,2,1,1,3,9,8,9,2);
		Integer[] a = input.toArray(new Integer[14]);

		int[] results = sort(a);
        System.out.println(heap);

	}




}
