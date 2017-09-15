function addLi(value){
	if(value!=""){
		$("#namelist").append('<li><input type="text" onblur="addLi(this.value)"></input></li>');
	}
}

function onSubmit(){
	var requestStr="";
	$("li").each(function(){
		var name = $(this).children()[0].value;
		if(name != ""){
			requestStr += name+" ";
		}	
	});
	$("#circle").val(requestStr);
	return true;
}	

function onSubmit(){
	var requestStr="";
	$("li").each(function(){
		var name = $(this).children()[0].value;
		if(name != ""){
			requestStr += name+" ";
		}	
	});
	$("#circle").val(requestStr);
	return true;
}

function getResult(){
	var circletext = $("#circle").val().trim();
	var startindextext = $("#startindex").val().trim();
	var interval = $("#interval").val().trim();
	if(circletext!=""&&startindextext!=""&&interval!=""){
		var json = {};
		var circleobj = [];
		
		
		var circlearr = circletext.trim().split(",");
		for(var i = 0;i<circlearr.length;i++){
			var row = {};
			row.name = circlearr[i];
			circleobj.push(row);
		}
		json.circle = circleobj;
		json.start = parseInt(startindextext);
		json.interval = parseInt(interval);
		
		var start = {};
		$.ajax({  
	        url:'NewJosephServlet',  
	        type:'post',
	        datatype:'json',
	        data: "json="+JSON.stringify(json),
	        success:function(msg){  
	        	var obj = JSON.parse(msg);
	        	var result = obj.result;
	        	$("#resultinput").val(result.toString());
	        } 
	    });
	}
}

function addName(input){
	if(input.value!=""){
		if(true){
			$("#names").append('<li class="li-name"><input class="input-name" type="text" onblur="addName(this)"/></li>');
			
			$(".input-name").keyup(function(e){
		        if(e.keyCode == 13)
		        	this.blur();
		    });
		}
	}
}



$(document).ready(function(){
	$("#names").append('<li class="li-name"><input class="input-name" type="text" onblur="addName(this)"/></li>');
	
	$(".input-name").keyup(function(e){
        if(e.keyCode == 13)
        	this.blur();
    });           
});
