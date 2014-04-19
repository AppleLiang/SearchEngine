package one.b;

import one.a.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Counts the total number of words and their frequencies in a text file.
 */
public final class WordFrequencyCounter {
	/**
	 * This class should not be instantiated.
	 */
	private WordFrequencyCounter() {}
	
	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lowercase alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique word in the original list. The frequency of each word
	 * is equal to the number of times that word occurs in the original list. 
	 * 
	 * The returned list is ordered by decreasing frequency, with tied words sorted
	 * alphabetically.
	 * 
	 * The original list is not modified.
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["this", "sentence", "repeats", "the", "word", "sentence"]
	 * 
	 * The output list of frequencies should be 
	 * ["sentence:2", "the:1", "this:1", "repeats:1",  "word:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of word frequencies, ordered by decreasing frequency.
	 */
	public static List<Frequency> computeWordFrequencies(List<String> words) {
		// TODO Write body!
		if(words.size() == 0)
		{
			return null;
		}
		// count every word's frequency using hashmap
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0; i < words.size(); i++)
		{
			String temp = words.get(i);
			if(map.containsKey(temp))
			{
				int f = map.get(temp);
				f++;
				map.put(temp, f);
			}
			else
			{
				map.put(temp, 1);
			}
		}
		
		// find the maximum frequency in order to make bucket sort
		int size = map.size();
		int freqarray[] = new int[size];
		int j = 0;
		for(String temp1:map.keySet())
		{
			freqarray[j] = map.get(temp1);
			j++;
		}
		Arrays.sort(freqarray);
		int maxfreq = freqarray[size - 1];
		
		// sort the frequency using buckest sort
		LinkedList<String>[] bucket = new LinkedList[maxfreq + 1];
		for(int m = 0;m < bucket.length; m++)
		{	
			bucket[m] = new LinkedList<String>();
		}
		
		for(String temp2:map.keySet())
		{
			int f = map.get(temp2);
			bucket[f].addLast(temp2);
		}
		for(int n = 0; n <= maxfreq; n++)
		{
			if(bucket[n].size() == 0)
			{
				continue;
			}
			else
			{
				Collections.sort(bucket[n]);
			}
		}
		//return the result
		ArrayList<Frequency> fre = new ArrayList<Frequency>();
		for(int k = maxfreq; k > 0; k--)
		{
			LinkedList<String> cur1 = bucket[k];
			if(cur1 == null)
			{
				continue;
			}
			else
			{
				for(String temp3:cur1)
				{
					Frequency f = new Frequency(temp3,k);
					fre.add(f);
				}
			}
		}
		return fre;
		
	}
	
	/**
	 * Runs the word frequency counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
		File file = new File("E:/testFile.txt");
		List<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computeWordFrequencies(words);
		Utilities.printFrequencies(frequencies);
	}
}
