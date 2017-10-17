<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Joseph Problem Input</title>
	<link rel="stylesheet" type="text/css" href="../css/mycss.css">	
	<script src="../js/jquery.js"></script>
	<script src="../js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="../js/script.js"></script>	
</head>
<body class="body-background">
	<div class="div-background">
		<form id="inputForm" method="POST">  
			<ul class="ul-input">
				<li class="li-title">Joseph Problem Solve</li>
				<li class="li-input">
					<div>
						<label class="label-tag">Start Index</label>
						<input class="input-text" type="text" id="startindex" name="startindex"/>
						<label class="error" id="startError"></label>
					</div>
				</li>
				<li class="li-input">
					<div>
						<label class="label-tag">Interval</label>
						<input class="input-text" type="text" id="interval" name="interval"/>
						<label class="error" id="intervalError"></label>
					</div>
				</li>
				<li class="li-input">
					<div>
						<label class="label-names-tag">Names</label>
						<ol class="ol-name" id="nameslist">	</ol>
						<label class="label-error" id="personsError">&nbsp;</label>
					</div>
				</li>
				<li>
					<div class="div-input-buttons">
							<input class="input-name-field" type="text" id="nameinput" disabled="disabled" onblur="blurfunction()"/>
							<div class="div-append-clear">
								<button class="button-append" type="button" id="appendbt" onclick="append()">Append</button>
								<button class="button-clear" type="button" id="clearbt" onclick="clearAll()">Clear</button>
							</div>
					</div> 
				</li>
			</ul>
			<ul class="ul-result">
				<li class="li-input">
					<div>
						<label class="label-tag">Result</label>
						<p class="p-result" id="resultinput">&nbsp;</p>
					</div>
				</li>
				<li class="li-input">
					<div>
						<input class="submit-newjoseph" type="submit" id="newSubmit" value="Calculate"/>
					</div>
				</li>
			</ul>
		</form>
	</div>
</body>
</html>