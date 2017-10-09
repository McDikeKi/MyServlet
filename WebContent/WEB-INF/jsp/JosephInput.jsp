<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Joseph Problem Solve</title>
	<link rel="stylesheet" type="text/css" href="css/mycss.css">
	<script src="js/jquery.js"></script>
	<script type="text/javascript" src="js/script.js"></script>
</head>
<body>
	<form action="http://localhost:8080/solve/JosephResult"
		method="post">
		<input type="hidden" id="circle" name="circle">
		<p style="font-size: 15px; font-weight: bold">Input the circle(Separated by spaces):</p>  
		<ol id="namelist">
			<li><input type="text" onblur="addLi(this.value)"/></li>
		</ol>
		
		<div style="margin-top: 20px">
			<p class="start-text">Start:</p>
			<input class="start-input" type="text" name="start" />
			<p class="interval-text">Interval:</p>
			<input class="interval-input" type="text" name="interval"/> 
			<input class="joseph-submit-bt" type="submit" value="submit" onkeypress="checkEnter()" onclick="return onSubmit();" />
		</div>
	</form>
</body>
</html>