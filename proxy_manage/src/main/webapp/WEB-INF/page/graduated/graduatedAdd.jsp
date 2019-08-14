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
<head>
<base href="<%=basePath + "/"%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="static/css/default.css" type="text/css" />
<link rel="stylesheet" href="static/css/all.css" type="text/css" />
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
<link rel="stylesheet" href="static/css/bootstrap.min.css"
	type="text/css" />
<title>Insert title here</title>
</head>
<body>

		<div>

			<form id="graduated" >
				<input style="margin-top: 5px" type="hidden" name="id" id="id">
				<div class="win"
					style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">
					<div style="width: 80px; height: 30px; float: left;">
						<span style="float: right;">学生姓名：</span>
					</div>
					<div style="width: width: 200px;">
						<input style="width: 150px; float: left;" id="nameadd"  class="easyui-combobox" data-options="    
				        valueField: 'id',    
				        textField: 'name',    
				        url: 'student/findAll',    
				        onSelect: function(rec){    
				            var url = 'student/findStuByName?id='+rec.id;    
				            $('#phoneadd').combobox('reload', url);    
	       			 }" />
					</div>
				</div>
				
				<div class="win"
					style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">
					<div style="width: 80px; height: 30px; float: left;">
						<span style="float: right;">联系方式：</span>
					</div>
					<div style="width: width: 200px;">
						<input style="width: 150px; float: left;" id="phoneadd" name="student.id"  class="easyui-combobox" data-options="valueField:'id',textField:'tel'">
					</div>
				</div>
				<div class="win"
					style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">
					<div style="width: 80px; height: 30px; float: left;">
						<span style="float: right;">标&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp题：</span>
					</div>
					<div style="width: width: 200px;">
						<input type="text" class="easyui-textbox"
							style="width: 150px; float: left;" id="titleadd" name="title">
					</div>
				</div>
				<div class="win"
					style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">
					<div style="width: 80px; height: 30px; float: left;">
						<span style="float: right;">毕业时间：</span>
					</div>
					<div style="width: width: 200px;">
						<input type="text" class="easyui-datebox"
							style="width: 150px; float: left;" id="graduatedTimeadd"
							name="graduatedTime">
					</div>
				</div>
				<div class="win"
					style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">
					<div style="width: 80px; height: 30px; float: left;">
						<span style="float: right;">是否精英：</span>
					</div>
					<div style="width: width: 200px;">
						<select id="elite" class="easyui-combobox" name="total"
							style="width: 143px; float: left;">
							<option value="请选择">请选择</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</div>
				</div>
				<!-- 现将内容放到code中（中转站），然后ueditor再从这里取值 -->
				<code id="testcon" style="display: none;"></code>
				<!--这是富文本编辑器区域-->
				<!-- <textarea id="editorupdate" required="required"
					style="width: 100%; height: 500px; display: none;"></textarea> -->
				<script id="editor" type="text/plain"
					style="width: 100%; height: 500px;"></script>
			</form>
		</div>
	<div id="btns" style="margin-left: 45%; margin-top: 30px">
		<div>
			<button class="easyui-linkbutton" style="width: 200px; height: 30px;"
				onclick="getContentTxt()">确认保存</button>
		</div>

	</div>


	<script type="text/javascript">
		var ue;
		//打开页面时运行
		$(function() {
			var id = '${id}';
			//调用远程获取ID接口 判断是否有ID  有则为修改,没有则是添加
			if (id != '') {
				//这里是ID有值
				$.ajax({
					url : 'graduated/getGraduatedByid',//请求方法的地址
					async : true, //请求是否异步，默认为异步，这也是ajax重要特性
					data : {
						id : id
					},
					type : "post", //请求方式
					success : function(data) {
						//通过ID获取要修改的内容信息
						$("#id").val(data.id);
						$("#titleadd").textbox("setValue", data.title);
						console.log(data.elite)
						if (data.elite == 1) {
							$("#elite").textbox("setValue", "是");
						} else {
							$("#elite").textbox("setValue", "否");
						}

						$("#graduatedTimeadd").textbox("setValue",
								data.graduatedTime);
						//从数据库获取内容
						$("#testcon").html(data.content);
						window.setTimeout(setContent, 1000);//一秒后再调用赋值方法
					}
				});
			} else {
				ue = UE.getEditor('editor',{   
					   enterTag : 'br'
					});
				//ue = UE.getEditor('editor');
			}
			/* studentName(); */
		}
		);
		/* function studentName(){
			$("#nameadd").combobox({
				valueField: 'id',    
		        textField: 'name',    
		        url: 'student/findAll',    
		        onSelect: function(rec){    
		            var url = 'student/findStuByName?id='+rec.id;    
		            $('#phoneadd').combobox('reload', url);
		        }
			})
		} */
		//赋值
		function setContent() {
			ue = UE.getEditor('editor',{   
				   enterTag : 'br'
				});
			var content = $('#testcon').html();
			// editor准备好之后才可以使用
			ue.addListener("ready", function() {
				//赋值
				ue.setContent(content);
			});
		}
		//保存按钮
		function getContentTxt() {
			//这里修改和添加共用一个
			//获取富文本信息内容
			var content = ue.getContent();
			//获取标题
			var title = $('#titleadd').textbox('getValue');
			//获取就业时间
			var graduatedTime = $('#graduatedTimeadd').textbox('getValue');
			//获取是否是精英等信息
			var elite ;
			var student_id;
			//根据是否有ID判断是添加还是修改  
			if ($("#id").val() != '') {
				var id = $("#id").val();

				if ($('#titleadd').textbox('getValue') == '') {
					$.messager.alert('警告', '标题不能为空');
					return false;
				}
				if ($('#graduatedTimeadd').textbox('getValue') == '') {
					$.messager.alert('警告', '毕业时间不能为空');
					return false;
				}
				if ($('#elite').textbox('getValue') == '请选择') {
					$.messager.alert('警告', '请选择是否是精英');
					return false;
				}
				if ($('#elite').textbox('getValue') == '是'|$('#elite').textbox('getValue') == '1') {
					elite = 1
				} else {
					elite = 0
				}
				 $.ajax({
					url : "graduated/graduatedUpdate",
					async : true,
					type : "post", //请求方式
					data : {
						"id" : id,
						"title" : title,
						"graduatedTime" : graduatedTime,
						"content" : content,
						"elite" : elite,
					},
					success : function(data) {
						window.location.replace("graduated/index");
						$('#graduated').form('clear');
					}
				});
				return false;
			}

			if ($('#titleadd').textbox('getValue') == '') {
				$.messager.alert('警告', '标题不能为空');
				return false;
			}
			if ($('#graduatedTimeadd').textbox('getValue') == '') {
				$.messager.alert('警告', '毕业时间不能为空');
				return false;
			}
			if ($('#elite').textbox('getValue') == '请选择') {
				$.messager.alert('警告', '请选择是否是精英');
				return false;
			}
			elite=$('#elite').textbox('getValue')
			student_id=$("#phoneadd").combobox("getValue");
			console.log(elite)

			 $.ajax({
				url : "graduated/graduatedAdd",
				type : "post",
				data : {
					"title" : title,
					"graduatedTime" : graduatedTime,
					"content" : content,
					"elite" : elite,
					"student.id":student_id,
				},
				success : function(data) {
					if(data=='success'){
						$.messager.alert('提示', '保存成功');
					}else{
						$.messager.alert('警告', '标题不能为空');
					}
					
					$('#graduated').form('clear');
					window.location.replace("graduated/index");
				}
			});

		}
	</script>
	<!-- 百度编辑器 -->
<script type="text/javascript" charset="utf-8"
	src="static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="static/ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8"
	src="static/ueditor/lang/zh-cn/zh-cn.js">
	
</script>
</html>

