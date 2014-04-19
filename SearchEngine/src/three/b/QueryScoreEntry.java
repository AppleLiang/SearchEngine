package three.b;

import java.util.HashMap;

public class QueryScoreEntry implements Comparable<QueryScoreEntry> {
	public int docid;
	public double score;
	public boolean isTitleContained;
	public int authorityScore;

	public QueryScoreEntry() {
		docid = 0;
		score = 0;
		isTitleContained = false;
		authorityScore = 0;
	}

	public QueryScoreEntry(int docid, double score) {
		this.docid = docid;
		this.score = score;
		this.isTitleContained = false;
		authorityScore = 0;
	}

	public QueryScoreEntry(int docid, double score, boolean anchor) {
		this.docid = docid;
		this.score = score;
		isTitleContained = anchor;
		authorityScore = 0;
	}

	public QueryScoreEntry(int docid, double score, boolean anchor,
			int authority) {
		this.docid = docid;
		this.score = score;
		isTitleContained = anchor;
		authorityScore = authority;
	}

	@Override
	public String toString() {
		String result = "docid: " + docid + "\t" + "score: " + score + "\t"
				+ "contain anchor?: " + isTitleContained;
		return result;
	}

	public String toString(HashMap<Integer, String> tempMap) {
		String result = "docid: " + docid + "\t" + "url: " + tempMap.get(docid)
				+ "\t" + "score: " + score;
		return result;
	}

	@Override
	public int compareTo(QueryScoreEntry o) {
		// Compare anchor text first
		if (this.isTitleContained == true && o.isTitleContained == false) {
			return 1;
		} else if (this.isTitleContained == false
				&& o.isTitleContained == true) {
			return -1;
		} else {
			// compare authority now
			if (this.authorityScore > o.authorityScore) {
				return 1;
			} else if (this.authorityScore < o.authorityScore) {
				return -1;
			} else {
				// finally compare tf-idf or cosine score
				if (this.score > o.score) {
					return 1;
				} else if (this.score < o.score) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

}
