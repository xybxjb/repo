<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="cn.deepcoding.model.Users"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>

<html>
<head id="Head1">
<base href="<%=basePath + "/"%>">
</base>
<title>深度智慧教务管理系统</title>
<link rel="icon" sizes="any" mask="" href="static/images/icon.png">
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet"
	href="static/jquery-easyui-1.4.1/themes/default/easyui.css"
	type="text/css" />
<link rel="stylesheet" href="static/jquery-easyui-1.4.1/themes/icon.css"
	type="text/css" />
<link rel="stylesheet" href="static/css/default.css" type="text/css" />
<script type="text/javascript" src='static/js/index.js'></script>

<script type="text/javascript">
$(function() {
	niname();

});
function niname() {
	$.ajax({
		 url:"users/niname",
		 type:"post",
		 datatype:"json",
		 success:function(data){
			 $("#name").html(data);
		 }});
}


	
	//设置登录窗口
	function initPwd() {
		$('#w').window({
			title : '修改密码',
			width : 300,
			modal : true,
			shadow : true,
			closed : true,
			height : 200,
			resizable : false
		});
	}
	//关闭登录窗口
	function close() {
		$('#w').window('close');
	}
	
	//修改密码
	 function serverLogin() {
		var $newpass = $('#txtNewPass');
		var $rePass = $('#txtRePass');
		var $tePass = $('#textEditPass');
		var id ;
		var $pas;
		<%if (request.getSession().getAttribute("user") != null) {%>
			id = <%=((Users) request.getSession().getAttribute("user")).getId()%>;
			pas = '<%=((Users) request.getSession().getAttribute("user")).getPassword()%>';
<%}%>
	$.ajax({
			url : "users/checkPassword",
			data : {
				'password' : $('#textEditPass').val()
			},
			type : "post",
			datatype : "json",
			success : function(data) {
				if (data) {
					if ($newpass.val() == '') {
						msgShow('系统提示', '请输入密码！', 'warning');
						return false;
					}
					if ($rePass.val() == '') {
						msgShow('系统提示', '请在一次输入密码！', 'warning');
						return false;
					}

					if ($newpass.val() != $rePass.val()) {
						msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
						return false;
					}

					$.ajax({
						url : "users/newPass",
						data : {
							password : $newpass.val(),
							id : id
						},
						type : "post",
						datatype : "json",
						success : function(data) {

							//$tePass.val('');
							$newpass.val('');
							$rePass.val('');
							close();
						}
					});
				} else {
					msgShow('系统提示', '原密码输入错误！', 'warning');
					return false;
				}

			}
		});

	}

	$(function() {
		initPwd();
		//
		$('#editpass').click(function() {
			$('#w').window('open');
		});

		$('#btnEp').click(function() {
			serverLogin();
			//alter('系统提示', '恭喜，密码修改成功！');
		})

	});
	function closeLogin() {
		$('#w').window('close');
	}
	function loginOut() {
		if (confirm("确定退出？")) {
			$.ajax({
				type : "post",
				url : "logout.action",
				success : function() {
					window.location.href = "login";
				}
			});
		}
	}
</script>

</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div class="north" region="north" split="true" border="false">
		<span style="float: right; padding-right: 20px;" class="head">
			<a href="doc.html">API文档 </a> &nbsp;&nbsp;&nbsp;&nbsp;欢迎
			<span id="name"></span> <a href="javascript:void(0)" id="editpass">修改密码</a>
			<a href="javascript:void(0)" onclick="loginOut()">安全退出</a></span> 
			<span style="padding-left: 10px; font-size: 16px;">
			<img src="static/images/blocks.gif" width="20" height="20"
			align="absmiddle" /> 深度智慧教务管理系统</span>
	</div>
	<div class="west" region="west" split="true" id="west">导航内容</div>
	<div id="mainPanle" class="mainPanle" region="center">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用"
				style="padding: 20px; text-align: center; overflow: hidden;"
				id="home">

				<p style="color: red; font-size: 72px">欢迎进入深度智慧教务管理系统</p>


			</div>
		</div>
	</div>


	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false"
		minimizable="false" maximizable="false" icon="icon-save"
		style="width: 300px; height: 200px; padding: 5px;">
		<form action="">
			<div region="center" border="false"
				style="padding: 10px; border: 1px solid #E0ECFF;">

				<div>
					<label>&nbsp;&nbsp;&nbsp;原密码：</label> <input id="textEditPass"
						type="Password" />
				</div>
				<div>
					<label>&nbsp;&nbsp;&nbsp;新密码：</label> <input id="txtNewPass"
						type="Password" />
				</div>
				<div>
					<label>确认密码：</label> <input id="txtRePass" type="Password" />
				</div>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok"
					href="javascript:void(0)"> 确定</a> <a class="easyui-linkbutton"
					icon="icon-cancel" href="javascript:void(0)" onclick="closeLogin()">取消</a>
			</div>
		</form>
	</div>

	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>


</body>
</html>