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
	        contentType: "application/json; charset=utf-8",
	        data: JSON.stringify(json),
	        dataType:'json',
	        success:function(msg){  
	        	var obj = msg;
	        	var result = obj.person;
	        	$("#resultinput").val(result.toString());
	        }, 
	    });
	}
}

function blurFunction(){
	if(textState==FOR_APPEND){
		$("#inputfield").before();
	}
}

function addName(input){
	y = input;
	if(input.value!=""){
		//input.attr('id','thisinput');
		//alert(input.id);
		x = $("ol li").last();
		x.children().attr('id',' temporaryid');
		if(input.id===x.children().attr('id')){		
			$("#names").append('<li class="li-name"><input class="input-name" type="text" onblur="addName(this)"/></li>');
			$(".input-name").keyup(function(e){
		        if(e.keyCode == 13)
		        	this.blur();
		    });
		}
		x.children().attr('id','');
	}
	else{
		x = $("ol li").last();
		x.children().attr('id',' temporaryid');
		if(input.id!=x.children().attr('id')){	
			$(input).attr('id','deletthis');
			$("ol li").each(function(){
				if($(this).children().attr('id')==input.id){
					$(this).remove();
				}
			});
		}
		x.children().attr('id','');
	}
	
	var circleText='';
	$('ol li').each(function(){
		circleText += $(this).children().val().toString()+';';
	});
	
	circleText.substring(0, 2);
	$('#circle').val(circleText);
	
}



$(document).ready(function(){
	var HIDDEN = 0; 
	var FOR_APPEND = 1;
	var FOR_RENAME = 2;
	var textState = HIDDEN;
	var tagId = "";
	
	$("#names").append('<li class="li-name"><input class="input-name" type="text" onblur="addName(this)"/></li>');
	
	$("#inputfield").keyup(function(e){
        if(e.keyCode == 13)
        	this.blur();
    });     
	
	$('#li1').hover(
			function(){
				$(this).children('div').css("display","block");
			},function(){
				$(this).children('div').css("display","none");
	});
	
	$('#li1').children('div').children().hover(
			function(){
				$(this).css("background-color","#EBEBEB");
			},function(){
				$(this).css("background-color","#fff");
	});
	
	$('#li2').hover(
			function(){
				$(this).children('div').css("display","block");
			},function(){
				$(this).children('div').css("display","none");
	});
	
	$('#li2').children('div').children().hover(
			function(){
				$(this).css("background-color","#EBEBEB");
			},function(){
				$(this).css("background-color","#fff");
	});
			
});

function append(){
	$("#inputfield").removeAttr("disabled");
	textState=FOR_APPEND;
};
