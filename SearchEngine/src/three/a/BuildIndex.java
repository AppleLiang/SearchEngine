package three.a;

import one.a.*;
import one.b.WordFrequencyCounter;
import three.b.Common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.Set;

public class BuildIndex implements Common{
	HashMap<String, Integer> documentFre;
	HashMap<String, Double> idfMap;


	/** this method is used to count frequency of every term in every page
	 * 
	 */
	public void counterTermFrequencyAndDocumentFrequency() {
		for (int i = 1; i <= numOfDoc; i++) {
			String filename = path + '/' + "text" + i + suffix;
			File input = new File(filename);
			if (input.exists()) {
				ArrayList<String> terms = new ArrayList<String>();
				// use method from project1 to count term frequency
				terms = Utilities.tokenizeFile(input);
				ArrayList<Frequency> termsFrequency = (ArrayList<Frequency>) WordFrequencyCounter
						.computeWordFrequencies(terms);
				if (termsFrequency == null) continue;
				// calculate the sqrt sum of term frequencies
				int sqrSum = 0;
				for (Frequency tempFrequency : termsFrequency) {
					sqrSum += tempFrequency.getFrequency() * tempFrequency.getFrequency();
				}
				double sqrtSum = Math.sqrt((double)sqrSum);
				// below is to write term frequency to disk and count document
				// frequency
				File tfFile = new File(termsFrePath + "/" + "tf" + i + suffix);
				try {
					tfFile.createNewFile();
					StringBuilder builder = new StringBuilder();
					for (Frequency temp : termsFrequency) {
						builder.append(temp.getText() + "\t"
								+ temp.getFrequency() + "\t" + ((double)temp.getFrequency() / sqrtSum) + "\n");
						// then count document frequency
						if (documentFre.containsKey(temp.getText())) {
							documentFre.put(temp.getText(),
									documentFre.get(temp.getText()) + 1);
						} else {
							documentFre.put(temp.getText(), 1);
						}
					}
					FileWriter tfFileWriter = new FileWriter(tfFile, true);						
					tfFileWriter.write(builder.toString());
					tfFileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				continue;
			}
		}
			// write document frequency to disk and compute the corresponding
			// idf
			File df = new File(documentFreFile);
			Set<Entry<String, Integer>> tempSet = documentFre.entrySet();
			for (Map.Entry<String, Integer> tempEntry : tempSet) {
				FileWriter dfFileWriter;
				String term = tempEntry.getKey();
				int frequency = tempEntry.getValue();
				try {
					dfFileWriter = new FileWriter(df, true);
					dfFileWriter.write(term + " " + frequency + "\n");
					dfFileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// add an entry in the idf hashmap
				idfMap.put(term, calculateIDF(frequency));
			}
		
	}


	/**
	 * this method is used to find positions of every term in every document
	 */
	public void findPositions() {
		String termsPosition = "/Users/Zhusong/Documents/workspace/Project 1/Assignment 1/tp";
		for (int i = 1; i <= numOfDoc; i++) {
			String filename = path + '/' + "text" + i + suffix;
			File input = new File(filename);
			if (input.exists()) {
				ArrayList<String> terms = new ArrayList<String>();
				terms = Utilities.tokenizeFile(input);
				HashMap<String, ArrayList<Integer>> tp = new HashMap<String, ArrayList<Integer>>();
				for (int j = 0; j < terms.size(); j++) {
					String temp = terms.get(j);
					if (tp.containsKey(temp)) {
						tp.get(temp).add(j);
					} else {
						ArrayList<Integer> positions = new ArrayList<Integer>();
						positions.add(j);
						tp.put(temp, positions);
					}
				}
				File tpFile = new File(termsPosition + "/" + "tp" + i + suffix);
				try {
					tpFile.createNewFile();
					Set<Map.Entry<String, ArrayList<Integer>>> tempSet = tp
							.entrySet();
					FileWriter tpFileWriter = new FileWriter(tpFile, true);
					BufferedWriter tpBufferedWriter = new BufferedWriter(
							tpFileWriter);
					for (Map.Entry<String, ArrayList<Integer>> tempEntry : tempSet) {
						StringBuilder bd = new StringBuilder();
						bd.append(tempEntry.getKey() + "\t");
						for (int temp : tempEntry.getValue()) {
							bd.append(temp + " ");
						}
						tpBufferedWriter.write(bd.toString());
						tpBufferedWriter.write("\n");
					}
					tpBufferedWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				continue;
			}
		}
	}

	/**
	 * helper function to calculate the inverted document frequency
	 * 
	 * @param df
	 * @return
	 */
	private double calculateIDF(int df) {
		return Math.log10(numOfDoc / (double) df);
	}

	private double calculateTF(int tf) {
		if (tf == 0) {
			return 0;
		}
		return 1 + Math.log10((double) tf);
	}

	/**
	 * this method is used to calculate the tf-idf of every term
	 */
	public void calculateTFIDF() {
		for (int i = 1; i <= numOfDoc; i++) {
			String inputFileName = termsFrePath + "/" + "tf" + i + suffix;
			File inputFile = new File(inputFileName);
			String outputFileName = tfidfFolderPath + "/" + "tf-idf" + i
					+ suffix;
			File outputFile = new File(outputFileName);
			if (inputFile.exists()) {
				try {
					outputFile.createNewFile();
					FileWriter writer = new FileWriter(outputFile, true);
					BufferedReader rd = new BufferedReader(new FileReader(
							inputFile));
					String line;
					while ((line = rd.readLine()) != null) {
						StringTokenizer tokenizer = new StringTokenizer(line);
						String term = tokenizer.nextToken();
						int termFrequency = Integer.parseInt(tokenizer
								.nextToken());
						double tf = calculateTF(termFrequency);
						double idf = idfMap.get(term);
						double tfidf = tf * idf;
						writer.write(term + "\t" + tf + "\t" + idf + "\t"
								+ tfidf + "\n");
					}
					writer.close();
					rd.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public BuildIndex(){
		documentFre = new HashMap<String, Integer>();
		idfMap = new HashMap<String, Double>();
	}
	
	public static void main(String arg[]) {
		BuildIndex buildIndex = new BuildIndex();
		buildIndex.counterTermFrequencyAndDocumentFrequency();
		buildIndex.calculateTFIDF();
	}
}
