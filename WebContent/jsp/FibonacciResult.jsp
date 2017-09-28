<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Fibonacci Result</title>
		<style type="text/css">
			.span-result{
				font-size:20px;
				font-weight:bold;
				margin-left:40px
			}
			.li-result{
				font-size:20px;
				font-weight:bold;
				list-style-type:none
			}
		</style>
	</head>
	<body>
		<p>
			<span class=\"span-result\">Fibonacci Sequence:</span>
			<ul>
				<%
					ArrayList<BigDecimal> result= (ArrayList<BigDecimal>) session.getAttribute("result");
					for (int i = 0; i < result.size(); i++) {
						out.println("<li class=\"li-result\">" + result.get(i) + "</li>");
					}		
				%>
			</ul>
	</p>
	</body>
</html>