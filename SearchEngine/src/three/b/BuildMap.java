package three.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BuildMap implements Common {
	public static void writeDocidMap() {
		HashMap<Integer, Integer> docidMap = new HashMap<Integer, Integer>();
		int j = 1;
		for (int i = 1; i <= numOfDoc; i++) {
			String fileName = termsFrePath + "/" + "tf" + i + ".txt";
			File file = new File(fileName);
			if (file.exists()) {
				docidMap.put(i, j);
				j++;
			}
		}
		File docidMapFile = new File("E:/docidMap.txt");
		try {
			docidMapFile.createNewFile();
			FileWriter docidMapWriter = new FileWriter(docidMapFile, true);
			BufferedWriter docidMapBufferedWriter = new BufferedWriter(docidMapWriter);
			for (int i = 1; i <= numOfDoc; i++) {
				if (docidMap.containsKey(i)) {
					String temp = "" + i + "\t" + docidMap.get(i) + "\n";
					docidMapBufferedWriter.write(temp);
				}
			}
			docidMapBufferedWriter.close();
			docidMapWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void writeDocidTermMap() {
		HashMap<Integer, Integer> docidNumOfTermsMap = new HashMap<Integer, Integer>();
		for (int i = 1; i <= numOfDoc; i++) {
			String filePath = termsFrePath + "/" + "tf" + i + suffix;
			File tfFile = new File(filePath);
			if (tfFile.exists()) {
				try {
					int numOfTerms = 0;
					BufferedReader bfReader = new BufferedReader(new FileReader(tfFile));
					String line;
					while ((line = bfReader.readLine()) != null) {
						numOfTerms ++;
					}
					docidNumOfTermsMap.put(i, numOfTerms);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		File docidNumOfTermsMapFile = new File("E:/docidNumOfTermsMap.txt");
		try {
			docidNumOfTermsMapFile.createNewFile();
			FileWriter docidNumOfTermsMapWriter = new FileWriter(docidNumOfTermsMapFile, true);
			BufferedWriter docidNumOfTermsBufferedWriter = new BufferedWriter(docidNumOfTermsMapWriter);
			for (int i = 1; i <= numOfDoc; i++) {
				if (docidNumOfTermsMap.containsKey(i)) {
					String temp = "" + i + "\t" + docidNumOfTermsMap.get(i) + "\n";
					docidNumOfTermsBufferedWriter.write(temp);
				}
			}
			docidNumOfTermsBufferedWriter.close();
			docidNumOfTermsMapWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void mergeTfFiles() {
		File totalTfFile = new File(tfFilePath);
		try {
			totalTfFile.createNewFile();
			FileWriter totalTFileWriter;
			totalTFileWriter = new FileWriter(totalTfFile, true);
			BufferedWriter bfWriter = new BufferedWriter(totalTFileWriter);
			for (int i = 0; i <= numOfDoc; i++) {
				String filePath = termsFrePath + "/" + "tf" + i + suffix;
				File tfFile = new File(filePath);
				if (tfFile.exists()) {
					BufferedReader bfReader = new BufferedReader(new FileReader(tfFile));
					String line;
					while ((line = bfReader.readLine()) != null) {
						bfWriter.write(line + "\n");
					}
				}
			}
			bfWriter.close();
			totalTFileWriter.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//BuildMap.writeDocidMap();
		//BuildMap.writeDocidTermMap();
		BuildMap.mergeTfFiles();
	}

}
