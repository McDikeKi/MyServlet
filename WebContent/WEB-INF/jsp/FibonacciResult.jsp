<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Fibonacci Result</title>
	</head>
	<body>
		<p>
			<span>Fibonacci Sequence:</span>
			<c:forEach items="${list}" var="node">
				<br/> <c:out value="${node}"></c:out>
			</c:forEach>
	</p>
	</body>
</html>