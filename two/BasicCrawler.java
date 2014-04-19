import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.Header;

public class BasicCrawler extends WebCrawler {
	 private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" + "|png|tiff?|mid|mp2|mp3|mp4"
             + "|wav|avi|mov|mpeg|ram|m4v|pdf" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");


/**
* You should implement this function to specify whether the given url
* should be crawled or not (based on your crawling logic).
*/
@Override
public boolean shouldVisit(WebURL url) {
     String href = url.getURL().toLowerCase();
     Pattern FILTERS2 = Pattern.compile(".*\\.ics.uci.edu.*");
     //return !FILTERS.matcher(href).matches() && FILTERS2.matcher(href).matches();
     Pattern FILTERS3 = Pattern.compile(".*calendar.ics.uci.edu.*");
     //return !FILTERS.matcher(href).matches() && href.startsWith("http://www.ics.uci.edu/");
     Pattern FILTERS4 = Pattern.compile(".*archive.ics.uci.edu.*");
     Pattern FILTERS5 = Pattern.compile(".*drzaius.ics.uci.edu.*");
     Pattern FILTERS6 = Pattern.compile(".*fano.ics.uci.edu.*");
     Pattern FILTERS7 = Pattern.compile(".*djp3-pc2.ics.uci.edu.*");
     Pattern FILTERS8 = Pattern.compile(".*wics.ics.uci.edu/events.*");
     return !FILTERS.matcher(href).matches() && !FILTERS3.matcher(href).matches() && FILTERS2.matcher(href).matches() && 
     !FILTERS4.matcher(href).matches() && !FILTERS5.matcher(href).matches() && !FILTERS7.matcher(href).matches() && !FILTERS6.matcher(href).matches() && !FILTERS8.matcher(href).matches();
}

/**
* This function is called when a page is fetched and ready to be processed
* by your program.
*/

public void visit(Page page) {
     int docid = page.getWebURL().getDocid();
     String url = page.getWebURL().getURL();
     
     // save url
     try {
		saveUrl(url, docid);
	 } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 }
     String domain = page.getWebURL().getDomain();
     String path = page.getWebURL().getPath();
     String subDomain = page.getWebURL().getSubDomain();
     String parentUrl = page.getWebURL().getParentUrl();
     String anchor = page.getWebURL().getAnchor();
     
    
     System.out.println("Docid: " + docid);
     System.out.println("URL: " + url);
     System.out.println("Domain: '" + domain + "'");
     System.out.println("Sub-domain: '" + subDomain + "'");
     System.out.println("Path: '" + path + "'");
     System.out.println("Parent page: " + parentUrl);
     System.out.println("Anchor text: " + anchor);
     
     if (page.getParseData() instanceof HtmlParseData) {
             HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
             String text = htmlParseData.getText();
             String title = htmlParseData.getTitle();
             // save title
             try {
            	 saveTitle(title, docid);
         	} catch (IOException e) {
         		// TODO Auto-generated catch block
         		e.printStackTrace();
         	}
             // save text
             try {
				saveText(text, docid);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             // save outgoing links
//             String html = htmlParseData.getHtml();
//             List<WebURL> links = htmlParseData.getOutgoingUrls();
//             try {
//				saveLinks(links, docid);
//			} catch (IOException e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//             System.out.println("Text length: " + text.length());
//             System.out.println("Html length: " + html.length());
//             System.out.println("Number of outgoing links: " + links.size());
     }

     Header[] responseHeaders = page.getFetchResponseHeaders();
     if (responseHeaders != null) {
             System.out.println("Response headers:");
             for (Header header : responseHeaders) {
                     System.out.println("\t" + header.getName() + ": " + header.getValue());
             }
     }
     
     System.out.println("=============");
}
public static void saveUrl (String url, int docid) throws IOException{
	FileWriter urlWriter = new FileWriter("E:/url.txt",true);
    urlWriter.write(docid + "  " + url + "\n");
    urlWriter.close();

}
public static void saveText (String text, int docid) throws IOException{
	File file = new File("E:/text/text" + docid + ".txt");
	FileWriter textWriter = new FileWriter(file);
    textWriter.write(text);
    textWriter.close();
}
//public static void saveLinks(List<WebURL> webUrls, int docid) throws IOException {
//	File file = new File("E:/outgoinglinks/" + docid + ".txt");
//	FileWriter linksWriter = new FileWriter(file, true);
//	BufferedWriter linksBfWriter = new BufferedWriter(linksWriter);
//	for (WebURL tempUrl : webUrls) {
//		linksBfWriter.write(tempUrl.getURL() + "\n");
//	}
//	linksBfWriter.close();
//	linksWriter.close();
//}
public static void saveTitle (String title, int docid) throws IOException{
	FileWriter urlWriter = new FileWriter("E:/title.txt",true);
    urlWriter.write(docid + "  " + title + "\n");
    urlWriter.close();
}
//public static void saveLength (int length) throws IOException{
//	FileWriter lengthWriter = new FileWriter("E:/length.txt",true);
//    lengthWriter.write(length + "\r\n");
//    lengthWriter.close();
//}
}
