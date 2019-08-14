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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="static/css/all.css" type="text/css" />
<link rel="stylesheet" href="static/css/default.css" type="text/css" />
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="static/js/student.js"></script>
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
			<form id="search_form" action="Dormitory/listAll" method="post">

				<div class="form_filed">
					<label class="ui_font" style="margin-left: -100px">宿舍号：</label> <input
						id="Dormitorynumber" value="请选择"> <label class="ui_font"
						style="margin-left: -100px">性别：</label> <select id="sex"
						class="easyui-combobox" style="width: 200px;">
						<option value="请选择"></option>
						<option value=1>男</option>
						<option value=2>女</option>
						<option value=0>空</option>

					</select> <label class="ui_font">学生姓名：</label> <input id="studentname"
						type="text" class="easyui-textbox" name="StudentName">
				</div>
				<div class="form_filed" style="float: right; margin-top: 20px">
					<input type="button" value="查询 " class="easyui-linkbutton"
						onclick="search();"
						style="width: 70px; height: 30px; margin-left: 30px; margin-top: -3px">

					<input type="button" value="添加宿舍 " class="easyui-linkbutton"
						onclick="add_dormitory();"
						style="width: 100px; height: 30px; margin-left: 30px; margin-top: -3px">
					<input type="button" value="查看全部 " class="easyui-linkbutton"
						onclick="init();"
						style="width: 100px; height: 30px; margin-left: 30px; margin-top: -3px">
				</div>
			</form>
		</fieldset>
		<table id="dormitoryList"></table>
	</div>

	<!-- 添加宿舍 -->

	<div id="add_dormitory" class="easyui-window" title="添加宿舍"
		closed="true" style="width: 400px; height: 400px;">
		<form action="Dormitory/DormitoryAdd" id="Dormitory_Add">
			<div class="editwindow"
				style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">
				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">宿舍号：</span>
				</div>
				<div style="width: width: 200px;">
					<input style="margin-top: 5px; float: left;" class="easyui-textbox"
						id="number" name="number">
				</div>


			</div>
			<div class="editwindow"
				style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">
				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">宿舍地址：</span>
				</div>
				<div style="width: width: 200px;">
					<input type="text" id="adress" name="adress" class="easyui-textbox">
				</div>
			</div>

			<div class="editwindow"
				style="margin-left: 10px; width: 300px; height: 30px">
				<div style="width: 100px; height: 30px; float: left;">
					<span style="float: right;">建宿日期：</span>
				</div>
				<div style="width: width: 200px;">
					<input style="float: left;" class="easyui-datebox" id="time"
						name="time">
				</div>
			</div>


			<div class="editwindow"
				style="padding-left: 20px; width: 300px; height: 30px">
				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">应住人数：</span>
				</div>
				<div style="width: width: 200px;">
					<select id="total" class="easyui-combobox" name="total"
						style="width: 143px; float: left;">
						<option value="请选择">请选择</option>
						<option value="6">6</option>
						<option value="5">5</option>
						<option value="4">4</option>
						<option value="3">3</option>
						<option value="2">2</option>
						<option value="1">1</option>
					</select>
				</div>
			</div>
			<div class="editwindow"
				style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">

				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">宿舍信息：</span>
				</div>
				<div style="width: width: 200px;">
					<input style="height: 80px; float: left;" class="easyui-textbox"
						id="note" name="note">
				</div>
			</div>
			<div class="editwindow" style="padding-left: 40px">
				<input type="button" value="保存" class="test"
					onclick="DormotoryAdd('Dormitory_Add','DormitoryAdd');"
					style="text-align: center; position: absolute; left: 40%; width: 100px; margin-top: 80px; height: 30px">
			</div>

		</form>
	</div>



	<!-- 修改宿舍 -->
	<div id="update_dormitory" class="easyui-window" title="修改宿舍"
		closed="true" style="width: 400px; height: 400px;">
		<form action="Dormitory/DormitoryUpdate" id="Dormitory_Update">
			<div class="editwindow"
				style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">

				<input style="margin-top: 5px" type="hidden" name="id" id="id">
				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">宿舍号：</span>
				</div>
				<div style="width: width: 200px;">
					<input style="margin-top: 5px; float: left;" class="easyui-textbox"
						id="Updatenumber" disabled="disabled" name="number">
				</div>
			</div>
			<div class="editwindow"
				style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">
				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">宿舍地址：</span>
				</div>
				<div style="width: width: 200px;">
					<input style="margin-top: 5px; float: left;" class="easyui-textbox"
						name="adress" id="Updateadress" class="easyui-textbox">
				</div>
			</div>
			<div class="editwindow"
				style="margin-left: 10px; width: 300px; height: 30px">
				<div style="width: 100px; height: 30px; float: left;">
					<span style="float: right;">建宿日期：</span>
				</div>
				<div style="width: width: 200px;">
					<input style="float: left;" class="easyui-datebox" id="Updatetime"
						name="time">
				</div>
			</div>
			<div class="editwindow"
				style="padding-left: 20px; width: 300px; height: 30px">
				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">应住人数：</span>
				</div>
				<div style="width: width: 200px;">
					<select id="Updatetotal" class="easyui-combobox" name="total"
						style="width: 143px; float: left;">
						<option value="请选择">请选择</option>
						<option value="6">6</option>
						<option value="5">5</option>
						<option value="4">4</option>
						<option value="3">3</option>
						<option value="2">2</option>
						<option value="1">1</option>
					</select>
				</div>
			</div>
			<div class="editwindow"
				style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">

				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">宿舍信息：</span>
				</div>
				<div style="width: width: 200px;">
					<input style="margin-top: 5px; float: left; height: 80px"
						class="easyui-textbox" name="note" id="Updatenote"
						class="easyui-textbox" class="easyui-textbox">
				</div>
			</div>

			<div class="editwindow" style="padding-left: 40px">
				<input type="button" value="更改" class="test"
					onclick="UpdateDormitory('Dormitory_Update');"
					style="text-align: center; position: absolute; left: 40%; width: 100px; margin-top: 80px; height: 30px">
			</div>
		</form>


	</div>
	<!-- 添加宿舍成员 -->
	<div id="add_student" class="easyui-window" title="添加宿舍成员"
		closed="true" style="width: 400px; height: 250px;">

		<form action="studentDormitory/StudentDormitoryAdd"
			id="StudentDormitory_Add">


			<div class="editwindow"
				style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">

				<input style="margin-top: 5px" type="hidden" name="id" id="id">
				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">请选择学生：</span>
				</div>
				<div style="width: width: 200px;">
				<input style="margin-top: 5px; float: left;"  id="student"  class="easyui-textbox"/>
					<!-- <input style="margin-top: 5px; float: left;" id="student"
					  class="easyui-textbox"	 name="student"> -->
				</div>
			</div>



			<div class="editwindow"
				style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">

				<input style="margin-top: 5px" type="hidden" name="id" id="id">
				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">请选择日期：</span>
				</div>
				<div style="width: width: 200px;">
					<input style="margin-top: 5px; float: left;" id="starttime"
						class="easyui-datebox" name="starttime">
				</div>
			</div>
			<div class="editwindow" style="padding-left: 40px">
				<input type="button" value="保存" class="test"
					onclick="StudentAdd('StudentDormitory_Add');"
					style="text-align: center; position: absolute; left: 40%; width: 100px; margin-top: 15px; height: 30px">
			</div>

		</form>


	</div>

	<!-- 宿舍信息展示 -->
	<div id="DormitoryQuery" class="easyui-window" title="宿舍信息展示"
		closed="true" style="width: 800px; height: 320px;">
		<table id="DormitoryQueryList"></table>
		<input type="button" value="确认 " class="easyui-linkbutton"
			onclick="init();"
			style="width: 100px; height: 30px; margin-left: 330px; margin-top: -3px">
	</div>
	<!-- 宿舍管理页面 -->

	<div id="DormitorySystem" class="easyui-window" title="宿舍管理"
		closed="true" style="width: 200px; height: 250px; text-align: center;"></div>
	<!-- 退宿页面 -->
	<div id="Dormitory_exit" class="easyui-window" title="学生退宿展示"
		closed="true" style="width: 800px; height: 300px;">

		<div style="margin-left: 180px; margin-button: 20px">

			<span style="margin-right: 45px">请选择退宿日期： </span> <input
				name="endtime" class="easyui-datebox" id="exittime"> </input>
		</div>
		<table id="Dormitoryexit"></table>
	</div>
	<!-- 移除宿舍页面 -->
	<div id="Dormitory_remove" class="easyui-window" title="学生移宿展示"
		closed="true" style="width: 800px; height: 300px;">

		<div style="margin-left: 180px; margin-button: 20px">

			<span style="margin-right: 45px">请选择移宿日期： </span> <input
				name="endtime" class="easyui-datebox" id="endtime"> </input>
		</div>
		<table id="Dormitoryremove"></table>
	</div>


</body>
<script type="text/javascript">
	function search() {
		var fields = $('#search_form').serializeArray();

		var params = $("#dormitoryList").datagrid('options').queryParams;
		$.each(fields, function(i, field) {
			params[field.name] = field.value;
		});
		$("#studentname").attr("value", "");

		$('#dormitoryList').datagrid('reload');
		$('#search_form').form('clear');
	}

	//根据性别查询宿舍
	$('#sex')
			.combobox(
					{
						onSelect : function(record) {
							$('#dormitoryList')
									.datagrid(
											{
												url : 'Dormitory/listAll',//请求方法的地址
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
												queryParams : {
													sex : record.value
												},//往后台传递参数，json格式 */
												columns : [ [
														{
															field : 'id',
															title : 'id',
															align : 'center',
															hidden : true
														},
														{
															field : 'number',
															title : '宿舍号',
															width : 20,
															align : 'center'
														},
														{
															field : 'total',
															title : '应住人数',
															width : 20,
															align : 'center'
														},
														{
															field : 'actual',
															title : '实住人数',
															width : 20,
															align : 'center'
														},
														{
															field : 'name',
															title : '宿舍人员',
															width : 80,
															align : 'center',
															formatter : function(
																	value, row,
																	index) {
																var name = '';
																for (var i = 0; i < row.studentdormitory.length; i++) {

																	var b = row.studentdormitory[i].student;

																	name += b.name
																			+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";

																}
																return name;

															}

														},
														{
															field : 'opt',
															title : '操作',
															align : 'center',
															width : 40,
															formatter : function(
																	value, row,
																	index) {
																var str = '<a href="javascript:void(0);" onclick="DormitorySystem('
																		+ row.id
																		+ ","
																		+ row.total
																		+ ","
																		+ row.actual
																		+ ')">宿舍管理</a>|';

																str += '<a href="javascript:void(0);" onclick="query('
																		+ row.id
																		+ ","
																		+ row.total
																		+ ","
																		+ row.actual
																		+ ')">查看</a>|';

																str += '<a href="javascript:void(0);" onclick="update('
																		+ row.id
																		+ ');">修改宿舍</a>|';

																return str;
															}

														} ] ]
											});
							$('#dormitoryList').datagrid('reload');
							$('#search_form').form('clear');
						}
					});

	//添加宿舍成员
	function StudentAdd(form) {
		var id = $('#student').textbox('getValue')
		console.log($('#starttime').textbox('getValue'))
		var date = $('#starttime').textbox('getValue')
		if ($('#student').textbox('getValue') == '请选择') {
			$.messager.alert('警告', '请选择学生');
			return false;
		}
		if ($('#starttime').textbox('getValue') == '') {
			$.messager.alert('警告', '请选择日期');
			return false;
		}
		$.ajax({
			url : "studentDormitory/StudentDormitoryAdd", //请求的url地址
			async : true, //请求是否异步，默认为异步，这也是ajax重要特性
			data : {
				'student.id' : id,
				'starttime' : date
			},
			type : "post", //请求方式
			success : function() {
				$('#add_student').window('close');
				init();
				Dormitorynumber()
				$('#add_dormitory').form('clear');
			}
		});

	}
	//添加宿舍不为空警告
	function DormitoryAddAlert() {

		if ($('#number').textbox('getValue') == '') {
			$.messager.alert('警告', '宿舍号不能为空');
			return false;
		}

		if ($('#adress').textbox('getValue') == '') {
			$.messager.alert('警告', '请填写宿舍地址');
			return false;
		}
		if ($('#time').textbox('getValue') == '') {
			$.messager.alert('警告', '请选择日期');
			return false;
		}
		if ($('#total').textbox('getValue') == '请选择') {
			$.messager.alert('警告', '请选择住宿人数');
			return false;
		}

	}

	//保存
	function DormotoryAdd(form, method) {
		//var classroom_name = $("#fromClassroomDate").textbox('getValue');
		var data = $("#" + form).serialize();

		DormitoryAddAlert()

		$.ajax({
			url : "Dormitory/" + method, //请求的url地址
			async : true, //请求是否异步，默认为异步，这也是ajax重要特性
			data : data,
			type : "post", //请求方式
			success : function(data) {

				if (data == "error") {
					$.messager.alert('警告', '已有该宿舍号，请重新添加');
					$('#Dormitory_Add').form('clear');
					return false;
				}
				$('#add_dormitory').window('close');
				$('#dormitoryList').datagrid('reload');
				$('#Dormitory_Add').form('clear');
			}
		});

	}
	//打开页面时运行
	$(function() {
		init();

		Dormitorynumber()

	});
	//宿舍下拉框
	function Dormitorynumber() {
		$('#Dormitorynumber')
				.combobox(
						{
							url : 'Dormitory/listdormitorynumber',
							selectOnNavigation : false,
							textField : 'number',
							editable : false,

							valueField : 'id',//没有这个不能选择
							onSelect : function(record) {
								Dormitorynumber()

								var params = $("#dormitoryList").datagrid(
										'options').queryParams;

								params.id = record.id;
								$("#studentname").attr("value", "");

								$('#dormitoryList').datagrid('reload');
								$('#search_form').form('clear');

							}

						});

	}

	function init() {
		//查看宿舍信息框关闭
		$('#DormitoryQuery').window('close');
		$('#dormitoryList')
				.datagrid(
						{
							url : 'Dormitory/listAll',//请求方法的地址
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
										field : 'number',
										title : '宿舍号',
										width : 20,
										align : 'center'
									},
									{
										field : 'total',
										title : '应住人数',
										width : 20,
										align : 'center'
									},
									{
										field : 'actual',
										title : '实住人数',
										width : 20,
										align : 'center'
									},
									{
										field : 'name',
										title : '宿舍人员',
										width : 80,
										align : 'center',
										formatter : function(value, row, index) {
											var name = '';
											for (var i = 0; i < row.studentdormitory.length; i++) {
												var b = row.studentdormitory[i].student;

												name += b.name
														+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
											}
											return name;
										}
									},
									{
										field : 'opt',
										title : '操作',
										align : 'center',
										width : 40,
										formatter : function(value, row, index) {
											var str = '<a href="javascript:void(0);" onclick="DormitorySystem('
													+ row.id
													+ ","
													+ row.total
													+ ","
													+ row.actual
													+ ')">宿舍管理</a>|';
											str += '<a href="javascript:void(0);" onclick="query('
													+ row.id
													+ ","
													+ row.total
													+ ","
													+ row.actual
													+ ')">查看</a>|';

											str += '<a href="javascript:void(0);" onclick="update('
													+ row.id + ');">修改宿舍</a>|';

											str += '<a href="javascript:void(0);" onclick="DormitoryDelete('
													+ row.id
													+ ","
													+ row.number
													+ ');">删除宿舍</a>';

											return str;
										}

									} ] ],
						/* 	toolbar : [ {
								iconCls : 'icon-add',
								text : '添加宿舍',
								handler : function() {
									add_dormitory()
								}
							} ] */

						});
	}
	//宿舍管理
	function DormitorySystem(id, total, actual) {

		//清空div
		$('#DormitorySystem').html("");
		//打开这个管理框
		$('#DormitorySystem').window('open');
		var MyDiv = document.getElementById("DormitorySystem");

		var put = document.createElement("button"); //createElement生成button对象
		//add.setAttribute("style","border:1px; border-style: solid ; margin-left:20px; margin-top:20px; ")
		put.className = 'easyui-linkbutton l-btn l-btn-small'; //给生产的button添加class
		put.style.height = '30px';
		put.style.width = '150px';
		put.style.marginTop = '10px';
		put.innerHTML = '增加宿舍成员';
		put.onclick = function() { //绑定点击事件
			Add(id, total, actual);
			$('#DormitorySystem').window('close');

		};

		var del = document.createElement("button"); //createElement生成button对象
		del.className = 'easyui-linkbutton l-btn l-btn-small';
		del.style.height = '30px';
		del.style.width = '150px';
		del.style.marginTop = '10px';
		del.innerHTML = '移除宿舍成员';
		del.onclick = function() { //绑定点击事件
			remove(id, total, actual);

			$('#DormitorySystem').window('close');

		};
		var bye = document.createElement("button"); //createElement生成button对象
		bye.className = 'easyui-linkbutton l-btn l-btn-small';
		bye.style.height = '30px';
		bye.style.width = '150px';
		bye.style.marginTop = '10px'
		bye.innerHTML = '宿舍成员退宿';
		bye.onclick = function() { //绑定点击事件
			exit(id, total, actual);
			$('#DormitorySystem').window('close');

		};
		//将三个按钮添加到div
		MyDiv.appendChild(put);
		MyDiv.appendChild(del);
		MyDiv.appendChild(bye);

	}
	//添加宿舍
	function add_dormitory() {
		$('#add_dormitory').window('open');

	}
	//向宿舍添加成员
	function Add(id, total, actual) {
		if (actual == total|actual>total) {
			$.messager.alert('警告', '宿舍已满，请换宿舍添加');
			return false;
		}
		$.ajax({
			url : "Dormitory/setdormitoryID", //请求的url地址
			async : true, //请求是否异步，默认为异步，这也是ajax重要特性
			data : {
				id : id
			},
			type : "post", //请求方式
		});

		$('#add_student').window('open');
		$("#student").combobox({          
			//后台返回的 json 数据方法地址         
			url : 'Dormitory/getstudnt',
			valueField:'id',//相当于option的value值
			textField:'name',//相当于<option></option>之间的显示值 value:1000    //默认显示值
			
	                
		});
	}
	//查看宿舍信息
	function query(id, total, actual) {
		if (actual == "0") {
			$.messager.alert('警告', '该宿舍为空');
			return false;
		}

		$('#DormitoryQuery').window('open');
		$('#DormitoryQueryList')
				.datagrid(
						{
							url : 'Dormitory/GetStudentDormitoryById',//请求方法的地址
							//title : '查询结果',
							//height :600px;,
							fitColumns : true, //列自适应 
							nowrap : true,//禁止文字自动换行
							singleSelect : true,
							loadMsg : '正在加载信息...',//当数据没有加载出来时显示的文字
							queryParams : {
								"id" : id,

							},//往后台传递参数，json格式 */
							columns : [ [
									{
										field : 'id',
										title : 'id',
										align : 'center',
										hidden : true

									},
									{
										field : 'name',
										title : '宿舍人员',
										width : 20,
										align : 'center',

									},
									{
										field : 'time',
										title : '住宿日期',
										width : 20,
										align : 'center'
									},
									{
										field : 'starttime',
										title : '宿舍创建时间',
										width : 40,
										align : 'center'
									},
									{
										field : 'adress',
										title : '宿舍地址',
										width : 40,
										align : 'center'
									},
									{
										field : 'note',
										title : '宿舍信息',
										width : 40,
										align : 'center'
									},
									{
										field : 'opt',
										title : '操作',
										align : 'center',
										width : 40,
										formatter : function(value, row, index) {
											var str = '<a href="javascript:void(0);" onclick="DormitorylleaderSystem('
													+ row.id + ')">设为宿舍长</a>';
											;
											return str;
										}

									}

							] ]
						});

	}
	//设置为宿舍长
	function DormitorylleaderSystem(id) {
		$.ajax({
			type : "post",
			data : {
				"id" : id
			},
			url : "studentDormitory/DormitorylleaderSystem",

		})
		init()

	}

	//修改宿舍前查询

	function update(id) {
		$.ajax({
			type : "post",
			data : {
				"id" : id
			},
			url : "Dormitory/GetById",
			success : function(data) {
				console.log(data.number)
				$("#id").val(data.id);
				$("#Updatenumber").textbox("setValue", data.number);
				$("#Updatetime").textbox("setValue", data.time);
				$("#Updatetotal").textbox("setValue", data.total);
				$("#Updateadress").textbox("setValue", data.adress);
				//$("#note").val(data.note);
				$("#Updatenote").textbox("setValue", data.note);

			}

		})
		$('#update_dormitory').window('open');

	}
	//修改宿舍
	function UpdateDormitory(form) {
		//var classroom_name = $("#fromClassroomDate").textbox('getValue');
		var data = $("#" + form).serialize();
		console.log(data)
		if ($('#Updatenumber').textbox('getValue') == '') {
			$.messager.alert('警告', '宿舍号不能为空');
			return false;
		}

		if ($('#Updateadress').textbox('getValue') == '') {
			$.messager.alert('警告', '请填写宿舍地址');
			return false;
		}
		if ($('#Updatetime').textbox('getValue') == '') {
			$.messager.alert('警告', '请选择日期');
			return false;
		}
		if ($('#Updatetotal').textbox('getValue') == '请选择') {
			$.messager.alert('警告', '请选择住宿人数');
			return false;
		}

		$.ajax({
			url : "Dormitory/DormitoryUpdate", //请求的url地址
			async : true, //请求是否异步，默认为异步，这也是ajax重要特性
			data : data,
			type : "post", //请求方式
			success : function(req) {

				if (req == "error") {
					$.messager.alert('警告', '已有该宿舍号，请重新添加');
					$('#Dormitory_Add').form('clear');
					return false;
				}
				$('#update_dormitory').window('close');
				$('#tableid1').datagrid('reload');
				$('#update_dormitory').form('clear');
				init();
			}
		});

	}

	//退宿前弹窗
	function exit(id, total, actual) {
		if (actual == "0") {
			$.messager.alert('警告', '该宿舍为空');
			return false;
		}
		$.ajax({
			url : "Dormitory/setdormitoryID", //请求的url地址
			async : true, //请求是否异步，默认为异步，这也是ajax重要特性
			data : {
				id : id
			},
			type : "post", //请求方式
		});
		if (window.confirm('该操作不可恢复，请慎重选择！')) {

			$('#Dormitory_exit').window('open');

			$('#Dormitoryexit')
					.datagrid(
							{
								url : 'Dormitory/GetStudentDormitoryById',//请求方法的地址
								//title : '查询结果',
								//height :600px;,
								fitColumns : true, //列自适应 
								nowrap : true,//禁止文字自动换行
								singleSelect : true,
								loadMsg : '正在加载信息...',//当数据没有加载出来时显示的文字
								queryParams : {
									"id" : id,

								},//往后台传递参数，json格式 */
								columns : [ [
										{
											field : 'id',
											title : 'id',
											align : 'center',
											//width : 20,
											hidden : true
										},

										{
											field : 'name',
											title : '宿舍人员',
											width : 20,
											align : 'center',

										},
										{
											field : 'time',
											title : '住宿日期',
											width : 20,
											align : 'center'
										},
										{
											field : 'opt',
											title : '操作',
											align : 'center',
											width : 40,
											formatter : function(value, row,
													index) {
												var str = '<a href="javascript:void(0);" onclick="Dormitoryexit('
														+ row.id + ')">退宿</a>';
												return str;
											}

										}

								] ]
							});
		}
		return false;
		//$.messager.alert('警告', warning);

	}
	//退宿逻辑跳转
	function Dormitoryexit(id) {
		var endtime = $('#exittime').textbox('getValue');
		console.log(endtime)
		if ($('#exittime').textbox('getValue') == '') {
			$.messager.alert('警告', '请选择退宿日期');
			return false;
		}
		$.ajax({
			url : "studentDormitory/studentexit", //请求的url地址
			async : true, //请求是否异步，默认为异步，这也是ajax重要特性
			data : {
				'student.id' : id,
				'endtime' : endtime,
			},
			type : "post", //请求方式
			success : function(req) {

				$('#Dormitory_exit').window('close');
				$('#dormitoryList').datagrid('reload');
				$('#Dormitory_exit').form('clear');
			}
		});

	} //移除宿前弹窗
	function remove(id, total, actual) {

		if (actual == "0") {
			$.messager.alert('警告', '该宿舍为空');
			return false;
		}
		//将宿舍ID放入request域中
		$.ajax({
			url : "Dormitory/setdormitoryID", //请求的url地址
			async : true, //请求是否异步，默认为异步，这也是ajax重要特性
			data : {
				id : id
			},
			type : "post", //请求方式
		});
		
		$.messager.confirm('确认','移除后，请尽快添加至新宿舍',function(r){    
		    if (r){    
		    	$('#Dormitory_remove').window('open');

				$('#Dormitoryremove')
						.datagrid(
								{
									url : 'Dormitory/GetStudentDormitoryById',//请求方法的地址
									//title : '查询结果',
									//height :600px;,
									fitColumns : true, //列自适应 
									nowrap : true,//禁止文字自动换行
									singleSelect : true,
									loadMsg : '正在加载信息...',//当数据没有加载出来时显示的文字
									queryParams : {
										"id" : id,

									},//往后台传递参数，json格式 */
									columns : [ [
											{
												field : 'id',
												title : 'id',
												align : 'center',
												//width : 20,
												hidden : true
											},

											{
												field : 'name',
												title : '宿舍人员',
												width : 20,
												align : 'center',

											},
											{
												field : 'time',
												title : '住宿日期',
												width : 20,
												align : 'center'
											},
											{
												field : 'opt',
												title : '操作',
												align : 'center',
												width : 40,
												formatter : function(value, row,
														index) {
													var str = '<a href="javascript:void(0);" onclick="Dormitoryremove('
															+ row.id
															+ ')">搬离该宿舍</a>';
													return str;
												}

											}

									] ]
								}); 
		    }    
		});
		return false;
	}

	//移除宿舍逻辑跳转
	function Dormitoryremove(id) {
		var endtime = $('#endtime').textbox('getValue');
		if ($('#endtime').textbox('getValue') == '') {
			$.messager.alert('警告', '请选择退宿日期');
			return false;
		}
		$.ajax({
			url : "studentDormitory/studentremove", //请求的url地址
			async : true, //请求是否异步，默认为异步，这也是ajax重要特性
			data : {
				'student.id' : id,
				endtime : endtime
			},
			type : "post", //请求方式
			success : function(req) {

				$('#Dormitory_remove').window('close');
				init();
				Dormitorynumber()
				$('#Dormitory_remove').form('clear');
			}
		});
	}
	//删除宿舍
	function DormitoryDelete(id, number) {
		
		$.messager.confirm('确认','您确定删除+'+number+'+号宿舍嘛？',function(r){    
		    if (r){    
		    	$.ajax({
					url : "Dormitory/deleteDormitory", //请求的url地址
					async : true, //请求是否异步，默认为异步，这也是ajax重要特性
					data : {
						id : id,
					},
					type : "post", //请求方式
					success : function(req) {

						$('#Dormitory_remove').window('close');
						init();
						Dormitorynumber()
						$('#Dormitory_remove').form('clear');
					}
				});
		    }    
		});
		
		
	}
</script>
</html>