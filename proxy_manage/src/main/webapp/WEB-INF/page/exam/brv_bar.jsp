<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath + "/"%>"></base>
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
<title>分班</title>
</head>
<script type="text/javascript">
	$(function() {
		getClass();
		getMajor();
		getCourse();	
		getStudentName();
		testClass();
		init("course/getFilterCourse",{name:""},"exam/getCourseIdScore");
		$("#tt").hide();
	});
	var array = [], columns = [], arr2 = [], arr3 = [];
	var index1 = 0, hang = 0;
	var bb = false;
	var fen = 0;

	function init(url, data, dgurl) {
		$
				.ajax({
					url : url,
					data : data,
					success : function(data) {
						hang = data.length;
						arr2 = [];
						index1 = 0;
						array.push({
							field : 'index',
							title : '序号',
							width : '100px',
							align : 'center',
							hidden : true,
							formatter : function(value, row, index) {
								numbers = index + 1;
								return index + 1;
							}
						});
						array.push({
							field : 'id',
							title : 'id',
							align : 'center',
							hidden : true
						});
						array.push({
							field : 'fxk',
							checkbox : true
						});
						array.push({
							field : 'student',
							title : '学生姓名',
							width : '90px',
							align : 'center',
							formatter : function(value, row, index) {
								return row.student.name
							}
						});
						array.push({
							field : 'classroom',
							title : '班级',
							width : '100px',
							align : 'center',
							formatter : function(value, row, index) {
								return row.classroom.name
							}
						});
						array.push({
							field : 'examDate',
							title : '考试时间',
							width : '110px',
							align : 'center'
						});
						array.push({
							field : 'name',
							title : '考试名称',
							width : '100px',
							align : 'center'
						});
						for (var i = 0; i < data.length; i++) {
							arr2[i] = data[i];
							array
									.push({
										field : 'kemu',
										title : data[i].name,
										width : '100px',
										align : 'center',
										formatter : function(value, row, index) {
											if (index1 == hang)
												index1 = 0;
											if (row.scores[index1].score >= 0) {
												return row.scores[index1++].score
											} else if (row.scores[index1].score == -1) {
												index1++;
												return null
											} else {
												index1++;
												return '<div  style="width: 25px;height: 25px; background: #adb1b8; border-radius: 100%;position:relative;margin:0 auto">'
														+ '<div id=""  style="width: 12px;height: 5px;background: white;position: absolute;left: 7px;top: 10px;"></div></div>'
											}
										}
									});
						}
						columns.push(array);
						$('#dg').datagrid({
							url : dgurl,
							title : '查询结果',
							singleSelect : false,
							height : window.innerHeight - 160,
							fitColumns : true,
							nowrap : false,
							loadMsg : '正在加载信息...',
							pagination : true,
							pagePosition : 'bottom',
							pageNumber : 1,
							pageSize : 10,
							pageList : [ 10, 15, 30 ],
							queryParams : {},
							columns : columns,
						});
						array = [];
						columns = [];
					}
				})
	}

	function clean() {
		$('#majorname').combobox('setValue', '');
		$('#highScore').textbox('setValue', '');
		$('#classname').combobox('setValue', '');
		$('#course').combobox('setValue', '');
		$('#studentname').textbox('setValue', '');
		init("course/getFilterCourse", {
			name : ""
		}, "exam/getCourseIdScore");
	}
	function find() {
		var majorId = $('#majorname').combobox('getValue');
		var courseId = $('#course').combobox('getValue');
		var highScore = $('#highScore').textbox('getValue');
		var classId = $('#classname').combobox('getValue');
		var studentName = $('#studentname').textbox('getValue');

		var fields = $('#search_form').serializeArray();
		var params = $("#dg").datagrid('options').queryParams;
		$.each(fields, function(i, field) {
			params[field.name] = field.value;
		});
		$('#dg').datagrid('reload')

	}

	//专业
	function getMajor() {
		$('#majorname').combobox({
			url : 'major/getAll',
			valueField : 'id',
			selectOnNavigation : false,
			textField : 'name',
			editable : false,
			onChange : function(val) {
				getCourse();
			}
		});
	}
	//课程
	function getCourse() {
		var majorId = $('#majorname').combobox("getValue");
		$('#course').combobox({
			url : 'course/getMajorId?majorId=' + majorId,
			valueField : 'id',
			selectOnNavigation : false,
			textField : 'name',
			editable : false,
		});
	}

	//班级
	function getClass() {
		$('#classname').combobox(
				{
					url : 'classroom/getAll',
					valueField : 'id',
					selectOnNavigation : false,
					editable : false,
					textField : 'name',
					onSelect : function() {
						$('#studentname').textbox('textbox').attr('readonly',
								true);
					},
					onChange : function(newValue, oldValue) {
						if ($('#classname').combobox('getValue') == "") {
							$('#studentname').textbox('textbox').attr(
									'readonly', false);
						}
					}
				});
	}
	//模拟班级--没有结束时间的
	function testClass() {
		$('#testclass').combobox({
			url : 'classroom/getAllBe',
			valueField : 'id',
			selectOnNavigation : false,
			editable : false,
			textField : 'name',
		});
	}
	function getStudentName() {
		$('#studentname').textbox({
			onChange : function(newValue, oldValue) {
				$('#classname').combobox('disable'); //不可操作

				if ($('#studentname').textbox('getValue') == "") {
					$('#classname').combobox('enable'); //可操作
				}
			}
		})
	}
	//模拟分班
	function tsetbrvbar() {
		var testClassId = $('#testclass').combobox('getValue');
		var checkedItems = $('#dg').datagrid('getChecked');
		if (testClassId == null || testClassId == "") {
			alert("请选择班级！")
		} else if (checkedItems.length == 0) {
			alert("请选择要分班的学生！！")
		} else {
			var studentIds = [];
			$.each(checkedItems, function(index, item) {
				studentIds.push(item.student.id);
			});
			$.ajax({
				url : 'student/updateTestClassId',
				type : 'post',
				traditional : true,//传数组需要
				data : {
					"testClassId" : testClassId,
					'studentIds' : studentIds
				},
				success : function() {
					$("#dg").datagrid('reload');
					alert("模拟分班成功")
					$('#testclass').combobox('setValue', '');
				}
			});
		}

	}
	//查看分班情况
	function overbrvbar() {
		$
				.ajax({
					url : 'student/listTestClassroomId',
					type : 'post',
					async : false,
					success : function(data) {
						if (data == '') {
							alert("暂无分班情况！")
						} else {
							$(".cc").hide();
							$("#tt").show();
							for (var i = 0; i < data.length; i++) {
								$
										.ajax({
											url : 'classroom/getById',
											type : 'post',
											data : {
												id : data[i]
											},
											async : false,
											success : function(data1) {
												var dat = data[i];
												var an = "<div style='height: 40px;text-align: center;margin-top: 15px;'>"
														+ "<a class='easyui-linkbutton' onclick='affirm("
														+ dat
														+ ")' >确认分班</a>"
														+ "<a class='easyui-linkbutton' onclick='cleanclass("
														+ dat
														+ ")' style='margin-left:50px'> 清空班级</a></div>";
												var xs = an
														+ "<table  id='fb"+data[i]+"'></table> ";
												if ($("#tt").tabs('exists',
														data1.name)) {
												} else {
													$("#tt").tabs('add', {
														title : data1.name,
														content : xs,

													});
												}
												$('#fb' + data[i])
														.datagrid(
																{
																	height : window.innerHeight - 110,
																	url : "student/getByTestClassId?testClassId="
																			+ data[i],
																	fitColumns : true,
																	nowrap : false,
																	loadMsg : '正在加载信息...',
																	pagination : true,
																	singleSelect : false,
																	pagePosition : 'bottom',
																	pageSize : 10,
																	pageNumber : 1,
																	pageList : [
																			10,
																			15,
																			30 ],
																	queryParams : {},
																	columns : [ [
																			{
																				field : 'fxk',
																				checkbox : true
																			},
																			{
																				field : 'id',
																				title : 'id',
																				align : 'center',
																				hidden : true
																			},
																			{
																				field : 'name',
																				title : '学生姓名',
																				width : '100px',
																				align : 'center'
																			},
																			{
																				field : 'dd',
																				title : '操作',
																				width : '100px',
																				align : 'center',
																				formatter : function(
																						value,
																						row,
																						index) {
																					var str = '<a  href="javascript:void(0);" onclick="delet('
																							+ row.id
																							+ ','
																							+ index
																							+ ','
																							+ dat
																							+ ')">移除</a>';
																					return str
																				}
																			} ] ],

																});
											}
										});
							}
						}
					}
				});
	}
	//移除
	function delet(id, index, dat) {
		$.ajax({
			url : 'student/deleteTestClassId',
			type : 'post',
			data : {
				"id" : id
			},
			success : function() {
				alert("移除成功")
			}
		});
		$('#fb' + dat).datagrid('deleteRow', index);
	}
	//确认分班
	function affirm(dat) {
		//选中行的值
		var chec = $('#fb' + dat).datagrid('getChecked');
		if (chec.length == 0) {
			alert("请选择学生")
		} else {
			var studentIds = [];
			$.each(chec, function(index, item) {
				studentIds.push(item.id);
			});
			$.ajax({
				url : 'student/updateClassId',
				type : 'post',
				traditional : true,//传数组需要
				data : {
					"testClassId" : dat,
					'studentIds' : studentIds
				},
				success : function() {
					$('#fb' + dat).datagrid('reload');
					alert("分班成功")
				}
			});

		}
	}
	//清空班级
	function cleanclass(dat) {
		$.ajax({
			url : 'student/getByTestClassId',
			type : 'post',
			data : {
				"testClassId" : dat
			},
			success : function(data) {
				for (var i = 0; i < data.rows.length; i++) {
					$.ajax({
						url : 'student/deleteTestClassId',
						type : 'post',
						data : {
							"id" : data.rows[i].id
						},
					});
				}
				$('#fb' + dat).datagrid('reload');
				alert("已清空");
			}
		});
	}
	//关闭
	function clos() {
		$("#tt").hide();
		$(".cc").show();
		$('#dg').datagrid('reload');
	}
</script>
<body>
	
	
	<!-- 查询结果 -->
	<div class="cc" >
		<form id="search_form" action="" method="post">
			<fieldset class="form_fieldset">
				<label>专业:</label> <input class="easyui-textbox" name="majorId"id="majorname"  />
				<label>限制课程 :</label> <input id="course" class="easyui-textbox" type="text" name="courseId" />
				<label>分数大于 :</label> <input id="highScore" class="easyui-numberbox" type="text" name="highScore"  min=1; max=100/>
				<label>班级 :</label> <input id="classname" class="easyui-textbox"type="text" name="classId" />
				<label>学生姓名 :</label> <input	name="studentName" type="text" id="studentname"class="easyui-textbox">
				<a  class="easyui-linkbutton"  data-options="iconCls:'icon-search'" onclick="find()"  >查找</a>  
				<a  class="easyui-linkbutton"  data-options="iconCls:'icon-reload'" onclick="clean()" style="margin-left:30px"  > 刷新清空</a>
			
			</fieldset>
		</form>
	
		<div>
			<fieldset class="form_fieldset">
			<label>模拟班级 :</label> <input id="testclass" class="easyui-textbox"type="text" name="test_class" />
			<a class="easyui-linkbutton"  onclick="tsetbrvbar()"  style="margin-left:20px" >模拟分班</a>  
			<a class="easyui-linkbutton"  onclick="overbrvbar()"  style="margin-left:30px"  > 查看分班情况</a>
			</fieldset>
		</div>
		<table id="dg" ></table>
	</div>
	
	<!-- 分班 -->
	<div id="tt"  class="easyui-tabs" style="position: relative;">
		<img src="static/images/close.png"  onclick="clos()" style="position: absolute;z-index: 999;right:20px; top: 7px; cursor:pointer;"/>
	</div> 
	
	
	
</body>
</html>



