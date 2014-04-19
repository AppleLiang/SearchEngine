package ir.assignments.two;

import ir.assignments.one.a.Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Problems {
	public static void countSubdomains(File input) throws IOException
	{
		ArrayList<String> urls = Utilities.tokenizeFile(input);
		HashMap<String, Integer> subDomains = new HashMap<String, Integer>();
		String domain = "ics.uci.edu";
		for(String url : urls)
		{
			int startOfDomain = url.indexOf(domain);
			int endOfDomain = startOfDomain + domain.length();
			String subDomain = url.substring(0, endOfDomain);
			if(!subDomains.containsKey(subDomain))
			{
				subDomains.put(subDomain, 1);
			}
			else
			{
				int num = subDomains.get(subDomain);
				num ++;
				subDomains.put(subDomain, num);
			}
		}
		ArrayList<String> temp = new ArrayList<String>();
		for(String str : subDomains.keySet())
		{
			String s = str + ", " + subDomains.get(str) + "\r\n";
			temp.add(s);
		}
		Collections.sort(temp);
		String path = "E:/Subdomains.txt";
		for(String str : temp)
		{
			FileWriter writer = new FileWriter(path,true);
			writer.write(str);
			writer.close();
		}
	}
	public static void findLongestPage(File input1, File input2)
	{
		ArrayList<String> urls = new ArrayList<String>();
		ArrayList<String> lengthStr = new ArrayList<String>();
		urls = Utilities.tokenizeFile(input1);
		lengthStr = Utilities.tokenizeFile(input2);
		ArrayList<Integer> length = new ArrayList<Integer>();
		for(String str : lengthStr)
		{
			int temp = Integer.parseInt(str);
			length.add(temp);
		}
		int maxLength = 0;
		for(int i : length)
		{
			if(i > maxLength)
			{
				maxLength = i;
			}
		}
		int index = length.indexOf(maxLength);
		String maxLengthUrl = urls.get(index);
		System.out.println("url with max length: " + maxLengthUrl + "\r\n" + "length: " + maxLength);
		
	}
	public static void findNumOfPages(File input)
	{
		ArrayList<String> urls = new ArrayList<String>();
		urls = Utilities.tokenizeFile(input);
		System.out.println("num of unique pages is: " + urls.size());
	}

}
