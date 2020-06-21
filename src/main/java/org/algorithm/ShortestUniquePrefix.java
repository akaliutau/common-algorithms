package org.algorithm;

import java.util.HashMap;
import java.util.Map;

public class ShortestUniquePrefix {

	static class TreeNode {
		public boolean isString = false;
		public Map<Character, TreeNode> leaves = new HashMap<>();
	}

	static class Tree {
		private TreeNode root = new TreeNode();

		public boolean add(String s) {
			TreeNode p = root;
			int len = s.length();
			for (int i = 0; i < len; i++) {
				char c = s.charAt(i);
				if (!p.leaves.containsKey(c)) {
					p.leaves.put(c, new TreeNode());
				}
				p = p.leaves.get(c);
			}
			// s already existed in this tree.
			if (p.isString) {
				return false;
			} else { 
				p.isString = true; // Inserts s into this tree.
				return true;
			}
		}

		public String getShortestUniquePrefix(String s) {
			TreeNode p = root;
			int len = s.length();
			StringBuilder prefix = new StringBuilder();
			for (int i = 0; i < len; i++) {
				char c = s.charAt(i);
				prefix.append(c);
				if (!p.leaves.containsKey(c)) {
					return prefix.toString();
				}
				p = p.leaves.get(c);
			}
			return "";
		}
	}

	public static String findShortestPrefix(String s, String[] dict) {
		// Builds a tree using given dictionary.
		Tree tree = new Tree();
		for (String word : dict) {
			tree.add(word);
		}
		return tree.getShortestUniquePrefix(s);
	}

	public static void main(String[] arg) {
		
		String[] dict = {"dog", "be", "car", "cut"};

		System.out.println(findShortestPrefix("cat", dict));

	}

}
