package org.algorithm;

import java.util.ArrayList;
import java.util.List;

public class JustifyText {

	// Joins strings in words[start : end] with numSpaces spaces spread evenly.
	static String getFinalLine(String[] words, int start, int end, int numSpaces) {
		int nWordsLine = end - start + 1;
		StringBuilder line = new StringBuilder();
		for (int i = start; i < end; ++i) {
			line.append(words[i]);
			--nWordsLine;
			int numCurrSpace = (int) Math.ceil((double) numSpaces / nWordsLine);
			for (int j = 0; j < numCurrSpace; j++) {
				line.append(" ");
			}
			numSpaces -= numCurrSpace;
		}
		line.append(words[end]);
		for (int i = 0; i < numSpaces; i++) {
			line.append(" ");
		}
		return line.toString();
	}

	public static List<String> justifyText(String[] words, int length) {
		int currStart = 0;
		int nWordsLine = 0;
		int lineLen = 0;
		List<String> result = new ArrayList<>();
		int n = words.length;
		for (int i = 0; i < n; ++i) {
			// currStart is the first word in the current line, and i is used to
			// identify the last word.
			++nWordsLine;
			int lookaheadLineLength = lineLen + words[i].length() + (nWordsLine - 1);
			if (lookaheadLineLength == length) {
				result.add(getFinalLine(words, currStart, i, i - currStart));
				currStart = i + 1;
				nWordsLine = 0;
				lineLen = 0;
			} else if (lookaheadLineLength > length) {
				result.add(getFinalLine(words, currStart, i - 1, length - lineLen));
				currStart = i;
				nWordsLine = 1;
				lineLen = words[i].length();
			} else { // lookaheadLineLength < L.
				lineLen += words[i].length();
			}
		}
		// Handles the last line. Last line is to be left-aligned.
		if (nWordsLine > 0) {
			StringBuilder line = new StringBuilder(getFinalLine(words, currStart, words.length - 1, nWordsLine - 1));
			
			for (int i = 0; i < length - lineLen - (nWordsLine - 1); i++) {
				line.append(" ");
			}
			result.add(line.toString());
		}
		return result;
	}

	public static void main(String[] arg) {
		
		String[] words = {"Hello", "there,", "beautiful", "world","!"};

		System.out.println(justifyText(words,11));

	}

}
