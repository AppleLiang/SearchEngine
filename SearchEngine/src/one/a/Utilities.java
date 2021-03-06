package one.a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
/**
 * A collection of utility methods for text processing.
 */
public class Utilities {
	/**
	 * Reads the input text file and splits it into alphanumeric tokens.
	 * Returns an ArrayList of these tokens, ordered according to their
	 * occurrence in the original text file.
	 * 
	 * Non-alphanumeric characters delineate tokens, and are discarded.
	 *
	 * Words are also normalized to lower case. 
	 * 
	 * Example:
	 * 
	 * Given this input string
	 * "An input string, this is! (or is it?)"
	 * 
	 * The output list of strings should be
	 * ["an", "input", "string", "this", "is", "or", "is", "it"]
	 * 
	 * @param input The file to read in and tokenize.
	 * @return The list of tokens (words) from the input file, ordered by occurrence.
	 */
	static String delim = "1234567890~!@#$%^&*()_+<>?,./:\";[]\\{}|-=\t\n\r\f ";
	public static ArrayList<String> tokenizeFile(File input) {
		// TODO Write body!
		ArrayList<String> result = new ArrayList<String>();	//Store the result
		try {
			BufferedReader rd = new BufferedReader(new FileReader(input));
			String line;
			while((line = rd.readLine()) != null){
				StringTokenizer tokenizer = new StringTokenizer(line, delim, false);
				while(tokenizer.hasMoreElements()){
					String word = tokenizer.nextToken().toLowerCase();
					boolean isWord = true;
					for (int i = 0; i < word.length(); i++) {
						if (word.charAt(i) > 122 || word.charAt(i) < 97) {
							isWord = false;
							break;
						}
					}
					if (isWord) {
						result.add(word);
					}
				}
				
			}
			rd.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Takes a list of {@link Frequency}s and prints it to standard out. It also
	 * prints out the total number of items, and the total number of unique items.
	 * 
	 * Example one:
	 * 
	 * Given the input list of word frequencies
	 * ["sentence:2", "the:1", "this:1", "repeats:1",  "word:1"]
	 * 
	 * The following should be printed to standard out
	 * 
	 * Total item count: 6
	 * Unique item count: 5
	 * 
	 * sentence	2
	 * the		1
	 * this		1
	 * repeats	1
	 * word		1
	 * 
	 * 
	 * Example two:
	 * 
	 * Given the input list of 2-gram frequencies
	 * ["you think:2", "how you:1", "know how:1", "think you:1", "you know:1"]
	 * 
	 * The following should be printed to standard out
	 * 
	 * Total 2-gram count: 6
	 * Unique 2-gram count: 5
	 * 
	 * you think	2
	 * how you		1
	 * know how		1
	 * think you	1
	 * you know		1
	 * 
	 * @param frequencies A list of frequencies.
	 */
	public static void printPalindromes(List<Frequency> frequencies) {
		if (frequencies == null)
		{
			System.out.println("These is no input text");
		}
		for (int i = 0;i < frequencies.size(); i++)
		{
			String str = frequencies.get(i).toString();
			System.out.println(str);
		}
	}
	
	public static void printTwoGrams(List<Frequency> frequencies){
		int total = 0;
		int uni = 0;
		if (frequencies == null)
		{
			System.out.println("Total 2-gram count: " + total);
			System.out.println("Unique 2-gram count: " + uni);
			System.out.println();
		}
		else
		{
			for (int i = 0;i < frequencies.size(); i++)
			{
				total += frequencies.get(i).getFrequency();
			}
			uni = frequencies.size();
			
			System.out.println("Total 2-gram count: " + total);
			System.out.println("Unique 2-gram count: " + uni);
			System.out.println();
			
			for (int i = 0;i < frequencies.size(); i++)
			{
				String str = frequencies.get(i).toString();
				System.out.println(str);
			}
		}
	}
	public static void printFrequencies(List<Frequency> frequencies) {
		// TODO Write body!
		int total = 0;
		int uni = 0;
		if (frequencies == null)
		{
			System.out.println("Total item count: " + total);
			System.out.println("Unique item count: " + uni);
			System.out.println();
		}
		else
		{
			for (int i = 0;i < frequencies.size(); i++)
			{
				total += frequencies.get(i).getFrequency();
			}
			uni = frequencies.size();
			
			System.out.println("Total item count: " + total);
			System.out.println("Unique item count: " + uni);
			System.out.println();
			
			for (int i = 0;i < frequencies.size(); i++)
			{
				String str = frequencies.get(i).toString();
				System.out.println(str);
			}
		}
	}
}

