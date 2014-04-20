<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>result</title>
</head>
<body>
<%  
	// get the input query
	String input = request.getParameter("query");  
	ArrayList<QueryScoreEntry[]> result = new ArrayList<QueryScoreEntry[]>();
	ArrayList<String> queries = new ArrayList<String>();
	queries.add(input);
	result = QueryScore.calculateVectorScoreWithTitle(queries);
	//String google = "www.google.com";
	//out.println(input);
	// build doc id url map
	HashMap<Integer, String> docidUrlMap = NDCG.buildDocidUrlMap();
	HashMap<Integer, String> docidTitleMap = NDCG.buildDocidTitleMap();
	String[] resultUrl = new String[5];
	String[] resultTitle = new String[5];
	for(int i = 0; i < 5; i++) {
		resultUrl[i] = docidUrlMap.get(result.get(0)[i].docid);
		resultTitle[i] = docidTitleMap.get(result.get(0)[i].docid);
	}
	
%> 
<p>
<a href= "<%out.println(resultUrl[0]);%>"><h3><%out.println(resultTitle[0]);%></h3></a>
<% out.println(resultUrl[0]); %><br />
</p><br />
<p>
<a href= "<%out.println(resultUrl[1]);%>"><h3><%out.println(resultTitle[1]);%></h3></a>
<% out.println(resultUrl[1]); %><br />
</p><br />
<p>
<a href= "<%out.println(resultUrl[2]);%>"><h3><%out.println(resultTitle[2]);%></h3></a>
<% out.println(resultUrl[2]); %><br />
</p><br />
<p>
<a href= "<%out.println(resultUrl[3]);%>"><h3><%out.println(resultTitle[3]);%></h3></a>
<% out.println(resultUrl[3]); %><br />
</p><br />
<p>
<a href= "<%out.println(resultUrl[4]);%>"><h3><%out.println(resultTitle[4]);%></h3></a>
<% out.println(resultUrl[4]); %><br />
</p><br />
</body>
</html>