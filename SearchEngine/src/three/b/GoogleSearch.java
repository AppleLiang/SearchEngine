package three.b;

import com.google.gson.Gson;
import three.b.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class GoogleSearch {
	public static ArrayList<String> search(String input) throws IOException {
		ArrayList<String> output = new ArrayList<String>();
		HashMap<String, Integer> urlDocidMap = NDCG.buildUrlDocidMap();
		for (int i = 0;; i = i + 4) {
			String address = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&start="+i+"&q=";
			String query = input;
			String charset = "UTF-8";
			URL url = new URL(address + URLEncoder.encode(query, charset));
			Reader reader = new InputStreamReader(url.openStream(), charset);
			GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
	 
			int total = results.getResponseData().getResults().size();
			System.out.println("total: "+total);
			// Show title and URL of each results
			for(int j=0; j <= total-1; j++){
				if (output.size() < 5) {
					//System.out.println("Title: " + results.getResponseData().getResults().get(j).getTitle());
					//System.out.println("URL: " + results.getResponseData().getResults().get(j).getUrl() + "\n");
					String url1 = results.getResponseData().getResults().get(j).getUrl();
					if (url1.charAt(4) == 's') {
						StringBuilder b = new StringBuilder("http");
						b.append(url1.substring(5));
						url1 = b.toString();
					}
					if(urlDocidMap.containsKey(url1)) {
						output.add(url1);
					}

				}
			}
			if(output.size() == 5) {
				break;
			}
		}
		return output;
	}
	public static void main(String args[]) {
		// get search queries
//		ArrayList<String> queries = new ArrayList<String>();
//		try {
//			BufferedReader rd = new BufferedReader(new FileReader("E:/queries.txt"));
//			String line;
//			while ((line = rd.readLine()) != null) {
//				queries.add(line);
//			}
//			rd.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ArrayList<String> result = new ArrayList<String>();
		try {
			result = GoogleSearch.search("REST" + " site:ics.uci.edu");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String str : result) {
			System.out.println(str);
		}
		System.out.println();
	}
}

class GoogleResults{
	 
    private ResponseData responseData;
    public ResponseData getResponseData() { return responseData; }
    public void setResponseData(ResponseData responseData) { this.responseData = responseData; }
    public String toString() { return "ResponseData[" + responseData + "]"; }
 
    static class ResponseData {
        private List<Result> results;
        public List<Result> getResults() { return results; }
        public void setResults(List<Result> results) { this.results = results; }
        public String toString() { return "Results[" + results + "]"; }
    }
 
    static class Result {
        private String url;
        private String title;
        public String getUrl() { return url; }
        public String getTitle() { return title; }
        public void setUrl(String url) { this.url = url; }
        public void setTitle(String title) { this.title = title; }
        public String toString() { return "Result[url:" + url +",title:" + title + "]"; }
    }
}