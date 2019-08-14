<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
<style type="text/css">

/* 	清除input的type=number时留下的滚动 */
input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none !important;
}
</style>
<title>Insert title here</title>
</head>

<body>
	<fieldset class="form_fieldset">

		<div class="searchdiv" style="width: 430px; float: left">

			<label class="ui_font">日期 :</label> <input class="easyui-datebox"
				disabled="true" id="examTime" /> <label class="ui_font"
				style="margin-left: 40px">考试名称 :</label> <input
				class="easyui-textbox" id="examName" value="" required="required" />
		</div>

		<div>
			<div style="float: left">
				<label class="ui_font" style="float: left">考试班级：</label> <input
					id="className" class="easyui-textbox"
					style="width: 130px; float: left" type="text"> <label
					class="ui_font" style="margin-left: 40px">学生姓名 : </label> <input
					type="text" value="" style="color: red" class="easyui-textbox"
					id="findName"> <a href="script:void(0)"
					class="easyui-linkbutton" data-options="iconCls:'icon-save'"
					style="width: 15%" id="save">保存</a> <a href="javascript:void(0)"
					style="margin-left: 10px" onclick="clean()"
					class="easyui-linkbutton" data-options="iconCls:'icon-reload'">条件清空</a>
			</div>
			<div style="width: 280px; float: right;">
				<form enctype="multipart/form-data" id="batchUpload"
					action="score/inExcel" method="post" class="form-horizontal"
					style="margin-left: 20px; float: left">
					<input class="easyui-filebox" name="file"
						style="height: 24px; width: 160px; float: left;"
						id="uploadEventFile"
						data-options="buttonText:'上传文件',prompt:'请选择Excel文件...'">
				</form>
				<a href="static/xls/成绩表.xlsx" class="easyui-linkbutton"
					data-options="iconCls:'icon-redo'" style="margin-left: 10px">模板导出</a>
			</div>
		</div>
	</fieldset>


	<div class="cc">
		<table id="dg"></table>
	</div>

	<div id="w" class="easyui-window" title="信息录入"
		data-options="iconCls:'icon-save'"
		style="width: 280px; height: 90px; padding: 10px; text-align: center"
		closed="true">

		<span id="show"></span>
	</div>


</body>
<script type="text/javascript">
	var array = [], columns = [], arr2 = [], arr3 = [];
	var index1 = 0, hang = 0;
	var bb = false;
	//检测有多少条信息
	var numbers = 0;
	$(function() {
		findAll("course/getStudentCourses", null, 'student/getSomeStudents');
		findExamName();
		getClassroom();
	});

	function findAll(url, datas, datagridUrl) {
		var examName = $("#examName").textbox('getValue');
		$.ajax({url : url,
				data : datas,
				success : function(data) {
				hang = data.length;
				arr2 = [];
				index1 = 0;
				array.push({
					field : 'id',
					title : 'id',
					align : 'center',
					hidden : true,
					formatter : function(value, row, index) {
					return '<input    name="id" class="chuan'+index+'" value="'+row.id+'" />';
					}
					});
						array.push({
									field : 'classroom',
									title : '班级',
									align : 'center',
									hidden : true,
									formatter : function(value, row, index) {
										return '<input  style="border:none;text-align:center"   name="classroomId" class="chuan'+index+'" value="'+row.classroom.id+'" />';
									}
								});
						array.push({
									field : 'teacher',
									title : '老师',
									align : 'center',
									hidden : true,
									formatter : function(value, row, index) {
										return '<input  style="border:none;text-align:center" name="teacherId" class="chuan'+index+'"  value="'+row.id+'" />';
									}
								});
						array.push({
							field : 'index',
							title : '序号',
							width : '160px',
							align : 'center',
							formatter : function(value, row, index) {
								numbers = index + 1;
								return index + 1;
							}
						});
						array.push({
							field : 'name',
							title : '姓名',
							width : '160px',
							align : 'center'
						});
						for (var i = 0; i < data.length; i++) {
							arr2[i] = data[i];
							array
									.push({
										field : 'kemu',
										title : data[i].name,
										width : '160px',
										align : 'center',
										formatter : function(value, row, index) {
											if ($('#examName').textbox(
													'getValue') == '') {
												return null
											}
											if (index1 == hang)
												index1 = 0;
											if (row.scores[index1].score >= 0) {
												;
												return row.scores[index1++].score
											} else if (row.scores[index1].score == -1) {
												return '<input  name="'+arr2[index1++].inputName+'" oninput="if(value>100)value=100" style="border:none;text-align:center;width:99%;height:27px;background-color:transparent;"  type="number" class="input1 chuan'
														+ index + '" />'
											} else {
												index1++;
												return '<div  style="width: 25px;height: 25px; background: #adb1b8; border-radius: 100%;position:relative;margin:0 auto">'
														+ '<div id=""  style="width: 12px;height: 5px;background: white;position: absolute;left: 7px;top: 10px;"></div></div>'
											}
										}
									})
						}
						columns.push(array);
						$('#dg').datagrid({
							url : datagridUrl,
							title : '查询结果',
							height : window.innerHeight - 60,
							fitColumns : true,
							nowrap : false,
							loadMsg : '正在加载信息...',
							pagination : true,
							singleSelect : true,
							pagePosition : 'bottom',
							pageNumber : 1,
							pageSize : 10,
							pageList : [ 10, 30, 50 ],
							queryParams : {},
							columns : columns,
						});
						array = [];
						columns = [];
					}
				})

	}

	//获取所有考试名称
	function findExamName() {
		$('#examName').combobox(
				{
					url : 'exam/getAllExamName',
					valueField : 'name',
					selectOnNavigation : false,
					textField : 'name',
					editable : false,
					onChange : function(val) {
						$.ajax({
							url : "exam/getExamTime",
							data : {
								name : val
							},
							success : function(examTime) {
								$('#examTime').textbox('setValue', examTime);
							}
						})
						var examName = $("#examName").textbox('getValue');
						var name = $("#findName").textbox('getValue');
						var classId = $("#className").textbox('getValue');
						if (classId == '') {
							findAll("course/getStudentCourses", {
								name : name
							}, 'student/getSomeStudents?name=' + name
									+ "&examName=" + examName);
						} else {
							findAll("course/getStudentCourses", {
								name : name,
								'classroom.id' : classId
							}, 'student/getSomeStudents?name=' + name
									+ "&classroom.id=" + classId + "&examName="
									+ examName);
						}
					}
				});
	}

	//点击上传按钮  
	$('#uploadEventFile').filebox({

		onChange : function(value) {
			var uploadEventFile = $("#uploadEventFile").filebox('getValue');
			if (uploadEventFile == '') {
				alert("请选择excel,再上传");
			} else if (uploadEventFile.lastIndexOf(".xls") < 0) {//可判断以.xls和.xlsx结尾的excel  
				alert("只能上传Excel文件");
			} else {
				var formData = new FormData($('form')[0]);
				sendAjaxRequest('score/inExcel', formData);
			}
		}
	})

	//实际上传操作    
	function sendAjaxRequest(url, data) {
		$.ajax({
			url : url,
			type : 'POST',
			data : data,
			cache : false,
			contentType : false,
			processData : false,
			success : function(result) {
				$("#show").html(result);
				$('#w').window('open');
				$('#dg').datagrid('reload');
			},
			error : function() {
				alert("excel上传失败");
			},

		});
	};

	//给班级下拉框传值
	function getClassroom() {
		$('#className')
				.combobox(
						{
							url : 'classroom/getAllBe',
							/* valueField设置传过来对象的字段作为value值 */
							valueField : 'id',
							selectOnNavigation : false,
							/* textField设置传过来对象的字段作为文本 */
							textField : 'name',
							editable : false,
							onChange : function(val) {
								var examName = $("#examName").textbox(
										'getValue');
								if (val) {
									var name = $("#findName").textbox(
											'getValue');
									findAll("course/getStudentCourses", {
										'classroom.id' : val,
										name : name
									}, 'student/getSomeStudents?classroom.id='
											+ val + "&name=" + name
											+ "&examName=" + examName);
								}
							}
						});
	}
	//学生姓名模糊查询
	$('#findName').textbox(
			{
				onChange : function() {
					var name = $("#findName").textbox('getValue');
					var examName = $("#examName").textbox('getValue');
					var classId = $('#className').textbox('getValue')

					if (classId == '') {
						findAll("course/getStudentCourses", {
							name : name
						}, 'student/getSomeStudents?name=' + name
								+ "&examName=" + examName);
					} else {
						findAll("course/getStudentCourses", {
							name : name,
							'classroom.id' : classId
						}, 'student/getSomeStudents?name=' + name
								+ "&classroom.id=" + classId + "&examName="
								+ examName);
					}

				}
			});

	//保存前验证是否有信息漏入并提醒
	$("#save").click(
			function() {
				var day = $("#examTime").textbox('getValue');
				var examName = $("#examName").textbox('getValue')
				if (day == null | day == "") {
					alert("请先选择日期！")
				} else if (examName == null | examName == "") {
					alert("请输入考试名称")
				} else {
					var kong = 0;
					var index = 0;
					var cuohang = "";
					var suo = 0;

					for (var i = 0; i < numbers; i++) {
						var data = $('.chuan' + i).serializeArray();
						var ge = $('.chuan' + i + '.input1').length;
						for (var j = 0; j < ge; j++) {
							if (data[j + 3].value == ""
									| data[j + 3].value == null) {
								kong++;
								$('.chuan' + i + '.input1').eq(j).css("border",
										"red 1px solid")
							} else {
								$('.chuan' + i + '.input1').eq(j).css("border",
										"0")
							}
						}
						if (kong == ge) {
							if (ge != 0) {
								index++;
							}
							arr3[i] = i;
							suo++;
							if (index == 1 && ge != 0) {
								cuohang += (i + 1)
							} else if (ge != 0) {
								cuohang += "," + (i + 1)
							}
						}
						kong = 0;
					}
					if (suo == numbers) {
						alert("无新添加信息，不能保存");
						return false;
					}

					if (index > 0) {
						if (window.confirm("第" + cuohang + "行学生信息未录入，是否确定提交")) {
							add(day);
							arr3 = [];
						} else {
							arr3 = [];
						}
					} else {
						add(day);
						arr3 = [];
					}
				}
			})

	//进行保存操作
	function add(date) {
		var suc = 0;
		var name = "";
		for (var i = 0; i < numbers; i++) {
			if (arr3[i] != i) {
				var examName = $("#examName").textbox("getValue");
				var dd = $('.chuan' + i).serialize() + "&date=" + date
						+ "&examName=" + examName;
				$("#w").window("open");
				$.ajax({
					type : "post",
					async : false,
					url : "exam/addExam",
					data : dd,
					success : function() {
						suc++;
						$("#show").html(
								"成功上传" + suc + "条信息,总共" + numbers + "条信息.");
						$('#dg').datagrid('reload');
					}
				})
			}
		}
		arr3 = [];
	}
	function clean() {
		$('.form_fieldset').form('clear');
		findAll("course/getStudentCourses", null, 'student/getSomeStudents');
	}
</script>
</html>


