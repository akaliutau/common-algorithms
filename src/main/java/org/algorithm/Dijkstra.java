package org.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
	
	public static final int MAX_DIST = 100000;
	
	public static class Node {
		String id;
		int dist = MAX_DIST;
		String prev;
		Map<String,Integer> children = new HashMap<>();
		
		public Node(String id) {
			this.id = id;
		}
		
		@Override
		public String toString() {
			return "Id:"+id+",dist="+dist+",connections:"+children.toString();
		}

	}
	
	public static void getPath(Map<String,Node> nodes, String fromNodeId, String toNodeId) {
		// create a set of processed nodes
		List<String> toProcess = new ArrayList<>();
		Set<String> processed = new HashSet<>();

		toProcess.add(fromNodeId);
		for (String id : nodes.keySet()) {
			if (!fromNodeId.equals(id)) {
				toProcess.add(id);
			}
		}
		// set distance for initial node
		nodes.get(fromNodeId).dist = 0;
		//process all nodes
		while(toProcess.size() > 0) {
			Node curNode = nodes.get(toProcess.remove(0));
			Set<String> connected = curNode.children.keySet();
			for (String id : connected) {// investigate all connections
				if (processed.contains(id) || id.equals(curNode.id)) continue;
				Node refNode = nodes.get(id);
				int dist = curNode.children.get(id);
				// best path must have a min sum, i.e. to be optimal
				if (refNode.dist > dist+curNode.dist) {
					refNode.prev = curNode.id;
					refNode.dist = dist+curNode.dist;
				}
			}
			processed.add(curNode.id);
		}
		System.out.println("min distance="+nodes.get(toNodeId).dist);
		// restore path using backtracing
		String curId = toNodeId;
		String path = nodes.get(toNodeId).id;
		while (!curId.equals(fromNodeId)) {
			Node curNode = nodes.get(curId);
			path = nodes.get(curNode.prev).id + "->" + path;
			curId = curNode.prev;
		}
		System.out.println("found path:"+path);
	}
	
	/**
	 * Test data input
	 */
	static final int[][] adjacentMatrix = new int[][]{ 
		{1,1,2,3,0}, 
		{0,1,1,0,4}, 
		{0,0,1,0,1}, 
		{0,0,0,1,1}, 
		{0,0,0,0,1} 
	};

	static final String[] nodeNames = new String[]{
			"1","2","3","4","5"
	};

	public static void main(String[] arg) {
		
		int size = 5;
		//build graph
		Map<String,Node> nodes = new HashMap<>();
		for (int i = 0; i < size; i++) {
			String name = String.valueOf(nodeNames[i]);
			nodes.put(name, new Node(name));
		}
		for (int i = 0; i < size; i++) {
			Node node = nodes.get(nodeNames[i]);
			for (int j = 0; j < size; j++) {
				Integer dist = adjacentMatrix[i][j];
				if (dist != 0) {
					node.children.put(nodeNames[j], dist);
				}
			}
		}
		for (int i = 0; i < size; i++) {
			System.out.println("node:"+nodes.get(nodeNames[i]).toString());
		}		
		getPath(nodes, "1", "5");
		
	}

}
