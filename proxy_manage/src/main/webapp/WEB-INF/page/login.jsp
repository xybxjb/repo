<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String basePath=request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>

<html>
<head id="Head1">
	<base href="<%=basePath+"/" %>"></base>
    <title>北京深度教育后台管理系统</title>
	<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
	<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
	<script type="text/javascript"	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="static/js/jquery.webcam.min.js"></script>
	<link rel="stylesheet"	href="static/jquery-easyui-1.4.1/themes/default/easyui.css"	type="text/css" />
	<link rel="stylesheet" href="static/jquery-easyui-1.4.1/themes/icon.css" type="text/css" />
	<link rel="stylesheet"	href="static/css/default.css"	type="text/css" />
</head>
<style>
	
	*{
	font-size:15px;
	}
	.login-formdiv{
		padding-left:20px;
	}
</style>
<script type="text/javascript">
var t2 ;
$(function() {
	var w = 320, h = 240;
	var pos = 0, ctx = null, saveCB, image = [];
	var canvas = document.createElement("canvas");
	canvas.setAttribute('width', w);
	canvas.setAttribute('height', h);
	if (canvas.toDataURL) {

		ctx = canvas.getContext("2d");

		image = ctx.getImageData(0, 0, w, h);

		saveCB = function(data) {

			var col = data.split(";");
			var img = image;

			for (var i = 0; i < w; i++) {
				var tmp = parseInt(col[i]);
				img.data[pos + 0] = (tmp >> 16) & 0xff;
				img.data[pos + 1] = (tmp >> 8) & 0xff;
				img.data[pos + 2] = tmp & 0xff;
				img.data[pos + 3] = 0xff;
				pos += 4;
			}

			if (pos >= 4 * w * h) {
				ctx.putImageData(img, 0, 0);
				/* $.post("servlet/CatD", {type: "data", image: canvas.toDataURL("image/png")}, function(msg){
				    console.log("===="+eval(msg));
				    pos = 0;
				    $("#img").attr("src", msg+"");
				}); */
				$.ajax({
					type : "post",
					url : "users/cat?t=" + new Date().getTime(),
					data : {
						type : "pixel",
						image : canvas.toDataURL("image/png")
					},
					dataType : "html",
					success : function(data) {
						console.log("====" + data);
						pos = 0;
						$("#img").attr("src", data);
						$("#pic").attr("value",data);
						window.setTimeout(pullPhoto(),1000*5); 
						;
					}
				});
			}
		};

	} else {

		saveCB = function(data) {
			image.push(data);

			pos += 4 * w;

			if (pos >= 4 * w * h) {
				/* $.post("servlet/CatD", {type: "pixel", image: image.join('|')}, function(msg){
				    console.log("+++++"+eval(msg));
				    pos = 0;
				    $("#img").attr("src", msg+"");
				}); */

				$.ajax({
					type : "post",
					url : "servlet/CatD",
					data : {
						type : "pixel",
						image : image.join('|')
					},
					dataType : "json",
					success : function(data) {
						console.log("+++++" + eval(msg));
						pos = 0;
						$("#img").attr("src", msg + "");
					}
				});
			}
		};
	}

	$("#webcam").webcam({
		width : w,
		height : h,
		mode : "callback",
		swffile : "static/js/jscam_canvas_only.swf",

		onSave : saveCB,

		onCapture : function() {
			webcam.save();
		},

		debug : function(type, string) {
			console.log(type + ": " + string);
			 if(string=='Camera started'){
				 t2 = window.setTimeout(AUTO(),1000*5); 	
			} 
			
		}
	});
});
//自动拍照并上传
function AUTO(){ 
		savePhoto();
	} 
//拍照
function savePhoto() {
	webcam.capture();
}
//上传
var ci=0;
function pullPhoto() {
	ci++;
	$.ajax({
		type : "post",
		url : "users/faceLogin",
		data : {
			image:$("#pic").val(),
		},
		success : function(data) {
			$("#pic").attr("value","");
			if(data==true){
				window.clearInterval(t2); 
	  			window.location.href="index";
	  		}else if(ci<4){
	  			//$.messager.alert('警告', '识别照片错误');
	  			AUTO();
	  		}else{
	  			$.messager.confirm('警告','请调整位置重新拍照',function(r){    
	  			    if (r){   
	  			    	ci=0;
	  			    	AUTO();
	  			    }})
	  		}
		}
	});
}
	function login(){
			var username=$("[name=username]").val();
			var pwd=$("[name=password]").val();
	        
			$.ajax({
				 url:"checks",
				 data:{username:username,password:pwd},
				 type:"post",
				 success:function(data){
				  		if(data==true){
				  			window.location.href="index";
				  		}else{
						$("#errorinfo").html("用户名或密码错误");
						$("#errorinfo").css("visibility","visible");
		 		}
			 },
			 dataType:"json"
		})
	}
	function keylogin(){
		if(event.keyCode==13){
			login();
		}
	}
	//人脸识别打开
	function faceLogin() {
		$.messager.confirm('警告','该人脸识别还处于测试阶段，请谨慎使用,如若超过3S无任何返应，请重新打开识别',function(r){    
		    if (r){   
		    	$('#faceAdd').window('open');
				$("#img").attr("src", "");
				$("#pic").attr("value","");
				
					
		    }})
		

	}
</script>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no" onkeyup="keylogin()">

<div style="float:left;padding-right:200px;">
</div>
<div style="width:100%;height:450px;">    	
	<div class="width">     		
		<img src="static/images/shendusss.png" width="100%" height="100%">
		<div style="width:250px;height:280px;background:#5F9F9F;filter:alpha(opacity=80);
			-moz-opacity:0.8;opacity:0.8;padding:50px;position:absolute;top:185px;right:165px;border-radius:10px">
		 	
		<p class="login-headtitle" style="font-weight: bold ; font-size: 25px; text-align: center;">欢迎登录</p>
			<form action="checks"   id="myForm" >		               				 
			<div class="err-msg"></div>
			<div class="login-formdiv">

				<label>用户名:</label>

				<input type="text" class="login-text"  value=""  name="username" id="name"  placeholder="请输入用户名">
			</div>	 
			<br>                   
			<div class="login-formdiv">
				<label >密&nbsp;&nbsp;码&nbsp;:</label>

				<input type="password" class="login-text"  value="" name="password" id="password"  placeholder="请输入密码">
				
				<input type="image"  disabled="disabled"  id="massages"  value="" />	                       
			</div>
			<div class="login-formdiv" id="errorinfo" style="visibility: hidden; height: 8px;color:red;padding-top:4px">
			</div>
			<div class="login-formdiv" style="text-align:center;">

				<p style="cursor:pointer;border-style:outset;border-width:5px;font-size:20px;font-weight: bold;
				 background:#3299CC; width:160px; margin-left: 35px; user-select:none; " 
				 type="button" onclick="login()">点击登录</p>
				 <p style="cursor:pointer;border-style:outset;border-width:5px;font-size:20px;font-weight: bold;
				 background:#3299CC; width:160px; margin-left: 35px; user-select:none; " 
				 type="button" onclick="faceLogin()">人脸登陆</p>


			</div>
			<br>
			<!--人脸添加页面  -->
<div id="faceAdd" class="easyui-window" title="人脸识别"
		closed="true" style="width: 400px; height: 300px; text-align: center;">
		<div id="webcam"></div>
	<!-- <div class="btn">
		<input type="button" value="尝试登陆" id="delBtn" onclick="pullPhoto();" />
		<input type="button" value="拍照" id="saveBtn" onclick="savePhoto();" />
	</div> -->

	<div id="photos" style="display: none;">
		<input type="text" style="display: none;" id="pic"> <img
			src="" id="img">
			<input type="text" style="display: none;" id="userid">
	</div>
		
		</div>
			<div class="login-alert">
				<input type="checkbox"  id="check_box"   class="login-input"/>下次自动登录
			</div>
		    </form>
		</div>
	</div>
</div>
</body>
</html>