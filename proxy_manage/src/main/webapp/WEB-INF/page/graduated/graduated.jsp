<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath + "/"%>"></base>
<!-- 百度编辑器 -->
<script type="text/javascript" charset="utf-8"
	src="static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="static/ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8"
	src="static/ueditor/lang/zh-cn/zh-cn.js">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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


	<!--搜索  -->

	<div
		style="margin-top: 20px; margin-left: 10px; margin-right: 10px; margin-bottom: 20px;">
		<fieldset class="form_fieldset">
			<form id="search_form" action="graduated/listAll" method="post">

				<div class="form_filed">
					<label class="ui_font" style="margin-left: -100px">标题搜索：</label> <input
						id="titleSelect" type="text" class="easyui-textbox" name="title">
				</div>
				<div class="form_filed" style="float: right; margin-top: 20px">
					<input type="button" value="查询 " class="easyui-linkbutton"
						onclick="search();"
						style="width: 70px; height: 30px; margin-left: 30px; margin-top: -3px">

					<input type="button" value="添加喜报 " class="easyui-linkbutton"
						onclick="add_graduated();"
						style="width: 100px; height: 30px; margin-left: 30px; margin-top: -3px">
					<input type="button" value="查看全部 " class="easyui-linkbutton"
						onclick="init();"
						style="width: 100px; height: 30px; margin-left: 30px; margin-top: -3px">
					<input type="button" value="重建索引库" class="easyui-linkbutton"
						onclick="updateSolr();"
						style="width: 100px; height: 30px; margin-left: 30px; margin-top: -3px">
				</div>
			</form>
		</fieldset>
		<table id="graduatedList"></table>
	</div>

	<!-- 就业信息展示 -->
	<div id="GraduatedQuery" class="easyui-window" title="文章内容展示"
		closed="true" style="width: 80%; height: 520px;">
		<!-- <h1 id="title"></h1>
		<span>创建时间：</span><span id="createTime" align="left"></span> -->
		<div id="content"></div>
		<input type="button" value="确认 " class="easyui-linkbutton"
			onclick="init();"
			style="width: 100px; height: 30px; margin-left: 40%; margin-top: 20px">
	</div>

</body>

<script type="text/javascript">
	//打开页面时运行
	$(function() {
		init();
	});

	function init() {
		//关闭查看框信息
		$('#GraduatedQuery').window('close');
		//查看所有就业信息
		$('#graduatedList')
				.datagrid(
						{
							url : 'graduated/listAll',//请求方法的地址
							title : '查询结果',
							height : window.innerHeight - 135,
							fitColumns : true, //列自适应 
							nowrap : true,//禁止文字自动换行
							pagination : true,
							singleSelect : true,
							pageNumber : 1,
							pageSize : 10,
							pageList : [ 10, 15, 30 ],
							loadMsg : '正在加载信息...',//当数据没有加载出来时显示的文字
							queryParams : {},//往后台传递参数，json格式 */
							columns : [ [
									{
										field : 'id',
										title : 'id',
										align : 'center',
										hidden : true
									},
									{
										field : 'title',
										title : '标题',
										width : 50,
										align : 'center',
										formatter : function(value, row, index) {
											return "<p style='float:left' title="+value+">"
													+ value + "</p>";

										}
									},
									{
										field : 'createTime',
										title : '录入时间',
										width : 20,
										align : 'center'
									},
									{
										field : 'graduatedTime',
										title : '毕业时间',
										width : 20,
										align : 'center'
									},
									{
										field : 'pageView',
										title : '访问量',
										width : 20,
										align : 'center'
									},
									{
										field : 'elite',
										title : '精英',
										width : 10,
										align : 'center',
										formatter : function(value, row, index) {
											if (row.elite == 1) {
												return '是'
											} else {
												return '否'
											}

										}
									},
									{
										field : 'opt',
										title : '操作',
										align : 'center',
										width : 20,
										formatter : function(value, row, index) {

											var str = '<a href="javascript:void(0);" onclick="query('
													+ row.id + ')">查看</a>|';

											str += '<a href="javascript:void(0);" onclick="update('
													+ row.id + ');">修改</a>|';

											str += '<a href="javascript:void(0);" onclick="graduatedDelete('
													+ row.id + ');">删除</a>';

											return str;
										}

									} ] ],

						});

	}
	//查看文章内容
	function query(id) {
		$("#title").empty();
		$("#createTime").empty();
		$("#content").empty();
		$.ajax({
			url : 'graduated/getGraduatedByid',//请求方法的地址
			async : true, //请求是否异步，默认为异步，这也是ajax重要特性
			data : {
				id : id
			},
			type : "post", //请求方式
			success : function(data) {
				/* $("#title").html(data.title);
				$("#createTime").html(data.createTime); */
				$("#content").append(data.content);

			}
		});

		$('#GraduatedQuery').window('open');

	}

	//跳转到添加页面
	function add_graduated() {
		window.location.replace("graduated/graduatedAddUrl");

	}
	//点击删除喜报按钮
	function graduatedDelete(id) {
		
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
				$.ajax({
					type : "post",
					url : "graduated/graduatedDelete",
					data : {
						"id" : id
					},
					success : function(data) {
						$('#graduatedList').datagrid('reload')

					}
				})
		    }    
		});  
	}
	//查询
	function search() {
		var fields = $('#search_form').serializeArray();
		var params = $("#graduatedList").datagrid('options').queryParams;
		$.each(fields, function(i, field) {
			params[field.name] = field.value;
		});
		$('#search_form').form('clear');
		$('#graduatedList').datagrid('reload')
	}
	//根据id查询修改内容
	function update(id) {

		//试着将ID以拼接的方式传至后台
		window.location.replace("graduated/graduatedUpdateUrl?id=" + id);

	}
	function updateSolr() {
		$.ajax({
			type : "post",
			url : "graduated/updateSolr",
			success : function(data) {
				if (data.status == 200) {
					$.messager.alert('提示', '创建索引库成功！');
				} else {
					$.messager.alert('提示', '创建索引库失败！');
				}
			}

		})
	}
</script>

</html>