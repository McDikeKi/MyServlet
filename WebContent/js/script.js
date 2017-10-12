var HIDDEN = 0; 
var FOR_APPEND = 1;
var FOR_RENAME = 2;
var FOR_INSERT = 3;
var textState = HIDDEN;
var tagId = "";
var idAccumulator=1;
var insertId="";
var renameId="";


$(document).ready(function(){
	
	$("#names").append('<li class="li-name"><input class="input-name" type="text" onblur="addName(this)"/></li>');
	
	$("#nameinput").keyup(function(e){
        if(e.keyCode == 13)
        	this.blur();
    });  
	
	$("#inputForm").validate({
		invalidHandler : function(){
			return false;
		},
		submitHandler : function(){
			getResult();
			return false;
		},
		rules: {
			startindex: {
			    required: true,
			    digits:true,
			    min:0
			},
		   	interval: {
			   	required: true,
			   	digits:true,
			   	min:1
		   	}
		},
		messages: {
			startindex: {
				required: "Start is required",
				digits:"Please enter only digits",
				min:"Must be greater than or equal to 0"
			},
			interval: {
		        required: "Interval is required",
		        digits:"Please enter only digits",
		        min:"Must be greater than or equal to 1"
			}
		}
	});		
});

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

function checkRequest(){
	var startindextext = $("#startindex").val().trim();
	var interval = $("#interval").val().trim();
	var nameNum = $("#nameslist li").length;
	if(nameNum<=0&&startindextext===""&&interval===""){
		return false;
	}
	if((!Number.isInteger(Number(startindextext)))||(!Number.isInteger(Number(interval)))){
		return false;
	}
	if(Number(startindextext)<0||Number(interval)<0){
		return false;
	}
	if(Number(startindextext)>=nameNum){
		return false;
	}
	return true;
}

function getResult(){
	//var circletext = $("#circle").val().trim();
//	if(!checkRequest()){
//		alert("Illegal input");
//		return;
//	}
	var startindextext = $("#startindex").val().trim();
	var interval = $("#interval").val().trim();
	var nameNum = $("#nameslist li").length;
	var json = {};	
	var request = {};
	//var persons = circletext.trim().split(",");
	var persons = [];
	$("#nameslist").children("li").each(function(){
		$(this).children("label").each(function(){
			persons.push($(this).text());
		});
	});
	
	request.start = parseInt(startindextext);
	request.interval = parseInt(interval);
	request.persons = persons;
	json.circle = request;
	console.log("json:"+JSON.stringify(json));
	
	$.ajax({  
		url:'/solve/JosephProblem/ProblemSolve',
        type:'post',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(json),
        dataType:'json',
        success:function(msg){ 
        	if(msg.person == null){
        		var errorMessage = "Illegal input\r\n";
        		var errors = msg.errors;
        		for(var i = 0;i<errors.length;i++){
        			errorMessage += errors[i].field+" - "+"Error field:"+errors[i].message;
        		}
        		alert(errorMessage);
        	}
        	else{
	        	var obj = msg;
	        	var result = obj.person;
	        	$("#resultinput").text(result.toString());
        	}
        }, 
    });
}

function setButtons(labelId){
	$('#'+labelId).parent().hover(
			function(){
				$(this).children('div').css("display","block");
			},function(){
				$(this).children('div').css("display","none");
	});
	
	$('#'+labelId).parent().children('div').children().hover(
			function(){
				$(this).css("background-color","#EBEBEB");
			},function(){
				$(this).css("background-color","#fff");
	});
	
	$('#'+labelId+'1').click(function(){
		$("#nameinput").css("display","block");
		$("#nameinput").removeAttr("disabled");
		$("#nameinput").focus();
		textState = FOR_INSERT;
		insertId = labelId;
	});	
	
	$('#'+labelId+'2').click(function(){
		$('#'+labelId+'').parent().remove();
	});
	
	$('#'+labelId+'3').click(function(){
		if($("#"+labelId).parent().prev().length != 0){
			addNameLabelBefore($("#"+labelId).parent().prev(),$("#"+labelId).text());
			$('#'+labelId+'').parent().remove();
		}
		else{
			$(this).blur();
		}
	});
	
	$('#'+labelId+'4').click(function(){
		if($("#"+labelId).parent().next().length != 0){
			addNameLabelAfter($("#"+labelId).parent().next(),$("#"+labelId).text());
			$('#'+labelId+'').parent().remove();
		}
		else{
			$(this).blur();
		}
	});
	
	$('#'+labelId+'5').click(function(){
		$("#"+labelId).css("background","#EBEBEB");
		$("#nameinput").css("display","block");
		$("#nameinput").removeAttr("disabled");
		$("#nameinput").focus();
		textState = FOR_RENAME;
		renameId = labelId;
	});
}

function addNameLabelAppend(name){
	var labelId=idAccumulator.toString();
	var zeroNum = 3-labelId.length;
	var zeroString = "";
	for(var i = 0;i < zeroNum;i++){
		zeroString += "0";
	}
	labelId = zeroString+labelId;
	
	$("#nameslist").append('<li class="li-for-name" id="li2" onblur="show()">'+
			'<label class="label-name" id="'+labelId+'">'+name+'</label>'+
			'<div class="div-option" >'+
				'<button class="button-lable-option" type="button" id="'+labelId+'1">Insert</button>'+
				'<button class="button-lable-option" type="button" id="'+labelId+'2">Delete</button>'+
				'<button class="button-lable-option" type="button" id="'+labelId+'3">Up</button>'+
				'<button class="button-lable-option" type="button" id="'+labelId+'4">Down</button>'+
				'<button class="button-lable-option" type="button" id="'+labelId+'5">Rename</button>'+
			'</div>'+
	'</li>');
		
	setButtons(labelId);
	idAccumulator++;
}

function addNameLabelBefore(locationLi,name){
	var labelId=idAccumulator.toString();
	var zeroNum = 3-labelId.length;
	var zeroString = "";
	for(var i = 0;i < zeroNum;i++){
		zeroString += "0";
	}
	labelId = zeroString+labelId;
	
	$(locationLi).before('<li class="li-for-name" id="li2" onblur="show()">'+
			'<label class="label-name" id="'+labelId+'">'+name+'</label>'+
			'<div class="div-option" >'+
				'<button class="button-lable-option" type="button" id="'+labelId+'1">Insert</button>'+
				'<button class="button-lable-option" type="button" id="'+labelId+'2">Delete</button>'+
				'<button class="button-lable-option" type="button" id="'+labelId+'3">Up</button>'+
				'<button class="button-lable-option" type="button" id="'+labelId+'4">Down</button>'+
				'<button class="button-lable-option" type="button" id="'+labelId+'5">Rename</button>'+
			'</div>'+
	'</li>');
		
	setButtons(labelId);
	idAccumulator++;
}

function addNameLabelAfter(locationLi,name){
	var labelId=idAccumulator.toString();
	var zeroNum = 3-labelId.length;
	var zeroString = "";
	for(var i = 0;i < zeroNum;i++){
		zeroString += "0";
	}
	labelId = zeroString+labelId;
	
	$(locationLi).after('<li class="li-for-name" id="li2" onblur="show()">'+
			'<label class="label-name" id="'+labelId+'">'+name+'</label>'+
			'<div class="div-option" >'+
				'<button class="button-lable-option" type="button" id="'+labelId+'1">Insert</button>'+
				'<button class="button-lable-option" type="button" id="'+labelId+'2">Delete</button>'+
				'<button class="button-lable-option" type="button" id="'+labelId+'3">Up</button>'+
				'<button class="button-lable-option" type="button" id="'+labelId+'4">Down</button>'+
				'<button class="button-lable-option" type="button" id="'+labelId+'5">Rename</button>'+
			'</div>'+
	'</li>');
		
	setButtons(labelId);
	idAccumulator++;
}

function blurfunction(){
	if(textState==FOR_APPEND){
		if($("#nameinput").val()!=""){
			addNameLabelAppend($("#nameinput").val());
			
			$("#nameinput").val("");
			$("#nameinput").attr("disabled","disabled");
			$("#nameinput").css("display","none");
			textState=HIDDEN;
		}
		else{
			$("#nameinput").val("");
			$("#nameinput").attr("disabled","disabled");
			$("#nameinput").css("display","none");
			textState=HIDDEN;
		}
	}
	if(textState==FOR_INSERT){
		if($("#nameinput").val()!=""){
			addNameLabelBefore($('#'+insertId).parent(),$("#nameinput").val());
			
			insertId="";
			$("#nameinput").val("");
			$("#nameinput").attr("disabled","disabled");
			$("#nameinput").css("display","none");
			textState=HIDDEN;
		}
		else{
			insertId = "";
			$("#nameinput").val("");
			$("#nameinput").attr("disabled","disabled");
			$("#nameinput").css("display","none");
			textState=HIDDEN;
		}
	}
	if(textState==FOR_RENAME){
		if($("#nameinput").val()!=""){
			$("#"+renameId).text($("#nameinput").val());
			$("#"+renameId).css("background","#fff");
			
			renameId="";
			$("#nameinput").val("");
			$("#nameinput").attr("disabled","disabled");
			$("#nameinput").css("display","none");
			textState=HIDDEN;
		}
		else{
			$("#"+renameId).css("background","#fff");
			renameId="";
			$("#nameinput").val("");
			$("#nameinput").attr("disabled","disabled");
			$("#nameinput").css("display","none");
			textState=HIDDEN;
		}
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

function append(){
	$("#nameinput").css("display","block");
	$("#nameinput").removeAttr("disabled");
	textState=FOR_APPEND;
	$("#nameinput").focus();
};

function clearAll(){
	$("ol li").each(function(){
		if($(this).attr("class")==="li-for-name"){
			$(this).remove();
		}
	});
	idAccumulator = 1;
}
