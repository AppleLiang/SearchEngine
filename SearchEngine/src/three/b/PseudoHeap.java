package three.b;

import java.util.Arrays;
import java.util.Collections;

public class PseudoHeap {
	private QueryScoreEntry[] queryScoreEntries;
	private int numberOfEntries;
	private int currentMinIndex;

	/**
	 * build an empty heap
	 * 
	 * @param N
	 */
	public PseudoHeap(int N) {
		numberOfEntries = N;
		queryScoreEntries = new QueryScoreEntry[N];
		currentMinIndex = -1;
	}

	/**
	 * build a heap with a given query array
	 * 
	 * @param entries
	 */
	public PseudoHeap(QueryScoreEntry[] entries) {
		numberOfEntries = entries.length;
		queryScoreEntries = new QueryScoreEntry[numberOfEntries];
		for (int i = 0; i < numberOfEntries; i++) {
			queryScoreEntries[i] = new QueryScoreEntry(entries[i].docid,
					entries[i].score, entries[i].isTitleContained, entries[i].authorityScore);
		}
		findMin();

	}

	/**
	 * 
	 * @return
	 */
	public QueryScoreEntry min() {
		return queryScoreEntries[currentMinIndex];
	}

	public void replaceMin(QueryScoreEntry entry) {
		queryScoreEntries[currentMinIndex] = entry;
		findMin();
	}

	private void sort() {
		Arrays.sort(queryScoreEntries, Collections.reverseOrder());
	}

	public QueryScoreEntry[] allEntries() {
		sort();
		return queryScoreEntries;
	}

	private void findMin() {
		double minScore = Double.MAX_VALUE;
		for (int i = 0; i < numberOfEntries; i++) {
			if (queryScoreEntries[i].isTitleContained == false && queryScoreEntries[i].score < minScore) {
				minScore = queryScoreEntries[i].score;
				currentMinIndex = i;
			}
		}
	}
}
