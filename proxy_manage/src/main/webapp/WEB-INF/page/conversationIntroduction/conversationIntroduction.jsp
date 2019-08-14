<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath + "/"%>"></base>
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
<title>Insert title here</title>
</head>
<style type="text/css">
input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none;
}

input[type="number"] {
	-moz-appearance: textfield;
}
</style>
<body>

	<fieldset class="form_fieldset">
		<div style="width: 750px; float: left;">
			<form id="search_form" action="" method="post">
				<label class="ui_font" style="margin-left: 10px">学生名称 :</label> <input
					class="easyui-textbox" name="name" id="name" /> <label
					class="ui_font">加入时间：</label> <input class="easyui-datebox"
					id="getbegindate" name="begindate" /> <label>-</label> <input
					id="getenddate" class="easyui-datebox" name="enddate" /> <input
					class="easyui-linkbutton" type="button" value="清空"
					onclick="cleans(); " style="width: 50px; margin: 0px 30px">
				<input type="button" class="easyui-linkbutton" style="width: 50px"
					value="查询 " onclick="search();">
			</form>
		</div>

		<div style="width: 280px; float: right;">
			<form enctype="multipart/form-data" id="batchUpload"
				action="conversationIntroduction/inExcel" method="post"
				class="form-horizontal" style="margin-left: 20px; float: left">
				<input class="easyui-filebox" name="file"
					style="height: 24px; width: 160px; float: left;"
					id="uploadEventFile"
					data-options="buttonText:'上传文件',prompt:'请选择Excel文件...'">
			</form>
		</div>
	</fieldset>

	<div id="w" class="easyui-window" title="谈话录入"
		data-options="iconCls:'icon-save'"
		style="width: 280px; height: 90px; padding: 10px; text-align: center"
		closed="true">


		<span id="show"></span>
	</div>

	<table id="listData"></table>
</body>
<script type="text/javascript">
	$(function() {
		init();
	});
	function init() {
		$('#listData')
				.datagrid(
						{
							url : 'conversationIntroduction/getByNameAndTime',//请求方法的地址
							title : '查询结果',
							height : window.innerHeight - 100,
							fitColumns : true, //列自适应 
							nowrap : false,//禁止文字自动换行
							pagination : true,
							pageSize : 20,
							pageNumber : 1,
							pageList : [ 20, 40, 60 ],
							singleSelect : true,
							loadMsg : '正在加载信息...',//当数据没有加载出来时显示的文字
							queryParams : {},//往后台传递参数，json格式 */
							columns : [ [
									{
										field : 'name',
										title : '学生姓名',
										width : 80,
										align : 'center'
									},
									{
										field : 'classroom',
										title : '学生班级',
										width : 80,
										align : 'center'
									},
									{
										field : 'converDate',
										title : '犯错时间',
										width : 80,
										align : 'center'
									},
									{
										field : 'pointTime',
										title : '时间段',
										width : 80,
										align : 'center'
									},
									{
										field : 'problemReason',
										title : '问题原因',
										width : 80,
										align : 'center'
									},
									{
										field : 'remarks',
										title : '备注',
										width : 80,
										align : 'center'
									},
									{
										field : 'opt',
										title : '操作',
										align : 'center',
										width : 80,
										formatter : function(value, row, index) {
											var str = "";
											str += '<a href="javascript:void(0);" onclick="open_ed('
													+ index + ')">编辑</a>';
											// 	                    str += '<a href="javascript:void(0);"<input type="button" onclick="/* deletes('+row.id+') */">|删除</a>';
											return str;
										}
									} ] ],
						});
	}

	function search() {
		var fields = $('#search_form').serializeArray();
		var params = $("#listData").datagrid('options').queryParams;
		$.each(fields, function(i, field) {
			params[field.name] = field.value;
		});
		$('#listData').datagrid('reload')
	}

	function cleans() {
		$('#getbegindate').textbox('setValue', '');
		$('#getenddate').textbox('setValue', '');
		$('#name').textbox('setValue', '');
		init();
	}
	
	  //点击上传按钮
    $('#uploadExcelFile').filebox({
        onChange : function (value) {
            var uploadExcelFile = $("#uploadExcelFile").filebox('getValue');
            if (uploadExcelFile == ''){
                alert("请选择Excel,在上传!!!");
            } else if (uploadExcelFile.lastIndexOf(".xls") < 0){//判断以.xls和.xlsx结尾的excel
                alert("只能上传Excel文件!!!");
            } else {
                var formData = new FormData($('form')[0]);
                ExcelAjaxRequest('conversationIntroduction/inExcel',formData);
            }
        }
    })


    //实际上传操作
    function ExcelAjaxRequest(url,data) {
        $.ajax({
            url : url,
            type : 'POST',
            data : data,
            cache : false,
            contentType : false,
            processData : false,
            success : function (result) {
                $("#show").html(result);
                $('#w').window('open');
            },
            error : function () {
                alert("Excel上传失败!!!")
            }
        })
    }
</script>
</html>