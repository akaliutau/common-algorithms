package org.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *	Used to find all paths in graph  
 */
public class DijkstraWithDuplicates {

	public static final int MAX_DIST = 100000;

	public static class Node {
		String id;
		int dist = MAX_DIST;
		int distMin;
		Node prevMin;
		List<Node> prev = new ArrayList<>();// includes alternative paths
		Map<Node, Integer> references = new HashMap<>();// map: linked Node=>distance

		public Node(String id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "Id:" + id + ",dist=" + dist + ",connections:" + references.size();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
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
			Node other = (Node) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
	}

	public static class Graph {

		private final List<Node> nodes = new ArrayList<>();
		private final Map<String, Node> nodeMap = new HashMap<>();
		private final Integer size;

		public Graph(String[] nodeNames, int[][] adjacentMatrix) {
			size = nodeNames.length;
			for (int i = 0; i < size; i++) {
				String name = String.valueOf(nodeNames[i]);
				nodeMap.put(name, new Node(name));
			}
			for (int i = 0; i < size; i++) {
				Node node = nodeMap.get(nodeNames[i]);
				for (int j = 0; j < size; j++) {
					Integer dist = adjacentMatrix[i][j];
					if (dist != 0) {
						Node reference = nodeMap.get(nodeNames[j]);
						node.references.put(reference, dist);
					}
				}
			}
			nodes.addAll(nodeMap.values());
		}

		public void getPath(String startNodeId, String endNodeId) {
			Node startNode = nodeMap.get(startNodeId);
			Node endNode = nodeMap.get(endNodeId);

			// create a set of processed nodes
			List<Node> toProcess = new ArrayList<>();
			List<Node> toProcess2 = new ArrayList<>();
			Set<String> processed = new HashSet<>();

			toProcess.add(startNode);
			toProcess2.add(startNode);
			for (Node node : nodes) {
				if (!startNode.equals(node)) {
					toProcess.add(node);
					toProcess2.add(node);
				}
			}
			// set distance for initial node
			startNode.dist = 0;

			// process all nodes
			while (toProcess.size() > 0) {
				Node curNode = toProcess.remove(0);
				for (Node refNode : curNode.references.keySet()) {
					if (processed.contains(refNode.id) || refNode.equals(curNode))
						continue;
					int dist = curNode.references.get(refNode) + curNode.dist;
					if (refNode.dist > dist) {
						refNode.prevMin = curNode;
						refNode.dist = dist;
						refNode.distMin = dist;
					}
				}
				processed.add(curNode.id);
			}
			System.out.println("min distance=" + endNode.dist);
			// set distance for initial node
			processed.clear();

			for (Node node : toProcess2) {
				node.dist = MAX_DIST;
			}
			startNode.dist = 0;
			// process all nodes
			while (toProcess2.size() > 0) {
				Node curNode = toProcess2.remove(0);
				for (Node refNode : curNode.references.keySet()) {
					if (processed.contains(refNode.id) || refNode.equals(curNode))
						continue;
					int dist = curNode.references.get(refNode) + curNode.dist;
					if (refNode.dist >= dist) {
						if (refNode.distMin == dist) {
							refNode.prev.add(curNode);
						}
						refNode.dist = dist;
					}
				}
				processed.add(curNode.id);
			}

			// restore path using backtracing
			Node curNode = endNode;
			String path = endNode.id;
			trace(startNode, curNode, path);
		}

		public static void trace(Node startNode, Node curNode, String path) {
			if (curNode.equals(startNode)) {
				System.out.println("found path:" + path);
				return;
			}
			for (Node route : curNode.prev) {
				trace(startNode, route, route.id + "->" + path);
			}
		}

		public void print() {
			for (int i = 0; i < size; i++) {
				System.out.println("node:" + nodes.get(i).toString());
			}
		}
	}

	/**
	 * Test data input
	 */
	static final int[][] adjacentMatrix = 
			new int[][] { 
				{ 1, 1, 2, 3, 0 }, 
				{ 0, 1, 1, 0, 4 }, 
				{ 0, 0, 1, 0, 1 },
				{ 0, 0, 0, 1, 1 }, 
				{ 0, 0, 0, 0, 1 } 
			};

	static final String[] nodeNames = new String[] { "1", "2", "3", "4", "5" };

	public static void main(String[] arg) {

		Graph graph = new Graph(nodeNames, adjacentMatrix);
		graph.getPath("1", "5");

	}

}
