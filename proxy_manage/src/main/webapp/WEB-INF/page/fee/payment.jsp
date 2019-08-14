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
 button{
	/*background-color:  #00a1cf; Blue  */
    border: none;
    color: white;
    padding: 5px 21px;
    margin-left: 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    border-radius: 7px;
	float: right; 
	background-color: white;
    color: black;
    border: 1px solid #00a1cf; /* Blue */
    outline:none;
	}
	
button:HOVER {
	background-color:  #00a1cf; /*Blue  */
	color: white;
}


	a{text-decoration:none}
</style>

</head>
<body>
<!-- 搜索 -->
<div>
<fieldset style=" border: 1px solid #00a1cf; ">
	<form id="search_form">
		<div style="margin-left: 60px;margin-top: 20px" >
			支付方式:<input type="text" name="name" class="easyui-textbox">
		</div>
	</form>
	<button onclick="open_add()">添加</button>
	<button onclick="search()">搜索</button>
	<button onclick="clean()">清空</button>
</fieldset>
</div>
<!--主体  -->
<div class="jeiguo">
	<table id="payment"></table>
</div>  
<!-- 添加支付方式 -->
<div id="add_window" title="添加支付方式">
	<div align="center" style="margin-top: 30px">
		<form id="add_form">
			支付方式：<input id="name" name="name"  class="easyui-textbox"><br><br>
			<input type="button" value="保存" onclick="add()">
		</form>
	</div>	
</div>
<!-- 编辑详情 -->
<div id="edit_window" title="修改详情">
	<div align="center">
		<form id="edit_form">
			<input type="hidden" id="id" name="id"><br>
			支付方式：<input id="edit_name" class="easyui-textbox" name="name"><br><br>
			<input type="button" value="保存" onclick="update()">
		</form>
	</div>
</div>
</body>
<script type="text/javascript">
	$(function() {
		init();
		close_window();
	});

	function init() {
		$('#payment').datagrid(
			{
				url : 'payment/getByName',//请求方法的地址
				title : '查询结果',
				height : window.innerHeight - 130,
				fitColumns : true, //列自适应 
				nowrap : false,//禁止文字自动换行
				/* idField: 'id',//主键列的列明 */
				loadMsg : '正在加载信息...',//当数据没有加载出来时显示的文字
				pagination : true,//是否有分页/
				singleSelect : true,//是否单行选择
				pagePosition : 'bottom',//分页符在底部,可选参数为top，bottom，both
				pageSize : 10,//页大小，一页多少条数据
				pageNumber : 1,//默认当前的页码
				pageList : [ 10, 15, 30 ],//一页可显示数据的条目 
				queryParams : {},//往后台传递参数，json格式 */
				columns : [ [
		
						{
							field : 'id',
							title : 'id',
							width : 10,
							align : 'center',
							hidden : true, 
						
						},
						{
							field : 'name',
							title : '支付方式',
							width : 10,
							align : 'center',
						},
						{
							field : 'opt',
							title : '操作',
							width : 20,
							align : 'center',
							formatter : function(value, row, index) {
								var str = "";
								str += "<a href='javascript:void(0)' onclick='edit("+row.id+")'>修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;"
								str += "<a href='javascript:void(0)' onclick='del("+row.id+")'>删除</a>";
								return str;
							}
						} ] ]
			});
	}

//模糊查询
function search() {
	var fields = $('#search_form').serializeArray();
	var params = $("#payment").datagrid('options').queryParams;
	$.each(fields, function(i, field) {
		params[field.name] = field.value;
	});
	$('#payment').datagrid('reload')
}
//删除
function del(id){
	if(confirm("确认删除")){
		$.ajax({
			url:"payment/delete",
			data:{"id":id},
			type:"post",
			success:function(data){
				$("#payment").datagrid("reload");
			}
		})
	}	
}
//打开添加窗口
function open_add(){
	 $("#add_window").window("open");
}
//添加方法
function add(){
	var data = $("#add_form").serialize();
	if($("#name").textbox("getValue")==''){
		alert("支付方式不能为空");
	}else{
		$.ajax({
			url:"payment/add",
			data:data,
			type:"post",
			success:function(data){
				$("#add_form").form("clear");
				$("#add_window").window("close");
				$("#payment").datagrid("reload");
				}
		});
	}
}
//回显
function edit(id){
	$("#edit_window").window("open");
	$.ajax({
		url:"payment/findById",
		data:{"id":id},
		type:"post",
		success:function(data){
			$("#id").val(data.id);
			$("#edit_name").textbox("setValue",data.name);
		}
	})
}
//修改详情
function update(){
	var data = $("#edit_form").serialize();
	$.ajax({
		url:"payment/update",
		data:data,
		type:"get",
		success:function(data){
			$("#edit_window").window("close");
			$("#payment").datagrid("reload");
		}
	})
}
//关闭窗口
function close_window(){
	$("#add_window").window({
		width:600,
		height:500,
		closed:true,
		modal:true
	})
	$("#edit_window").window({
		width:600,
		height:500,
		closed:true,
		modal:true
	})
}
//清空
function clean(){
	$("#search_form").form("clear");
	init();
}
</script>
</html>
