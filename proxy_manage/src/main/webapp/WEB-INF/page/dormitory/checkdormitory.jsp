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
			<form id="search_form" action="Dormitory/listAll" method="post"
				style="margin: 0px; padding: 0px">

				<div class="form_filed">
					<label class="ui_font">宿舍号：</label> <input id="Dormitorynumber"
						name="did" value="请选择"> <label class="ui_font">查寝时间：</label>
					<input name="Checkdata" class="easyui-datebox" id="Checkdata"></input>

				</div>
				<div class="form_filed">
					<input type="button" value="查询 " class="easyui-linkbutton"
						onclick="search();"
						style="width: 70px; height: 30px; margin-left: 30px; margin-top: -3px">
					<input type="button" value="添加查寝记录 " class="easyui-linkbutton"
						onclick="checkdormitoryadd();"
						style="width: 100px; height: 30px; margin-left: 30px; margin-top: -3px">
				</div>


			</form>
		</fieldset>
		<table id="checkdormitoryList"></table>
	</div>
	<!-- 添加查寝记录 -->
	<div id="add_checkdormitory" class="easyui-window" title="添加查寝记录"
		closed="true" style="width: 400px; height: 400px;">
		<form action="CheckDormitory/CheckDormitoryAdd" id="CheckDormitoryAdd">

			<div class="editwindow"
				style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">
				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">宿舍号：</span>
				</div>
				<div style="width: width: 200px;">
					<input type="text" style="width: 150px; float: left;"
						id="DormitorynumberAdd" name="did" value="请选择">
				</div>
			</div>


			<div class="editwindow"
				style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">
				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">查寝时间：</span>
				</div>
				<div style="width: width: 200px;">
					<input type="text" style="width: 150px; float: left;"
						name="Checkdata" class="easyui-datebox" id="CheckdataAdd">
				</div>
			</div>


			<div class="editwindow"
				style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">
				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">实住人数：</span>
				</div>
				<div style="width: width: 200px;">
					<select id="actualAdd" class="easyui-combobox" name="actual"
						style="width: 150px; float: left;">
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
					<span style="float: right;">缺勤人数：</span>
				</div>
				<div style="width: width: 200px;">
					<select id="absenceAdd" class="easyui-combobox" name="absence"
						style="width: 150px; float: left;">
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
					<span style="float: right;">查寝情况：</span>
				</div>
				<div style="width: width: 200px;">
					<select id="situationadd" class="easyui-combobox" name="situation"
						style="width: 150px; float: left;">
						<option value="请选择">请选择</option>
						<option value="3">吵闹</option>
						<option value="2">缺人</option>
						<option value="1">正常</option>
					</select>
				</div>
			</div>

			<div class="editwindow"
				style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">
				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">查寝备注：</span>
				</div>
				<div style="width: width: 200px;">
					<input type="text" id="noteadd" name="note" class="easyui-textbox"
						style="height: 80px; width: 150px; float: left;">
				</div>
			</div>


			<div class="editwindow" style="padding-left: 40px">
				<input type="button" value="保存" class="test"
					onclick="CheckDormotoryAdd();"
					style="text-align: center; position: absolute; left: 40%; width: 100px; margin-top: 63px; height: 30px">
			</div>

		</form>


	</div>




	<!-- 修改查寝记录 -->
	<div id="update_checkdormitory" class="easyui-window" title="修改查寝记录"
		closed="true" style="width: 400px; height: 400px;">
		<form action="CheckDormitory/CheckDormitoryAdd"
			id="CheckDormitoryUpdate">




			<div class="editwindow"
				style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">
				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">宿舍号：</span>
				</div>
				<div style="width: width: 200px;">
					<input type="text"
						style="width: 150px; float: left; margin-left: 26px"
						disabled="disabled" id="number">
				</div>
			</div>

			<div class="editwindow"
				style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">
				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">查寝时间：</span>
				</div>
				<div style="width: width: 200px;">
					<input type="text" style="width: 150px; float: left;"
						name="Checkdata" class="easyui-datebox" id="CheckdataUpdate">
				</div>
			</div>
			<div class="editwindow"
				style="padding-left: 20px; margin-top: 10px; width: 300px; height: 30px">
				<div style="width: 80px; height: 30px; float: left;">
					<span style="float: right;">实住人数：</span>
				</div>
				<div style="width: width: 200px;">
					<select id="actualUpdate" class="easyui-combobox" name="actual"
						style="width: 150px; float: left;">
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
					<span style="float: right;">缺勤人数：</span>
				</div>
				<div style="width: width: 200px;">
					<select id="absenceUpdate" class="easyui-combobox" name="absence"
						style="width: 150px; float: left;">
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
					<span style="float: right;">查寝情况：</span>
				</div>
				<div style="width: width: 200px;">
					<select id="situationUpdate" class="easyui-combobox"
						name="situation" style="width: 150px; float: left;">
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
					<span style="float: right;">查寝备注：</span>
				</div>
				<div style="width: width: 200px;">
					<input type="text" id="noteUpdate" name="note"
						class="easyui-textbox"
						style="height: 80px; width: 150px; float: left;">
				</div>
			</div>



			<div class="editwindow" style="padding-left: 40px">
				<input type="button" value="修改" class="test"
					onclick="CheckDormotoryUpdate();"
					style="text-align: center; position: absolute; left: 40%; width: 100px; margin-top: 63px; height: 30px">
			</div>

		</form>


	</div>

</body>
<script type="text/javascript">
	//搜索
	function search() {
		var DormitoryNumberId = $('#Dormitorynumber').textbox('getValue');
		var CheckData = $('#Checkdata').textbox('getValue');

		var fields = $('#search_form').serializeArray();
		var data = {
			did : DormitoryNumberId,
			checkdata : CheckData
		};

		console.log(data)
		var params = $("#checkdormitoryList").datagrid('options').queryParams;
		$.each(fields, function(i, field) {
			params[field.name] = field.value;
		});

		$('#checkdormitoryList').datagrid('reload');
		$('#search_form').form('clear');

	}

	//打开页面时运行
	$(function() {
		init();
		DormitoryList()
	});
	function init() {
		//遍历所有查寝信息
		$('#checkdormitoryList')
				.datagrid(
						{
							url : 'CheckDormitory/listAll', //请求方法的地址
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
										title : '宿舍号 ',
										width : 20,
										align : 'center',
										formatter : function(value, row, index) {
											return row.dormitory.number;

										}

									},
									{
										field : 'checkdata',
										title : '查寝时间',
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
										field : 'absence',
										title : '缺勤人数',
										width : 20,
										align : 'center'
									},
									{
										field : 'situation',
										title : '宿舍状况',
										width : 20,
										align : 'center',
										formatter : function(value, row, index) {
											if (row.situation == 1) {
												return "正常";
											}

											if (row.situation == 2) {
												return "缺人";
											}
											if (row.situation == 3) {
												return "吵闹";
											}

										}
									},
									{
										field : 'note',
										title : '查寝备注',
										width : 80,
										align : 'center',
										formatter : function(value, row, index) {

											if (row.note == null
													| row.note == '') {
												return "无";
											} else {
												return row.note;
											}

										}
									},
									{
										field : 'opt',
										title : '操作',
										align : 'center',
										width : 40,
										formatter : function(value, row, index) {
											var str = '<a href="javascript:void(0);" onclick="checkdormitoryGetById('
													+ row.id + ');">修改记录</a>|';
											str += '<a href="javascript:void(0);" onclick="checkdormitorydelete('
													+ row.id + ');">删除记录</a>';
											return str;
										}

									} ] ],
						/* 	toolbar : [ {
								iconCls : 'icon-add',
								text : '添加查寝记录',
								handler : function() {
									checkdormitoryadd()
								}
							} ] */

						});
	}
	function DormitoryList() {
		//宿舍下拉框	
		$('#Dormitorynumber').combobox({
			url : 'Dormitory/listdormitorynumber',
			selectOnNavigation : false,
			textField : 'number',
			editable : false,
			valueField : 'id',//没有这个不能选择

		})

	}
	//点击添加查寝记录按钮
	function checkdormitoryadd() {
		//清空原有数据
		$('#CheckDormitoryAdd').form('clear');
		$('#add_checkdormitory').window('open');
		//添加查寝时的宿舍下拉框
		$('#DormitorynumberAdd').combobox({
			url : 'Dormitory/listdormitorynumber',
			selectOnNavigation : false,
			textField : 'number',
			editable : false,
			valueField : 'id',//没有这个不能选择

		})
	}
	//添加查寝记录
	function CheckDormotoryAdd() {
		if ($('#DormitorynumberAdd').textbox('getValue') == '请选择') {
			$.messager.alert('警告', '请选择宿舍号');
			return false;
		}
		if ($('#CheckdataAdd').textbox('getValue') == '') {
			$.messager.alert('警告', '查寝时间不能为空');
			return false;
		}
		if ($('#actualAdd').textbox('getValue') == '请选择') {
			$.messager.alert('警告', '实际人数不能为空');
			return false;
		}
		if ($('#absenceAdd').textbox('getValue') == '请选择') {
			$.messager.alert('警告', '缺勤人数不能为空');
			return false;
		}
		if ($('#situationadd').textbox('getValue') == '请选择') {
			$.messager.alert('警告', '查寝情况不能为空');
			return false;
		}
		var data = $("#CheckDormitoryAdd").serialize();
		console.log(data);

		$.ajax({
			url : "CheckDormitory/CheckDormitoryAdd", //请求的url地址
			async : true, //请求是否异步，默认为异步，这也是ajax重要特性
			data : data,
			type : "post", //请求方式
			success : function(data) {

				if (data == "error") {
					$.messager.alert('警告', '已有该宿记录，请重新添加');
					$('#Dormitory_Add').form('clear');
					return false;
				}
				$('#add_checkdormitory').window('close');
				$('#checkdormitoryList').datagrid('reload');
				$('#CheckDormitoryAdd').form('clear');
			}
		});

	}

	//点击删除查寝记录按钮
	function checkdormitorydelete(row) {

		if (window.confirm('该操作不可恢复，请慎重删除！')) {
			$.ajax({
				url : "CheckDormitory/CheckDormitoryDelete", //请求的url地址
				async : true, //请求是否异步，默认为异步，这也是ajax重要特性
				data : {
					id : row
				},
				type : "post", //请求方式
				success : function(data) {
					$('#checkdormitoryList').datagrid('reload');
				}
			});
			
		}
	}
	//修改记录前查询记录信息
	function checkdormitoryGetById(row) {
		$.ajax({
			type : "post",
			data : {
				id : row
			},
			url : "CheckDormitory/CheckDormitoryGetById",
			success : function(data) {
				$("#id").val(data.id);
				$("#did").val(data.dormitory.id);

				$("#CheckdataUpdate").textbox("setValue", data.checkdata);
				$("#actualUpdate").textbox("setValue", data.actual);
				$("#absenceUpdate").textbox("setValue", data.absence);
				$("#situationUpdate").textbox("setValue", data.situation);

				$("#number").val(data.dormitory.number);
				$("#noteUpdate").textbox("setValue", data.note);
			}

		})
		$('#update_checkdormitory').window('open');

	}

	//修改后保存
	function CheckDormotoryUpdate() {

		if ($('#CheckdataUpdate').textbox('getValue') == '') {
			$.messager.alert('警告', '查寝时间不能为空');
			return false;
		}
		if ($('#actualUpdate').textbox('getValue') == '请选择') {
			$.messager.alert('警告', '请选择实际人数');
			return false;
		}
		if ($('#absenceUpdate').textbox('getValue') == '请选择') {
			$.messager.alert('警告', '请选择缺勤人数');
			return false;
		}
		if ($('#situationUpdate').textbox('getValue') == '请选择') {
			$.messager.alert('警告', '请选择查寝情况');
			return false;
		}
		var data = $("#CheckDormitoryUpdate").serialize();
		console.log(data);

		$.ajax({
			url : "CheckDormitory/CheckDormitoryUpdate", //请求的url地址
			async : true, //请求是否异步，默认为异步，这也是ajax重要特性
			data : data,
			type : "post", //请求方式
			success : function(data) {

				if (data == "error") {
					$.messager.alert('警告', '该宿舍这天已有记录，请重新修改');
					return false;
				}
				$('#update_checkdormitory').window('close');
				$('#checkdormitoryList').datagrid('reload');
				$('#CheckDormitoryUpdate').form('clear');
			}
		});

	}
</script>

</html>